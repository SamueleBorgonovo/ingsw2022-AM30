package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.toClient.PingToClientMessage;
import it.polimi.ingsw.messages.toServer.*;
import it.polimi.ingsw.server.ClientHandlerInterface;

public class MessageHandler {
    GameHandler gameHandler;

    public MessageHandler(){
        gameHandler = new GameHandler();
    }

    public void process(ChooseAssistantMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseAssistant(clientHandler,message.getAssistant());
    }

    public void process(ChooseCharacterMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseCharacter(clientHandler,message.getCharacter());
    }

    public void process(ChooseCloudMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseCloud(clientHandler,message.getCloudID());
    }

    public void process(ChooseIslandEffectMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseIslandEffect(clientHandler,message.getIslandID());
    }

    public void process(ChooseNicknameMessage message, ClientHandlerInterface clientHandler){
        if(message.getReconnect())
            gameHandler.reconnectPlayer(clientHandler);
        else
            gameHandler.checkNickname(clientHandler,message.getNickname());
    }

    public void process(ChooseStudentsEffectMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseStudentsEffect(clientHandler, message.getStudents());
    }

    public void process(ChooseWizardMessage message, ClientHandlerInterface clientHandler){
        gameHandler.chooseWizard(clientHandler,message.getWizard());
    }

    public void process(CreatePlayerInGameMessage message, ClientHandlerInterface clientHandler){
        gameHandler.addPlayer(clientHandler, message.getGamemode(), message.getNumofplayers());
    }

    public void process(MoveMotherNatureMessage message, ClientHandlerInterface clientHandler){
        gameHandler.moveMotherNature(clientHandler, message.getMovement());
    }

    public void process(MoveStudentToHallMessage message, ClientHandlerInterface clientHandler){
        gameHandler.moveStudentToHall(clientHandler, message.getStudent());
    }

    public void process(MoveStudentToIslandMessage message, ClientHandlerInterface clientHandler){
        gameHandler.moveStudentToIsland(clientHandler, message.getIslandID(), message.getStudent());
    }

    public void process(PingToServerMessage message, ClientHandlerInterface clientHandler) {
        if (message.isPing()) {
            PingToClientMessage message2 = new PingToClientMessage(false);
            clientHandler.sendMessageToClient(message2);
        }else clientHandler.stopTimer();
    }

}
