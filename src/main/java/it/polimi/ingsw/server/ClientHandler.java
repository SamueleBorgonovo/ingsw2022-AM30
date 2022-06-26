package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.DisconnectMessage;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.messages.toClient.PingToClientMessage;
import it.polimi.ingsw.messages.toServer.MessageToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * ClientHandler handles a connection between client and server, permitting sending and
 * receiving messages.
 */
public class ClientHandler implements Runnable, ClientHandlerInterface {
    private final int PING_PERIOD = 5000;
    private final int TIMEOUT_FOR_RESPONSE = 240000;
    private final MessageHandler messageHandler;
    private final Socket clientSocket;
    private boolean active;
    private boolean pingactive;
    private final Thread pinger;
    private Thread timer;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private String nickname;
    private final GameHandler gameHandler;
    private boolean disconnectionCalled=false;
    private final ArrayList<Thread> timerThreads = new ArrayList<>();

    /**
     * Constructor ClientHandler instantiates attributes of the server
     * @param clientSocket the socket that accepted che client connection
     * @param messageHandler the handler of all messages from client to server
     * @param gameHandler the gameHandler of the server
     */
    public ClientHandler(Socket clientSocket, MessageHandler messageHandler,GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.messageHandler = messageHandler;
        this.clientSocket = clientSocket;
        this.pinger = new Thread(this::startPinger);
    }

    /**
     * Method used to start the pinger to the client
     */
    public void startPinger() {
        while(pingactive){
            try {
                Thread.sleep(PING_PERIOD);
                PingToClientMessage message = new PingToClientMessage(true);
                sendMessageToClient(message);
                startTimer();
            }catch (InterruptedException e){
                break;
            }
        }
    }

    /**
     * Method used to start the timer of the ping. If the time is expired connection will close
     */
    public void startTimer(){
        timer = new Thread(() -> {
            try{
                Thread.sleep(TIMEOUT_FOR_RESPONSE);
                handleSocketDisconnection(true,false);
            } catch (InterruptedException ignored){}
        });
        timerThreads.add(timer);
        timer.start();
    }

    /**
     * Method used to stop the timer of the ping
     */
    public synchronized void stopTimer(){
        if (timer != null && timer.isAlive()){
            for(Thread time : timerThreads) {
                time.interrupt();
            }
            timerThreads.clear();
        }
    }

    /**
     * Method used to get the messageHandler
     * @return the messageHandler
     */
    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    /**
     * Method used to get the nickname of the client connected through the ClientHandler
     * @return the nickname of the client
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Method used to set the nickname of the client connected through the ClientHandler
     * @param nickname nickname chosen by the client
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Method run overrides from Runnable. It creates the input-output stream and activates the read from the client
     */
    @Override
    public void run(){
        try {
            is = new ObjectInputStream(clientSocket.getInputStream());
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            active=true;
            pingactive=true;
            pinger.start();
            try {
                   while(active){
                       Object messageFromClient = is.readObject();
                       ((MessageToServer) messageFromClient).action(this);
                   }
           } catch (ClassNotFoundException ignored) {}

        } catch (IOException e) {
           handleSocketDisconnection(e instanceof SocketTimeoutException,false);
        }
    }

    /**
     * Method used to send a message to the client
     * @param message message to send
     */
    public synchronized void sendMessageToClient(MessageToClient message){
        try {
            os.writeObject(message);
            os.flush();
            os.reset();
        }
        catch(IOException e) {
            System.out.println("Exception di sendMessage del clientHandler");
            handleSocketDisconnection(e instanceof SocketTimeoutException,false);
        }
    }

    /**
     * Method used to disconnect the server in this socket
     * @param timeout if is true, disconnection caused by a timeout expired
     * @param gameEnded if is true, disconnection caused by the end of the game
     */
    public synchronized void handleSocketDisconnection(boolean timeout,boolean gameEnded) {
        if (!disconnectionCalled) {
            disconnectionCalled=true;
            stopTimer();
            active = false;
            pingactive=false;
            if(!gameEnded) {
                gameHandler.disconnectPlayer(getNickname());

                try {
                    if(timeout) sendMessageToClient(new DisconnectMessage(nickname,false,false));
                    os.flush();
                    os.reset();
                } catch (IOException ignored) {}
            }
            try {
                is.close();
            } catch (IOException ignored) {}
            try {
                os.close();
            } catch (IOException ignored) {}
            try {
                clientSocket.close();
            } catch (IOException ignored) {}

        }
    }

    /**
     * Method used to end the ping system
     */
    public void closePinger(){
        stopTimer();
        pingactive=false;
    }
}