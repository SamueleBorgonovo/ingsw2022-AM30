package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View.cli.Graphical.Graphic;
import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CLI extends View {

    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 65535;

    private Graphic graphic;


    public void init() throws IOException, ClassNotFoundException {
        boolean check;
        System.out.println("Welcome to Eriantys");
        Scanner stdin = new Scanner(System.in);
        String ip;
        do{
            System.out.println("Please insert the ip-addres");
            ip = stdin.nextLine();
            System.out.println("Please insert port");
            int port;
            port = stdin.nextInt();
            while (port < MIN_PORT || port > MAX_PORT) {
                System.out.println("Port Number is not valid, please insert a new one");
                port = stdin.nextInt();
            }
            Client client = new Client(ip, port, this);
            check = client.setupConnection();
            if(!check)
                System.out.println("Connection not valid. Please try again");
        }while(!check);
    }

    @Override
    public String chooseNickname(boolean validNickname){
        if(!validNickname)
            System.out.println("Invalid nickname. Please try again");
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose your nickname");
        return stdin.nextLine();
    }

    @Override
    public GameMode chooseGameMode(){
        Scanner stdin = new Scanner(System.in);
        System.out.println("Press s for SimpleMode or e for ExpertMode  s|e");
        String choice = stdin.nextLine();
        boolean pass = false;
        do {
            if (choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("e")) {
                pass = true;;
            }
            else
                choice=stdin.nextLine();
        } while (!pass);

        if(choice.equalsIgnoreCase("s"))
            return GameMode.SIMPLEMODE;
        else
            return GameMode.EXPERTMODE;
    }

    @Override
    public int chooseNumberOfPlayers(){
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose the number of the players ( 2 | 3 )");
        int choice = stdin.nextInt();
        boolean pass = false;
        do {
            if (choice==2 || choice==3)
                pass = true;
            else
                choice = stdin.nextInt();
        } while (!pass);

        return choice;
    }

    @Override
    public Wizard chooseWizard(ArrayList<Wizard> avaiableWizards){
        Wizard wizard = null;
        Scanner stdin = new Scanner(System.in);
        boolean checkwizard=false;
        System.out.println("Choose your wizard by typing his color (green, yellow, pink or blue");
        //this.graphic.draw(avaiableWizards);
        String choice = stdin.nextLine().toLowerCase();

        while (!checkwizard) {
            switch (choice) {
                case ("green") -> {
                    wizard = Wizard.WIZARDGREEN;
                    checkwizard = true;
                }
                case ("yellow") -> {
                    wizard = Wizard.WIZARDYELLOW;
                    checkwizard = true;
                }
                case ("pink") -> {
                    wizard = Wizard.WIZARDPINK;
                    checkwizard = true;
                }
                case ("blue") -> {
                    wizard = Wizard.WIZARDBLUE;
                    checkwizard = true;
                }
                default -> {
                    System.out.println("Typing error or invalid wizard: Try again");
                    choice = stdin.nextLine().toLowerCase();
                }
            }
            }
        return wizard;
        }


    @Override
    public Assistant chooseAssistant( ArrayList<Assistant> avaiableAssistant){
        Scanner stdin = new Scanner(System.in);
        Assistant assistant= null;
        System.out.println("Choose one assistant between this available by typing his number associated");
        //this.graphic.draw(avaiableAssistant);
        int assistantInt = stdin.nextInt();
        boolean check = false;
        while(!check) {
            for (Assistant assistantCheck : Assistant.values())
                if (assistant.ordinal() == assistantInt && avaiableAssistant.contains(assistant)) {
                    assistant=assistantCheck;
                    check=true;
                    break;
                }
            if(!check) {
                System.out.println("Selection not valid. Try again");
                assistantInt = stdin.nextInt();
            }
        }
        return assistant;
    }

    @Override
    public PlayerState chooseNextAction(ArrayList<PlayerState> possibleAction) {
        Scanner stdin = new Scanner(System.in);
        boolean check = false;
        PlayerState playerStatechoosen = null;
        System.out.println("Choose your next action by typing the action's number");
        for(PlayerState playerState : possibleAction)
            System.out.println(playerState + "num:" + possibleAction.indexOf(playerState)+1);
        int actionNum = stdin.nextInt();
        while(!check){
            if(actionNum >= 1 || actionNum <= possibleAction.size()+1) {
                playerStatechoosen = possibleAction.get(actionNum - 1);
                check = true;
            }
            else
                System.out.println("Number not valid. Please try again");
            actionNum = stdin.nextInt();
        }
        return playerStatechoosen;
    }

    @Override
    public Student chooseStudentToMove(HashMap<Student, Integer> hall) {
        Scanner stdin = new Scanner(System.in);
        boolean check = false;
        Student studentChosen=null;
        System.out.println("Choose the student to move by typing his color");
        //this.graphic.drawPlance;

        return studentChosen;
    }

}
