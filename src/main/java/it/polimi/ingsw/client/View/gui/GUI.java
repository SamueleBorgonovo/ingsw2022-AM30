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
            nicknameScene.moveLoginButtonX(110);
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
            dashboardController.setBackgroundImage(getClient().getWizard());
            dashboardController.setup();
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setupPlance(player);
                dashboardController.setEntranceStudentClickable();
            }
        });

    }

    ///////////////////////////////////////////////////////////////////
    //View methods                                                   //
    ///////////////////////////////////////////////////////////////////
    @Override
    public void chooseNickname(boolean validNickname, boolean reconnect) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(!validNickname)
                    nicknameScene.setWrongNickname(true);
                else if(reconnect) {
                    nicknameScene.setReconnectButton(true);
                    nicknameScene.moveLoginButtonX(-10);
                }
                else
                    client.gameSetup();
            }
        });
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

    public void setCharacter4played(boolean character4played) {
        this.character4played = character4played;
    }

    @Override
    public void moveMotherNature() {
        int num=0;
        if(character4played)
            num=2;
        int finalNum = num+player.getLastassistantplayed().getValue();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setMotherNatureView(finalNum,getBoard().getMotherNature());
            }
        });
        character4played = false;//////////////////////////////////////////
    }

    @Override
    public void chooseCloud() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setCloudsClickable();
            }
        });
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
    public void print() {

    }

    @Override
    public void printStartGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: Game is starting!");
                dashboardController.setupChoiceBox(null);
            }
        });
    }

    @Override
    public void winner(ArrayList<String> nicknamesWinner) {

    }

    @Override
    public void printAssistantChosen(String nick, Assistant assistant) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: "+nick+" choosed assistant "+assistant);
            }
        });
    }

    @Override
    public void printTurn(String nick,boolean isAssistantPhase) {
        if(!nick.equals(nickname))
            currentPlayerState=PlayerState.WAITING;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setupPlance(player);
                if(isAssistantPhase)
                    dashboardController.setGameUpdateLabel("GAME: "+nick+" is choosing an assistant");
                else dashboardController.setGameUpdateLabel("GAME: "+nick+" started his turn");
            }
        });
    }

    @Override
    public void printCharacterChosen(String nick, CharacterView character) {

    }

    @Override
    public void printCloudChosen(String nick, int cloudID) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setupPlance(currentPlayerView);
                dashboardController.setupArchipelago();
                if (!nick.equals(nickname)) {
                    dashboardController.setGameUpdateLabel("GAME: " + nick + " choosed cloud " + cloudID);
                }
            }
        });
    }

    @Override
    public void printStudentToHall(String nick, Student student) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(!nick.equals(nickname)) {
                    dashboardController.setGameUpdateLabel("GAME: " + nick + " moved student " + student + " in his hall");
                    dashboardController.setupPlance(currentPlayerView);
                }
            }
        });
    }

    @Override
    public void printStudentToIsland(String nick, Student student, int IslandID) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!nick.equals(nickname)) {
                    dashboardController.setupPlance(currentPlayerView);
                    dashboardController.setupArchipelago();
                    dashboardController.setGameUpdateLabel("GAME: " + nick + " moved student " + student + " on island " + IslandID);
                }
            }
        });
    }

    @Override
    public void printMotherNatureMovement(String nick, int islandID) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!nick.equals(nickname)) {
                    dashboardController.setupArchipelago();
                    dashboardController.setupPlance(currentPlayerView);
                    dashboardController.setGameUpdateLabel("GAME: " + nick + " moved MotherNature on island " + islandID);
                }
            }
        });
    }

    @Override
    public void printPlayerDisconnection(String nick) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: "+nick+" disconnected");
            }
        });
    }

    @Override
    public void printPlayerConnection(String nick, boolean reconnect) {
        if(correctlyConnected) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (reconnect)
                        dashboardController.setGameUpdateLabel("GAME: " + nick + " reconnected");
                    else dashboardController.setGameUpdateLabel("GAME: " + nick + " connected to the game");
                }
            });
        }
    }
    public void printInvalidAssistant(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: Invalid assistant");
            }
        });
    }

    @Override
    public void printInvalidCloud() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: Invalid cloud");
            }
        });
    }

    @Override
    public void printInvalidIsland() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: Invalid island");
            }
        });
    }

    @Override
    public void printInvalidStudent() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: Invalid student");
            }
        });
    }

    @Override
    public void printInvalidTurn() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: Is not your turn!");
            }
        });
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setGameUpdateLabel("GAME: You are the only remained, timer to win started");
            }
        });
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dashboardController.setupArchipelago();
               // dashboardController.setupPlayerView(player);
            }
        });

    }

    @Override
    public void nextMove(PlayerState playerState) {
        //if (playerState != PlayerState.CHARACTHERISLANDPHASE && playerState != PlayerState.CHARACTHERSTUDENTSPHASE){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    dashboardController.setupPlance(player);
                    dashboardController.setupArchipelago();
                }
            });
       // }
        switch (playerState){
            case STUDENTPHASE -> {
                currentPlayerState=PlayerState.STUDENTPHASE;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dashboardController.setCharacterButtonClicked();
                        dashboardController.setGameUpdateLabel("GAME: Move one student from entrance!");

                    }
                });
                chooseStudentToMove();
            }
            case ASSISTANTPHASE -> {
                currentPlayerState=PlayerState.ASSISTANTPHASE;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dashboardController.setCharacterButtonNotClicked();
                        dashboardController.setupChoiceBoxAssistantPhase(true);
                        dashboardController.setGameUpdateLabel("GAME: Choose one assistant!");
                    }
                });
                chooseAssistant();
            }
            case MOTHERNATUREPHASE -> {
                currentPlayerState=PlayerState.MOTHERNATUREPHASE;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dashboardController.setCharacterButtonClicked();
                        dashboardController.setGameUpdateLabel("GAME: Move MotherNature to an island! MAX:"+player.getLastassistantplayed().getValue());
                    }
                });
                moveMotherNature();
            }
            case CLOUDPHASE -> {
                currentPlayerState=PlayerState.CLOUDPHASE;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dashboardController.setCharacterButtonClicked();
                        dashboardController.setGameUpdateLabel("GAME: Choose one cloud!");
                    }
                });
                chooseCloud();
            }
            case CHARACTHERSTUDENTSPHASE -> {
                System.out.println("Fino a qui ci sei poi non so");
                dashboardController.setCharacterButtonClicked();
                currentPlayerState=PlayerState.CHARACTHERSTUDENTSPHASE;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dashboardController.inputStudentCharacter();
                    }
                });
            }
            case CHARACTHERISLANDPHASE -> {
                System.out.println("Fino a qui ci sei poi non so");
                dashboardController.setCharacterButtonClicked();
                currentPlayerState=PlayerState.CHARACTHERISLANDPHASE;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dashboardController.inputIslandCharacter();
                    }
                });
            }
        }
    }

}
