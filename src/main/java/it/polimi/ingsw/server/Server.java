package it.polimi.ingsw.server;


import it.polimi.ingsw.controller.GameHandler;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
   private int serverPort;
    private final ExecutorService executor;
    private boolean isActive;
    GameHandler gameHandler;
    public Server(int port) {
        serverPort=port;
        executor = Executors.newCachedThreadPool();
        GameHandler gameHandler = new GameHandler();
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server avviato");
            while(true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                executor.submit(clientHandler);
            }
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }


    /*
        Message mess = takemessage(...);
        mess.action(this);
     */

}
