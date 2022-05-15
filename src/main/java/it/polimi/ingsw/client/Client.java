package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerInterface;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private final int PING_PERIOD = 5000;
    private final int TIMEOUT_FOR_RESPONSE = 240000;
    private String ip;
    private int port;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    boolean active=false;
    private String nickname;
    private Wizard wizard;
    private GameMode gamemode;
    int numofPlayers;
    private final Thread socketListener;
    private final Thread pinger;
    private Thread timer;
    private View view;
    private ClientMessageHandler messageHandler;
    private boolean pingActive;
    private int studentPlayed;


    boolean characterPlayed = false;

    //Cli e gui dopo che vengono scelti ip e porta chiamano il costruttore di client e poi il suo .setup
    public Client(String ip, int port, View view){
        this.ip = ip;
        this.port = port;
        this.view = view;
        studentPlayed=0;
        messageHandler = new ClientMessageHandler(this,view);

        this.socketListener = new Thread(this::messageListener);
        this.pinger = new Thread(this::startPinger);
    }
    public boolean setupConnection() throws IOException, ClassNotFoundException {
        Socket socket;
        try{
            socket = new Socket(ip, port);
        } catch (IOException e) {
            return false;
        }
        System.out.println("Correctly connected to the Server");

       output = new ObjectOutputStream(socket.getOutputStream());
       input = new ObjectInputStream(socket.getInputStream());

        return true;
    }

    public void gameSetup() throws IOException, ClassNotFoundException {
        //Mettere tutto la scelta delle options in un metodo a parte
        String nick;
        nick = view.chooseNickname(true);
        boolean isReconnecting = false;
        boolean correct = false;
        do {

            ChooseNicknameMessage message = new ChooseNicknameMessage(nick, false);
            sendMessage(message);
            Object message2 = input.readObject();
            if (message2 instanceof NicknameMessage) {
                if (((NicknameMessage) message2).getCheck()) {
                    correct = true;
                    setNickname(nick);
                    if (((NicknameMessage) message2).getReconnect()) {
                        if (view.tryToReconnect()) {
                            sendMessage(new ChooseNicknameMessage(nick, true));
                            isReconnecting = true;
                        }
                    }
                } else nick = view.chooseNickname(false);

            }
        } while (!correct);

        active=true;
        socketListener.start();

        pingActive = true;
        pinger.start();

        if (!isReconnecting) {

            this.gamemode=view.chooseGameMode();
            this.numofPlayers=view.chooseNumberOfPlayers();

            CreatePlayerInGameMessage message = new CreatePlayerInGameMessage(nickname, gamemode, numofPlayers);
            sendMessage(message);

        }
    }

    public void nextMove(PlayerState playerState){
        PossibleAction action;
        if(playerState==PlayerState.STUDENTPHASE){
            action=view.chooseNextAction(playerState);
            studentPlayed++;
            switch(action){
                case MOVESTUDENTT0ISLAND -> {

                }
                case MOVESTUDENTTOHALL -> {

                }
                case USECHARACTER -> {

                }
            }
        }else if(playerState==PlayerState.MOTHERNATUREPHASE || playerState==PlayerState.CLOUDPHASE){
            action=view.chooseNextAction(playerState);
            switch(action){
                case USECHARACTER -> {
                    //view.useCharacter();
                }
                case MOVEMOTHERNATURE -> {
                    //view.moveMotherNature();
                }
                case CHOOSECLOUD -> {
                    //view.chooseCloud();
                }
            }
        }else if(playerState==PlayerState.ASSISTANTPHASE){
                    //view.chooseAssistant();
        }else if(playerState==PlayerState.CHARACTHERISLANDPHASE){
                    //view.metodoIsland
        }else if(playerState==PlayerState.CHARACTHERSTUDENTSPHASE){
                    //view.metodoStudent
        }
    }

    public void updateView(ArrayList<PlayerInterface> players, Board board,boolean isAccepted){
        view.setPlayers(players);
        view.setBoard(board);
        if(isAccepted)
            view.print();

    }

    public void setTurn(String nickname){
        if(this.nickname.equals(nickname)){
            //print è iniziato il mio turno
        }
        else {
            //print è iniziato il turno di "nickname"
        }
    }

    public void messageListener(){
        try{
        while(active){
            Object messageFromServer = input.readObject();
            ((MessageToClient) messageFromServer).action(this);
        }
        }catch(IOException | ClassNotFoundException e) {
            //Da scrivere cosa fare in questi casi
        }

    }

    public void sendMessage(MessageToServer message){
        try {

            output.writeObject(message);
            output.reset();
            output.flush();
        } catch (IOException e) {
            System.err.println("Error during send process.");
            System.err.println(e.getMessage());
        }
    }

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

    public void startTimer(){
        timer = new Thread(() -> {
            try{
                Thread.sleep(TIMEOUT_FOR_RESPONSE);
                //handleSocketDisconnection(true);
            } catch (InterruptedException e){ }
        });
        timer.start();
    }

    public void stopTimer(){
        if (timer != null && timer.isAlive()){
            timer.interrupt();
            //timer = null;
        }
    }

    public void changestudentPlayed(){
        studentPlayed--;
    }

    public ClientMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public GameMode getGamemode() {
        return gamemode;
    }

    public void setGamemode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

    public int getNumofPlayers() {
        return numofPlayers;
    }

    public void setNumofPlayers(int numofPlayers) {
        this.numofPlayers = numofPlayers;
    }


    public void setCharacterPlayed(boolean characterPlayed) {
        this.characterPlayed = characterPlayed;
    }

    public boolean isCharacterPlayed() {
        return characterPlayed;
    }
}
