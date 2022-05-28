package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.messages.toServer.ChooseAssistantMessage;
import it.polimi.ingsw.messages.toServer.ChooseCloudMessage;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardController {
    private GUI gui;
    private ImageView[][] hallView = new ImageView[5][10];
    private ArrayList<ImageView> entranceView = new ArrayList<>();
    private ImageView[][] matImageView = new ImageView[3][4];
    private Student studentToMove;


    public void setup() {
        entranceView.add(entranceStudent1);
        entranceView.add(entranceStudent2);
        entranceView.add(entranceStudent3);
        entranceView.add(entranceStudent4);
        entranceView.add(entranceStudent5);
        entranceView.add(entranceStudent6);
        entranceView.add(entranceStudent7);
        entranceView.add(entranceStudent8);
        entranceView.add(entranceStudent9);

        hallView[0][0] = hall1Student1;
        hallView[0][1] = hall1Student2;
        hallView[0][2] = hall1Student3;
        hallView[0][3] = hall1Student4;
        hallView[0][4] = hall1Student5;
        hallView[0][5] = hall1Student6;
        hallView[0][6] = hall1Student7;
        hallView[0][7] = hall1Student8;
        hallView[0][8] = hall1Student9;
        hallView[0][9] = hall1Student10;

        hallView[1][0] = hall2Student1;
        hallView[1][1] = hall2Student2;
        hallView[1][2] = hall2Student3;
        hallView[1][3] = hall2Student4;
        hallView[1][4] = hall2Student5;
        hallView[1][5] = hall2Student6;
        hallView[1][6] = hall2Student7;
        hallView[1][7] = hall2Student8;
        hallView[1][8] = hall2Student9;
        hallView[1][9] = hall2Student10;

        hallView[2][0] = hall3Student1;
        hallView[2][1] = hall3Student2;
        hallView[2][2] = hall3Student3;
        hallView[2][3] = hall3Student4;
        hallView[2][4] = hall3Student5;
        hallView[2][5] = hall3Student6;
        hallView[2][6] = hall3Student7;
        hallView[2][7] = hall3Student8;
        hallView[2][8] = hall3Student9;
        hallView[2][9] = hall3Student10;

        hallView[3][0] = hall4Student1;
        hallView[3][1] = hall4Student2;
        hallView[3][2] = hall4Student3;
        hallView[3][3] = hall4Student4;
        hallView[3][4] = hall4Student5;
        hallView[3][5] = hall4Student6;
        hallView[3][6] = hall4Student7;
        hallView[3][7] = hall4Student8;
        hallView[3][8] = hall4Student9;
        hallView[3][9] = hall4Student10;

        hallView[4][0] = hall5Student1;
        hallView[4][1] = hall5Student2;
        hallView[4][2] = hall5Student3;
        hallView[4][3] = hall5Student4;
        hallView[4][4] = hall5Student5;
        hallView[4][5] = hall5Student6;
        hallView[4][6] = hall5Student7;
        hallView[4][7] = hall5Student8;
        hallView[4][8] = hall5Student9;
        hallView[4][9] = hall5Student10;

        matImageView[0][0] = cloud1Student1;
        matImageView[0][1] = cloud1Student2;
        matImageView[0][2] = cloud1Student3;
        matImageView[1][0] = cloud2Student1;
        matImageView[1][1] = cloud2Student2;
        matImageView[1][2] = cloud2Student3;

        if (gui.getNumOfPlayers() == 3) {
            matImageView[0][3] = cloud1Student4;
            matImageView[1][3] = cloud2Student4;
            matImageView[2][0] = cloud3Student1;
            matImageView[2][1] = cloud3Student2;
            matImageView[2][2] = cloud3Student3;
            matImageView[2][3] = cloud3Student4;
        }
    }

    public void setGui(GUI gui){
        this.gui=gui;
    }
    @FXML
    public Pane cloud1Pane;

    @FXML
    public ImageView cloud1Student2;

    @FXML
    public ImageView cloud1Student4;

    @FXML
    public ImageView cloud1Student3;

    @FXML
    public ImageView cloud1Student1;

    @FXML
    public Pane cloud3Pane;

    @FXML
    public ImageView cloud3Student2;

    @FXML
    public ImageView cloud3Student4;

    @FXML
    public ImageView cloud3Student3;

    @FXML
    public ImageView cloud3Student1;

    @FXML
    public Pane cloud2Pane;

    @FXML
    public ImageView cloud2Student2;

    @FXML
    public ImageView cloud2Student4;

    @FXML
    public ImageView cloud2Student3;

    @FXML
    public ImageView cloud2Student1;

    @FXML
    public Pane assistantsPane;

    @FXML
    public ImageView lion;

    @FXML
    public ImageView ostrich;

    @FXML
    public ImageView cat;

    @FXML
    public ImageView eagle;

    @FXML
    public ImageView fox;

    @FXML
    public ImageView snake;

    @FXML
    public ImageView octopus;

    @FXML
    public ImageView dog;

    @FXML
    public ImageView elephants;

    @FXML
    public ImageView turtle;

    @FXML
    public Pane plancePane;

    @FXML
    public ImageView entranceStudent9;

    @FXML
    public ImageView entranceStudent6;

    @FXML
    public ImageView entranceStudent4;

    @FXML
    public ImageView entranceStudent2;

    @FXML
    public ImageView entranceStudent1;

    @FXML
    public ImageView entranceStudent5;

    @FXML
    public ImageView entranceStudent3;

    @FXML
    public ImageView entranceStudent7;

    @FXML
    public ImageView entranceStudent8;

    @FXML
    public HBox hallGreen;

    @FXML
    public ImageView hall1Student1;

    @FXML
    public ImageView hall1Student2;

    @FXML
    public ImageView hall1Student3;

    @FXML
    public ImageView hall1Student4;

    @FXML
    public ImageView hall1Student5;

    @FXML
    public ImageView hall1Student6;

    @FXML
    public ImageView hall1Student7;

    @FXML
    public ImageView hall1Student8;

    @FXML
    public ImageView hall1Student9;

    @FXML
    public ImageView hall1Student10;

    @FXML
    public HBox hallRed;

    @FXML
    public ImageView hall2Student1;

    @FXML
    public ImageView hall2Student2;

    @FXML
    public ImageView hall2Student3;

    @FXML
    public ImageView hall2Student4;

    @FXML
    public ImageView hall2Student5;

    @FXML
    public ImageView hall2Student6;

    @FXML
    public ImageView hall2Student7;

    @FXML
    public ImageView hall2Student8;

    @FXML
    public ImageView hall2Student9;

    @FXML
    public ImageView hall2Student10;

    @FXML
    public HBox hallYellow;

    @FXML
    public ImageView hall3Student1;

    @FXML
    public ImageView hall3Student2;

    @FXML
    public ImageView hall3Student3;

    @FXML
    public ImageView hall3Student4;

    @FXML
    public ImageView hall3Student5;

    @FXML
    public ImageView hall3Student6;

    @FXML
    public ImageView hall3Student7;

    @FXML
    public ImageView hall3Student8;

    @FXML
    public ImageView hall3Student9;

    @FXML
    public ImageView hall3Student10;

    @FXML
    public HBox hallPink;

    @FXML
    public ImageView hall4Student1;

    @FXML
    public ImageView hall4Student2;

    @FXML
    public ImageView hall4Student3;

    @FXML
    public ImageView hall4Student4;

    @FXML
    public ImageView hall4Student5;

    @FXML
    public ImageView hall4Student6;

    @FXML
    public ImageView hall4Student7;

    @FXML
    public ImageView hall4Student8;

    @FXML
    public ImageView hall4Student9;

    @FXML
    public ImageView hall4Student10;

    @FXML
    public HBox hallBlue;

    @FXML
    public ImageView hall5Student1;

    @FXML
    public ImageView hall5Student2;

    @FXML
    public ImageView hall5Student3;

    @FXML
    public ImageView hall5Student4;

    @FXML
    public ImageView hall5Student5;

    @FXML
    public ImageView hall5Student6;

    @FXML
    public ImageView hall5Student7;

    @FXML
    public ImageView hall5Student8;

    @FXML
    public ImageView hall5Student9;

    @FXML
    public ImageView hall5Student10;

    @FXML
    public ImageView professorBlue;

    @FXML
    public ImageView professorGreen;

    @FXML
    public ImageView professorRed;

    @FXML
    public ImageView professorYellow;

    @FXML
    public ImageView professorPink;

    @FXML
    public Label nicknameLabel;

    @FXML
    public Label playerStateLabel;

    @FXML
    public Label gameUpdateLabel;

    @FXML
    public Pane characterButtor;

    @FXML
    public Label characterButtonLabel;

    @FXML
    public HBox coinPane;

    @FXML
    public Label coinLabel;

    @FXML
    public Pane charactersPane;

    @FXML
    public Pane hallPane;

    public void chosenCloud1(MouseEvent mouseEvent) {
        ChooseCloudMessage message = new ChooseCloudMessage(1);
        gui.getClient().sendMessage(message);
    }

    public void chosenCloud2(MouseEvent mouseEvent) {
        ChooseCloudMessage message = new ChooseCloudMessage(2);
        gui.getClient().sendMessage(message);
    }

    public void chosenCloud3(MouseEvent mouseEvent) {
        ChooseCloudMessage message = new ChooseCloudMessage(3);
        gui.getClient().sendMessage(message);
    }

    public void chosenAssistantLion(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.LION));
    }

    public void chosenAssistantOstrich(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.OSTRICH));
    }

    public void chosenAssistantCat(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.CAT));
    }

    public void chosenAssistantEagle(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.EAGLE));
    }

    public void chosenAssistantFox(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.FOX));
    }

    public void chosenAssistantSnake(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.SNAKE));
    }

    public void chosenAssistantOctopus(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.OCTOPUS));
    }

    public void chosenAssistantDog(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.DOG));
    }

    public void chosenAssistantElephants(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.ELEPHANTS));
    }

    public void chosenAssistantTurtle(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.TURTLE));
    }

    public void chosenEntranceStudent9(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(8);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent6(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(5);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent4(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(3);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent2(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(1);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent1(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(0);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent5(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(4);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent3(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(2);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent7(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(6);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenEntranceStudent8(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(7);
        setEntranceStudentNotClickable();
        hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        //settare le isole cliccabili
    }

    public void chosenHall(MouseEvent mouseEvent) {
        int numInPlanceColor = gui.getPlayer().getPlance().getHall().get(studentToMove).intValue();
        hallView[studentToMove.ordinal()][numInPlanceColor].setVisible(true);
    }

    public void chosenHallGreen(MouseEvent mouseEvent) {
    }

    public void chosenHallRed(MouseEvent mouseEvent) {
    }

    public void chosenHallYellow(MouseEvent mouseEvent) {
    }

    public void chosenHallPink(MouseEvent mouseEvent) {
    }

    public void chosenHallBlue(MouseEvent mouseEvent) {
    }

    public void clickCharacetrButton(MouseEvent mouseEvent) {
    }

    public void clickAssistabtButton(MouseEvent mouseEvent) {
    }

    //////////////////////////////////////////////////////////////


    //It changes only plance
    public void setupPlayerView(PlayerView player){

        nicknameLabel.setText(player.getNickname());
        plancePane.setVisible(true);
        plancePane.setDisable(true);
        assistantsPane.setVisible(false);
        assistantsPane.setDisable(true);
        for(int count=0;count<player.getPlance().getEntrance().size();count++){
            entranceView.get(count).setImage(new Image(getImageFromStudent(gui.getPlayer().getPlance().getEntrance().get(count))));
            entranceView.get(count).setVisible(true);
            entranceView.get(count).setDisable(false);
        }

        for(int count=0;count<player.getPlance().getNumberOfStudentHall(Student.GREEN);count++){
            hallView[0][count].setVisible(true);
            hallView[0][count].setDisable(false);
        }
        for(int count=player.getPlance().getNumberOfStudentHall(Student.GREEN);count<10;count++){
            hallView[0][count].setVisible(false);
            hallView[0][count].setDisable(true);
        }

        for(int count=0;count<player.getPlance().getNumberOfStudentHall(Student.RED);count++){
            hallView[1][count].setVisible(true);
            hallView[1][count].setDisable(false);
        }
        for(int count=player.getPlance().getNumberOfStudentHall(Student.RED);count<10;count++){
            hallView[1][count].setVisible(false);
            hallView[1][count].setDisable(true);
        }

        for(int count=0;count<player.getPlance().getNumberOfStudentHall(Student.YELLOW);count++){
            hallView[2][count].setVisible(true);
            hallView[2][count].setDisable(false);
        }
        for(int count=player.getPlance().getNumberOfStudentHall(Student.YELLOW);count<10;count++){
            hallView[2][count].setVisible(false);
            hallView[2][count].setDisable(true);
        }

        for(int count=0;count<player.getPlance().getNumberOfStudentHall(Student.PINK);count++){
            hallView[3][count].setVisible(true);
            hallView[3][count].setDisable(false);
        }
        for(int count=player.getPlance().getNumberOfStudentHall(Student.PINK);count<10;count++){
            hallView[3][count].setVisible(false);
            hallView[3][count].setDisable(true);
        }

        for(int count=0;count<player.getPlance().getNumberOfStudentHall(Student.BLUE);count++){
            hallView[4][count].setVisible(true);
            hallView[4][count].setDisable(false);
        }
        for(int count=player.getPlance().getNumberOfStudentHall(Student.BLUE);count<10;count++){
            hallView[4][count].setVisible(false);
            hallView[4][count].setDisable(true);
        }

        professorBlue.setDisable(true);
        professorBlue.setVisible(false);
        professorGreen.setDisable(true);
        professorGreen.setVisible(false);
        professorPink.setDisable(true);
        professorPink.setVisible(false);
        professorRed.setDisable(true);
        professorRed.setVisible(false);
        professorYellow.setDisable(true);
        professorYellow.setVisible(false);

        for(Professor prof : player.getPlance().getProfessors()){
            switch (prof){
                case RED_DRAGON -> {
                    professorRed.setDisable(false);
                    professorRed.setVisible(true);
                }
                case BLUE_UNICORN -> {
                    professorBlue.setDisable(false);
                    professorBlue.setVisible(true);
                }
                case GREEN_FROG -> {
                    professorGreen.setDisable(false);
                    professorGreen.setVisible(true);
                }
                case PINK_FAIRY -> {
                    professorPink.setDisable(false);
                    professorPink.setVisible(true);
                }
                case YELLOW_ELF -> {
                    professorYellow.setDisable(false);
                    professorYellow.setVisible(true);
                }
            }
        }

        //MANCANO LE TOWER NELLA PLANCIA ED è FINITO
    }

    public void setupArchipelago(){

        //MANCA LA PARTE DELLE ISOLE
        showClouds();
    }


    public void setAssistantView(){
        plancePane.setVisible(false);
        assistantsPane.setVisible(true);

        lion.setDisable(true);
        lion.setOpacity(0.3);
        ostrich.setDisable(true);
        ostrich.setOpacity(0.3);
        cat.setDisable(true);
        cat.setOpacity(0.3);
        eagle.setDisable(true);
        eagle.setOpacity(0.3);
        fox.setDisable(true);
        fox.setOpacity(0.3);
        snake.setDisable(true);
        snake.setOpacity(0.3);
        octopus.setDisable(true);
        octopus.setOpacity(0.3);
        dog.setDisable(true);
        dog.setOpacity(0.3);
        elephants.setDisable(true);
        elephants.setOpacity(0.3);
        turtle.setDisable(true);
        turtle.setOpacity(0.3);

        for(Assistant assistant : gui.getPlayer().getAssistantCards()){
            switch (assistant){
                case LION -> {
                    lion.setVisible(true);
                    lion.setDisable(false);
                    lion.setOpacity(1);
                }
                case OSTRICH -> {
                    ostrich.setVisible(true);
                    ostrich.setDisable(false);
                    ostrich.setOpacity(1);
                }
                case CAT -> {
                    cat.setVisible(true);
                    cat.setDisable(false);
                    cat.setOpacity(1);
                }
                case EAGLE -> {
                    eagle.setVisible(true);
                    eagle.setDisable(false);
                    eagle.setOpacity(1);
                }
                case FOX -> {
                    fox.setVisible(true);
                    fox.setDisable(false);
                    fox.setOpacity(1);
                }
                case SNAKE -> {
                    snake.setVisible(true);
                    snake.setDisable(false);
                    snake.setOpacity(1);
                }
                case OCTOPUS -> {
                    octopus.setVisible(true);
                    octopus.setDisable(false);
                    octopus.setOpacity(1);
                }
                case DOG -> {
                    dog.setVisible(true);
                    dog.setDisable(false);
                    dog.setOpacity(1);
                }
                case ELEPHANTS -> {
                    elephants.setVisible(true);
                    elephants.setDisable(false);
                    elephants.setOpacity(1);
                }
                case TURTLE -> {
                    turtle.setVisible(true);
                    turtle.setDisable(false);
                    turtle.setOpacity(1);
                }
            }
        }
    }

    public void showClouds() {

        int i = 0;
        for (CloudView cloud : gui.getBoard().getClouds()) {
            int j = 0;
            for (Student student : cloud.getStudents()) {
                matImageView[i][j].setImage(new Image(getImageFromStudent(student)));
                j++;
            }
            i++;
        }

        for (CloudView cloud : gui.getBoard().getClouds()) {
            if (cloud.isChoosen()) {
                switch (cloud.getCloudID()) {
                    case (1) -> {
                        cloud1Pane.setDisable(true);
                        cloud1Pane.setOpacity(0.3);
                    }
                    case (2) -> {
                        cloud2Pane.setDisable(true);
                        cloud2Pane.setOpacity(0.3);
                    }
                    case (3) -> {
                        cloud3Pane.setDisable(true);
                        cloud3Pane.setOpacity(0.3);
                    }
                }
            } else {
                switch (cloud.getCloudID()) {
                    case (1) -> {
                        cloud1Pane.setDisable(false);
                        cloud1Pane.setOpacity(1);
                    }
                    case (2) -> {
                        cloud2Pane.setDisable(false);
                        cloud2Pane.setOpacity(1);
                    }
                    case(3) ->{
                        cloud3Pane.setDisable(false);
                        cloud3Pane.setOpacity(1);
                    }
                }
            }
        }
    }

   /* public ImageView getImageFromStudent(Student student){
        ImageView studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("")));

        studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/STUDENT_GREEN.png")));
        if (student==Student.GREEN)
            studentImage.
        else if (student==Student.RED)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/STUDENT_RED.png")));
        else if (student==Student.YELLOW)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/STUDENT_YELLOW.png")));
        else if (student==Student.BLUE)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/STUDENT_BLUE.png")));
        else if (student==Student.PINK)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/STUDENT_PINK.png")));

        return studentImage;
    }

    */

    public String getImageFromStudent(Student student){
        String s="";
        switch (student){
            case GREEN -> {
                s="img/STUDENT_GREEN.png";
            }
            case RED -> {
                s="img/STUDENT_RED.png";
            }
            case YELLOW -> {
                s="img/STUDENT_YELLOW.png";
            }
            case BLUE -> {
                s="img/STUDENT_BLUE.png";
            }
            case PINK -> {
                s="img/STUDENT_PINK.png";
            }
        }
        return s;
    }


    public void setEntranceStudentClickable(){
        for(ImageView imageView : entranceView) {
            int i=0;
            imageView.setImage(new Image(getImageFromStudent(gui.getPlayer().getPlance().getEntrance().get(i))));
            imageView.setVisible(true);
            imageView.setDisable(false);
        }
    }

    public void setEntranceStudentNotClickable(){
        for(ImageView imageView : entranceView) {
            imageView.setVisible(true);
            imageView.setDisable(true);
        }
    }

}
