package it.polimi.ingsw.server;


import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.controller.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
   private final int serverPort;
    private boolean isActive;
    MessageHandler messageHandler;
    GameHandler gameHandler;

    public Server(int port) {
        serverPort = port;
        gameHandler = new GameHandler();
        messageHandler = new MessageHandler();
        isActive = true;
    }

    public void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server started");
            while(isActive) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, messageHandler,gameHandler);
                new Thread(clientHandler).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
