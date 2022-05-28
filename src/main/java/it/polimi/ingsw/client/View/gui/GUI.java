package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.controller.virtualView.BoardView;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.model.game.EffectHandler;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GUI extends Application implements View{
    private Stage primaryStage;
    private Client client;
    private NicknameController nicknameScene;
    private GameSettingsController gameSettingsScene;
    private WizardController wizardScene;
    private ConnectController connectScene;
    private DashboardController dashboardController;
    private FXMLLoader fxmlLoader;
    private String nickname;
    private BoardView board;
    private ArrayList<PlayerView> players = new ArrayList<>();
    private EffectHandler effectHandler;
    private PlayerView player;
    private int numOfPlayers;
    private GameMode gameMode;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage=primaryStage;
        instantiateConnectionScene();
    }

    public Client getClient() {
        return client;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        client.setNickname(nickname);
    }

    public String getNickname() {
        return nickname;
    }

    public BoardView getBoard() {
        return board;
    }

    public ArrayList<PlayerView> getPlayers() {
        return players;
    }

    public PlayerView getPlayer() {
        return player;
    }

    public void resetControllers(){
        nicknameScene=null;
        wizardScene=null;
        connectScene=null;
        dashboardController=null;
        gameSettingsScene=null;
    }

    private void createConnectionScene(String pathOfFxmlFile, FunctionInterface functionInterface) {
        Platform.runLater(() -> {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(pathOfFxmlFile));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
                scene = new Scene(new Label("Error loading the scene"));
            }
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            functionInterface.executeFunction();
        });
    }

    public void instantiateConnectionScene(){
        createConnectionScene("/Connect_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            connectScene = fxmlLoader.getController();
            connectScene.setGui(this);
        });
    }

    private void createNicknameScene(String pathOfFxmlFile, FunctionInterface functionInterface) {
        Platform.runLater(() -> {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(pathOfFxmlFile));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
                scene = new Scene(new Label("Error loading the scene"));
            }
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            functionInterface.executeFunction();
        });
    }

    public void instantiateNicknameScene(){
        createNicknameScene("/Nickname_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            nicknameScene = fxmlLoader.getController();
            nicknameScene.setReconnectButton(false);
            nicknameScene.setGui(this);
        });
    }

    public void instantiateGameSettingsScene(){
        createGameSettingsScene("/Game_Settings_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            gameSettingsScene = fxmlLoader.getController();
            gameSettingsScene.setGui(this);
        });
    }

    private void createGameSettingsScene(String pathOfFxmlFile, FunctionInterface functionInterface) {
        Platform.runLater(() -> {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(pathOfFxmlFile));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
                scene = new Scene(new Label("Error loading the scene"));
            }
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            functionInterface.executeFunction();
        });
    }

    public void instantiateWizardScene(ArrayList<Wizard> availableWizards){
        createWizardScene("/Wizard_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            wizardScene = fxmlLoader.getController();
            wizardScene.setGui(this);
            wizardScene.setWizards(availableWizards);
        });
    }

    private void createWizardScene(String pathOfFxmlFile, FunctionInterface functionInterface) {
        Platform.runLater(() -> {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(pathOfFxmlFile));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
                scene = new Scene(new Label("Error loading the scene"));
            }
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            functionInterface.executeFunction();
        });
    }

    public void instantiateDashBoardScene(){
        createDashboardScene("/Eriantys_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            dashboardController = fxmlLoader.getController();
            dashboardController.setGui(this);
        });
    }

    private void createDashboardScene(String pathOfFxmlFile, FunctionInterface functionInterface) {
        Platform.runLater(() -> {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(pathOfFxmlFile));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
                scene = new Scene(new Label("Error loading the scene"));
            }
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            functionInterface.executeFunction();
        });
    }


    public boolean createClient(String ip, int port) {
        client = new Client(ip,port,this);
        try {
            return client.setupConnection();
        }
        catch (Exception e){

        }
        return false;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    ///////////////////////////////////////////////////////////////////
    //View methods
    ///////////////////////////////////////////////////////////////////
    @Override
    public void chooseNickname(boolean validNickname, boolean reconnect) {
        if(!validNickname)
            nicknameScene.setWrongNickname(true);
        else if(reconnect)
                nicknameScene.setReconnectButton(true);
            else
                client.gameSetup();
    }

    @Override
    public boolean tryToReconnect() {
        return false;
    }

    public void chooseSettings(){
        instantiateGameSettingsScene();
    }

    @Override
    public void chooseWizard(ArrayList<Wizard> availableWizards) {
        instantiateWizardScene(availableWizards);
    }

    @Override
    public void chooseAssistant() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setAssistantView();
            }
        });
    }

    @Override
    public PossibleAction chooseNextAction(PlayerState playerState) {
        return null;
    }

    @Override
    public void moveStudentToHall() {}

    @Override
    public void moveStudentToIsland () {}

    @Override
    public void moveMotherNature() {}

    @Override
    public void chooseCloud() {
        dashboardController.setClouds(board.getClouds());
        dashboardController.showClouds();
    }

    @Override
    public void useCharacter(PlayerState playerState) {}

    @Override
    public void inputStudentCharacter() {

    }

    @Override
    public void inputIslandCharacter() {

    }

    @Override
    public void setPlayers(ArrayList<PlayerView> players) {
        this.players.clear();
        this.players.addAll(players);
        for (PlayerView play : players)
            if (play.getNickname().equals(client.getNickname()))
                player = play;

    }

    @Override
    public void setBoard(BoardView board) {
            this.board = board;
    }

    @Override
    public void setEffectHandler(EffectHandler effectHandler) {
        this.effectHandler=effectHandler;
    }

    @Override
    public void print() {

    }

    @Override
    public void printStartGame() {

    }

    @Override
    public void winner(ArrayList<String> nicknamesWinner) {

    }

    @Override
    public void printAssistantChosen(String nick, Assistant assistant) {

    }

    @Override
    public void printTurn(String nick) {

    }

    @Override
    public void printCharacterChosen(String nick, CharacterView character) {

    }

    @Override
    public void printCloudChosen(String nick, int cloudID) {

    }

    @Override
    public void printStudentToHall(String nick, Student student) {

    }

    @Override
    public void printStudentToIsland(String nick, Student student, int IslandID) {

    }

    @Override
    public void printMotherNatureMovement(String nick, int islandID) {

    }

    @Override
    public void printPlayerDisconnection(String nick) {

    }

    @Override
    public void printPlayerConnection(String nick, boolean reconnect) {

    }
    public void printInvalidAssistant(){

    }

    @Override
    public void printInvalidCloud() {

    }

    @Override
    public void printInvalidIsland() {

    }

    @Override
    public void printInvalidStudent() {

    }

    @Override
    public void printInvalidTurn() {

    }

    @Override
    public void printInvalidStop() {

    }

    @Override
    public void printInvalidWizard() {

    }

    @Override
    public void printWinnerInstantly(ArrayList<String> nickname, int type) {

    }

    @Override
    public void printWinnerEndRound(ArrayList<String> nickname, int type) {

    }

    @Override
    public void printWaitingForPlayers(boolean lobby) {

    }

    @Override
    public void printGameEndedTimeout() {

    }

    @Override
    public void printWinClose() {

    }

    @Override
    public void printConnectionClosed(boolean timeout) {

    }

    @Override
    public void correctlyConnected() {
        resetControllers();
        instantiateDashBoardScene();
    }

    @Override
    public void nextMove(PlayerState playerState) {
        switch (playerState){
            case STUDENTPHASE -> {

            }
            case ASSISTANTPHASE -> {
                chooseAssistant();
            }
            case MOTHERNATUREPHASE -> {

            }
            case CLOUDPHASE -> {

            }
            case CHARACTHERSTUDENTSPHASE -> {

            }
            case CHARACTHERISLANDPHASE -> {

            }
        }
    }

}
