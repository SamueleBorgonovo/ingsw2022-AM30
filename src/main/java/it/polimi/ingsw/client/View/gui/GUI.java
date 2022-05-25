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
    Stage primaryStage;
    Client client;
    NicknameController nicknameScene;
    GameSettingsController gameSettingsScene;
    WizardDController wizardScene;
    ConnectController connectScene;
    FXMLLoader fxmlLoader;


    @Override
    public void start(Stage primaryStage){
        this.primaryStage=primaryStage;
        instantiateConnectionScene();
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

    private void instantiateConnectionScene(){
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
            connectScene = fxmlLoader.getController();
            connectScene.setGui(this);


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


    @Override
    public void chooseNickname(boolean validNickname, boolean reconnect) {
        nicknameScene.showNicknameScene(primaryStage, validNickname, reconnect);

    }

    @Override
    public boolean tryToReconnect() {
        return false;
    }

    @Override
    public GameMode chooseGameMode() {
        return null;
    }

    @Override
    public int chooseNumberOfPlayers() {
        return 0;
    }

    @Override
    public void chooseWizard(ArrayList<Wizard> availableWizards) {
        wizardScene.showWizardScene(primaryStage, availableWizards);
    }

    @Override
    public void chooseAssistant() {}

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
    public void chooseCloud() {}

    @Override
    public void useCharacter(PlayerState playerState) {}

    @Override
    public void inputStudentCharacter() {

    }

    @Override
    public void inputIslandCharacter() {

    }

    @Override
    public void setPlayers(ArrayList<PlayerView> players) {}

    @Override
    public void setBoard(BoardView board) {

    }

    @Override
    public void setEffectHandler(EffectHandler effectHandler) {

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
}
