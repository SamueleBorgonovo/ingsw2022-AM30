package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.server.ClientHandler;

public class ChooseAssistantMessage extends Message {
    Assistant assistant;
    public void ChooseAssistantMessage(Assistant assistant){
        this.assistant=assistant;
    }

    public void action(ClientHandler clienthandler){
        clienthandler.getGameHandler().chooseAssistant(clienthandler.getNickname(),assistant);
    }


}
