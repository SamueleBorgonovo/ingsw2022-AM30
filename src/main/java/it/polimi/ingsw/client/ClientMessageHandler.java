package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toClient.InvalidMoveMessages.*;
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
        view.printInvalidAssistant();
    }

    public void process(InvalidTurnMessage message){
        view.printInvalidTurn();
    }

    public void process(InvalidStopMessage message){
        view.printInvalidStop();
    }

    public void process(OutOfCoinsMessage message){

    }

    public void process(InvalidCharacterMessage message){

    }

    public void process(InvalidCloudMessage message){
        view.printInvalidCloud();
    }

    public void process(InvalidValueMessage message){

    }

    public void process(InvalidStudentMessage message){
        view.printInvalidStudent();
    }

    public void process(InvalidStudentEffectMessage message){

    }

    public void process(InvalidIslandMessage message){
        view.printInvalidIsland();
    }

    public void process(WizardsListMessage message){
        view.chooseWizard(message.getList());
    }

    public void process(TimeExpiredMessage message){

    }

    public void process(DisconnectMessage message){
        client.handleDisconnection(message.getNickname(), message.isTimeout(),message.isWin());
    }

    public void process(NoGameMessage message){

    }

    public void process(InvalidWizardMessage message){
        view.printInvalidWizard();
    }

    public void process(PlayerStateMessage message){
        client.nextMove(message.getState());
    }

    public void process(BoardUpdateMessage message){
        view.setBoard(message.getBoard());
    }

    public void process(SetTurnMessage message){
        client.setTurn(message.getNickname(), message.isassistantPhase());
    }

    public void process(StartGameMessage message){
        view.printStartGame();
    }

    public void process(NicknameMessage message){
        view.chooseNickname(message.getCheck(),message.getReconnect());
    }

    public void process(ConnectMessage message){
        view.printPlayerConnection(message.getNickname(),message.getReconnect());
    }

    public void process(PlanceUpdateMessage message){
            view.setPlayers(message.getPlayers());
    }

    public void process(AssistantChoosedMessage message){
        view.printAssistantChosen(message.getNickname(), message.getAssistant());
    }

    public void process(StudentHallChoosedMessage message){
        view.printStudentToHall(message.getNickname(),message.getStudent());
    }

    public void process(StudentIslandChoosedMessage message){
        view.printStudentToIsland(message.getNickname(),message.getStudent(),message.getIslandID());
    }

    public void process(CharacterChoosedMessage message){
        view.printCharacterChosen(message.getNickname(),message.getCharacter());
    }

    public void process(CloudChoosedMessage message){
        view.printCloudChosen(message.getNickname(),message.getCloud());
    }

    public void process(MotherNatureMoveMessage message){
        view.printMotherNatureMovement(message.getNickname(), message.getIslandId());
    }

    public void process(WinIstantlyMessage message){
        view.printWinnerInstantly(message.getNickname(),message.getWinType());
    }

    public void process(WinEndRoundMessage message){
        view.printWinnerEndRound(message.getNickname(),message.getWinType());
    }

    public void process(WaitingForPlayersMessage message){
        view.printWaitingForPlayers(message.isLobby());
    }

    public void process(EffectHandlerUpdateMessage message){
        view.setEffectHandler(message.getEffectHandler());
    }
}



