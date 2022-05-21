package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.game.EffectHandler;

public class EffectHandlerUpdateMessage extends MessageToClient{
    private EffectHandler effectHandler;
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
