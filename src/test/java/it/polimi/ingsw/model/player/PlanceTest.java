package it.polimi.ingsw.model.player;

import it.polimi.ingsw.controller.virtualView.PlanceView;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlanceTest {

    Plance p = new Plance(Tower.WHITE,8);

    @Test
    void addStudentHall() {
        for(Student student : Student.values()){
            assertEquals(0,p.getNumberOfStudentHall(student));
            p.addStudentHall(student);
            assertEquals(1,p.getNumberOfStudentHall(student));
            p.addStudentHall(student);
            assertEquals(2,p.getNumberOfStudentHall(student));
            p.removeStudentFromHall(student);
            assertEquals(1,p.getNumberOfStudentHall(student));
            p.removeStudentFromHall(student);
        }
    }

    @Test
    void getNumberOfStudentHall() {
        for(Student student : Student.values()){
            assertEquals(0,p.getNumberOfStudentHall(student));
            p.addStudentHall(student);
            assertEquals(1,p.getNumberOfStudentHall(student));
            p.removeStudentFromHall(student);
            assertEquals(0,p.getNumberOfStudentHall(student));
        }
    }

    @Test
    void removeStudentFromHall() {
        for(Student student : Student.values()){
            assertEquals(0,p.getNumberOfStudentHall(student));
            p.addStudentHall(student);
            assertEquals(1,p.getNumberOfStudentHall(student));
            p.removeStudentFromHall(student);
            assertEquals(0,p.getNumberOfStudentHall(student));
        }
    }

    @Test
    void getNumOfTowers() {
        assertEquals(8,p.getNumOfTowers());
        p.removeTower();
        assertEquals(7,p.getNumOfTowers());
        p.addTower();
        assertEquals(8,p.getNumOfTowers());
    }

    @Test
    void addTower() {
        assertEquals(8,p.getNumOfTowers());
        p.removeTower();
        assertEquals(7,p.getNumOfTowers());
        p.addTower();
        assertEquals(8,p.getNumOfTowers());
    }

    @Test
    void removeTower() {
        assertEquals(8,p.getNumOfTowers());
        p.removeTower();
        assertEquals(7,p.getNumOfTowers());
        p.addTower();
        assertEquals(8,p.getNumOfTowers());
    }

    @Test
    void getTower() {
        for(Tower tower : Tower.values()){
            Plance p = new Plance(tower,8);
            assertEquals(tower,p.getTower());
        }
    }

    @Test
    void getEntrance() {
        ArrayList<Student> entrance = new ArrayList<>();

        for(Student student : Student.values()){
            p.addStudentEntrance(student);
            entrance.add(student);
            assertEquals(entrance,p.getEntrance());
            p.removeStudentEntrance(student);
            entrance.clear();
        }
    }

    @Test
    void addStudentEntrance() {
        ArrayList<Student> entrance = new ArrayList<>();

        for(Student student : Student.values()){
            p.addStudentEntrance(student);
            entrance.add(student);
            assertEquals(entrance,p.getEntrance());
            p.removeStudentEntrance(student);
            entrance.clear();
        }
    }

    @Test
    void removeStudentEntrance() {
        ArrayList<Student> entrance = new ArrayList<>();

        for(Student student : Student.values()){
            p.addStudentEntrance(student);
            entrance.add(student);
            assertEquals(entrance,p.getEntrance());
            p.removeStudentEntrance(student);
            entrance.clear();
        }
    }

    @Test
    void getProfessors() {
        ArrayList<Professor> professors = new ArrayList<>();

        for(Professor professor : Professor.values()){
            p.addProfessor(professor);
            professors.add(professor);
            assertEquals(professors,p.getProfessors());
            p.removeProfessor(professor);
            professors.clear();
        }
    }

    @Test
    void addProfessor() {
        ArrayList<Professor> professors = new ArrayList<>();

        for(Professor professor : Professor.values()){
            p.addProfessor(professor);
            professors.add(professor);
            assertEquals(professors,p.getProfessors());
            p.removeProfessor(professor);
            professors.clear();
        }
    }

    @Test
    void removeProfessor() {
        ArrayList<Professor> professors = new ArrayList<>();

        for(Professor professor : Professor.values()){
            p.addProfessor(professor);
            professors.add(professor);
            assertEquals(professors,p.getProfessors());
            p.removeProfessor(professor);
            professors.clear();
        }
    }

    @Test
    void getHall(){
        Game game = new Game(GameMode.EXPERTMODE,2);
        game.addPlayer("dan");
        game.addPlayer("tom");
        game.getPlayer(1).getPlance().addStudentHall(Student.RED);
        game.getPlayer(1).getPlance().addStudentHall(Student.YELLOW);
        HashMap<Student,Integer> hall=game.getPlayer(1).getPlance().getHall();

        assertEquals(1,hall.get(Student.RED));
        assertEquals(1,hall.get(Student.YELLOW));
        assertEquals(0,hall.get(Student.BLUE));
        assertEquals(0,hall.get(Student.GREEN));
        assertEquals(0,hall.get(Student.PINK));
    }

    @Test
    void getPlanceView(){
        Game game = new Game(GameMode.EXPERTMODE,2);
        game.addPlayer("dan");
        game.addPlayer("tom");
        ArrayList<Student> entrance= new ArrayList<>(game.getPlayer(1).getPlance().getEntrance());
        ArrayList<Professor> professors= new ArrayList<>(game.getPlayer(1).getPlance().getProfessors());
        HashMap<Student,Integer> hall= game.getPlayer(1).getPlance().getHall();
        int numoftowers=game.getPlayer(1).getPlance().getNumOfTowers();
        Tower tower=game.getPlayer(1).getPlance().getTower();

        PlanceView planceView=game.getPlayer(1).getPlance().getPlanceView();

        assertEquals(entrance.size(),planceView.getEntrance().size());
        assertTrue(planceView.getEntrance().containsAll(entrance));

        assertEquals(professors.size(),planceView.getProfessors().size());
        assertTrue(planceView.getProfessors().containsAll(professors));

        assertEquals(hall.size(),planceView.getHall().size());

        assertEquals(numoftowers,planceView.getNumoftowers());
        assertEquals(tower,planceView.getTower());
    }
}