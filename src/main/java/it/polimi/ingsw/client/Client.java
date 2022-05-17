package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private final int PING_PERIOD = 10000;
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
    private boolean myTurn=false;


    boolean characterPlayed = false;

    //Cli e gui dopo che vengono scelti ip e porta chiamano il costruttore di client e poi il suo .setup
    public Client(String ip, int port, View view){
        this.ip = ip;
        this.port = port;
        this.view = view;
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

        active=true;
        socketListener.start();

        pingActive = true;
        pinger.start();


        return true;
    }

    public void gameSetup(){
        this.gamemode=view.chooseGameMode();
        this.numofPlayers=view.chooseNumberOfPlayers();

        CreatePlayerInGameMessage message = new CreatePlayerInGameMessage(nickname, gamemode, numofPlayers);
        sendMessage(message);

    }

    public void nextMove(PlayerState playerState){
        PossibleAction action;
        if(playerState==PlayerState.STUDENTPHASE){
            action=view.chooseNextAction(playerState);
            switch(action){
                case MOVESTUDENTT0ISLAND -> {
                    view.moveStudentToIsland();
                }
                case MOVESTUDENTTOHALL -> {
                    view.moveStudentToHall();
                }
                case USECHARACTER -> {
                    view.useCharacter();
                }
            }
        }else if(playerState==PlayerState.MOTHERNATUREPHASE){
            if(getGamemode()==GameMode.EXPERTMODE) {
                action = view.chooseNextAction(playerState);
                switch (action) {
                    case USECHARACTER -> {
                        view.useCharacter();
                    }
                    case MOVEMOTHERNATURE -> {
                        view.moveMotherNature();
                    }
                }
            }else view.moveMotherNature();
        }else if(playerState==PlayerState.ASSISTANTPHASE){
                    view.chooseAssistant();
        }else if(playerState==PlayerState.CHARACTHERISLANDPHASE){
                    //view.metodoIsland
        }else if(playerState==PlayerState.CHARACTHERSTUDENTSPHASE){
                    //view.metodoStudent
        }else if(playerState==PlayerState.CLOUDPHASE){
            if(getGamemode()==GameMode.EXPERTMODE){
                action=view.chooseNextAction(playerState);
                switch (action) {
                    case CHOOSECLOUD -> {
                        view.chooseCloud();
                    }
                    case USECHARACTER -> {
                        view.useCharacter();
                    }
                }
            }else view.chooseCloud();
        }
    }

    public void updateBoard(Board board, boolean isAccepted){
        view.setBoard(board);
        if(isAccepted)
            view.print();
    }

    public void setTurn(String nickname){
        if(this.nickname.equals(nickname)){
            //print è iniziato il mio turno
            myTurn=true;
        }
        else {
            //print è iniziato il turno di "nickname"
            myTurn=false;
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

    public synchronized void sendMessage(MessageToServer message){
        try {
            output.writeObject(message);
            output.reset();
            output.flush();
        } catch (IOException e) {
            System.err.println("Error during send process.");
            e.printStackTrace();
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
