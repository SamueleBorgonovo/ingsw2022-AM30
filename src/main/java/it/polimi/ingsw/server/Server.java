package it.polimi.ingsw.server;


import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.net.ServerSocket;

public class Server {
    private static int port;

    public static void main(String[] args) throws UnknownHostException {
        Server server = new Server();
        Scanner stdin = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Insert the port");
        port = stdin.nextInt();
        while(port<1024 || port>65000){
            System.out.println("Port Number is not valid, please insert a new one");
            port=stdin.nextInt();
        };

        System.out.println("IP: " + Inet4Address.getLocalHost().getHostAddress());
        System.out.println("Server listening on port: " + port);
        server.start();
    }

    public int getServerPort(int port){ return port;}


    private void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.accept();
            System.out.println("Server avviato");

        }catch (IOException e) {
            e.printStackTrace();
        } {

        }

    }

}
