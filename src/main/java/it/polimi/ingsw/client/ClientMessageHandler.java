package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toServer.PingToServerMessage;

public class ClientMessageHandler {

    private Client client;
    public ClientMessageHandler(Client client){
        this.client=client;
    }

    public void process(PingToClientMessage message) {
        if (message.isPing()) {
            PingToServerMessage pong = new PingToServerMessage(false);
            client.sendMessage(pong);
        }
        else client.stopTimer();
    }

    public void process(InvalidAssistantMessage message){
    }

    public void process(InvalidTurnMessage message){
    }

    public void process(InvalidStopMessage message){

    }

    public void process(OutOfCoinsMessage message){

    }

    public void process(InvalidCharacterMessage message){

    }

    public void process(InvalidCloudMessage message){

    }

    public void process(InvalidValueMessage message){

    }

    public void process(InvalidStudentMessage message){

    }

    public void process(InvalidStudentEffectMessage message){

    }

    public void process(InvalidIslandMessage message){

    }

    public void process(WizardsListMessage message){
        //Fa vedere a video/cli la lista del messaggio  //metodo getList()
    }

    public void process(TimeExpiredMessage message){

    }

    public void process(DisconnectMessage message){

    }

    public void process(NoGameMessage message){

    }

    public void process(InvalidWizardMessage message){

    }
}
