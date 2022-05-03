package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.toClient.CorrectNicknameMessage;
import it.polimi.ingsw.messages.toClient.InvalidNicknameMessage;
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
    ObjectOutputStream output;
    ObjectInputStream input;
    private static int port;
    boolean active=false;
    String nickname;
    Wizard wizard;
    GameMode gamemode;
    int numofPlayers;

    public boolean setup() throws ClassNotFoundException, IOException {
        Scanner stdin = new Scanner(System.in);
        String ip;
        System.out.println("Please insert the ip-addres");
        ip = stdin.nextLine();
        System.out.println("Please insert port");
        int port;
        port=stdin.nextInt();
        Socket socket;
        try{
            socket = new Socket(ip, port);
        } catch (IOException e) {
            return false;
        }
        System.out.println("Correctly connected to the Server");

       output = new ObjectOutputStream(socket.getOutputStream());
       input = new ObjectInputStream(socket.getInputStream());

        System.out.println("Choose your nickname");
        boolean correct=false;
        do{
            String s=stdin.nextLine();
            ChooseNicknameMessage message = new ChooseNicknameMessage(s);
            sendMessage(message);
            Object message2 = input.readObject();
            if(message2 instanceof CorrectNicknameMessage) {
                correct = true;
                setNickname(s);
            }
            if(message2 instanceof InvalidNicknameMessage)
                System.out.println("Nickname already chosen, please insert another one");


        }while(correct);

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

        if(cho=='2')
            this.setNumofPlayers(2);
        else
            this.setNumofPlayers(3);

        CreatePlayerInGameMessage message = new CreatePlayerInGameMessage(nickname,gamemode,numofPlayers);
        sendMessage(message);

        //Manca la parte della scelta del wizard
        return true;

        //Vedere come gestire l'arrivo dei messaggi
         /*    active=true;
        new Thread(() -> {
            try {
                while(active) {
                    Object messageFromServer = input.readObject();
                    ((MessageToClient) messageFromServer).action();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

     */

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
