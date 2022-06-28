package it.polimi.ingsw.server;

import it.polimi.ingsw.client.View.cli.InputParser;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * Class used to start the server on a given port
 */
public class ServerMain {
    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 49151;

    public static void main(String[] args) throws IOException {
        int serverPort;
        InputParser inputParser = new InputParser();
        System.out.println("Welcome");
        System.out.println("Insert the port");
        serverPort = inputParser.intParser();
        while(serverPort<MIN_PORT || serverPort>MAX_PORT){
            System.out.println("Port Number is not valid, please insert a new one");
            serverPort=inputParser.intParser();
        }
        int i=0;
    /*   try {
            System.out.println(System.getProperty("os.name"));
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface inet = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration address = inet.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) address.nextElement();
                    if (inetAddress.isSiteLocalAddress()) {
                        if (i == 0) {
                            System.out.println("LAN ip: " + inetAddress.getHostAddress());
                            i++;
                        }else System.out.println("Wi-Fi ip: "+inetAddress.getHostAddress());
                    }
                }
            }
        } catch (Exception ignored) {}

     */
        String[] array = new String[1];
        array[0]="/192";
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface net: Collections.list(nets)){
                try {
                    NetworkInterface nif = net;

                 Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
                 InetSocketAddress inetAddr = new InetSocketAddress(nifAddresses.nextElement(), 0);
                    DatagramSocket socket = new DatagramSocket(inetAddr);
                 //if(Arrays.stream(array).anyMatch(socket.getLocalAddress().toString()::contains)) {
                     //if(Arrays.stream(new String[]{"eth"}).anyMatch(nif.getName()::contains))
                        System.out.println("LAN: " + socket.getLocalAddress());
                     //if(Arrays.stream(new String[]{"wlan"}).anyMatch(nif.getName()::contains))
                        System.out.println("Wi-Fi: " + socket.getLocalAddress());
                 //}
              } catch(SocketException ex){
                    System.out.println(ex.toString());
              }
              catch(NoSuchElementException ex)
              {
                    //System.out.println(ex.toString());
              }
        }
        System.out.println("Server listening on port: " + serverPort);
        Server server = new Server(serverPort);
        server.start();
    }




}
