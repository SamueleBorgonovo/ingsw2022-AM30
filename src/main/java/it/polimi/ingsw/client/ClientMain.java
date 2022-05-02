package it.polimi.ingsw.client;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {

  public void main(String[] args) throws IOException, ClassNotFoundException {

      Scanner stdin = new Scanner(System.in);
      System.out.println("Do you want use CLI or GUI? c | g");
      stdin.next();
      //To see how to setup CLI and GUI

        Client client = new Client();
        boolean worked;
        do {
          worked = client.setup();
          if (!worked) {
            System.out.println("Connessione non riuscita");
          }
        }while(worked);



       /* if(!setup()) {
            System.out.println("Connesione non riuscita");
        }

        try {
            //client.run();
        } catch (Exception exception) {
            System.out.println();
            System.out.println("|!| I wasn't able to connect to the specified ip-port address. Press ENTER to try again.");
            stdin.nextLine();

            for (int i = 0; i < 50; ++i) System.out.println();

            main(args);
        }

        */


    }


}
