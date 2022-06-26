package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.client.View.cli.Graphical.Graphic;
import it.polimi.ingsw.controller.virtualView.IslandView;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * InputParser class handles the input required in cli for the actions in the game
 */
public class InputParser {
    Graphic graphic = new Graphic();

    /**
     * Method intParser handles the input in cli when a number is required
     *
     * @return the number typed by the player
     */
    public int intParser() {
        Scanner stdin = new Scanner(System.in);
        boolean check = false;
        int a = 0;
        while (!check) {
            try {
                a = Integer.parseInt(stdin.nextLine());
                check = true;
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Number not valid. Please try again");
            }
        }
        return a;
    }

    /**
     * Method studentParser handles the input in cli when a student is required
     *
     * @return the student chosen by the player
     */
    public Student studentParser(){
        Scanner stdin = new Scanner(System.in);
        boolean check = false;
        Student studentChosen=null;
        System.out.println("Choose the student to move by typing his color ( g | r | y | p | b )");
        String input=stdin.nextLine().toLowerCase();

        while(!check){
            switch (input) {
                case ("g") -> {
                        studentChosen = Student.GREEN;
                        check = true;
                    }
                case ("r") -> {
                        studentChosen = Student.RED;
                        check = true;
                    }
                case ("y") -> {
                        studentChosen = Student.YELLOW;
                        check = true;
                    }
                case ("p") -> {
                        studentChosen = Student.PINK;
                        check = true;
                }
                case ("b") -> {
                        studentChosen = Student.BLUE;
                        check = true;
                }
                default -> {
                    System.out.println("Selection not valid. Try again");
                    input = stdin.nextLine().toLowerCase();
                }
            }

        }
        return studentChosen;
    }

    /**
     * Method IslandParser handles the input in cli when an island is required
     *
     * @return the island chosen by the player
     */
    public int IslandParser(ArrayList<IslandView> archipelago, int motherNature){
        System.out.println("Choose one Island between this available by typing his number associated");
        this.graphic.printArchipelago(archipelago,motherNature);
        int islandID=intParser();
        boolean check = false;
        while(!check){
            if(islandID >= 1 && islandID <= archipelago.size()) {
                check=true;
            }
            else {
                System.out.println("Selection not valid. Try again");
                islandID = intParser();
            }
        }
        return islandID;
    }
}
