package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Client handles connection with server, permitting sending and receiving messages
 */
public class Client {

    private final int PING_PERIOD = 10000;
    private final int TIMEOUT_FOR_RESPONSE = 240000;
    private final String ip;
    private final int port;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    boolean active=false;
    private String nickname;
    private Wizard wizard;
    private GameMode gamemode;
    int numofPlayers;
    private final Thread socketListener;
    private final Thread pinger;
    private Thread timer;
    private final View view;
    private final ClientMessageHandler messageHandler;
    private boolean pingActive;
    private boolean myTurn=false;
    boolean characterPlayed = false;
    private final ArrayList<Thread> timerThreads = new ArrayList<>();

    /**
     * Constructor Client instantiates attributes to connect with the server. It instantiate also
     *  threads used to receive messages and to ping server
     * @param ip ip of the server
     * @param port port of the server
     * @param view view chosen by the player
     */
    public Client(String ip, int port, View view){
        this.ip = ip;
        this.port = port;
        this.view = view;
        messageHandler = new ClientMessageHandler(this,view);

        this.socketListener = new Thread(this::messageListener);
        this.pinger = new Thread(this::startPinger);
    }

    /**
     * Method used to set up the connection with the server and creates input-output stream.
     * It Starts also the message receiver and pinger threads.
     * @return true if correctly connected, false otherwise
     * @throws IOException if input-output stream is not created
     */
    public boolean setupConnection() throws IOException{
        try{
            socket = new Socket(ip, port);
        } catch (IOException e) {
            return false;
        }
        System.out.println("Correctly connected to the Server");

       output = new ObjectOutputStream(socket.getOutputStream());
       input = new ObjectInputStream(socket.getInputStream());

        active=true;
        socketListener.start();

        pingActive = true;
        pinger.start();


        return true;
    }

    /**
     * Method used to call the view method to choose settings
     */
    public void gameSetup(){
        view.chooseSettings();
    }

    /**
     * Method used to set settings of the game
     * @param gamemode gamemode of the game
     * @param numofPlayers num of players of the game
     * @param wizard wizard chosen by the player
     */
    public void setSettings(GameMode gamemode,int numofPlayers,Wizard wizard){
        this.gamemode=gamemode;
        this.numofPlayers=numofPlayers;
        this.wizard=wizard;
        view.displayGame();
    }

    /**
     * Method used to set turn of the player
     * @param nickname nickname of the player that has to play
     * @param assistantPhase is true if is assistant phase, false otherwise
     */
    public void setTurn(String nickname, boolean assistantPhase){
        if(this.nickname.equals(nickname)){
            myTurn=true;
        }
        else {
            myTurn=false;
        }
        view.printTurn(nickname,assistantPhase);
    }

    /**
     * Method used to handle disconnection of a client from the server
     * @param nick nickname of the player disconnected
     * @param timeout if timeout is true game ended because a timeout expired
     * @param win if win is true game ended because a player won the game
     */
    public void handleDisconnection(String nick,boolean timeout,boolean win){
        //if timeout=true server disconnected all players, else someone is disconnected from game
        if(win){
            sendMessage(new DisconnectionRespondMessage());
            pingActive=false;
            stopTimer();
            view.printWinClose();
            handleSocketDisconnection(false);
            view.setExit();
        }else if(timeout){
            sendMessage(new DisconnectionRespondMessage());
            pingActive=false;
            stopTimer();
            view.printGameEndedTimeout();
            handleSocketDisconnection(true);
            view.setExit();
        }else {
            if (!nick.equals(nickname))
                view.printPlayerDisconnection(nick);
            else {
                pingActive = false;
                stopTimer();
                handleSocketDisconnection(true);
            }
        }
        }

    /**
     * Method that receives messages from server
     */
    public void messageListener(){
        try{
        while(active){
            Object messageFromServer = input.readObject();
            ((MessageToClient) messageFromServer).action(this);
        }
        }catch(IOException | ClassNotFoundException e) {
            System.out.println("Exception del metodo che riceve i messaggi dal server");
            handleSocketDisconnection(false);
        }

    }

    /**
     * Method used to send a message to the server
     * @param message message to send to the server
     */
    public synchronized void sendMessage(MessageToServer message){
        if(pingActive) {
            try {
                output.reset();
                output.writeObject(message);
                output.flush();
            } catch (IOException e) {
                System.out.println("Exception nel sendMessage del client");
                handleSocketDisconnection(false);
            }
        }

    }

    /**
     * Method used to start the pinger system
     */
    public void startPinger() {
        while(pingActive){
            try {
                Thread.sleep(PING_PERIOD);
                PingToServerMessage message = new PingToServerMessage(true);
                sendMessage(message);
                startTimer();
            }catch (InterruptedException e){
                break;
            }
        }
    }

    /**
     * Method used to disconnect the client from the server
     * @param timeout if is true is called by the pinger system
     */
    public void handleSocketDisconnection(boolean timeout){
        active=false;
        pingActive=false;
        stopTimer();
        try {
            input.close();
        } catch (IOException e) {
        }
        try {
            output.close();
        } catch (IOException e) {
        }
        try {
            socket.close();
        } catch (IOException e) {
        }
        view.printConnectionClosed(timeout);
    }

    /**
     * Method used to start the timer of the ping system
     */
    public synchronized void startTimer(){
        timer = new Thread(() -> {
            try{
                Thread.sleep(TIMEOUT_FOR_RESPONSE);
                System.out.println("Timeout del pinger lato client scattato");
                handleSocketDisconnection(true);
            } catch (InterruptedException e){
            }
        });
        timerThreads.add(timer);
        timer.start();
    }

    /**
     * Method used to stop the timer of the ping system
     */
    public synchronized void stopTimer(){
        if (timer != null && timer.isAlive()){
            for(Thread time : timerThreads) {
                time.interrupt();
            }
            timerThreads.clear();
        }
    }

    /**
     * Method used to get the messageHandler of the client
     * @return the messafeHandler of the client
     */
    public ClientMessageHandler getMessageHandler() {
        return messageHandler;
    }

    /**
     * Method used to get the nickname of the player
     * @return the nickname of the player
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Method used to set the nickname of the player
     * @param nickname nickname of the player
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Method used to get the wizard of the player
     * @return the wizerd of the player
     */
    public Wizard getWizard() {
        return wizard;
    }

    /**
     * Method used to set the wizard of the player
     * @param wizard wizard of the player
     */
    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    /**
     * Method used to get the game-mode of the game
     * @return the game-mode of the game
     */
    public GameMode getGamemode() {
        return gamemode;
    }

    /**
     * Method used to set the game-mode chosen by the player
     * @param gamemode gamemode chosen by the player
     */
    public void setGamemode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

    /**
     * Method used to get the num of players of the game
     * @return the num of players of the game
     */
    public int getNumofPlayers() {
        return numofPlayers;
    }

    /**
     * Method used to set the num of players chosen by the player
     * @param numofPlayers the num of player chosen by the player
     */
    public void setNumofPlayers(int numofPlayers) {
        this.numofPlayers = numofPlayers;
    }

    /**
     * Method used to set the boolean of the character played
     * @param characterPlayed set if the character is played
     */
    public void setCharacterPlayed(boolean characterPlayed) {
        this.characterPlayed = characterPlayed;
    }

    /**
     * Method used to know if the character is played
     * @return true if the character is played, false otherwise
     */
    public boolean isCharacterPlayed() {
        return characterPlayed;
    }

    /**
     * Method used to know if is this client's turn
     * @return true if the is client's turn, false otherwise
     */
    public boolean isMyTurn() {
        return myTurn;
    }
}
