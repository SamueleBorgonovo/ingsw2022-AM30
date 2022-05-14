package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

            /*
            //Arriva la lista di wizard disponibili e sceglie

            correct = false;
            do {
                Object message2 = input.readObject();
                if (message2 instanceof WizardsListMessage) {
                    ((WizardsListMessage) message2).action(this);
                    correct = true;
                }
            } while (!correct);
               */
        }
    }

    public void nextMove(PlayerState playerState){
        //chiama choosenextaction in view
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
