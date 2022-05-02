package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.exceptions.InvalidTurnException;
import it.polimi.ingsw.exceptions.WrongAssistantException;
import it.polimi.ingsw.messages.toServer.MessageToServer;
import it.polimi.ingsw.messages.toServer.CreatePlayerInGameMessageToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable, ClientHandlerInterface {
    private final int PING_PERIOD = 5000;
    private Server server;
    private Socket clientSocket;
    private boolean active;
    private final Thread pinger;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    GameHandler gameHandler;
    String nickname;

    public ClientHandler(Socket clientSocket, Server server) {
        this.server = server;
        gameHandler = server.getGameHandler();
        this.clientSocket = clientSocket;
        this.pinger = new Thread(() -> {
            while (active) {
                try {
                    Thread.sleep(PING_PERIOD);   //To add ping methods
                    // sendMessageToClient(ConnectionMessage.PING);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void run(){
        try {
            is = new ObjectInputStream(clientSocket.getInputStream());
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            boolean arrived=false;
            //pinger.start();
            try {
                   do {
                       Object messageFromClient = is.readObject();
                       if (messageFromClient instanceof CreatePlayerInGameMessageToServer) {
                           setNickname(((CreatePlayerInGameMessageToServer) messageFromClient).getNickname());
                           ((CreatePlayerInGameMessageToServer) messageFromClient).action(this);
                           arrived=true;
                       }
                       else{
                           //Manda il messaggio di darmi il messaggio giusto
                       }
                   }while(!arrived);

                   while(true){
                       Object messageFromClient = is.readObject();
                       ((MessageToServer) messageFromClient).action(this);
                   }

           } catch (ClassNotFoundException | InvalidTurnException | WrongAssistantException ignored) {}

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void sendMessage(MessageToServer messageToServer) throws IOException {
        os.writeObject(messageToServer);
        os.flush();
        os.reset();
    }
}