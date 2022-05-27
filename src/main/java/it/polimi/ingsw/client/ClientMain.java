package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.cli.CLI;
import it.polimi.ingsw.client.View.gui.GUI;
import javafx.application.Application;

import java.util.Scanner;

public class ClientMain {

  public static void main(String[] args) throws Exception {

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
          Application.launch(GUI.class, args);
      }
    }

}
