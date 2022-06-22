package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.ClientHandlerInterface;

/**
 * Message to send the player's cloud choose
 */
public class ChooseCloudMessage extends MessageToServer {
    int cloudID;

    public ChooseCloudMessage(int cloudID){
        this.cloudID = cloudID;
    }

    public int getCloudID() {
        return cloudID;
    }

    public void action(ClientHandlerInterface clientHandler) {
        clientHandler.getMessageHandler().process(this, clientHandler);
    }

}
