package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final int PING_PERIOD = 5000;
    private Server server;
    private Socket clientSocket;
    private boolean active;
    private final Thread pinger;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    GameHandler gameHandler;
    String nickname;

    public ClientHandler(Socket clientSocket, Server server) {
        this.server = server;
        gameHandler = server.getGameHandler();
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

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public void run(){
        try {
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());
            //pinger.start();
            try {
                while (active) {
                    Object messageFromClient = is.readObject();
                    //if(!(messageFromClient instanceof CreateGameMessage)

                    //messageHandler.gestiscimessagio(messageFromClient);
                    //Settare nickname
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

     /*
        Message mess = takemessage(...);
        mess.action(this);
     */
}