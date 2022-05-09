package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.player.Wizard;

import java.util.ArrayList;

public class WizardsListMessage extends MessageToClient{
    ArrayList<Wizard> list = new ArrayList<>();

    public WizardsListMessage(ArrayList<Wizard> list){
        this.list=list;
    }

    public void action(Client client){
        client.getMessageHandler().process(this);
    }

    public ArrayList<Wizard> getList() {
        return list;
    }
}
