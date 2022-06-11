package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.board.TypeOfInputCharacter;
import it.polimi.ingsw.model.game.EffectHandler;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Wizard;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class DashboardController {

    private GUI gui;
    private Student studentToMove;
    private int numOfStudentChosen;
    private boolean characterButtonClicked = false;
    private CharacterView characterPlayed;
    private int numOfCharacterPlayed;
    int numEffect7=0;
    int numEffect10=0;
    ArrayList<Student> effect7Students = new ArrayList<>();
    ArrayList<Student> effect10Students = new ArrayList<>();


    public void setup() {

        getImageViewFromString("entranceStudent8").setVisible(false);
        getImageViewFromString("entranceStudent8").setDisable(true);
        getImageViewFromString("entranceStudent9").setVisible(false);
        getImageViewFromString("entranceStudent9").setDisable(true);

        coinPane.setVisible(false);
        characterButtor.setVisible(false);
        characterButtonLabel.setVisible(false);
        if (gui.getGameMode() == GameMode.EXPERTMODE) {
            coinLabel.setText(String.valueOf(gui.getPlayer().getCoins()));
            coinPane.setVisible(true);
            characterButtor.setVisible(true);
            characterButtor.setDisable(true);
            characterButtor.setOpacity(0.3);
            characterButtonLabel.setVisible(true);
            characterButtonLabel.setDisable(true);
            characterButtonLabel.setOpacity(1);
        }
        gui.setCurrentPlayerView(gui.getPlayer());
        setupPlayerView(gui.getPlayer());
        setupArchipelago();
        setupPlance(gui.getPlayer());
        charactersPane.setVisible(false);
        showPlanceChoiceBox.setDisable(true);
        showPlanceChoiceBox.setOpacity(0.3);
    }

    public void setupChoiceBox(PlayerView player) {
        if (player == null) {
            showPlanceChoiceBox.getItems().add(gui.getPlayers().get(0).getNickname() + " plance");
            showPlanceChoiceBox.getItems().add(gui.getPlayers().get(1).getNickname() + " plance");
            if (gui.getNumOfPlayers() == 3)
                showPlanceChoiceBox.getItems().add(gui.getPlayers().get(2).getNickname() + " plance");

            showPlanceChoiceBox.setOnAction((event) -> {
                if (gui.getCurrentPlayerState() != PlayerState.ASSISTANTPHASE) {
                    int selectedIndex = showPlanceChoiceBox.getSelectionModel().getSelectedIndex();
                    gui.setCurrentPlayerView(gui.getPlayers().get(selectedIndex));
                    setupPlance(gui.getPlayers().get(selectedIndex));
                    if (gui.getPlayer().getNickname().equals(gui.getPlayers().get(selectedIndex).getNickname()) && gui.getCurrentPlayerState() != PlayerState.WAITING
                            && gui.getCurrentPlayerState() != PlayerState.RECONNECTED) {
                        gui.nextMove(gui.getCurrentPlayerState());
                    }
                }
                showPlanceLabel.setText("");
            });

            showPlanceChoiceBox.setDisable(false);
            showPlanceChoiceBox.setOpacity(1);

        }
    }

    public void setupChoiceBoxAssistantPhase(boolean isPhase) {
        if (isPhase) {
            showPlanceChoiceBox.setDisable(true);
            showPlanceChoiceBox.setOpacity(0.3);
        } else {
            showPlanceChoiceBox.setDisable(false);
            showPlanceChoiceBox.setOpacity(1);
        }
    }

    public void setBackgroundImage(Wizard wizard){
        switch (wizard){
            case WIZARD_BLUE -> {
                backgroundImage.setImage(new Image("img/wizardBlueBackground.jpg"));
            }
            case WIZARD_PINK -> {
                backgroundImage.setImage(new Image("img/wizardPinkBackground.jpg"));
            }
            case WIZARD_YELLOW -> {
                backgroundImage.setImage(new Image("img/wizardYellowBackground.jpg"));
            }
            case WIZARD_GREEN -> {
                backgroundImage.setImage(new Image("img/wizardGreenBackground.jpg"));
            }
        }
    }


    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @FXML
    public ImageView backgroundImage;

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

    @FXML
    public Pane island1Pane;

    @FXML
    public Label island1RedStudentLabel;

    @FXML
    public Label island1GreenStudentLabel;

    @FXML
    public Label island1PinkStudentLabel;

    @FXML
    public Label island1YellowStudentLabel;

    @FXML
    public Label island1BlueStudentLabel;

    @FXML
    public ImageView island1MotherNature;

    @FXML
    public Label island1TowerLabel;

    @FXML
    public ImageView island1TowerImage;

    @FXML
    public ImageView island1Stop;

    @FXML
    public Pane island2Pane;

    @FXML
    public Label island2RedStudentLabel;

    @FXML
    public Label island2GreenStudentLabel;

    @FXML
    public Label island2PinkStudentLabel;

    @FXML
    public Label island2YellowStudentLabel;

    @FXML
    public Label island2BlueStudentLabel;

    @FXML
    public ImageView island2MotherNature;

    @FXML
    public Label island2TowerLabel;

    @FXML
    public ImageView island2TowerImage;

    @FXML
    public ImageView island2Stop;

    @FXML
    public Pane island3Pane;

    @FXML
    public Label island3RedStudentLabel;

    @FXML
    public Label island3GreenStudentLabel;

    @FXML
    public Label island3PinkStudentLabel;

    @FXML
    public Label island3YellowStudentLabel;

    @FXML
    public Label island3BlueStudentLabel;

    @FXML
    public ImageView island3MotherNature;

    @FXML
    public Label island3TowerLabel;

    @FXML
    public ImageView island3TowerImage;

    @FXML
    public ImageView island3Stop;

    @FXML
    public Pane island4Pane;

    @FXML
    public Label island4RedStudentLabel;

    @FXML
    public Label island4GreenStudentLabel;

    @FXML
    public Label island4PinkStudentLabel;

    @FXML
    public Label island4YellowStudentLabel;

    @FXML
    public Label island4BlueStudentLabel;

    @FXML
    public ImageView island4MotherNature;

    @FXML
    public Label island4TowerLabel;

    @FXML
    public ImageView island4TowerImage;

    @FXML
    public ImageView island4Stop;

    @FXML
    public Pane island5Pane;

    @FXML
    public Label island5RedStudentLabel;

    @FXML
    public Label island5GreenStudentLabel;

    @FXML
    public Label island5PinkStudentLabel;

    @FXML
    public Label island5YellowStudentLabel;

    @FXML
    public Label island5BlueStudentLabel;

    @FXML
    public ImageView island5MotherNature;

    @FXML
    public Label island5TowerLabel;

    @FXML
    public ImageView island5TowerImage;

    @FXML
    public ImageView island5Stop;

    @FXML
    public Pane island6Pane;

    @FXML
    public Label island6RedStudentLabel;

    @FXML
    public Label island6GreenStudentLabel;

    @FXML
    public Label island6PinkStudentLabel;

    @FXML
    public Label island6YellowStudentLabel;

    @FXML
    public Label island6BlueStudentLabel;

    @FXML
    public ImageView island6MotherNature;

    @FXML
    public Label island6TowerLabel;

    @FXML
    public ImageView island6TowerImage;

    @FXML
    public ImageView island6Stop;

    @FXML
    public Pane island7Pane;

    @FXML
    public Label island7RedStudentLabel;

    @FXML
    public Label island7GreenStudentLabel;

    @FXML
    public Label island7PinkStudentLabel;

    @FXML
    public Label island7YellowStudentLabel;

    @FXML
    public Label island7BlueStudentLabel;

    @FXML
    public ImageView island7MotherNature;

    @FXML
    public Label island7TowerLabel;

    @FXML
    public ImageView island7TowerImage;

    @FXML
    public ImageView island7Stop;

    @FXML
    public Pane island8Pane;

    @FXML
    public Label island8RedStudentLabel;

    @FXML
    public Label island8GreenStudentLabel;

    @FXML
    public Label island8PinkStudentLabel;

    @FXML
    public Label island8YellowStudentLabel;

    @FXML
    public Label island8BlueStudentLabel;

    @FXML
    public ImageView island8MotherNature;

    @FXML
    public Label island8TowerLabel;

    @FXML
    public ImageView island8TowerImage;

    @FXML
    public ImageView island8Stop;

    @FXML
    public Pane island9Pane;

    @FXML
    public Label island9RedStudentLabel;

    @FXML
    public Label island9GreenStudentLabel;

    @FXML
    public Label island9PinkStudentLabel;

    @FXML
    public Label island9YellowStudentLabel;

    @FXML
    public Label island9BlueStudentLabel;

    @FXML
    public ImageView island9MotherNature;

    @FXML
    public Label island9TowerLabel;

    @FXML
    public ImageView island9TowerImage;

    @FXML
    public ImageView island9Stop;

    @FXML
    public Pane island10Pane;

    @FXML
    public Label island10RedStudentLabel;

    @FXML
    public Label island10GreenStudentLabel;

    @FXML
    public Label island10PinkStudentLabel;

    @FXML
    public Label island10YellowStudentLabel;

    @FXML
    public Label island10BlueStudentLabel;

    @FXML
    public ImageView island10MotherNature;

    @FXML
    public Label island10TowerLabel;

    @FXML
    public ImageView island10TowerImage;

    @FXML
    public ImageView island10Stop;

    @FXML
    public Pane island11Pane;

    @FXML
    public Label island11RedStudentLabel;

    @FXML
    public Label island11GreenStudentLabel;

    @FXML
    public Label island11PinkStudentLabel;

    @FXML
    public Label island11YellowStudentLabel;

    @FXML
    public Label island11BlueStudentLabel;

    @FXML
    public ImageView island11MotherNature;

    @FXML
    public Label island11TowerLabel;

    @FXML
    public ImageView island11TowerImage;

    @FXML
    public ImageView island11Stop;

    @FXML
    public Pane island12Pane;

    @FXML
    public Label island12RedStudentLabel;

    @FXML
    public Label island12GreenStudentLabel;

    @FXML
    public Label island12PinkStudentLabel;

    @FXML
    public Label island12YellowStudentLabel;

    @FXML
    public Label island12BlueStudentLabel;

    @FXML
    public ImageView island12MotherNature;

    @FXML
    public Label island12TowerLabel;

    @FXML
    public ImageView island12TowerImage;

    @FXML
    public ImageView island12Stop;

    @FXML
    public ImageView planceTower1;

    @FXML
    public ImageView planceTower2;

    @FXML
    public ImageView planceTower3;

    @FXML
    public ImageView planceTower4;

    @FXML
    public ImageView planceTower5;

    @FXML
    public ImageView planceTower6;

    @FXML
    public ImageView planceTower7;

    @FXML
    public ImageView planceTower8;

    @FXML
    public ChoiceBox showPlanceChoiceBox;

    @FXML
    public Label showPlanceLabel;

    @FXML
    public ImageView character1Image;

    @FXML
    public ImageView character2Image;

    @FXML
    public ImageView character3Image;

    @FXML
    public GridPane character1StudentsPane;

    @FXML
    public GridPane character2StudentsPane;

    @FXML
    public GridPane character3StudentsPane;

    @FXML
    public ImageView character1Student1;

    @FXML
    public ImageView character1Student2;

    @FXML
    public ImageView character1Student3;

    @FXML
    public ImageView character1Student4;

    @FXML
    public ImageView character1Student5;

    @FXML
    public ImageView character1Student6;

    @FXML
    public ImageView character2Student1;

    @FXML
    public ImageView character2Student2;

    @FXML
    public ImageView character2Student3;

    @FXML
    public ImageView character2Student4;

    @FXML
    public ImageView character2Student5;

    @FXML
    public ImageView character2Student6;

    @FXML
    public ImageView character3Student1;

    @FXML
    public ImageView character3Student2;

    @FXML
    public ImageView character3Student3;

    @FXML
    public ImageView character3Student4;

    @FXML
    public ImageView character3Student5;

    @FXML
    public ImageView character3Student6;

    @FXML
    public GridPane character1StopsPane;

    @FXML
    public GridPane character2StopsPane;

    @FXML
    public GridPane character3StopsPane;

    @FXML
    public ImageView character1Stop1;

    @FXML
    public ImageView character1Stop2;

    @FXML
    public ImageView character1Stop3;

    @FXML
    public ImageView character1Stop4;

    @FXML
    public ImageView character2Stop1;

    @FXML
    public ImageView character2Stop2;

    @FXML
    public ImageView character2Stop3;

    @FXML
    public ImageView character2Stop4;

    @FXML
    public ImageView character3Stop1;

    @FXML
    public ImageView character3Stop2;

    @FXML
    public ImageView character3Stop3;

    @FXML
    public ImageView character3Stop4;

    @FXML
    public ImageView character1CoinImage;

    @FXML
    public ImageView character2CoinImage;

    @FXML
    public ImageView character3CoinImage;

    @FXML
    public Pane cloudsPane;

    @FXML
    public Pane winnerPane;

    @FXML
    public Label winnerNicknameLabel;

    public void chosenCloud1(MouseEvent mouseEvent) {
        chosenCluod(1);
    }

    public void chosenCloud2(MouseEvent mouseEvent) {
        chosenCluod(2);
    }

    public void chosenCloud3(MouseEvent mouseEvent) {
        chosenCluod(3);
    }

    public void chosenCluod (int cloudID){
        ChooseCloudMessage message = new ChooseCloudMessage(cloudID);
        gui.getClient().sendMessage(message);
        this.characterPlayed=null;
        gui.setCharacter4played(false);

        numEffect7=0;
        numEffect10=0;
        effect7Students.clear();
        effect10Students.clear();
    }

    public void chosenAssistantLion(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.LION);
    }

    public void chosenAssistantOstrich(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.OSTRICH);
    }

    public void chosenAssistantCat(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.CAT);
    }

    public void chosenAssistantEagle(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.EAGLE);
    }

    public void chosenAssistantFox(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.FOX);
    }

    public void chosenAssistantSnake(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.SNAKE);
    }

    public void chosenAssistantOctopus(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.OCTOPUS);
    }

    public void chosenAssistantDog(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.DOG);
    }

    public void chosenAssistantElephants(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.ELEPHANTS);
    }

    public void chosenAssistantTurtle(MouseEvent mouseEvent) {
        chosenAssistant(Assistant.TURTLE);
    }

    public void chosenAssistant (Assistant assistant){
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(assistant));
    }

    public void chosenEntranceStudent9(MouseEvent mouseEvent) {
        chosenEntranceStudent(8);
    }

    public void chosenEntranceStudent6(MouseEvent mouseEvent) {
        chosenEntranceStudent(5);
    }

    public void chosenEntranceStudent4(MouseEvent mouseEvent) {
        chosenEntranceStudent(3);
    }

    public void chosenEntranceStudent2(MouseEvent mouseEvent) {
        chosenEntranceStudent(1);
    }

    public void chosenEntranceStudent1(MouseEvent mouseEvent) {
        chosenEntranceStudent(0);
    }

    public void chosenEntranceStudent5(MouseEvent mouseEvent) {
        chosenEntranceStudent(4);
    }

    public void chosenEntranceStudent3(MouseEvent mouseEvent) {
       chosenEntranceStudent(2);
    }

    public void chosenEntranceStudent7(MouseEvent mouseEvent) {
       chosenEntranceStudent(6);
    }

    public void chosenEntranceStudent8(MouseEvent mouseEvent) {
        chosenEntranceStudent(7);
    }

    public void chosenEntranceStudent(int studentNumber){
        if(gui.getCurrentPlayerState()==PlayerState.STUDENTPHASE) {
            studentToMove = gui.getPlayer().getPlance().getEntrance().get(studentNumber);
            setEntranceStudentNotClickable();
            if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
                hallPane.setDisable(false);
            System.out.println("Scelto studente di colore" + studentToMove);
            numOfStudentChosen = studentNumber;
            setArchipelagoClickable();
        }
        else if(gui.getCurrentPlayerState()==PlayerState.CHARACTHERSTUDENTSPHASE && characterPlayed.getTypeOfInputCharacter()==TypeOfInputCharacter.EFFECT7INPUT){
            setStudentsCardNotClickable();
            effect7Students.add(gui.getPlayer().getPlance().getEntrance().get(studentNumber));
            numEffect7--;
            int y=studentNumber+1;
            getImageViewFromString("entranceStudent"+ y).setDisable(true);
            getImageViewFromString("entranceStudent"+ y).setOpacity(0.3);
            if(numEffect7==0) {
                ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(effect7Students);
                gui.getClient().sendMessage(message);
            }
        }
        else if(gui.getCurrentPlayerState()==PlayerState.CHARACTHERSTUDENTSPHASE && characterPlayed.getTypeOfInputCharacter()==TypeOfInputCharacter.EFFECT10INPUT){
            effect10Students.add(gui.getPlayer().getPlance().getEntrance().get(studentNumber));
            numEffect10++;
            int y=studentNumber+1;
            getImageViewFromString("entranceStudent"+ y).setDisable(true);
            getImageViewFromString("entranceStudent"+ y).setOpacity(0.3);
            int num=0;
            for(Student student : Student.values())
                num=num+gui.getPlayer().getPlance().getNumberOfStudentHall(student);
            if(numEffect10==2 || num==1)
                setEntranceStudentNotClickable();
            plancePane.setDisable(false);
            hallPane.setDisable(false);
            hallBlue.setDisable(false);
            hallYellow.setDisable(false);
            hallRed.setDisable(false);
            hallPink.setDisable(false);
            hallGreen.setDisable(false);
        }
    }

    public void chosenHall(MouseEvent mouseEvent) {
        if(gui.getCurrentPlayerState()==PlayerState.STUDENTPHASE) {
            numOfStudentChosen++;
            getImageViewFromString("entranceStudent" + numOfStudentChosen).setVisible(false);
            getImageViewFromString("entranceStudent" + numOfStudentChosen).setDisable(true);
            MoveStudentToHallMessage message = new MoveStudentToHallMessage(studentToMove);
            gui.getClient().sendMessage(message);
            System.out.println("PROVA2");
        }
    }

    public void chosenHallGreen(MouseEvent mouseEvent) {
        chosenHallColor(Student.GREEN);
    }

    public void chosenHallRed(MouseEvent mouseEvent) {
        chosenHallColor(Student.RED);
    }

    public void chosenHallYellow(MouseEvent mouseEvent) {
        chosenHallColor(Student.YELLOW);
    }

    public void chosenHallPink(MouseEvent mouseEvent) {
        chosenHallColor(Student.PINK);
    }

    public void chosenHallBlue(MouseEvent mouseEvent) {
        chosenHallColor(Student.BLUE);
    }

    public void chosenHallColor (Student studentColor){
        System.out.println("PROVA");
        if(this.characterPlayed.getTypeOfInputCharacter() == TypeOfInputCharacter.STUDENT){
            ArrayList<Student> students = new ArrayList<>();
            students.add(studentColor);
            ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(students);
            gui.getClient().sendMessage(message);
        }
        else if(this.characterPlayed.getTypeOfInputCharacter() == TypeOfInputCharacter.EFFECT10INPUT){
            effect10Students.add(studentColor);
            numEffect10--;
            if(numEffect10==0) {
                ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(effect10Students);
                gui.getClient().sendMessage(message);
            }
        }
    }

    public void clickCharacetrButton(MouseEvent mouseEvent) {
        if (!characterButtonClicked) {
            characterButtonClicked = true;
            charactersPane.setVisible(true);
            plancePane.setVisible(false);
            characterButtonLabel.setText("RETURN TO PLANCE");
            characterButtor.setDisable(false);
            setupCharacterView();
                charactersPane.setDisable(false);
                character1Image.setDisable(false);
                character2Image.setDisable(false);
                character3Image.setDisable(false);
        } else {
            characterButtonClicked = false;
            charactersPane.setVisible(false);
            plancePane.setVisible(true);
            characterButtonLabel.setText("PLAY CHARACTER");
            characterButtor.setDisable(false);
            charactersPane.setDisable(true);
            character1Image.setDisable(true);
            character2Image.setDisable(true);
            character3Image.setDisable(true);
        }
    }

    public void chosenIsland1(MouseEvent mouseEvent) {
        chosenIsland(1);
    }

    public void chosenIsland2(MouseEvent mouseEvent) {
        chosenIsland(2);
    }

    public void chosenIsland3(MouseEvent mouseEvent) {
        chosenIsland(3);
    }

    public void chosenIsland9(MouseEvent mouseEvent) {
       chosenIsland(9);
    }

    public void chosenIsland4(MouseEvent mouseEvent) {
        chosenIsland(4);
    }


    public void chosenIsland7(MouseEvent mouseEvent) {
        chosenIsland(7);
    }

    public void chosenIsland8(MouseEvent mouseEvent) {
       chosenIsland(8);
    }

    public void chosenIsland10(MouseEvent mouseEvent) {
        chosenIsland(10);
    }

    public void chosenIsland11(MouseEvent mouseEvent) {
        chosenIsland(11);
    }

    public void chosenIsland5(MouseEvent mouseEvent) {
        chosenIsland(5);
    }

    public void chosenIsland12(MouseEvent mouseEvent) {
        chosenIsland(12);
    }

    public void chosenIsland6(MouseEvent mouseEvent) {
        chosenIsland(6);
    }

    public void chosenIsland(int islandID){
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            //int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(islandID-1).getStudents());
            //num++;
            //System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(islandID, studentToMove);
            gui.getClient().sendMessage(message);
        }
        else if (gui.getCurrentPlayerState() == PlayerState.MOTHERNATUREPHASE) {
            int num;
            if (islandID - gui.getBoard().getMotherNature() > 0) {
                num = islandID - gui.getBoard().getMotherNature();
            } else num = gui.getBoard().getIslandViews().size() - Math.abs(islandID - gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
        else if(gui.getCurrentPlayerState() == PlayerState.CHARACTHERISLANDPHASE){
            ChooseIslandEffectMessage message = new ChooseIslandEffectMessage(islandID);
            gui.getClient().sendMessage(message);
        }
    }
    //////////////////////////////////////////////////////////////


    //First setup of the player
    public void setupPlayerView(PlayerView player) {

        nicknameLabel.setText(player.getNickname());
        setPlayerStateLabel(player.getPlayerState());
        setGameUpdateLabel("GAME: Waiting for players");
        plancePane.setVisible(true);
        plancePane.setDisable(true);
        setEntranceStudentNotClickable();
        hallPane.setDisable(true);
        hallPane.setVisible(true);
        assistantsPane.setVisible(false);
        assistantsPane.setDisable(true);
    }

    public void setupArchipelago() {

        //Set islands
        int i = 0;
        for (i = 1; i <= gui.getBoard().getIslandViews().size(); i++) {
            getPaneFromString("island" + i + "Pane").setVisible(true);
            getPaneFromString("island" + i + "Pane").setDisable(true); //<------------------------------------------------------------------------------------------
            if (gui.getBoard().getMotherNature() == i)
                getImageViewFromString("island" + i + "MotherNature").setVisible(true);
            else getImageViewFromString("island" + i + "MotherNature").setVisible(false);

            if (gui.getBoard().getIslandViews().get(i - 1).isStop())
                getImageViewFromString("island" + i + "Stop").setVisible(true);
            else getImageViewFromString("island" + i + "Stop").setVisible(false);

            getLabelFromString("island" + i + "BlueStudentLabel").setText(Integer.toString(numOfColorStudent(Student.BLUE, gui.getBoard().getIslandViews().get(i - 1).getStudents())));
            getLabelFromString("island" + i + "RedStudentLabel").setText(Integer.toString(numOfColorStudent(Student.RED, gui.getBoard().getIslandViews().get(i - 1).getStudents())));
            getLabelFromString("island" + i + "PinkStudentLabel").setText(Integer.toString(numOfColorStudent(Student.PINK, gui.getBoard().getIslandViews().get(i - 1).getStudents())));
            getLabelFromString("island" + i + "YellowStudentLabel").setText(Integer.toString(numOfColorStudent(Student.YELLOW, gui.getBoard().getIslandViews().get(i - 1).getStudents())));
            getLabelFromString("island" + i + "GreenStudentLabel").setText(Integer.toString(numOfColorStudent(Student.GREEN, gui.getBoard().getIslandViews().get(i - 1).getStudents())));

            if (gui.getBoard().getIslandViews().get(i - 1).getNumOfTowers() == 0) {
                getLabelFromString("island" + i + "TowerLabel").setVisible(false);
                getImageViewFromString("island" + i + "TowerImage").setVisible(false);
            } else {
                getLabelFromString("island" + i + "TowerLabel").setText(Integer.toString(gui.getBoard().getIslandViews().get(i - 1).getNumOfTowers()));
                getImageViewFromString("island" + i + "TowerImage").setImage(new Image(getImageFromTower(gui.getBoard().getIslandViews().get(i - 1).getTower())));
                getLabelFromString("island" + i + "TowerLabel").setVisible(true);
                getImageViewFromString("island" + i + "TowerImage").setVisible(true);
            }
        }

        for (int count = i; count <= 12; count++) {
            getPaneFromString("island" + count + "Pane").setVisible(false);
        }

        //Set clouds
        setupClouds();
    }

    public void setupPlance(PlayerView player) {
        coinLabel.setText(String.valueOf(gui.getPlayer().getCoins())); //////////////////////////////////////////////////////////////
        //Set entrance
        int i = 0;
        for (i = 1; i <= player.getPlance().getEntrance().size(); i++) {
            getImageViewFromString("entranceStudent" + i).setImage(new Image(getImageFromStudent(player.getPlance().getEntrance().get(i - 1))));
            getImageViewFromString("entranceStudent" + i).setVisible(true);
            getImageViewFromString("entranceStudent" + i).setOpacity(1);
            getImageViewFromString("entranceStudent" + i).setDisable(true);
        }

        for (int count = i + 1; count <= 9; count++) {
            getImageViewFromString("entranceStudent" + i).setVisible(false);
            getImageViewFromString("entranceStudent" + i).setDisable(true);
        }

        //Set hall
        hallBlue.setDisable(true);
        hallGreen.setDisable(true);
        hallPink.setDisable(true);
        hallRed.setDisable(true);
        hallYellow.setDisable(true);
        hallBlue.setOpacity(1);
        hallGreen.setOpacity(1);
        hallPink.setOpacity(1);
        hallRed.setOpacity(1);
        hallYellow.setOpacity(1);

        int c=0;
        for(Student student : Student.values()){
            c++;
            for (int count = 1; count <= player.getPlance().getNumberOfStudentHall(student); count++) {
                getImageViewFromString("hall"+c+"Student" + count).setVisible(true);
            }
            for (int count = player.getPlance().getNumberOfStudentHall(student) + 1; count <= 10; count++) {
                getImageViewFromString("hall"+c+"Student" + count).setVisible(false);
            }
        }

        //Set professors
        for(Student color : Student.values())
            getImageViewFromString("professor"+color.getText(color)).setVisible(false);

        for (Professor prof : player.getPlance().getProfessors())
            getImageViewFromString("professor" + prof.getText(prof)).setVisible(true);


        //Set towers
        int count;
        for (count = 1; count <= player.getPlance().getNumoftowers(); count++) {
            getImageViewFromString("planceTower" + count).setImage(new Image(getImageFromTower(player.getPlance().getTower())));
            getImageViewFromString("planceTower" + count).setVisible(true);
        }
        int num = 0;
        if (gui.getNumOfPlayers() == 2)
            num = 8;
        if (gui.getNumOfPlayers() == 3)
            num = 6;
        for (i = count; count <= num; count++) {
            getImageViewFromString("planceTower" + i).setVisible(false);
        }

        plancePane.setVisible(true);
        plancePane.setDisable(true);
        setEntranceStudentNotClickable();
        hallPane.setDisable(true);
        hallPane.setVisible(true);
        assistantsPane.setVisible(false);
        assistantsPane.setDisable(true);
        charactersPane.setVisible(false);
        charactersPane.setDisable(true);

        //set Characters //////////////////////////////////////////////////////////////////////////
        for(int k=1; k<=3; k++)
            for(int j=1; j<=6; j++)
                getImageViewFromString("character" + k + "Student" + j).setDisable(true);

        for(int k=1; k<=3; k++)
            for(int j=1; j<=4; j++)
                getImageViewFromString("character" + k + "Stop" + j).setDisable(true);
    }


    public void setAssistantView() {
        plancePane.setVisible(false);
        plancePane.setDisable(true);
        assistantsPane.setVisible(true);
        assistantsPane.setDisable(false);

        for(Assistant assistant: Assistant.values()){
            getImageViewFromString(assistant.getText(assistant)).setDisable(true);
            getImageViewFromString(assistant.getText(assistant)).setOpacity(0.3);
        }

        for (Assistant assistant : gui.getPlayer().getAssistantCards()) {
            getImageViewFromString(assistant.getText(assistant)).setDisable(false);
            getImageViewFromString(assistant.getText(assistant)).setOpacity(1);
        }
    }

    public void setupClouds() {

        if (gui.getBoard().getClouds().size() == 2) {
            for (int count = 1; count <= gui.getBoard().getClouds().size(); count++)
                getImageViewFromString("cloud" + count + "Student" + 4).setVisible(false);
        }
        for (int i = 1; i <= gui.getBoard().getClouds().size(); i++)
            for (int j = 1; j <= gui.getBoard().getClouds().get(i - 1).getStudents().size(); j++) {
                getImageViewFromString("cloud" + i + "Student" + j).setImage(new Image(getImageFromStudent(gui.getBoard().getClouds().get(i - 1).getStudents().get(j - 1))));
            }
        if (gui.getBoard().getClouds().size() == 2) {
            getPaneFromString("cloud" + 3 + "Pane").setVisible(false);
            getPaneFromString("cloud" + 3 + "Pane").setDisable(true);
        }
        for (CloudView cloud : gui.getBoard().getClouds()) {
            if (cloud.isChoosen()) {
                getPaneFromString("cloud" + cloud.getCloudID() + "Pane").setDisable(true);
                getPaneFromString("cloud" + cloud.getCloudID() + "Pane").setOpacity(0.3);
            } else {
                getPaneFromString("cloud" + cloud.getCloudID() + "Pane").setDisable(true);
                getPaneFromString("cloud" + cloud.getCloudID() + "Pane").setOpacity(1);
            }
        }
    }

    public void setupCharacterView() {
        for (int i = 1; i <= gui.getBoard().getCharacters().size(); i++) {
            getImageViewFromString("character" + i + "Image").setImage(new Image("img/" + gui.getBoard().getCharacters().get(i - 1).getName() + ".jpg"));
            getImageViewFromString("character" + i + "Image").setVisible(true);
            getImageViewFromString("character" + i + "Image").setDisable(false);
            if(gui.getBoard().getCharacters().get(i-1).isUsed())
                getImageViewFromString("character"+i+"CoinImage").setVisible(true);
            setStudentsOnCharacter(i);
        }
    }

    public void setStudentsOnCharacter(int index) {
        EffectHandler handler = gui.getEffectHandler();
        switch (gui.getBoard().getCharacters().get(index - 1).getName()) {
            case "MONK" -> {
                getGridPaneFromString("character" + index + "StudentsPane").setVisible(true);
                for (int i = 0; i < handler.getEffect1students().size(); i++) {
                    getImageViewFromString("character" + index + "Student" + (i + 1)).setImage(new Image(getImageFromStudent(handler.getEffect1students().get(i))));
                }
            }
            case "FARMER", "HERALD", "POSTMAN", "CENTAUR", "KNIGHT", "FUNGARO", "MINSTREL", "PICAROON" -> {
                getGridPaneFromString("character" + index + "StudentsPane").setVisible(false);
            }
            case "GRANDMA" -> {
                getGridPaneFromString("character" + index + "StudentsPane").setVisible(false);
                getGridPaneFromString("character" + index + "StopsPane").setVisible(true);
                int i;
                for (i = 1; i <= handler.getNumofislandstops(); i++) {
                    getImageViewFromString("character" + index + "Stop" + i).setVisible(true);
                }
                for (int j = i; j <= 4; j++) {
                    getImageViewFromString("character" + index + "Stop" + j).setVisible(false);
                }
            }
            case "JESTER" -> {
                getGridPaneFromString("character" + index + "StudentsPane").setVisible(true);
                for (int i = 0; i < handler.getEffect7students().size(); i++) {
                    getImageViewFromString("character" + index + "Student" + (i + 1)).setImage(new Image(getImageFromStudent(handler.getEffect7students().get(i))));
                    getImageViewFromString("character" + index + "Student" + (i + 1)).setOpacity(1);
                }
            }
            case "PRINCESS" -> {
                getGridPaneFromString("character" + index + "StudentsPane").setVisible(true);
                for (int i = 0; i < handler.getEffect11students().size(); i++) {
                    getImageViewFromString("character" + index + "Student" + (i + 1)).setImage(new Image(getImageFromStudent(handler.getEffect11students().get(i))));
                }
            }
        }
    }

    public void setMotherNatureView(int num, int motherNatureIsland) {
        int k;
        for (int i = 0; i < num; i++) {
            k = ((motherNatureIsland + i) % gui.getBoard().getIslandViews().size()) + 1;
            getPaneFromString("island" + k + "Pane").setDisable(false);
        }
    }

    public String getImageFromStudent(Student student) {
        return "img/STUDENT_"+student.getText(student).toUpperCase()+".png";
    }

    public String getImageFromTower(Tower tower) {
        return "img/TOWER_"+tower.getText(tower)+".png";
    }

    public ImageView getImageViewFromString(String s) {
        Object imageview = null;
        try {
            imageview = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((ImageView) imageview);
    }

    public Pane getPaneFromString(String s) {
        Object imageview = null;
        try {
            imageview = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((Pane) imageview);
    }

    public Label getLabelFromString(String s) {
        Object imageview = null;
        try {
            imageview = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((Label) imageview);
    }

    public GridPane getGridPaneFromString(String s) {
        Object gridPane = null;
        try {
            gridPane = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((GridPane) gridPane);
    }

    public void setEntranceStudentClickable() {
        plancePane.setDisable(false);
        for (int j = 1; j <= gui.getPlayer().getPlance().getEntrance().size(); j++)
            getImageViewFromString("entranceStudent" + j).setDisable(false);
    }

    public void setEntranceStudentNotClickable() {
        for (int j = 1; j <= gui.getPlayer().getPlance().getEntrance().size(); j++)
            getImageViewFromString("entranceStudent" + j).setDisable(true);
    }

    public void setGameUpdateLabel(String s) {
        gameUpdateLabel.setText(s);
    }

    public void setPlayerStateLabel(PlayerState playerState) {
        if (playerState == PlayerState.WAITING || playerState == PlayerState.RECONNECTED || playerState == PlayerState.DISCONNECTED)
            playerStateLabel.setText(playerState.toString());
        else playerStateLabel.setText("PLAYING");
    }

    public int numOfColorStudent(Student student, ArrayList<Student> students) {
        int i = 0;
        for (Student stud : students)
            if (stud.equals(student))
                i++;
        return i;
    }

    public void setArchipelagoClickable() {
        for (int i = 1; i <= gui.getBoard().getIslandViews().size(); i++)
            getPaneFromString("island" + i + "Pane").setDisable(false);
    }

    public void setCloudsClickable() {
        for (int count = 1; count <= gui.getBoard().getClouds().size(); count++) {
            if (!gui.getBoard().getClouds().get(count - 1).isChoosen())
                getPaneFromString("cloud" + count + "Pane").setDisable(false);
        }
    }

    public void setCharacterButtonClicked() {
        characterButtor.setDisable(false);
        characterButtor.setOpacity(1);
        characterButtonLabel.setDisable(false);
    }

    public void setCharacterButtonNotClicked() {
        characterButtor.setDisable(true);
        characterButtor.setOpacity(0.3);
        characterButtonLabel.setDisable(true);
    }

    public void resetCharacterButton(){
        characterButtonLabel.setText("PLAY CHARACTER");
        characterButtonClicked=false;
    }

    public void inputStudentCharacter() {
       switch (this.characterPlayed.getTypeOfInputCharacter()) {
           case EFFECT1INPUT, EFFECT11INPUT -> {
                for (int i = 1; i <= 4; i++)
                    getImageViewFromString("character"+ numOfCharacterPlayed + "Student" + i).setDisable(false);
                setGameUpdateLabel("EFFECT: Choose one student from the card");
            }
           case EFFECT7INPUT ->{
               for (int i = 1; i <= 6; i++)
                   getImageViewFromString("character"+ numOfCharacterPlayed + "Student" + i).setDisable(false);
               setGameUpdateLabel("EFFECT: Choose un to 3 students from the card and then from the entrance");
           }
           case STUDENT -> {
               plancePane.setDisable(false);
               hallPane.setDisable(false);
               hallBlue.setDisable(false);
               hallGreen.setDisable(false);
               hallPink.setDisable(false);
               hallRed.setDisable(false);
               hallYellow.setDisable(false);
               setGameUpdateLabel("EFFECT: Choose one color from the hall");
           }
            case EFFECT10INPUT ->{
               setEntranceStudentClickable();
                setGameUpdateLabel("EFFECT: Choose up two students from the entrance and then from the hall");
            }
        }
    }

    public void inputIslandCharacter(){
        setArchipelagoClickable();
        setGameUpdateLabel("EFFECT: Choose one island");
    }

    public void choseCharacter1Student1(MouseEvent mouseEvent) {
        choseStudentCard1to4(0,1);
    }

    public void choseCharacter1Student2(MouseEvent mouseEvent) {
        choseStudentCard1to4(1,1);
    }

    public void choseCharacter1Student3(MouseEvent mouseEvent) {
        choseStudentCard1to4(2,1);
    }

    public void choseCharacter1Student4(MouseEvent mouseEvent) {
        choseStudentCard1to4(3,1);;
    }

    public void choseCharacter1Student5(MouseEvent mouseEvent) {
        choseStudent5or6(4,1);
    }

    public void choseCharacter1Student6(MouseEvent mouseEvent) {
        choseStudent5or6(5,1);
    }

    public void choseCharacter2Student1(MouseEvent mouseEvent) {
        choseStudentCard1to4(0,2);
    }

    public void choseCharacter2Student2(MouseEvent mouseEvent) {
        choseStudentCard1to4(2,2);
    }

    public void choseCharacter2Student3(MouseEvent mouseEvent) {
        choseStudentCard1to4(2,2);
    }

    public void choseCharacter2Student4(MouseEvent mouseEvent) {
        choseStudentCard1to4(3,2);
    }

    public void choseCharacter2Student5(MouseEvent mouseEvent) {
        choseStudent5or6(4,2);
    }

    public void choseCharacter2Student6(MouseEvent mouseEvent) {
        choseStudent5or6(5,2);
    }

    public void choseCharacter3Student1(MouseEvent mouseEvent) {
        choseStudentCard1to4(0,3);
    }

    public void choseCharacter3Student2(MouseEvent mouseEvent) {
        choseStudentCard1to4(1,3);
    }

    public void choseCharacter3Student3(MouseEvent mouseEvent) {
        choseStudentCard1to4(2,3);
    }

    public void choseCharacter3Student4(MouseEvent mouseEvent) {
        choseStudentCard1to4(3,3);
    }

    public void choseCharacter3Student5(MouseEvent mouseEvent) {
        choseStudent5or6(4,3);
    }

    public void choseCharacter3Student6(MouseEvent mouseEvent) {
        choseStudent5or6(5,3);
    }

    public void choseStudentCard1to4 (int numOfStudent, int numOfCharacter){
        ArrayList<Student> students =new ArrayList<>();
        if(characterPlayed.getTypeOfInputCharacter()==TypeOfInputCharacter.EFFECT1INPUT){
            students.add(gui.getEffectHandler().getEffect1students().get(numOfStudent));
            ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(students);
            gui.getClient().sendMessage(message);
        }
        else if(characterPlayed.getTypeOfInputCharacter()==TypeOfInputCharacter.EFFECT11INPUT){
            if(gui.getPlayer().getPlance().getNumberOfStudentHall(gui.getEffectHandler().getEffect11students().get(numOfStudent)) < 10) {
                students.add(gui.getEffectHandler().getEffect11students().get(numOfStudent));
                ChooseStudentsEffectMessage message = new ChooseStudentsEffectMessage(students);
                gui.getClient().sendMessage(message);
            }
            else
                gameUpdateLabel.setText("You can't use this one");
        }
        else if(characterPlayed.getTypeOfInputCharacter()==TypeOfInputCharacter.EFFECT7INPUT){
            effect7Students.add(gui.getEffectHandler().getEffect7students().get(numOfStudent));
            numEffect7++;
            int y=numOfStudent+1;
            getImageViewFromString("character"+numOfCharacter+"Student"+y).setDisable(true);
            getImageViewFromString("character"+numOfCharacter+"Student"+y).setOpacity(0.3);
            if(effect7Students.size()==3)
                setStudentsCardNotClickable();
            setEntranceStudentClickable();
        }
    }

    public void choseStudent5or6(int numOfStudent, int numOfCharacter){
        effect7Students.add(gui.getEffectHandler().getEffect7students().get(numOfStudent));
        numEffect7++;
        int y=numOfStudent+1;
        getImageViewFromString("character"+numOfCharacter+"Student"+y).setDisable(true);
        getImageViewFromString("character"+numOfCharacter+"Student"+y).setOpacity(0.3);
        if(effect7Students.size()==3)
            setStudentsCardNotClickable();
        setEntranceStudentClickable();
    }

    public void choseCharacter1(MouseEvent mouseEvent) {
        choseCharacter(1);
    }

    public void choseCharacter2(MouseEvent mouseEvent) {
        choseCharacter(2);
    }

    public void choseCharacter3(MouseEvent mouseEvent) {
        choseCharacter(3);
    }

    public void choseCharacter(int characterNumber){
        boolean checkM=true;
        boolean checkP = true;
        if(gui.getBoard().getCharacters().get(characterNumber-1).getTypeOfInputCharacter()==TypeOfInputCharacter.EFFECT10INPUT)
            checkM = checkMinstrel();
        if(gui.getBoard().getCharacters().get(characterNumber-1).getTypeOfInputCharacter()==TypeOfInputCharacter.EFFECT11INPUT)
            checkP=checkPrincess();
        if(gui.getBoard().getCharacters().get(characterNumber-1).getCost()<= gui.getPlayer().getCoins() && checkM && checkP && characterPlayed==null) {
            ChooseCharacterMessage message = new ChooseCharacterMessage(gui.getBoard().getCharacters().get(characterNumber-1));
            gui.getClient().sendMessage(message);
            this.characterPlayed = gui.getBoard().getCharacters().get(characterNumber-1);
            numOfCharacterPlayed = characterNumber;
        }
        else
            gameUpdateLabel.setText("You don't have enough coins or you can't use this now");

        if(characterPlayed.getTypeOfInputCharacter()==TypeOfInputCharacter.INT)
            gui.setCharacter4played(true);
        gui.getClient().setCharacterPlayed(true);

       /* if(characterPlayed!=null)
            charactersPane.setDisable(true);
            for(int i=1; i<=3; i++)
                getImageViewFromString("character"+i+"Image").setDisable(true);
                */
    }

    public void setStudentsCardNotClickable(){
        for(int i=1; i<=6 ; i++)
            getImageViewFromString("character" + numOfCharacterPlayed + "Student" + i).setDisable(true);
    }

    public boolean checkMinstrel(){
        boolean check = true;
        int num=0;
        for(Student student : Student.values())
            num=num + gui.getPlayer().getPlance().getNumberOfStudentHall(student);
        if(num==0)
            check=false;
        return check;
    }

    public boolean checkPrincess(){
        boolean check = false;
        for(Student student : Student.values())
            if(gui.getPlayer().getPlance().getNumberOfStudentHall(student) < 10)
                check=true;
        return check;
    }
}