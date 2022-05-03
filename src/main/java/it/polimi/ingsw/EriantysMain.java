package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientMain;
import it.polimi.ingsw.server.ServerMain;

import java.io.IOException;
import java.util.Scanner;

public class EriantysMain {

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        System.out.println("Welcome to Eriantys Game");
        Scanner scanner = new Scanner(System.in);
        boolean pass = false;
        char cho='a';
        do {
            System.out.println("Do you want to start a client or server? c | s");
            String choice = scanner.nextLine();
            if (choice.toLowerCase().startsWith("c") || choice.toLowerCase().startsWith("s")) {
                pass=true;
                cho = choice.toLowerCase().charAt(0);
            }

        }while (!pass);

        if (cho=='s') {
            ServerMain server = new ServerMain();
            server.main(args);
        }
        if (cho=='c') {
            ClientMain client = new ClientMain();
            client.main(args);
        }
    }
}
