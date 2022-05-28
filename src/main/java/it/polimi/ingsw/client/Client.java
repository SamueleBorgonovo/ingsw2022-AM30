package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.client.View.cli.PossibleAction;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.PlayerState;
import it.polimi.ingsw.model.player.Wizard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private final int PING_PERIOD = 10000;
    private final int TIMEOUT_FOR_RESPONSE = 240000;
    private String ip;
    private int port;
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
    private View view;
    private ClientMessageHandler messageHandler;
    private boolean pingActive;
    private boolean myTurn=false;
    boolean characterPlayed = false;
    private ArrayList<Thread> timerThreads = new ArrayList<>();

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
        view.chooseSettings();

    }

    public void setSettings(GameMode gamemode,int numofPlayers){
        this.gamemode=gamemode;
        this.numofPlayers=numofPlayers;

    }


    public void setTurn(String nickname, boolean assistantPhase){
        if(this.nickname.equals(nickname)){
            myTurn=true;
        }
        else {
            myTurn=false;
        }
        if(!assistantPhase)
            view.printTurn(nickname);
    }

    public void handleDisconnection(String nick,boolean timeout,boolean win){
        //if gameEnded=true server disconnected all players, else someone is disconnected from game
        if(win){
            view.printWinClose();
            System.out.println("Nell'handleDisconnection quando qualcuno ha vinto");
            handleSocketDisconnection(false);
        }
        if(timeout){
            view.printGameEndedTimeout();
        }else view.printPlayerDisconnection(nick);
    }




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

    public synchronized void sendMessage(MessageToServer message){
        try {
            output.reset();
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            System.out.println("Exception nel sendMessage del client");
            handleSocketDisconnection(false);
            /*System.err.println("Error during send process.");
            e.printStackTrace();
             */
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

    public synchronized void stopTimer(){
        if (timer != null && timer.isAlive()){
            for(Thread timer : timerThreads) {
                timer.interrupt();
            }
            timerThreads.clear();
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

    public boolean isMyTurn() {
        return myTurn;
    }
}
