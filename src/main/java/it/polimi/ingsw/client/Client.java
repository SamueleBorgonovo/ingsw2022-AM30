package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.controller.MessageHandler;
import it.polimi.ingsw.messages.toClient.InvalidAssistantMessage;
import it.polimi.ingsw.messages.toClient.NicknameMessage;
import it.polimi.ingsw.messages.toClient.MessageToClient;
import it.polimi.ingsw.messages.toClient.WizardsListMessage;
import it.polimi.ingsw.messages.toServer.ChooseNicknameMessage;
import it.polimi.ingsw.messages.toServer.CreatePlayerInGameMessage;
import it.polimi.ingsw.messages.toServer.MessageToServer;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Wizard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    String ip;
    int port;
    ObjectOutputStream output;
    ObjectInputStream input;
    boolean active=false;
    String nickname;
    Wizard wizard;
    GameMode gamemode;
    int numofPlayers;
    private final Thread socketListener;
    private View view;
    ClientMessageHandler messageHandler;

    //Cli e gui dopo che vengono scelti ip e porta chiamano il costruttore di client e poi il suo .setup
    public Client(String ip, int port, View view){ //Mettere ip e port come parametri del costruttore
        this.ip = ip;
        this.port = port;
        this.view = view;
        messageHandler = new ClientMessageHandler();

        this.socketListener = new Thread(this::messageListener);
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
       gameSetup();

       active=true;
       socketListener.start();


        return true;
    }

    public void gameSetup() throws IOException, ClassNotFoundException {
        Scanner stdin = new Scanner(System.in);
        //Mettere tutto la scelta delle options in un metodo a parte
        System.out.println("Choose your nickname"); //Invece di questi print usare view. per poi dividere in cli o gui
        boolean correct=false;
        do{
            String s=stdin.nextLine();
            ChooseNicknameMessage message = new ChooseNicknameMessage(s);
            sendMessage(message);
            Object message2 = input.readObject();
            if(message2 instanceof NicknameMessage) {
                if(((NicknameMessage) message2).getCheck()) {
                    correct=true;
                    setNickname(s);
                }
            }
        }while(!correct);

        System.out.println("Press s for SimpleMode or e for ExpertMode");
        String choice = stdin.nextLine();
        boolean pass=false;
        char cho='a';
        do {
            if (choice.toLowerCase().startsWith("c") || choice.toLowerCase().startsWith("s")) {
                pass = true;
                cho = choice.toLowerCase().charAt(0);
            }
        }while(!pass);

        if(cho=='s')
            this.setGamemode(GameMode.SIMPLEMODE);
        else
            this.setGamemode(GameMode.EXPERTMODE);

        System.out.println("Choose the number of the players ( 2 | 3 )");
        String choice2 = stdin.nextLine();
        boolean pass2=false;
        char cho2='0';
        do {
            if (choice2.toLowerCase().startsWith("1") || choice2.toLowerCase().startsWith("2")) {
                pass2 = true;
                cho2 = choice.toLowerCase().charAt(0);
            }
        }while(!pass2);

        if(cho2=='2')
            this.setNumofPlayers(2);
        else
            this.setNumofPlayers(3);

        CreatePlayerInGameMessage message = new CreatePlayerInGameMessage(nickname,gamemode,numofPlayers);
        sendMessage(message);
        //Arriva la lista di wizard disponibili e sceglie
        correct=false;
        do{
            Object message2 = input.readObject();
            if(message2 instanceof WizardsListMessage) {
                ((WizardsListMessage) message2).action(this);
                correct=true;
            }
        }while(!correct);

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

}
