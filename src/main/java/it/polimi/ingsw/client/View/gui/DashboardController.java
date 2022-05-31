package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Professor;
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


    public void setup() {

        getImageViewFromString("entranceStudent8").setVisible(false);
        getImageViewFromString("entranceStudent8").setDisable(true);
        getImageViewFromString("entranceStudent9").setVisible(false);
        getImageViewFromString("entranceStudent9").setDisable(true);

        if(gui.getClient().getGamemode()!= GameMode.EXPERTMODE) {
            coinPane.setVisible(false);
            characterButtor.setVisible(false);
        }
        gui.setCurrentPlayerView(gui.getPlayer());
        setupPlayerView(gui.getPlayer());
        setupArchipelago();
        setupPlance(gui.getPlayer());
        showPlanceChoiceBox.setDisable(true);
        showPlanceChoiceBox.setOpacity(0.3);
    }

    public void setupChoiceBox(PlayerView player){
        if(player==null) {
            showPlanceChoiceBox.getItems().add(gui.getPlayers().get(0).getNickname() + " plance");
            showPlanceChoiceBox.getItems().add(gui.getPlayers().get(1).getNickname() + " plance");
            if (gui.getNumOfPlayers() == 3)
                showPlanceChoiceBox.getItems().add(gui.getPlayers().get(2).getNickname() + " plance");

            showPlanceChoiceBox.setOnAction((event) -> {
                if(gui.getCurrentPlayerState()!=PlayerState.ASSISTANTPHASE) {
                    int selectedIndex = showPlanceChoiceBox.getSelectionModel().getSelectedIndex();
                    gui.setCurrentPlayerView(gui.getPlayers().get(selectedIndex));
                    setupPlance(gui.getPlayers().get(selectedIndex));
                    if(gui.getPlayer().getNickname().equals(gui.getPlayers().get(selectedIndex).getNickname()) && gui.getCurrentPlayerState()!=PlayerState.WAITING
                       && gui.getCurrentPlayerState()!=PlayerState.RECONNECTED){
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
        }else {
            showPlanceChoiceBox.setDisable(false);
            showPlanceChoiceBox.setOpacity(1);
        }
    }


    public void setGui(GUI gui) {
        this.gui = gui;
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
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.LION));
    }

    public void chosenAssistantOstrich(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.OSTRICH));
    }

    public void chosenAssistantCat(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.CAT));
    }

    public void chosenAssistantEagle(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.EAGLE));
    }

    public void chosenAssistantFox(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.FOX));
    }

    public void chosenAssistantSnake(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.SNAKE));
    }

    public void chosenAssistantOctopus(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.OCTOPUS));
    }

    public void chosenAssistantDog(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.DOG));
    }

    public void chosenAssistantElephants(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.ELEPHANTS));
    }

    public void chosenAssistantTurtle(MouseEvent mouseEvent) {
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(Assistant.TURTLE));
    }

    public void chosenEntranceStudent9(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(8);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 8;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent6(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(5);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 5;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent4(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(3);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 3;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent2(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(1);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 1;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent1(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(0);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 0;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent5(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(4);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        numOfStudentChosen = 4;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent3(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(2);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 2;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent7(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(6);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 6;
        setArchipelagoClickable();
    }

    public void chosenEntranceStudent8(MouseEvent mouseEvent) {
        studentToMove = gui.getPlayer().getPlance().getEntrance().get(7);
        setEntranceStudentNotClickable();
        if (gui.getPlayer().getPlance().getHall().get(studentToMove) < 10)
            hallPane.setDisable(false);
        System.out.println("Scelto studente di colore" + studentToMove);
        numOfStudentChosen = 7;
        setArchipelagoClickable();
    }

    public void chosenHall(MouseEvent mouseEvent) {
        numOfStudentChosen++;
        getImageViewFromString("entranceStudent" + numOfStudentChosen).setVisible(false);
        getImageViewFromString("entranceStudent" + numOfStudentChosen).setDisable(true);
        MoveStudentToHallMessage message = new MoveStudentToHallMessage(studentToMove);
        gui.getClient().sendMessage(message);
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

    public void chosenIsland1(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(0).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(1, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(1-gui.getBoard().getMotherNature()>0){
                num=1-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(1-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland2(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(1).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(2, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(2-gui.getBoard().getMotherNature()>0){
                num=2-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(2-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland3(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(2).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(3, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(3-gui.getBoard().getMotherNature()>0){
                num=3-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(3-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland9(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(8).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(9, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(9-gui.getBoard().getMotherNature()>0){
                num=9-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(9-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland4(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(3).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(4, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(4-gui.getBoard().getMotherNature()>0){
                num=4-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(4-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }



    public void chosenIsland7(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(6).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(7, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(7-gui.getBoard().getMotherNature()>0){
                num=7-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(7-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland8(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(7).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(8, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(8-gui.getBoard().getMotherNature()>0){
                num=8-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(8-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland10(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(9).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(10, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(10-gui.getBoard().getMotherNature()>0){
                num=10-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(10-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland11(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(10).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(11, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(11-gui.getBoard().getMotherNature()>0){
                num=11-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(11-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland5(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(4).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(5, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(5-gui.getBoard().getMotherNature()>0){
                num=5-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(5-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland12(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(11).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(12, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(12-gui.getBoard().getMotherNature()>0){
                num=12-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(12-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }

    public void chosenIsland6(MouseEvent mouseEvent) {
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
            int num = numOfColorStudent(studentToMove, gui.getBoard().getIslandViews().get(5).getStudents());
            num++;
            System.out.println("Sei qui");
            MoveStudentToIslandMessage message = new MoveStudentToIslandMessage(6, studentToMove);
            gui.getClient().sendMessage(message);
        }
        if(gui.getCurrentPlayerState()==PlayerState.MOTHERNATUREPHASE){
            int num;
            if(6-gui.getBoard().getMotherNature()>0){
                num=6-gui.getBoard().getMotherNature();
            }else num=gui.getBoard().getIslandViews().size()-Math.abs(6-gui.getBoard().getMotherNature());
            gui.getClient().sendMessage(new MoveMotherNatureMessage(num));
        }
    }
    //////////////////////////////////////////////////////////////


    //First setup of the player
    public void setupPlayerView(PlayerView player){

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

    public void setupArchipelago(){

        //Set islands
        int i=0;
        for(i=1;i<=gui.getBoard().getIslandViews().size();i++){
            getPaneFromString("island"+i+"Pane").setVisible(true);
            getPaneFromString("island"+i+"Pane").setDisable(true); //<------------------------------------------------------------------------------------------
            if(gui.getBoard().getMotherNature()==i)
                getImageViewFromString("island"+i+"MotherNature").setVisible(true);
            else getImageViewFromString("island"+i+"MotherNature").setVisible(false);

            if(gui.getBoard().getIslandViews().get(i-1).isStop())
                getImageViewFromString("island"+i+"Stop").setVisible(true);
            else getImageViewFromString("island"+i+"Stop").setVisible(false);

            getLabelFromString("island"+i+"BlueStudentLabel").setText(Integer.toString(numOfColorStudent(Student.BLUE,gui.getBoard().getIslandViews().get(i-1).getStudents())));
            getLabelFromString("island"+i+"RedStudentLabel").setText(Integer.toString(numOfColorStudent(Student.RED,gui.getBoard().getIslandViews().get(i-1).getStudents())));
            getLabelFromString("island"+i+"PinkStudentLabel").setText(Integer.toString(numOfColorStudent(Student.PINK,gui.getBoard().getIslandViews().get(i-1).getStudents())));
            getLabelFromString("island"+i+"YellowStudentLabel").setText(Integer.toString(numOfColorStudent(Student.YELLOW,gui.getBoard().getIslandViews().get(i-1).getStudents())));
            getLabelFromString("island"+i+"GreenStudentLabel").setText(Integer.toString(numOfColorStudent(Student.GREEN,gui.getBoard().getIslandViews().get(i-1).getStudents())));

            if(gui.getBoard().getIslandViews().get(i-1).getNumOfTowers()==0){
                getLabelFromString("island"+i+"TowerLabel").setVisible(false);
                getImageViewFromString("island"+i+"TowerImage").setVisible(false);
            }else {
                getLabelFromString("island"+i+"TowerLabel").setText(Integer.toString(gui.getBoard().getIslandViews().get(i-1).getNumOfTowers()));
                getImageViewFromString("island" + i + "TowerImage").setImage(new Image(getImageFromTower(gui.getBoard().getIslandViews().get(i - 1).getTower())));
                getLabelFromString("island"+i+"TowerLabel").setVisible(true);
                getImageViewFromString("island"+i+"TowerImage").setVisible(true);
            }
        }

        for(int count=i;count<=12;count++){
            getPaneFromString("island"+count+"Pane").setVisible(false);
        }

        //Set clouds
        setupClouds();
    }

    public void setupPlance(PlayerView player){
        //Set entrance
            int i = 0;
            for (i = 1; i <= player.getPlance().getEntrance().size(); i++) {
                getImageViewFromString("entranceStudent" + i).setImage(new Image(getImageFromStudent(player.getPlance().getEntrance().get(i - 1))));
                getImageViewFromString("entranceStudent" + i).setVisible(true);
                getImageViewFromString("entranceStudent" + i).setDisable(true);
            }

            for (int count = i + 1; count <= 9; count++) {
                getImageViewFromString("entranceStudent" + i).setVisible(false);
                getImageViewFromString("entranceStudent" + i).setDisable(true);
            }

            //Set hall
            for (int count = 1; count <= player.getPlance().getNumberOfStudentHall(Student.GREEN); count++) {
                getImageViewFromString("hall1Student" + count).setVisible(true);
            }
            for (int count = player.getPlance().getNumberOfStudentHall(Student.GREEN) + 1; count <= 10; count++) {
                getImageViewFromString("hall1Student" + count).setVisible(false);
            }

            for (int count = 1; count <= player.getPlance().getNumberOfStudentHall(Student.RED); count++) {
                getImageViewFromString("hall2Student" + count).setVisible(true);
            }
            for (int count = player.getPlance().getNumberOfStudentHall(Student.RED) + 1; count <= 10; count++) {
                getImageViewFromString("hall2Student" + count).setVisible(false);
            }

            for (int count = 1; count <= player.getPlance().getNumberOfStudentHall(Student.YELLOW); count++) {
                getImageViewFromString("hall3Student" + count).setVisible(true);
            }
            for (int count = player.getPlance().getNumberOfStudentHall(Student.YELLOW) + 1; count <= 10; count++) {
                getImageViewFromString("hall3Student" + count).setVisible(false);
            }

            for (int count = 1; count <= player.getPlance().getNumberOfStudentHall(Student.PINK); count++) {
                getImageViewFromString("hall4Student" + count).setVisible(true);
            }
            for (int count = player.getPlance().getNumberOfStudentHall(Student.PINK) + 1; count <= 10; count++) {
                getImageViewFromString("hall4Student" + count).setVisible(false);
            }

            for (int count = 1; count <= player.getPlance().getNumberOfStudentHall(Student.BLUE); count++) {
                getImageViewFromString("hall5Student" + count).setVisible(true);
            }
            for (int count = player.getPlance().getNumberOfStudentHall(Student.BLUE) + 1; count <= 10; count++) {
                getImageViewFromString("hall5Student" + count).setVisible(false);
            }
            //System.out.println("Ciaooooooooooooooooooooooooo");


            //Set professors

            getImageViewFromString("professorGreen").setVisible(false);
            getImageViewFromString("professorRed").setVisible(false);
            getImageViewFromString("professorYellow").setVisible(false);
            getImageViewFromString("professorPink").setVisible(false);
            getImageViewFromString("professorBlue").setVisible(false);
            for (Professor prof : player.getPlance().getProfessors()) {
                getImageViewFromString("professor" + prof.getText(prof)).setVisible(true);
            }

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

    }


    public void setAssistantView(){
        plancePane.setVisible(false);
        plancePane.setDisable(true);
        assistantsPane.setVisible(true);
        assistantsPane.setDisable(false);


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
                    lion.setDisable(false);
                    lion.setOpacity(1);
                }
                case OSTRICH -> {
                    ostrich.setDisable(false);
                    ostrich.setOpacity(1);
                }
                case CAT -> {
                    cat.setDisable(false);
                    cat.setOpacity(1);
                }
                case EAGLE -> {
                    eagle.setDisable(false);
                    eagle.setOpacity(1);
                }
                case FOX -> {
                    fox.setDisable(false);
                    fox.setOpacity(1);
                }
                case SNAKE -> {
                    snake.setDisable(false);
                    snake.setOpacity(1);
                }
                case OCTOPUS -> {
                    octopus.setDisable(false);
                    octopus.setOpacity(1);
                }
                case DOG -> {
                    dog.setDisable(false);
                    dog.setOpacity(1);
                }
                case ELEPHANTS -> {
                    elephants.setDisable(false);
                    elephants.setOpacity(1);
                }
                case TURTLE -> {
                    turtle.setDisable(false);
                    turtle.setOpacity(1);
                }
            }
        }
    }

    public void setupClouds() {

        if(gui.getBoard().getClouds().size()==2){
            for(int count=1;count<=gui.getBoard().getClouds().size();count++)
                getImageViewFromString("cloud"+count+"Student"+4).setVisible(false);
        }
        for(int i=1;i<=gui.getBoard().getClouds().size();i++)
            for(int j=1;j<=gui.getBoard().getClouds().get(i-1).getStudents().size();j++){
                getImageViewFromString("cloud"+i+"Student"+j).setImage(new Image(getImageFromStudent(gui.getBoard().getClouds().get(i-1).getStudents().get(j-1))));
            }
        if(gui.getBoard().getClouds().size()==2) {
            getPaneFromString("cloud" + 3 + "Pane").setVisible(false);
            getPaneFromString("cloud"+3+"Pane").setDisable(true);
        }
        for (CloudView cloud : gui.getBoard().getClouds()) {
            if (cloud.isChoosen()) {
                getPaneFromString("cloud"+ cloud.getCloudID() + "Pane").setDisable(true);
                getPaneFromString("cloud"+ cloud.getCloudID() + "Pane").setOpacity(0.3);
            } else {
                getPaneFromString("cloud"+ cloud.getCloudID() + "Pane").setDisable(true);
                getPaneFromString("cloud"+ cloud.getCloudID() + "Pane").setOpacity(1);
            }
        }
    }

    public void setMotherNatureView(int num,int motherNatureIsland){
        //int j=0;
        int k;
        //System.out.println("Sono qua amico");
        for(int i=0; i<num ; i++) {
            k = ((motherNatureIsland + i) % 12) + 1;
            getPaneFromString("island"+k+"Pane").setDisable(false);
        }
        /*for(int i=motherNatureIsland;i<=gui.getBoard().getIslandViews().size() && i<=num;i++){
            getPaneFromString("island"+i+"Pane").setDisable(false);
            j++;
        }
        for(int k=1;k<=num-j;k++)
            getPaneFromString("island"+k+"Pane").setDisable(false);

         */
    }

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

    public String getImageFromTower(Tower tower){
        String s="";
        switch (tower){
            case WHITE -> {
                s="img/TOWER_WHITE.png";
            }
            case BLACK -> {
                s="img/TOWER_BLACK.png";
            }
            case GREY -> {
                s="img/TOWER_GEY.png";
            }
        }
        return s;
    }

    public ImageView getImageViewFromString(String s){
        Object imageview = null;
        try{
            imageview=getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((ImageView) imageview); 
    }

    public Pane getPaneFromString(String s){
        Object imageview = null;
        try{
            imageview=getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((Pane) imageview);
    }

    public Label getLabelFromString(String s){
        Object imageview = null;
        try{
            imageview=getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((Label) imageview);
    }

    public void setEntranceStudentClickable(){
        plancePane.setDisable(false);
        for(int j=1 ; j<=gui.getPlayer().getPlance().getEntrance().size(); j++) {
            getImageViewFromString("entranceStudent" + j).setDisable(false);
            //entranceView.get(j).setImage(new Image(getImageFromStudent(gui.getPlayer().getPlance().getEntrance().get(j))));
            //entranceView.get(j).setVisible(true);
            //entranceView.get(j).setDisable(false);

        }
    }

    public void setEntranceStudentNotClickable(){
        for(int j=1 ; j<=gui.getPlayer().getPlance().getEntrance().size(); j++) {
            //entranceView.get(j).setVisible(true);
            //entranceView.get(j).setDisable(true);
            getImageViewFromString("entranceStudent" + j).setDisable(true);
        }
    }

    public void setGameUpdateLabel(String s){
        gameUpdateLabel.setText(s);
    }

    public void setPlayerStateLabel(PlayerState playerState){
        if(playerState==PlayerState.WAITING || playerState==PlayerState.RECONNECTED || playerState==PlayerState.DISCONNECTED)
            playerStateLabel.setText(playerState.toString());
        else playerStateLabel.setText("PLAYING");
    }

    public int numOfColorStudent(Student student,ArrayList<Student> students){
        int i=0;
        for(Student stud : students)
            if(stud.equals(student))
                i++;
        return i;
    }

    public void setArchipelagoClickable(){
        for(int i=1; i<=gui.getBoard().getIslandViews().size();i++)
            getPaneFromString("island"+i+"Pane").setDisable(false);
    }

    public void setCloudsClickable(){
        for(int count=1;count<=gui.getBoard().getClouds().size();count++){
            if(!gui.getBoard().getClouds().get(count-1).isChoosen())
                getPaneFromString("cloud"+count+"Pane").setDisable(false);
        }
    }

    public void choseCharacter1Student1(MouseEvent mouseEvent) {
    }

    public void choseCharacter1Student2(MouseEvent mouseEvent) {
    }

    public void choseCharacter1Student3(MouseEvent mouseEvent) {
    }

    public void choseCharacter1Student4(MouseEvent mouseEvent) {
    }

    public void choseCharacter1Student5(MouseEvent mouseEvent) {
    }

    public void choseCharacter1Student6(MouseEvent mouseEvent) {
    }

    public void choseCharacter2Student1(MouseEvent mouseEvent) {
    }

    public void choseCharacter2Student2(MouseEvent mouseEvent) {
    }

    public void choseCharacter2Student3(MouseEvent mouseEvent) {
    }

    public void choseCharacter2Student4(MouseEvent mouseEvent) {
    }

    public void choseCharacter2Student5(MouseEvent mouseEvent) {
    }

    public void choseCharacter2Student6(MouseEvent mouseEvent) {
    }

    public void choseCharacter3Student1(MouseEvent mouseEvent) {
    }

    public void choseCharacter3Student2(MouseEvent mouseEvent) {
    }

    public void choseCharacter3Student3(MouseEvent mouseEvent) {
    }

    public void choseCharacter3Student4(MouseEvent mouseEvent) {
    }

    public void choseCharacter3Student5(MouseEvent mouseEvent) {
    }

    public void choseCharacter3Student6(MouseEvent mouseEvent) {
    }

    public void choseCharacter1(MouseEvent mouseEvent) {
    }

    public void choseCharacter2(MouseEvent mouseEvent) {
    }

    public void choseCharacter3(MouseEvent mouseEvent) {
    }
}
