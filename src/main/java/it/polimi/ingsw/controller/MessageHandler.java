package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.toClient.PingToClientMessage;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.server.ClientHandlerInterface;

/**
 * MessageHandler handles all messages received from the clients
 */
public class MessageHandler {
    GameHandler gameHandler;

    /**
     * Constructor messageHandler instantiates the gameHandler
     */
    public MessageHandler(){
        gameHandler = new GameHandler();
    }

    /**
     * Method used to process ChooseAssistantMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(ChooseAssistantMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseAssistant(clientHandler,message.getAssistant());
    }

    /**
     * Method used to process ChooseCharacterMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(ChooseCharacterMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseCharacter(clientHandler,message.getCharacter());
    }

    /**
     * Method used to process ChooseCloudMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(ChooseCloudMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseCloud(clientHandler,message.getCloudID());
    }

    /**
     * Method used to process ChooseIslandEffectMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(ChooseIslandEffectMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseIslandEffect(clientHandler,message.getIslandID());
    }

    /**
     * Method used to process ChooseNicknameMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(ChooseNicknameMessage message, ClientHandlerInterface clientHandler){
        if(message.getReconnect())
            gameHandler.reconnectPlayer(clientHandler);
        else
            gameHandler.checkNickname(clientHandler,message.getNickname(),message.getNewGame());
    }

    /**
     * Method used to process ChooseStudentsEffectMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(ChooseStudentsEffectMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseStudentsEffect(clientHandler, message.getStudents());
    }

    /**
     * Method used to process ChooseWizardMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(ChooseWizardMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseWizard(clientHandler,message.getWizard());
    }

    /**
     * Method used to process CreatePlayerInGameMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(CreatePlayerInGameMessage message, ClientHandlerInterface clientHandler){
        gameHandler.addPlayer(clientHandler, message.getGamemode(), message.getNumofplayers());
    }

    /**
     * Method used to process MoveMotherNatureMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(MoveMotherNatureMessage message, ClientHandlerInterface clientHandler){
        gameHandler.moveMotherNature(clientHandler, message.getMovement());
    }

    /**
     * Method used to process MoveStudentToHallMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(MoveStudentToHallMessage message, ClientHandlerInterface clientHandler){
        gameHandler.moveStudentToHall(clientHandler, message.getStudent());
    }

    /**
     * Method used to process MoveStudentToIslandMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(MoveStudentToIslandMessage message, ClientHandlerInterface clientHandler){
        gameHandler.moveStudentToIsland(clientHandler, message.getIslandID(), message.getStudent());
    }

    /**
     * Method used to process PingToServerMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(PingToServerMessage message, ClientHandlerInterface clientHandler) {
        if (message.isPing()) {
            PingToClientMessage message2 = new PingToClientMessage(false);
            clientHandler.sendMessageToClient(message2);
        }else {
            clientHandler.stopTimer();
        }
    }

    /**
     * Method used to process DisconnectionRespondMessage
     * @param message message received from client
     * @param clientHandler clientHandler of the client that sent the message
     */
    public void process(DisconnectionRespondMessage message,ClientHandlerInterface clientHandler){
        clientHandler.handleSocketDisconnection(false,true);
    }


}
