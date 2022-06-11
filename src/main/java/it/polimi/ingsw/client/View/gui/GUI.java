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
import it.polimi.ingsw.model.player.Player;
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
    private boolean correctlyConnected=false;
    private PlayerView currentPlayerView;
    private boolean character4played=false;
    private PlayerState currentPlayerState;
    private Wizard wizard;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage=primaryStage;
        instantiateConnectionScene();
    }

    public void setWizard(Wizard wizard){
        this.wizard=wizard;
    }

    public Client getClient() {
        return client;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        client.setNickname(nickname);
    }

    public PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setCurrentPlayerView(PlayerView player){currentPlayerView=player;}

    public String getNickname() {
        return nickname;
    }

    public BoardView getBoard() {
        return board;
    }

    public EffectHandler getEffectHandler(){return effectHandler;}

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

    private void createScene(String pathOfFxmlFile, FunctionInterface functionInterface) {
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
        createScene("/Connect_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            connectScene = fxmlLoader.getController();
            connectScene.setGui(this);
        });
    }

    public void instantiateNicknameScene(){
        createScene("/Nickname_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            nicknameScene = fxmlLoader.getController();
            nicknameScene.setReconnectButton(false);
            nicknameScene.moveLoginButtonX(110);
            nicknameScene.setGui(this);
        });
    }

    public void instantiateGameSettingsScene(){
        createScene("/Game_Settings_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            gameSettingsScene = fxmlLoader.getController();
            gameSettingsScene.setGui(this);
        });
    }

    public void instantiateWizardScene(ArrayList<Wizard> availableWizards){
        createScene("/Wizard_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            wizardScene = fxmlLoader.getController();
            wizardScene.setGui(this);
            wizardScene.setWizards(availableWizards);
        });
    }

    public void instantiateDashBoardScene(){
        createScene("/Eriantys_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            dashboardController = fxmlLoader.getController();
            dashboardController.setGui(this);
            dashboardController.setBackgroundImage(getClient().getWizard());
            dashboardController.setup();
        });
    }

    public boolean createClient(String ip, int port) {
        client = new Client(ip,port,this);
        try {
            return client.setupConnection();
        }
        catch (Exception e){}
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

    public void chooseStudentToMove(){
        Platform.runLater(() -> {
            dashboardController.setupPlance(player);
            dashboardController.setEntranceStudentClickable();
        });
    }

    ///////////////////////////////////////////////////////////////////
    //View methods                                                   //
    ///////////////////////////////////////////////////////////////////
    @Override
    public void chooseNickname(boolean validNickname, boolean reconnect) {
        Platform.runLater(() -> {
            if(!validNickname)
                nicknameScene.setWrongNickname(true);
            else if(reconnect) {
                nicknameScene.setReconnectButton(true);
                nicknameScene.moveLoginButtonX(-10);
            }
            else
                client.gameSetup();
        });
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
        Platform.runLater(() -> dashboardController.setAssistantView());
    }

    public void setCharacter4played(boolean character4played) {
        this.character4played = character4played;
    }

    @Override
    public void moveMotherNature() {
        int num=0;
        if(character4played)
            num=2;
        int finalNum = num+player.getLastassistantplayed().getMovement();
        Platform.runLater(() -> dashboardController.setMotherNatureView(finalNum,getBoard().getMotherNature()));
        character4played = false;//////////////////////////////////////////
    }

    @Override
    public void chooseCloud() {
        Platform.runLater(() -> dashboardController.setCloudsClickable());
    }


    @Override
    public void setPlayers(ArrayList<PlayerView> players) {
        this.players.clear();
        this.players.addAll(players);
        for (PlayerView play : players) {
            if (play.getNickname().equals(client.getNickname()))
                player = play;
            if(currentPlayerView!=null)
                if(play.getNickname().equals(currentPlayerView.getNickname()))
                     currentPlayerView=play;
        }

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
    public void printStartGame() {
        Platform.runLater(() -> {
            dashboardController.setGameUpdateLabel("GAME: Game is starting!");
            dashboardController.setupChoiceBox(null);
        });
    }

    @Override
    public void winner(ArrayList<String> nicknamesWinner) {

    }

    @Override
    public void printAssistantChosen(String nick, Assistant assistant) {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: "+nick+" choosed assistant "+assistant));
    }

    @Override
    public void printTurn(String nick,boolean isAssistantPhase) {
        if(!nick.equals(nickname)) {
            currentPlayerState = PlayerState.WAITING;
            Platform.runLater(() -> dashboardController.setCharacterButtonNotClicked());
        }
        Platform.runLater(() -> {
            dashboardController.setupPlance(player);
            if(isAssistantPhase)
                dashboardController.setGameUpdateLabel("GAME: "+nick+" is choosing an assistant");
            else dashboardController.setGameUpdateLabel("GAME: "+nick+" started his turn");
        });
    }

    @Override
    public void printCharacterChosen(String nick, CharacterView character) {
        if(!nick.equals(nickname)){
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: "+nick+" played character "+character.getName()));
        }else{
            Platform.runLater(() -> dashboardController.resetCharacterButton());
        }
    }

    @Override
    public void printCloudChosen(String nick, int cloudID) {
        Platform.runLater(() -> {
            dashboardController.setupPlance(currentPlayerView);
            dashboardController.setupArchipelago();
            if (!nick.equals(nickname)) {
                dashboardController.setGameUpdateLabel("GAME: " + nick + " choosed cloud " + cloudID);
            }
        });
    }

    @Override
    public void printStudentToHall(String nick, Student student) {
        Platform.runLater(() -> {
            if(!nick.equals(nickname)) {
                dashboardController.setGameUpdateLabel("GAME: " + nick + " moved student " + student + " in his hall");
                dashboardController.setupPlance(currentPlayerView);
            }
        });
    }

    @Override
    public void printStudentToIsland(String nick, Student student, int IslandID) {
        Platform.runLater(() -> {
            if (!nick.equals(nickname)) {
                dashboardController.setupPlance(currentPlayerView);
                dashboardController.setupArchipelago();
                dashboardController.setGameUpdateLabel("GAME: " + nick + " moved student " + student + " on island " + IslandID);
            }
        });
    }

    @Override
    public void printMotherNatureMovement(String nick, int islandID) {
        Platform.runLater(() -> {
            if (!nick.equals(nickname)) {
                dashboardController.setupArchipelago();
                dashboardController.setupPlance(currentPlayerView);
                dashboardController.setGameUpdateLabel("GAME: " + nick + " moved MotherNature on island " + islandID);
            }
        });
    }

    @Override
    public void printPlayerDisconnection(String nick) {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: "+nick+" disconnected"));
    }

    @Override
    public void printPlayerConnection(String nick, boolean reconnect) {
        if(correctlyConnected) {
            Platform.runLater(() -> {
                if (reconnect)
                    dashboardController.setGameUpdateLabel("GAME: " + nick + " reconnected");
                else dashboardController.setGameUpdateLabel("GAME: " + nick + " connected to the game");
            });
        }
    }
    public void printInvalidAssistant(){
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid assistant"));
    }

    @Override
    public void printInvalidCloud() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid cloud"));
    }

    @Override
    public void printInvalidIsland() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid island"));
    }

    @Override
    public void printInvalidStudent() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid student"));
    }

    @Override
    public void printInvalidTurn() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Is not your turn!"));
    }

    @Override
    public void printInvalidStop() {

    }

    @Override
    public void printInvalidWizard() {
        Platform.runLater(() -> wizardScene.setWrongWizard());
    }

    @Override
    public void printWinnerInstantly(ArrayList<String> nickname, int type) {
        Platform.runLater(() -> dashboardController.winner(nickname));
        if(type == 1)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Only 3 islands remained"));
        if(type==2)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Last tower built"));

    }

    @Override
    public void printWinnerEndRound(ArrayList<String> nickname, int type) {
        Platform.runLater(() -> dashboardController.winner(nickname));
        if(type == 1)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Coins bag empty!"));
        if(type==2)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: All assistant cards has been played"));
    }

    @Override
    public void printWaitingForPlayers(boolean lobby) {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: You are the only remained, timer to win started"));
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
        correctlyConnected=true;
        Platform.runLater(() -> {
            dashboardController.setupArchipelago();
        });

    }

    @Override
    public void nextMove(PlayerState playerState) {
        currentPlayerState=playerState;
            Platform.runLater(() -> {
                dashboardController.setupArchipelago();
                dashboardController.setPlayerStateLabel(currentPlayerState);
            });
        switch (playerState){
            case STUDENTPHASE -> {
                Platform.runLater(() -> {
                    dashboardController.setupPlance(player);
                    dashboardController.setEntranceStudentClickable();
                    dashboardController.setCharacterButtonClicked();
                    dashboardController.setGameUpdateLabel("GAME: Move one student from entrance!");

                });
                chooseStudentToMove();
            }
            case ASSISTANTPHASE -> {
                Platform.runLater(() -> {
                    dashboardController.setupPlance(player);
                    dashboardController.setCharacterButtonNotClicked();
                    dashboardController.setupChoiceBoxAssistantPhase(true);
                    dashboardController.setGameUpdateLabel("GAME: Choose one assistant!");
                });
                chooseAssistant();
            }
            case MOTHERNATUREPHASE -> {
                Platform.runLater(() -> {
                    dashboardController.setupPlance(player);
                    dashboardController.setCharacterButtonClicked();
                    dashboardController.setGameUpdateLabel("GAME: Move MotherNature to an island! MAX:"+player.getLastassistantplayed().getMovement());
                });
                moveMotherNature();
            }
            case CLOUDPHASE -> {
                Platform.runLater(() -> {
                    dashboardController.setupPlance(player);
                    dashboardController.setCharacterButtonClicked();
                    dashboardController.setGameUpdateLabel("GAME: Choose one cloud!");
                });
                chooseCloud();
            }
            case CHARACTHERSTUDENTSPHASE -> {
                System.out.println("Fino a qui ci sei poi non so");
                Platform.runLater(() -> {
                    dashboardController.clickCharacetrButton(null);
                    dashboardController.setCharacterButtonClicked();
                    dashboardController.inputStudentCharacter();
                });
            }
            case CHARACTHERISLANDPHASE -> {
                System.out.println("Fino a qui ci sei poi non so");
                Platform.runLater(() -> {
                    dashboardController.clickCharacetrButton(null);
                    dashboardController.setCharacterButtonClicked();
                    dashboardController.inputIslandCharacter();
                });
            }
        }
    }

}
