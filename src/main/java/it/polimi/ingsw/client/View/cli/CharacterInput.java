package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.virtualView.IslandView;
import it.polimi.ingsw.messages.toServer.ChooseIslandEffectMessage;
import it.polimi.ingsw.messages.toServer.ChooseStudentsEffectMessage;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * CharacterInput class handles the input required in cli when a character is in action
 */
public class CharacterInput {

    private final InputParser inputParser = new InputParser();
    //effect2, effect6, effect8 have no input

    /**
     * Method islandInput handles the input when the character requires an island for his effect
     *
     * @param client the instance of client in turn
     * @param archipelago the archipelago of the game
     * @param motherNature the id of the island with mother nature in the game
     */
    public void islandInput(Client client, ArrayList<IslandView> archipelago, int motherNature){
        //effect3, effect5, effect1 part 2 input
        int islandID = inputParser.IslandParser(archipelago,motherNature);
        ChooseIslandEffectMessage message = new ChooseIslandEffectMessage(islandID);
        client.sendMessage(message);
    }

    /**
     * Method genericStudentInput handles the input when the character requires a student's color for his effect
     *
     * @param client the instance of client in turn
     */
    public void genericStudentInput(Client client){
        //effect9, effect12
        Student student = inputParser.studentParser();
        ArrayList<Student> listStudent = new ArrayList<>();
        listStudent.add(student);
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(listStudent);
        client.sendMessage(message);
    }

    /**
     * Method jesterInput handles the input when the character chosen is jester
     *
     * @param client the instance of client in turn
     * @param studentsCard the students on the jester's card
     * @param studentsEntrance the students in the entrance of the player in turn
     */
    public void jesterInput(Client client, ArrayList<Student> studentsCard, ArrayList<Student> studentsEntrance){
        //effect7
        ArrayList<Student> studentList = new ArrayList<>();
        System.out.println("How many students do you want to move? 1 | 2 | 3");
        int num = inputParser.intParser();
        while(num<1 || num>3){
            System.out.println("Number not valid. Please try again");
            num = inputParser.intParser();
        }
        System.out.println("Choose the students to move from card to the entrance");
        int i=0;
        while(i<num)
        {
            int k=i+1;
            System.out.println("Student number " + k + " :");
            Student student = inputParser.studentParser();
            if(studentsCard.contains(student)){
                i++;
                studentsCard.remove(student);
                studentList.add(student);
            }
            else{
                System.out.println("Student non available");
            }
        }

        System.out.println("Choose the students to move from the entrance to the card");
        int j=0;
        while(j<num)
        {
            int k=j+1;
            System.out.println("Student number " + k + " :");
            Student student = inputParser.studentParser();
            if(studentsEntrance.contains(student)){
                j++;
                studentsCard.remove(student);
                studentList.add(student);
            }
            else{
                System.out.println("Student non available");
            }
        }
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(studentList);
        client.sendMessage(message);
    }

    /**
     * Method minstrelInput handles the input when the character chosen is jester
     *
     * @param client the instance of client in turn
     * @param entrance the students in the entrance of the player in turn
     * @param hall the students in the hall of the player in turn
     */
    public void minstrelInput(Client client, ArrayList<Student> entrance, HashMap<Student,Integer> hall){
        //effect10
        System.out.println("How many students do you want to move? 1 | 2");
        ArrayList<Student> studentList = new ArrayList<>();
        int count=0;
        for (Student student: Student.values())
            count = count + hall.get(student);
        int num = inputParser.intParser();
        while(num<1 || num>2 || num>count){
            System.out.println("Number not valid or you don't have enough students in the hall. Please try again");
            num = inputParser.intParser();
        }
        System.out.println("Choose the students to move from entrance to the hall");
        int i=0;
        while(i<num)
        {
            int k = i+1;
            System.out.println("Student number " + k + " :");
            Student student = inputParser.studentParser();
            if(entrance.contains(student)){
                i++;
                entrance.remove(student);
                studentList.add(student);
            }
            else{
                System.out.println("Student non available");
            }
        }

        System.out.println("Choose the students to move from the hall to the entrance");
        int j=0;
        while(j<num)
        {
            int k=j+1;
            System.out.println("Student number " + k + " :");
            Student student = inputParser.studentParser();
            if(hall.get(student) >0){
                j++;
                hall.replace(student, hall.get(student) - 1);
                studentList.add(student);
            }
            else{
                System.out.println("Student non available");
            }
        }
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(studentList);
        client.sendMessage(message);
    }

    /**
     * Method studentFromCard is used when a player chose a student from the character's card
     *
     * @param client the instance of the client in turn
     * @param studentsCard the students on the character's card
     * @param hall the students in the hall of the player in turn
     */
    public void studentFromCard(Client client, ArrayList<Student> studentsCard, HashMap<Student,Integer> hall) {
        //effect 11, effect 1 part 1
        boolean check=true;
        ArrayList<Student> studentsList = new ArrayList<>();
        Student studentChosen = inputParser.studentParser();
        while (!studentsCard.contains(studentChosen) && check) {
            System.out.println("Student not available. Please try again");
            studentChosen = inputParser.studentParser();
            if (hall != null && hall.get(studentChosen) == 10)
                System.out.println("Max students. Please try again");
                check=false;
        }
        studentsList.add(studentChosen);
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(studentsList);
        client.sendMessage(message);
    }
}

