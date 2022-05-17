package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toClient.StatesMessages.*;
import it.polimi.ingsw.messages.toServer.PingToServerMessage;

public class ClientMessageHandler {

    private Client client;
    private View view;
    public ClientMessageHandler(Client client, View view){
        this.client=client;
        this.view=view;
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
        //Mandare messaggio di errore
    }

    public void process(InvalidStudentEffectMessage message){

    }

    public void process(InvalidIslandMessage message){

    }

    public void process(WizardsListMessage message){
        view.chooseWizard(message.getList());
    }

    public void process(TimeExpiredMessage message){

    }

    public void process(DisconnectMessage message){

    }

    public void process(NoGameMessage message){

    }

    public void process(InvalidWizardMessage message){

    }

    public void process(PlayerStateMessage message){
        client.nextMove(message.getState());
    }

    public void process(BoardUpdateMessage message){
        view.setBoard(message.getBoard());
    }

    public void process(SetTurnMessage message){
        client.setTurn(message.getNickname());
    }

    public void process(StartGameMessage message){
        view.printStartGame();
    }

    public void process(NicknameMessage message){
        view.chooseNickname(message.getCheck(),message.getReconnect());
    }

    public void process(ConnectMessage message){
        if(message.getReconnect()){
            //mi sto riconnettendo alla partita
        }else{
            client.gameSetup();
        }
    }

    public void process(PlanceUpdateMessage message){
            view.setPlayers(message.getPlayers());
    }

    public void process(AssistantChoosedMessage message){

    }

    public void process(StudentHallChoosedMessage message){

    }

    public void process(StudentIslandChoosedMessage message){

    }

    public void process(CharacterChoosedMessage message){

    }

    public void process(CloudChoosedMessage message){

    }

    public void process(MotherNatureMoveMessage message){

    }
}



