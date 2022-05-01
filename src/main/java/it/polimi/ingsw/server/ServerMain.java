package it.polimi.ingsw.server;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerMain {
    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 65535;

    public void main(String[] args) throws UnknownHostException {
        Scanner stdin = new Scanner(System.in);
        int serverPort;
        System.out.println("Welcome");
        System.out.println("Insert the port");
        serverPort = stdin.nextInt();
        while(serverPort<MIN_PORT || serverPort>MAX_PORT){
            System.out.println("Port Number is not valid, please insert a new one");
            serverPort=stdin.nextInt();
        };

        System.out.println("IP: " + Inet4Address.getLocalHost().getHostAddress());
        System.out.println("Server listening on port: " + serverPort);
        Server server = new Server(serverPort);
        server.start();
    }


}
