package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.model.game.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputParser {
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

    public int IslandParser(int numOfIslands){
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose one Island between this available by typing his number associated");
        String input=stdin.nextLine();
        int islandID=intParser();
        boolean check = false;
        while(!check){
            if(islandID >= 1 && islandID <= numOfIslands) {
                check=true;
            }
            else
                System.out.println("Selection not valid. Try again");
            islandID = Integer.parseInt(input);
        }
        return islandID;
    }
}
