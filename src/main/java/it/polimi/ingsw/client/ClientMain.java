package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.cli.CLI;
import it.polimi.ingsw.client.View.GUI;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {

  public static void main(String[] args) throws IOException, ClassNotFoundException {

      Scanner stdin = new Scanner(System.in);
      System.out.println("Do you want use CLI or GUI? c | g");
      String ris = stdin.nextLine();

      boolean pass=false;
      char r='a';
      do {
          if (ris.equalsIgnoreCase("c") || ris.equalsIgnoreCase("g")) {
              pass = true;
              r = ris.toLowerCase().charAt(0);
          }
          else{
              System.out.println("Selection not valid. Please try again");
              ris= stdin.nextLine();
          }
      }while(!pass);

      if(r=='c') {
          CLI cli = new CLI();
          cli.init();
      }
      else{
          GUI gui = new GUI();
          gui.init();
      }
      //To see how to setup CLI and GUI
      /*
        Client client = new Client();
        boolean worked;
        do {
          worked = client.setupConnection();
          if (!worked) {
            System.out.println("Connection failed");
          }
        }while(!worked);
        /*



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
