package it.polimi.ingsw.client.View.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.toServer.ChooseIslandEffectMessage;
import it.polimi.ingsw.messages.toServer.ChooseStudentsEffectMessage;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterInput {

    private final InputParser inputParser = new InputParser();
    //effect2, effect6, effect8 have no input

    public void monkInput(Client client, ArrayList<Student> students, int numOfIslands){
        //effect1 input
        Student studentChosen = inputParser.studentParser();
        while(!students.contains(studentChosen)){
            System.out.println("Student not available. Please try again");
            studentChosen = inputParser.studentParser();
        }

        ArrayList<Student> listStudent = new ArrayList<>();
        listStudent.add(studentChosen);
        ChooseStudentsEffectMessage messageStudent = new ChooseStudentsEffectMessage(listStudent);
        client.sendMessage(messageStudent);

        int islandID = inputParser.IslandParser(numOfIslands);
        ChooseIslandEffectMessage messageIsland = new ChooseIslandEffectMessage(islandID);
        client.sendMessage(messageIsland);
    }

    public void islandInput(Client client, int numOfIslands){
        //effect3, effect5 input
        int islandID = inputParser.IslandParser(numOfIslands);
        ChooseIslandEffectMessage message = new ChooseIslandEffectMessage(islandID);
        client.sendMessage(message);
    }

    public void intInput(Client client){
        //effect4 input
        System.out.println("How many additional movements do you want to do?");
        int num = inputParser.intParser();
        while(num>2){
            System.out.println("Number not valid. Please try again");
            num = inputParser.intParser();
        }
    }

    public void genericStudentInput(Client client){
        //effect9, effect12
        Student student = inputParser.studentParser();
        ArrayList<Student> listStudent = new ArrayList<>();
        listStudent.add(student);
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(listStudent);
        client.sendMessage(message);
    }

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
            Student student = inputParser.studentParser();
            if(studentsCard.contains(student)){
                i++;
                studentsCard.remove(student);
                studentList.add(student);
            }
        }

        System.out.println("Choose the students to move from the entrance to the card");
        int j=0;
        while(j<num)
        {
            Student student = inputParser.studentParser();
            if(studentsCard.contains(student)){
                j++;
                studentsCard.remove(student);
                studentList.add(student);
            }
        }
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(studentList);
        client.sendMessage(message);
    }

    public void minstrelInput(Client client, ArrayList<Student> entrance, HashMap<Student,Integer> hall){
        //effect10
        System.out.println("How many students do you want to move? 1 | 2");
        ArrayList<Student> studentList = new ArrayList<>();
        int num = inputParser.intParser();
        while(num<1 || num>2){
            System.out.println("Number not valid. Please try again");
            num = inputParser.intParser();
        }
        System.out.println("Choose the students to move from entrance to the hall");
        int i=0;
        while(i<num)
        {
            Student student = inputParser.studentParser();
            if(entrance.contains(student)){
                i++;
                entrance.remove(student);
                studentList.add(student);
            }
        }

        System.out.println("Choose the students to move from the hall to the entrance");
        int j=0;
        while(j<num)
        {
            Student student = inputParser.studentParser();
            if(hall.get(student) >0){
                j++;
                hall.replace(student, hall.get(student) - 1);
                studentList.add(student);
            }
        }
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(studentList);
        client.sendMessage(message);
    }


    public void spoiledprincess(Client client, ArrayList<Student> studentsCard) {
        ArrayList<Student> studentsList = new ArrayList<>();
        Student studentChosen = inputParser.studentParser();
        while (!studentsCard.contains(studentChosen)) {
            System.out.println("Student not available. Please try again");
            studentChosen = inputParser.studentParser();
        }
        studentsList.add(studentChosen);
        ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(studentsList);
    }
}

