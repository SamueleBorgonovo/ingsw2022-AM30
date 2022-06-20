package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.game.EffectHandler;

/**
 * Message to send the effectHandler update to the client
 */
public class EffectHandlerUpdateMessage extends MessageToClient{
    private final EffectHandler effectHandler;
    public EffectHandlerUpdateMessage(EffectHandler effectHandler){
        this.effectHandler=effectHandler;
    }
    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public EffectHandler getEffectHandler() {
        return effectHandler;
    }
}
