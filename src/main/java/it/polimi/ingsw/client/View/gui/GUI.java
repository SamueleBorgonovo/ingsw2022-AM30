package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.controller.virtualView.BoardView;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.model.game.EffectHandler;
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

/**
 * Class GUI controls all scene controllers
 */
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
    private final ArrayList<PlayerView> players = new ArrayList<>();
    private EffectHandler effectHandler;
    private PlayerView player;
    private boolean correctlyConnected=false;
    private PlayerView currentPlayerView;
    private boolean character4played=false;
    private PlayerState currentPlayerState;
    private Wizard wizard;

    /**
     * Method start is called when GUI is launched. It sets the primary stage of the view and starts the view
     * @param primaryStage primary stage to set
     */
    @Override
    public void start(Stage primaryStage){
        this.primaryStage=primaryStage;
        instantiateConnectionScene();
    }

    /**
     * Method setWizard sets the wizard chosen by the player
     * @param wizard wizard chosen by the player
     */
    public void setWizard(Wizard wizard){
        this.wizard=wizard;
    }

    /**
     * Method getClient returns the client of the gui
     * @return the client of the gui
     */
    public Client getClient() {
        return client;
    }

    /**
     * Method setNickname sets the nickname chosen by the player
     * @param nickname nickname chosen by the player
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
        client.setNickname(nickname);
    }

    /**
     * Method getCurrentPlayerState returns the current playerState of the player
     * @return the current playrState of the player
     */
    public PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    /**
     * Method setCurrentPlayerView sets the current playerView the player is viewing
     * @param player the playerView currently viewing
     */
    public void setCurrentPlayerView(PlayerView player){currentPlayerView=player;}

    /**
     * Method getNickname returns the nickname of the player
     * @return the nickname of the player
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Method getBoard returns the BoardView of the game
     * @return the boardView of the game
     */
    public BoardView getBoard() {
        return board;
    }

    /**
     * Method getEffectHandler returns the EffectHandler of the game
     * @return the EffectHandler of the game
     */
    public EffectHandler getEffectHandler(){return effectHandler;}

    /**
     * Method getPlayers returns the PlayerView of all players in the game
     * @return the PlayerView of all players in the game
     */
    public ArrayList<PlayerView> getPlayers() {
        return players;
    }

    /**
     * Method getPlayer returns the PlayerView of the player in the game
     * @return the PlayerView of the player in the game
     */
    public PlayerView getPlayer() {
        return player;
    }

    /**
     * Method resetControllers sets to null all controllers
     */
    public void resetControllers(){
        nicknameScene=null;
        wizardScene=null;
        connectScene=null;
        dashboardController=null;
        gameSettingsScene=null;
    }

    /**
     * Method createScene creates a new scene to view
     * @param pathOfFxmlFile path of the file fxml of the scene to create
     * @param functionInterface function to execute
     */
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

    /**
     * Method instantiateConnectionScene instantiates a new ConnectionScene
     */
    public void instantiateConnectionScene(){
        createScene("/Connect_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            connectScene = fxmlLoader.getController();
            connectScene.setGui(this);
        });
    }

    /**
     * Method instantiateNicknameScene instantiates a new NicknameScene
     */
    public void instantiateNicknameScene(){
        createScene("/Nickname_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            nicknameScene = fxmlLoader.getController();
            nicknameScene.setReconnectButton(false);
            nicknameScene.setGui(this);
        });
    }

    /**
     * Method instantiateGameSettingsScene instantiates a new GameSettingsScene
     */
    public void instantiateGameSettingsScene(){
        createScene("/Game_Settings_Dashboard.fxml", () -> {
            primaryStage.setTitle("Eriantys");
            primaryStage.setResizable(false);
            primaryStage.show();
            gameSettingsScene = fxmlLoader.getController();
            gameSettingsScene.setGui(this);
        });
    }

    /**
     * Method instantiateWizardScene instantiates a new WizardScene
     * @param availableWizards available wizards that player can choose
     */
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

    /**
     * Method instantiateDashBoardScene instantiates a new DashBoardScene
     */
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

    /**
     * Method createClient creates an instance of the client and call the method to connect the client with the server
     * @param ip ip of the server to connect
     * @param port port of the server to connect
     * @return true if connection correctly executed, false otherwise
     */
    public boolean createClient(String ip, int port) {
        client = new Client(ip,port,this);
        try {
            return client.setupConnection();
        }
        catch (Exception e){}
        return false;
    }

    ///////////////////////////////////////////////////////////////////
    //View methods                                                   //
    ///////////////////////////////////////////////////////////////////

    /**
     * Method chooseNickname calls methods in nicknameScene to set the nickname and reconnect to a game
     * @param validNickname true if the nickname is valid, false otherwise
     * @param reconnect true if the player can reconnect to a game, false otherwise
     */
    @Override
    public void chooseNickname(boolean validNickname, boolean reconnect) {
        Platform.runLater(() -> {
            if(!validNickname)
                nicknameScene.setWrongNickname(true);
            else if(reconnect) {
                nicknameScene.setReconnectButton(true);
                nicknameScene.setLoginButton(304.0);
            }
            else
                client.gameSetup();
        });
    }

    /**
     * Method chooseStudentToMove calls methods in dashboardController that allows to choose student from entrance
     */
    public void chooseStudentToMove(){
        Platform.runLater(() -> {
            dashboardController.setupPlance(player);
            dashboardController.setEntranceStudentClickable();
        });
    }

    /**
     * Method chooseSettings instantiates the GameSettings scene
     */
    public void chooseSettings(){
        instantiateGameSettingsScene();
    }

    /**
     * Method chooseWizard instantiates the Wizard Scene
     * @param availableWizards available wizard that player can choose
     */
    @Override
    public void chooseWizard(ArrayList<Wizard> availableWizards) {
        instantiateWizardScene(availableWizards);
    }

    /**
     * Method chooseAssistant calls a method in dashboardController that allows to choose the assistant
     */
    @Override
    public void chooseAssistant() {
        Platform.runLater(() -> dashboardController.setAssistantView());
    }

    /**
     * Method setCharacter4Played sets the boolean that checks if the character 4 is being played
     * @param character4played true if character 4 played is played, false otherwise
     */
    public void setCharacter4played(boolean character4played) {
        this.character4played = character4played;
    }

    /**
     * Method moveMotherNature calls a method in dashboardController that allows to choose where to move motherNature
     */
    @Override
    public void moveMotherNature() {
        int num=0;
        if(character4played)
            num=2;
        int finalNum = num+player.getLastassistantplayed().getMovement();
        Platform.runLater(() -> dashboardController.setMotherNatureView(finalNum,getBoard().getMotherNature()));
        character4played = false;//////////////////////////////////////////
    }

    /**
     * Method chooseCloud calls a method in dashboardController that allows to choose clouds
     */
    @Override
    public void chooseCloud() {
        Platform.runLater(() -> dashboardController.setCloudsClickable());
    }

    /**
     * Method setPlayers sets the updated playerView of all players from game
     * @param players arraylist of the view of all players
     */
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

    /**
     * Method setBoard sets the updated BoardView from game
     * @param board view of the boardView
     */
    @Override
    public void setBoard(BoardView board) {
            this.board = board;
    }

    /**
     * Method setEffectHandler sets the updated effectHandler from game
     * @param effectHandler effectHandler from game
     */
    @Override
    public void setEffectHandler(EffectHandler effectHandler) {
        this.effectHandler=effectHandler;
    }

    /**
     * Method printStartGame calls methods in dashboardController that sets the view to start the game
     * @param restart true if the game is restarting, false otherwise
     */
    @Override
    public void printStartGame(boolean restart) {
        Platform.runLater(() -> {
            if(!restart) {
                dashboardController.setGameUpdateLabel("GAME: Game is starting!");
            }else dashboardController.setGameUpdateLabel("GAME: Game is re-starting!");
            dashboardController.setupChoiceBox();
        });
    }

    /**
     * Method printAssistantChosen handles the choice of an assistant by a player
     * @param nick nickname of the player that chose the assistant
     * @param assistant assistant chosen
     */
    @Override
    public void printAssistantChosen(String nick, Assistant assistant) {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: "+nick+" choosed assistant "+assistant));
    }

    /**
     * Method printTurn handles the start of a turn of a player
     * @param nick nickname of the player that started his turn
     * @param isAssistantPhase true if the player starts the assistant phase, false otherwise
     */
    @Override
    public void printTurn(String nick,boolean isAssistantPhase) {
        if(!nick.equals(nickname)) {
            currentPlayerState = PlayerState.WAITING;
            Platform.runLater(() -> dashboardController.setCharacterButtonNotClickable());
        }
        Platform.runLater(() -> {
            dashboardController.setupPlance(player);
            if(isAssistantPhase)
                dashboardController.setGameUpdateLabel("GAME: "+nick+" is choosing an assistant");
            else dashboardController.setGameUpdateLabel("GAME: "+nick+" started his turn");
        });
    }

    /**
     * Method printCharacterChosen handles the choice of a character by a player
     * @param nick nickname of the player that chose the character
     * @param character character chosen
     */
    @Override
    public void printCharacterChosen(String nick, CharacterView character) {
        if(!nick.equals(nickname)){
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: "+nick+" played character "+character.getName()));
        }else{
            Platform.runLater(() -> dashboardController.resetCharacterButton());
        }
    }

    /**
     * Method printCloudChosen handles the choice of a cloud by a player
     * @param nick nickname of the player that chose the cloud
     * @param cloudID cloudID of the cloud chosen
     */
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

    /**
     * Method printStudentToHall handles the moves of a student to the hall by a player
     * @param nick nickname of the player that moved the student
     * @param student student moved
     */
    @Override
    public void printStudentToHall(String nick, Student student) {
        Platform.runLater(() -> {
            if(!nick.equals(nickname)) {
                dashboardController.setGameUpdateLabel("GAME: " + nick + " moved student " + student + " in his hall");
                dashboardController.setupPlance(currentPlayerView);
            }
        });
    }

    /**
     * Method printStudentToIsland handles the moves of a student to an island by a player
     * @param nick nickname of the player that moved the student
     * @param student student moved
     * @param IslandID islandID chosen by the player
     */
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

    /**
     * Method printMotherNatureMovement handles the moves of motherNature by a player
     * @param nick nickname of the player that moved motherNature
     * @param islandID islandID where motherNature has been moved
     */
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

    /**
     * Method printPlayerDisconnection handles the disconnection of a player
     * @param nick nickname of the player disconnected
     */
    @Override
    public void printPlayerDisconnection(String nick) {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: "+nick+" disconnected"));
    }

    /**
     * Method printPlayerConnection handles the connection of a player
     * @param nick nickname of the player connected
     * @param reconnect true if the player is reconnecting, false otherwise
     */
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

    /**
     * Method printInvalidAssistant warns the player that tried to move an invalid assistant
     */
    public void printInvalidAssistant(){
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid assistant"));
    }

    /**
     * Method printInvalidCloud warns the player that tried to move an invalid cloud
     */
    @Override
    public void printInvalidCloud() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid cloud"));
    }

    /**
     * Method printInvalidIsland warns the player that tried to move an invalid island
     */
    @Override
    public void printInvalidIsland() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid island"));
    }

    /**
     * Method printInvalidStudent warns the player that tried to move an invalid student
     */
    @Override
    public void printInvalidStudent() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid student"));
    }

    /**
     * Method printInvalidCharacter warns the player that the character he tried to play is not available
     */
    public void printInvalidCharacter(){
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid Character"));
    }

    /**
     * Method printInvalidCoins warns the player that he hasn't enough coins to play that character
     */
    public void printInvalidCoins(){
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: You don't have enough coins!"));
    }

    /**
     * Method printInvalidValue warns the player that he tried to move motherNature in an invalid island
     */
    public void printInvalidValue(){
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Invalid island to move motherNature"));
    }

    /**
     * Method printInvalidTurn warns the player that tried to do an action when wasn't his turn
     */
    @Override
    public void printInvalidTurn() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Is not your turn!"));
    }

    /**
     * Method printInvalidStop warns the player that tried to use a stop but there wasn't enough
     */
    @Override
    public void printInvalidStop() {
        Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: There are not enough stops"));
    }

    /**
     * Method printInvalidWizard warns the player that tried to choose an invalid wizard
     */
    @Override
    public void printInvalidWizard() {
        Platform.runLater(() -> wizardScene.setWrongWizard());
    }

    /**
     * Method printWinnerInstantly handles the players' win in the middle of a turn
     * @param nickname players' nicknames that won
     * @param type type of win
     */
    @Override
    public void printWinnerInstantly(ArrayList<String> nickname, int type) {
        Platform.runLater(() -> dashboardController.winner(nickname));
        if(type == 1)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Only 3 islands remained"));
        if(type==2)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Last tower built"));
    }

    /**
     * Method printWinnerEndRound handles the players' win in the end of a turn
     * @param nickname players' nicknames that won
     * @param type type of win
     */
    @Override
    public void printWinnerEndRound(ArrayList<String> nickname, int type) {
        Platform.runLater(() -> dashboardController.winner(nickname));
        if(type == 1)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Coins bag empty!"));
        if(type==2)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: All assistant cards has been played"));
    }

    /**
     * Method printWaitingForPlayers warns the player that the game is in pause because there are not enough players
     * @param lobby true if the game is not started, false if the game is set in pause
     */
    @Override
    public void printWaitingForPlayers(boolean lobby) {
        if(lobby)
            Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: Waiting for players"));
        else Platform.runLater(() -> dashboardController.setGameUpdateLabel("GAME: You are the only remained, timer to win started"));
    }

    /**
     * Method printGameEndedTimeout warns the player that the game is ended because no-one reconnected
     */
    @Override
    public void printGameEndedTimeout() {
        ArrayList<String> s=new ArrayList<>();
        s.add(getClient().getNickname());
        Platform.runLater(() -> {
            dashboardController.winner(s);
            dashboardController.setGameUpdateLabel("GAME: timer to reconnect ended");
        } );
    }

    /**
     * Method printWinClose handles the disconnection caused by the end of the game
     */
    @Override
    public void printWinClose() {
        Platform.runLater(() -> {
            dashboardController.setGameUpdateLabel("Game Ended. Disconnection from server");
        });
    }

    /**
     * Method printConnectionClosed handles the connection closed with the server
     * @param timeout true if connection closed because of a timeout expired, false otherwise
     */
    @Override
    public void printConnectionClosed(boolean timeout) {
        Platform.runLater(() -> {
            if(timeout) dashboardController.setGameUpdateLabel("Connection with server closed. Time expired");
            else dashboardController.setGameUpdateLabel("Connection with server closed");
        });
    }

    /**
     * Method correctlyConnected calls the methods to set the first setup of the view
     */
    @Override
    public void correctlyConnected() {
        resetControllers();
        instantiateDashBoardScene();
        correctlyConnected=true;
        Platform.runLater(() -> {
            dashboardController.setupArchipelago();
        });

    }

    /**
     * Method nextMove calls the methods to play the next phase of the game
     * @param playerState phase of the game
     */
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
                    dashboardController.setCharacterButtonClickable();
                    dashboardController.setGameUpdateLabel("GAME: Move one student from entrance!");

                });
                chooseStudentToMove();
            }
            case ASSISTANTPHASE -> {
                Platform.runLater(() -> {
                    dashboardController.setupPlance(player);
                    dashboardController.setCharacterButtonNotClickable();
                    dashboardController.setupChoiceBoxAssistantPhase(true);
                    dashboardController.setGameUpdateLabel("GAME: Choose one assistant!");
                });
                chooseAssistant();
            }
            case MOTHERNATUREPHASE -> {
                Platform.runLater(() -> {
                    dashboardController.setupPlance(player);
                    dashboardController.setCharacterButtonClickable();
                    dashboardController.setGameUpdateLabel("GAME: Move MotherNature to an island! MAX:"+player.getLastassistantplayed().getMovement());
                });
                moveMotherNature();
            }
            case CLOUDPHASE -> {
                Platform.runLater(() -> {
                    dashboardController.setupPlance(player);
                    dashboardController.setCharacterButtonClickable();
                    dashboardController.setGameUpdateLabel("GAME: Choose one cloud!");
                });
                chooseCloud();
            }
            case CHARACTHERSTUDENTSPHASE -> {
                System.out.println("Fino a qui ci sei poi non so");
                Platform.runLater(() -> {
                    dashboardController.clickCharacterButton();
                    dashboardController.setCharacterButtonClickable();
                    dashboardController.inputStudentCharacter();
                });
            }
            case CHARACTHERISLANDPHASE -> {
                System.out.println("Fino a qui ci sei poi non so");
                Platform.runLater(() -> {
                    dashboardController.clickCharacterButton();
                    dashboardController.setCharacterButtonClickable();
                    dashboardController.inputIslandCharacter();
                });
            }
        }
    }

    /**
     * Method setExit calls a method in dashboardController that sets the Exit button
     */
    public void setExit(){
        Platform.runLater(() -> dashboardController.setExitButton());
    }

    /**
     * Method displayGame calls the method to display the Game View
     */
    public void displayGame(){
        instantiateDashBoardScene();
    }

    /**
     * Method noGameReconnect handles that the player can't anymore reconnect to the game
     */
    public void noGameReconnect(){
        Platform.runLater(() -> {
            nicknameScene.noGameReconnect();
        });
    }

}
