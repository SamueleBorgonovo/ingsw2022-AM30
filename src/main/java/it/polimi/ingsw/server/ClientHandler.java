package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.messages.toServer.MessageToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable, ClientHandlerInterface {
    private final int PING_PERIOD = 5000;
    private MessageHandler controller;
    private Socket clientSocket;
    private boolean active;
    private final Thread pinger;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private String nickname;

    public ClientHandler(Socket clientSocket, MessageHandler messageHandler) {
        controller = messageHandler;
        this.clientSocket = clientSocket;
        this.pinger = new Thread(() -> {
            while (active) {
                try {
                    Thread.sleep(PING_PERIOD);   //To add ping methods
                    // sendMessageToClient(ConnectionMessage.PING);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

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
            //pinger.start();
            try {
                   while(true){
                       Object messageFromClient = is.readObject();
                       ((MessageToServer) messageFromClient).action(this);
                   }
           } catch (ClassNotFoundException ignored) {}

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void sendMessageToClient(MessageToClient message){
        try {
            os.writeObject(message);
            os.flush();
            os.reset();
        }
        catch(IOException e) {
            //Fa qualcosa
        }
    }


}