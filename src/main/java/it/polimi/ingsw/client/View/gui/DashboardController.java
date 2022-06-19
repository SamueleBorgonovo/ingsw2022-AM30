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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Class DashboardController controls the scene of the game
 */
public class DashboardController {

    private GUI gui;
    private Student studentToMove;
    private int numOfStudentChosen;
    private boolean characterButtonClicked = false;
    private CharacterView characterPlayed;
    private int numOfCharacterPlayed;
    private boolean exitButton = false;
    int numEffect7=0;
    int numEffect10=0;
    ArrayList<Student> effect7Students = new ArrayList<>();
    ArrayList<Student> effect10Students = new ArrayList<>();


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

    /**
     * Method setGui sets an instance of the gui
     * @param gui the instance of the gui to set
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * Method setup sets the first view of the game
     */
    public void setup() {
        getImageViewFromString("entranceStudent8").setVisible(false);
        getImageViewFromString("entranceStudent8").setDisable(true);
        getImageViewFromString("entranceStudent9").setVisible(false);
        getImageViewFromString("entranceStudent9").setDisable(true);
        getImageViewFromString("planceTower7").setVisible(false);
        getImageViewFromString("planceTower8").setVisible(false);

        coinPane.setVisible(false);
        characterButtor.setVisible(false);
        characterButtonLabel.setVisible(false);
        if (gui.getClient().getGamemode() == GameMode.EXPERTMODE) {
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

    /**
     * Method setupChoiceBox sets the choice box of the players' plance
     */
    public void setupChoiceBox() {
            for(int i=0;i<gui.getPlayers().size();i++)
                showPlanceChoiceBox.getItems().remove(gui.getPlayers().get(i).getNickname()+" plance");
            for(int i=0;i<gui.getPlayers().size();i++)
                showPlanceChoiceBox.getItems().add(gui.getPlayers().get(i).getNickname()+" plance");

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

    /**
     * Method setupChoiceBoxAssistantPhase sets the choice box during the assistant phase
     * @param isPhase true if is assistant phase, false otherwise
     */
    public void setupChoiceBoxAssistantPhase(boolean isPhase) {
        if (isPhase) {
            showPlanceChoiceBox.setDisable(true);
            showPlanceChoiceBox.setOpacity(0.3);
        } else {
            showPlanceChoiceBox.setDisable(false);
            showPlanceChoiceBox.setOpacity(1);
        }
    }

    /**
     * Method setBackgroundImage sets the background image of the player
     * @param wizard wizard chosen by the player
     */
    public void setBackgroundImage(Wizard wizard){
        backgroundImage.setImage(new Image("img/" + wizard.toString() + "_BACKGROUND.jpg"));
    }

    /* Setup of the game */

    /**
     * Method setupPlayerView sets the view of the player chosen
     * @param player player to set the view
     */
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

    /**
     * Method setupArchipelago sets the view of the archipelago
     */
    public void setupArchipelago() {
        //Set islands
        int i;
        for (i = 1; i <= gui.getBoard().getIslandViews().size(); i++) {
            getPaneFromString("island" + i + "Pane").setVisible(true);
            getPaneFromString("island" + i + "Pane").setDisable(true);
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

    /**
     * Method setupPlance sets the plance of the player chosen
     * @param player player to set the view
     */
    public void setupPlance(PlayerView player) {
        coinLabel.setText(String.valueOf(gui.getPlayer().getCoins()));

        //Set entrance
        int i;
        for (i = 1; i <= player.getPlance().getEntrance().size(); i++) {
            getImageViewFromString("entranceStudent" + i).setImage(new Image(getImageFromStudent(player.getPlance().getEntrance().get(i - 1))));
            getImageViewFromString("entranceStudent" + i).setVisible(true);
            getImageViewFromString("entranceStudent" + i).setOpacity(1);
            getImageViewFromString("entranceStudent" + i).setDisable(true);
        }

        for (int count=i; count < 10; count++) {
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
        if (gui.getClient().getNumofPlayers() == 2)
            num = 8;
        if (gui.getClient().getNumofPlayers() == 3)
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

        //set Characters
        for(int k=1; k<=3; k++)
            for(int j=1; j<=6; j++)
                getImageViewFromString("character" + k + "Student" + j).setDisable(true);

        for(int k=1; k<=3; k++)
            for(int j=1; j<=4; j++)
                getImageViewFromString("character" + k + "Stop" + j).setDisable(true);
    }

    /**
     * Method setupClouds sets the view of clouds
     */
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

    /**
     * Method setupCharacterView sets the view of characters
     */
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

    /**
     * Method setGameUpdateLabel sets the text contained in gameUpdateLabel
     * @param s Text to set
     */
    public void setGameUpdateLabel(String s) {
        gameUpdateLabel.setText(s);
    }

    /**
     * Method setPlayerStateLabel sets the player state of the player
     * @param playerState playerState of the player
     */
    public void setPlayerStateLabel(PlayerState playerState) {
        if (playerState == PlayerState.WAITING || playerState == PlayerState.RECONNECTED || playerState == PlayerState.DISCONNECTED)
            playerStateLabel.setText(playerState.toString());
        else playerStateLabel.setText("PLAYING");
    }

    /* Control of the assistant view Section */

    /**
     * Method setAssistantView sets the assistant view
     */
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

    /**
     * Method chosenAssistant handles the choice of an assistant
     * @param assistant assistant chosen
     */
    public void chosenAssistant (Assistant assistant){
        plancePane.setVisible(true);
        assistantsPane.setVisible(false);
        plancePane.setDisable(false);
        assistantsPane.setDisable(true);
        setupChoiceBoxAssistantPhase(false);
        gui.getClient().sendMessage(new ChooseAssistantMessage(assistant));
    }

    /* Button of assistant section */

    /**
     * Method chosenAssistantLion is called when the player choice the Lion assistant
     */
    public void chosenAssistantLion() {
        chosenAssistant(Assistant.LION);
    }

    /**
     * Method chosenAssistantOstrich is called when the player choice the Ostrich assistant
     */
    public void chosenAssistantOstrich() {
        chosenAssistant(Assistant.OSTRICH);
    }

    /**
     * Method chosenAssistantCat is called when the player choice the Cat assistant
     */
    public void chosenAssistantCat() {
        chosenAssistant(Assistant.CAT);
    }

    /**
     * Method chosenAssistantEagle is called when the player choice the Eagle assistant
     */
    public void chosenAssistantEagle() {
        chosenAssistant(Assistant.EAGLE);
    }

    /**
     * Method chosenAssistantFox is called when the player choice the Fox assistant
     */
    public void chosenAssistantFox() {
        chosenAssistant(Assistant.FOX);
    }

    /**
     * Method chosenAssistantSnake is called when the player choice the Snake assistant
     */
    public void chosenAssistantSnake() {
        chosenAssistant(Assistant.SNAKE);
    }

    /**
     * Method chosenAssistantOctopus is called when the player choice the Octopus assistant
     */
    public void chosenAssistantOctopus() {chosenAssistant(Assistant.OCTOPUS);}

    /**
     * Method chosenAssistantDog is called when the player choice the Dog assistant
     */
    public void chosenAssistantDog() {
        chosenAssistant(Assistant.DOG);
    }

    /**
     * Method chosenAssistantElephants is called when the player choice the Elephants assistant
     */
    public void chosenAssistantElephants() {
        chosenAssistant(Assistant.ELEPHANTS);
    }

    /**
     * Method chosenAssistantTurtle is called when the player choice the Turtle assistant
     */
    public void chosenAssistantTurtle() {
        chosenAssistant(Assistant.TURTLE);
    }

    /* Control of Cloud Section */

    /**
     * Method chosenCloud handles the choice of a cloud
     * @param cloudID cloudID of che cloud chosen
     */
    public void chosenCloud(int cloudID){
        ChooseCloudMessage message = new ChooseCloudMessage(cloudID);
        gui.getClient().sendMessage(message);
        this.characterPlayed=null;
        gui.setCharacter4played(false);
        numEffect7=0;
        numEffect10=0;
        effect7Students.clear();
        effect10Students.clear();
    }

    /* Button of Cloud Section */

    /**
     * Method chosenCloud1 is called when the player choice the first cloud
     */
    public void chosenCloud1() {
        chosenCloud(1);
    }

    /**
     * Method chosenCloud2 is called when the player choice the second cloud
     */
    public void chosenCloud2() {
        chosenCloud(2);
    }

    /**
     * Method chosenCloud3 is called when the player choice the third cloud
     */
    public void chosenCloud3() {
        chosenCloud(3);
    }


    /* Control of Entrance Section */

    /**
     * Method chosenEntranceStudent handles the choice of a student in entrance
     * @param studentNumber the number of the student chosen
     */
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

    /**
     * Method setEntranceStudentClickable sets the students in entrance clickable
     */
    public void setEntranceStudentClickable() {
        plancePane.setDisable(false);
        for (int j = 1; j <= gui.getPlayer().getPlance().getEntrance().size(); j++)
            getImageViewFromString("entranceStudent" + j).setDisable(false);
    }

    /**
     * Method setEntranceStudentNotClickable sets the students in entrance not clickable
     */
    public void setEntranceStudentNotClickable() {
        for (int j = 1; j <= gui.getPlayer().getPlance().getEntrance().size(); j++)
            getImageViewFromString("entranceStudent" + j).setDisable(true);
    }

    /* Button of Entrance Section */

    /**
     * Method chosenEntranceStudent1 is called when the player choice the first student in entrance
     */
    public void chosenEntranceStudent1() {
        chosenEntranceStudent(0);
    }

    /**
     * Method chosenEntranceStudent2 is called when the player choice the second student in entrance
     */
    public void chosenEntranceStudent2() {
        chosenEntranceStudent(1);
    }

    /**
     * Method chosenEntranceStudent3 is called when the player choice the third student in entrance
     */
    public void chosenEntranceStudent3() {
        chosenEntranceStudent(2);
    }

    /**
     * Method chosenEntranceStudent4 is called when the player choice the fourth student in entrance
     */
    public void chosenEntranceStudent4() {
        chosenEntranceStudent(3);
    }

    /**
     * Method chosenEntranceStudent5 is called when the player choice the fifth student in entrance
     */
    public void chosenEntranceStudent5() {
        chosenEntranceStudent(4);
    }

    /**
     * Method chosenEntranceStudent6 is called when the player choice the sixth student in entrance
     */
    public void chosenEntranceStudent6() {
        chosenEntranceStudent(5);
    }

    /**
     * Method chosenEntranceStudent7 is called when the player choice the seventh student in entrance
     */
    public void chosenEntranceStudent7() {
        chosenEntranceStudent(6);
    }

    /**
     * Method chosenEntranceStudent8 is called when the player choice the eighth student in entrance
     */
    public void chosenEntranceStudent8() {
        chosenEntranceStudent(7);
    }

    /**
     * Method chosenEntranceStudent9 is called when the player choice the ninth student in entrance
     */
    public void chosenEntranceStudent9() {
        chosenEntranceStudent(8);
    }

    /* Control of Hall section */

    /**
     * Method chosenHallColor is called when the player choice a color of the hall
     * @param studentColor the student color that player has chosen
     */
    public void chosenHallColor (Student studentColor){
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

    /* Button of Hall Section */

    /**
     * Method chosenHall is called when the player clicks on the hall when has to move a student from entrance
     */
    public void chosenHall() {
        if(gui.getCurrentPlayerState()==PlayerState.STUDENTPHASE) {
            numOfStudentChosen++;
            getImageViewFromString("entranceStudent" + numOfStudentChosen).setVisible(false);
            getImageViewFromString("entranceStudent" + numOfStudentChosen).setDisable(true);
            MoveStudentToHallMessage message = new MoveStudentToHallMessage(studentToMove);
            gui.getClient().sendMessage(message);
        }
    }

    /**
     *Method chosenHallGreen is called when the player choice the green color
     */
    public void chosenHallGreen() {
        chosenHallColor(Student.GREEN);
    }

    /**
     *Method chosenHallRed is called when the player choice the red color
     */
    public void chosenHallRed() {
        chosenHallColor(Student.RED);
    }

    /**
     *Method chosenHallYellow is called when the player choice the yellow color
     */
    public void chosenHallYellow() {
        chosenHallColor(Student.YELLOW);
    }

    /**
     *Method chosenHallPink is called when the player choice the pink color
     */
    public void chosenHallPink() {
        chosenHallColor(Student.PINK);
    }

    /**
     *Method chosenHallBlue is called when the player choice the blue color
     */
    public void chosenHallBlue() {
        chosenHallColor(Student.BLUE);
    }

    /* Control of Character Section */

    /**
     * Method setCharacterButtonClicked sets the Character button clickable
     */
    public void setCharacterButtonClickable() {
        characterButtor.setDisable(false);
        characterButtor.setOpacity(1);
        characterButtonLabel.setDisable(false);
    }

    /**
     * Method setCharacterButtonNotClicked sets the Character button not clickable
     */
    public void setCharacterButtonNotClickable() {
        characterButtor.setDisable(true);
        characterButtor.setOpacity(0.3);
        characterButtonLabel.setDisable(true);
    }

    /**
     * Method resetCharacterButton reset the Character button
     */
    public void resetCharacterButton(){
        characterButtonLabel.setText("PLAY CHARACTER");
        characterButtonClicked=false;
    }

    /**
     * Method setStudentsOnCharacter sets the view of students in character cards
     * @param index the index of the character to set
     */
    public void setStudentsOnCharacter(int index) {
        EffectHandler handler = gui.getEffectHandler();
        switch (gui.getBoard().getCharacters().get(index - 1).getName()) {
            case "MONK" -> {
                getGridPaneFromString("character" + index + "StudentsPane").setVisible(true);
                for (int i = 0; i < handler.getEffect1students().size(); i++) {
                    getImageViewFromString("character" + index + "Student" + (i + 1)).setImage(new Image(getImageFromStudent(handler.getEffect1students().get(i))));
                }
            }
            case "FARMER", "HERALD", "POSTMAN", "CENTAUR", "KNIGHT", "FUNGARO", "MINSTREL", "PICAROON" -> getGridPaneFromString("character" + index + "StudentsPane").setVisible(false);

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

    public void setStudentsCardNotClickable(){
        for(int i=1; i<=6 ; i++)
            getImageViewFromString("character" + numOfCharacterPlayed + "Student" + i).setDisable(true);
    }

    /* Button of Character Section */

    /**
     * Method clickCharacterButton is called when the player clicks the CharacterButton
     */
    public void clickCharacterButton() {
        if(exitButton){
            System.exit(1);
        }else {
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
    }

    /**
     * Method choseCharacter1Student1 is called when the player choice the first student of the first character
     */
    public void choseCharacter1Student1() {
        choseStudentCard1to4(0,1);
    }

    /**
     * Method choseCharacter1Student2 is called when the player choice the second student of the first character
     */
    public void choseCharacter1Student2() {
        choseStudentCard1to4(1,1);
    }

    /**
     * Method choseCharacter1Student3 is called when the player choice the third student of the first character
     */
    public void choseCharacter1Student3() {
        choseStudentCard1to4(2,1);
    }

    /**
     * Method choseCharacter1Student4 is called when the player choice the fourth student of the first character
     */
    public void choseCharacter1Student4() {
        choseStudentCard1to4(3,1);
    }

    /**
     * Method choseCharacter1Student5 is called when the player choice the fifth student of the first character
     */
    public void choseCharacter1Student5() {
        choseStudent5or6(4,1);
    }

    /**
     * Method choseCharacter1Student6 is called when the player choice the sixth student of the first character
     */
    public void choseCharacter1Student6() {
        choseStudent5or6(5,1);
    }

    /**
     * Method choseCharacter2Student1 is called when the player choice the first student of the second character
     */
    public void choseCharacter2Student1() {
        choseStudentCard1to4(0,2);
    }

    /**
     * Method choseCharacter2Student2 is called when the player choice the second student of the second character
     */
    public void choseCharacter2Student2() {
        choseStudentCard1to4(2,2);
    }

    /**
     * Method choseCharacter2Student3 is called when the player choice the third student of the second character
     */
    public void choseCharacter2Student3() {
        choseStudentCard1to4(2,2);
    }

    /**
     * Method choseCharacter2Student4 is called when the player choice the fourth student of the second character
     */
    public void choseCharacter2Student4() {
        choseStudentCard1to4(3,2);
    }

    /**
     * Method choseCharacter2Student5 is called when the player choice the fifth student of the second character
     */
    public void choseCharacter2Student5() {
        choseStudent5or6(4,2);
    }

    /**
     * Method choseCharacter2Student6 is called when the player choice the sixth student of the second character
     */
    public void choseCharacter2Student6() {
        choseStudent5or6(5,2);
    }

    /**
     * Method choseCharacter3Student1 is called when the player choice the first student of the third character
     */
    public void choseCharacter3Student1() {
        choseStudentCard1to4(0,3);
    }

    /**
     * Method choseCharacter3Student2 is called when the player choice the second student of the third character
     */
    public void choseCharacter3Student2() {
        choseStudentCard1to4(1,3);
    }

    /**
     * Method choseCharacter3Student3 is called when the player choice the third student of the third character
     */
    public void choseCharacter3Student3() {
        choseStudentCard1to4(2,3);
    }

    /**
     * Method choseCharacter3Student4 is called when the player choice the fourth student of the third character
     */
    public void choseCharacter3Student4() {
        choseStudentCard1to4(3,3);
    }

    /**
     * Method choseCharacter3Student5 is called when the player choice the fifth student of the third character
     */
    public void choseCharacter3Student5() {
        choseStudent5or6(4,3);
    }

    /**
     * Method choseCharacter3Student6 is called when the player choice the sixth student of the third character
     */
    public void choseCharacter3Student6() {
        choseStudent5or6(5,3);
    }

    /**
     * Method choseCharacter1 is called when the player choice the first character
     */
    public void choseCharacter1() {
        choseCharacter(1);
    }

    /**
     * Method choseCharacter2 is called when the player choice the second character
     */
    public void choseCharacter2() {
        choseCharacter(2);
    }

    /**
     * Method choseCharacter3 is called when the player choice the third character
     */
    public void choseCharacter3() {
        choseCharacter(3);
    }

    /* Control of Islands Section */

    /**
     * Method chosenIsland handles the choice of an island
     * @param islandID islandID of the island
     */
    public void chosenIsland(int islandID){
        if (gui.getCurrentPlayerState() == PlayerState.STUDENTPHASE) {
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

    /**
     * Method setMotherNatureView sets the right islands clickable during motherNature phase
     * @param num number of possible motherNature's movement
     * @param motherNatureIsland islandID of the island where motherNature is
     */
    public void setMotherNatureView(int num, int motherNatureIsland) {
        int k;
        for (int i = 0; i < num; i++) {
            k = ((motherNatureIsland + i) % gui.getBoard().getIslandViews().size()) + 1;
            getPaneFromString("island" + k + "Pane").setDisable(false);
        }
    }

    /**
     * Method setArchipelagoClickable sets the archipelago clickable
     */
    public void setArchipelagoClickable() {
        for (int i = 1; i <= gui.getBoard().getIslandViews().size(); i++)
            getPaneFromString("island" + i + "Pane").setDisable(false);
    }

    /**
     * Method setCloudsClickable sets the clouds clickable
     */
    public void setCloudsClickable() {
        for (int count = 1; count <= gui.getBoard().getClouds().size(); count++) {
            if (!gui.getBoard().getClouds().get(count - 1).isChoosen())
                getPaneFromString("cloud" + count + "Pane").setDisable(false);
        }
    }

    /* Button of Islands Section */

    /**
     * Method chosenIsland1 is called when the payer choice the first island
     */
    public void chosenIsland1() {
        chosenIsland(1);
    }

    /**
     * Method chosenIsland2 is called when the payer choice the second island
     */
    public void chosenIsland2() {
        chosenIsland(2);
    }

    /**
     * Method chosenIsland3 is called when the payer choice the third island
     */
    public void chosenIsland3() {
        chosenIsland(3);
    }

    /**
     * Method chosenIsland4 is called when the payer choice the fourth island
     */
    public void chosenIsland4() {
        chosenIsland(4);
    }

    /**
     * Method chosenIsland5 is called when the payer choice the fifth island
     */
    public void chosenIsland5() {
        chosenIsland(5);
    }

    /**
     * Method chosenIsland6 is called when the payer choice the sixth island
     */
    public void chosenIsland6() {
        chosenIsland(6);
    }

    /**
     * Method chosenIsland7 is called when the payer choice the seventh island
     */
    public void chosenIsland7() {
        chosenIsland(7);
    }

    /**
     * Method chosenIsland8 is called when the payer choice the eighth island
     */
    public void chosenIsland8() {
        chosenIsland(8);
    }

    /**
     * Method chosenIsland9 is called when the payer choice the ninth island
     */
    public void chosenIsland9() {
       chosenIsland(9);
    }

    /**
     * Method chosenIsland10 is called when the payer choice the tenth island
     */
    public void chosenIsland10() {
        chosenIsland(10);
    }

    /**
     * Method chosenIsland11 is called when the payer choice the eleventh island
     */
    public void chosenIsland11() {
        chosenIsland(11);
    }

    /**
     * Method chosenIsland12 is called when the payer choice the twelfth island
     */
    public void chosenIsland12() {
        chosenIsland(12);
    }


    ////////USEFUL METHODS

    /**
     * Method setExitButton sets the button to close the game
     */
    public void setExitButton(){
        exitButton=true;
        characterButtor.setVisible(true);
        characterButtor.setDisable(false);
        characterButtonLabel.setVisible(true);
        characterButtonLabel.setDisable(false);
        characterButtonLabel.setText("EXIT");
    }

    /**
     * Method winner sets the winner scene
     * @param nicknames nicknames of the players that won
     */
    public void winner(ArrayList<String> nicknames){
        cloudsPane.setVisible(false);
        cloudsPane.setDisable(true);
        winnerPane.setVisible(true);
        winnerNicknameLabel.setText(nicknames.get(0));
    }

    /**
     * Method getImageFromStudent return the path of the student image
     * @param student the student to get the image
     * @return the path of the student image chosen
     */
    public String getImageFromStudent(Student student) {
        return "img/STUDENT_"+student.getText(student).toUpperCase()+".png";
    }

    /**
     * Method getImageFromTower return the path of the tower image
     * @param tower the tower to get the image
     * @return the path of the tower image chosen
     */
    public String getImageFromTower(Tower tower) {
        return "img/TOWER_"+tower.toString()+".png";
    }

    /**
     * Method getImageViewFromString returns the imageView attribute of the string requested
     * @param s name of the imageView to get
     * @return the imageView attribute of the string requested
     */
    public ImageView getImageViewFromString(String s) {
        Object imageview = null;
        try {
            imageview = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((ImageView) imageview);
    }

    /**
     * Method getPaneFromString returns the Pane attribute of the string requested
     * @param s name of the Pane to get
     * @return the Pane attribute of the string requested
     */
    public Pane getPaneFromString(String s) {
        Object imageview = null;
        try {
            imageview = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((Pane) imageview);
    }

    /**
     * Method getLabelFromString returns the Label attribute of the string requested
     * @param s name of the Label to get
     * @return the Label attribute of the string requested
     */
    public Label getLabelFromString(String s) {
        Object imageview = null;
        try {
            imageview = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((Label) imageview);
    }

    /**
     * Method getGridPaneFromString returns the GridPane attribute of the string requested
     * @param s name of the GridPane to get
     * @return the GridPane attribute of the string requested
     */
    public GridPane getGridPaneFromString(String s) {
        Object gridPane = null;
        try {
            gridPane = getClass().getDeclaredField(s).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ((GridPane) gridPane);
    }

    /**
     * Method numOfColorStudent is used to get the number of a student type in an arrayList
     * @param student student type to get the number
     * @param students arrayList to check
     * @return the number of a student type in the arrayList
     */
    public int numOfColorStudent(Student student, ArrayList<Student> students) {
        int i = 0;
        for (Student stud : students)
            if (stud.equals(student))
                i++;
        return i;
    }
}