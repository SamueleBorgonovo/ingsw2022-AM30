package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientMain;
import it.polimi.ingsw.server.ServerMain;
import java.util.Scanner;

/**
 * Class EriantysMain is the main start of the application
 */
public class EriantysMain {

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to Eriantys Game");
        Scanner scanner = new Scanner(System.in);
        boolean pass = false;
        char cho='a';
        do {
            System.out.println("Do you want to start a client or server? c | s");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("c")|| choice.equalsIgnoreCase("s")) {
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
