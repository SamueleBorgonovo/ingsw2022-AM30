package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlance {
        @Test
        public void testAddStudentToHall01()
        {
            ArrayList<ArrayList<Student>> hallWanted = new ArrayList<>();

            ArrayList<Student> entrance2 = new ArrayList<>();
            ArrayList<Professor> tableProfessor2 = new ArrayList<>();
            ArrayList<ArrayList<Student>> hall2 = new ArrayList<>();
            ArrayList<Tower> towers2 = new ArrayList<>();

            String nickname= "LUCA";
            int playerID=0;
            PlayerState playerState= PlayerState.PLAYINGYOURTURN;
            Plance plance= new Plance(entrance2, tableProfessor2,hall2,towers2);
            final Wizard wizard= Wizard.WIZARDBLUE;
            int coins=0;
            ArrayList<Assistant> assistantCards = new ArrayList<>();

            ArrayList<Student> entrance = new ArrayList<>();
            ArrayList<Professor> tableProfessor = new ArrayList<>();
            ArrayList<ArrayList<Student>> hall = new ArrayList<>();
            ArrayList<Tower> towers = new ArrayList<>();
            Player player= new Player(nickname,playerID,playerState,plance,wizard,coins,assistantCards);

            Plance p = new Plance(entrance, tableProfessor,hall,towers);
            hallWanted.get(0).add(Student.GREEN);
            hallWanted.get(0).add(Student.GREEN);
            hallWanted.get(1).add(Student.RED);
            hallWanted.get(3).add(Student.PINK);
            hallWanted.get(3).add(Student.PINK);
            hallWanted.get(3).add(Student.PINK);
            hallWanted.get(4).add(Student.BLUE);
            p.addStudentHall(Student.GREEN);

        }

    }
