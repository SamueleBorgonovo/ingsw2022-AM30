package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.DisconnectMessage;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.messages.toClient.PingToClientMessage;
import it.polimi.ingsw.messages.toClient.TimeExpiredMessage;
import it.polimi.ingsw.messages.toServer.MessageToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientHandler implements Runnable, ClientHandlerInterface {
    private final int PING_PERIOD = 5000;
    private final int TIMEOUT_FOR_RESPONSE = 240000;
    private MessageHandler controller;
    private Socket clientSocket;
    private boolean active;
    private final Thread pinger;
    private Thread timer;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private String nickname;
    private GameHandler gameHandler;
    private boolean gameStarted;

    public ClientHandler(Socket clientSocket, MessageHandler messageHandler,GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.controller = messageHandler;
        this.clientSocket = clientSocket;
        this.pinger = new Thread(this::startPinger);
    }

    public void startPinger() {
        while(active){
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
                //handleSocketDisconnection(true);
            } catch (InterruptedException e){ }
        });
        timer.start();
    }


    public void stopTimer(){
        if (timer != null && timer.isAlive()){
            timer.interrupt();
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
            pinger.start();
            try {
                   while(active){
                       Object messageFromClient = is.readObject();
                       ((MessageToServer) messageFromClient).action(this);
                   }
           } catch (ClassNotFoundException ignored) {}

        } catch (IOException e) {
           handleSocketDisconnection(e instanceof SocketTimeoutException);

        }
    }

    public void sendMessageToClient(MessageToClient message){
        try {
            os.writeObject(message);
            os.flush();
            os.reset();
        }
        catch(IOException e) {
            handleSocketDisconnection(e instanceof SocketTimeoutException);
        }
    }

    //if timer expired timeout is true(also socketTimerExpire), else is false
    public void handleSocketDisconnection(boolean timeout){
        stopTimer();
        active=false;
        gameHandler.disconnectPlayer(this);
        try{
            if(timeout)
                os.writeObject(new TimeExpiredMessage());
            else
                os.writeObject(new DisconnectMessage());

            os.flush();
            os.reset();
        }catch (IOException e) { }
        try{
            is.close();
        } catch (IOException e) { }
        try{
            os.close();
        } catch (IOException e) { }
        try{
            clientSocket.close();
        } catch (IOException e) { }

    }


}