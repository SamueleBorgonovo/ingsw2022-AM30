package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.messages.toClient.PingToClientMessage;
import it.polimi.ingsw.messages.toClient.TimeExpiredMessage;
import it.polimi.ingsw.messages.toServer.MessageToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ClientHandler implements Runnable, ClientHandlerInterface {
    private final int PING_PERIOD = 5000;
    private final int TIMEOUT_FOR_RESPONSE = 240000;
    private MessageHandler controller;
    private Socket clientSocket;
    private boolean active;
    private boolean pingactive;
    private final Thread pinger;
    private Thread timer;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private String nickname;
    private GameHandler gameHandler;
    private boolean gameStarted;
    private boolean disconnectionCalled=false;
    private ArrayList<Thread> timerThreads = new ArrayList<>();

    public ClientHandler(Socket clientSocket, MessageHandler messageHandler,GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.controller = messageHandler;
        this.clientSocket = clientSocket;
        this.pinger = new Thread(this::startPinger);
    }

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

    public void startTimer(){
        timer = new Thread(() -> {
            try{
                Thread.sleep(TIMEOUT_FOR_RESPONSE);
                System.out.println("Timer del pinger scattato "+getNickname());
                handleSocketDisconnection(true,false);
            } catch (InterruptedException e){ }
        });
        timerThreads.add(timer);
        timer.start();
    }


    public synchronized void stopTimer(){
        if (timer != null && timer.isAlive()){
            for(Thread time : timerThreads) {
                time.interrupt();
            }
            timerThreads.clear();
            //timer = null;
        }
    }



    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public MessageHandler getController() {
        return controller;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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
            System.out.println("IOException nel metodo che riceve i messaggi dal client");
           handleSocketDisconnection(e instanceof SocketTimeoutException,false);
        }
    }

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

    //if timer expired timeout is true(also socketTimerExpire), else is false
    public synchronized void handleSocketDisconnection(boolean timeout,boolean gameEnded) {
        if (!disconnectionCalled) {
            disconnectionCalled=true;
            stopTimer();
            active = false;
            pingactive=false;
            if(!gameEnded) {
                gameHandler.disconnectPlayer(getNickname());

                try {
                    if (timeout)
                        os.writeObject(new TimeExpiredMessage());
                    os.flush();
                    os.reset();
                } catch (IOException e) {
                }
            }
            try {
                is.close();
            } catch (IOException e) {
            }
            try {
                os.close();
            } catch (IOException e) {
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
            }

        }
    }

    public void closePinger(){
        stopTimer();
        pingactive=false;
    }



}