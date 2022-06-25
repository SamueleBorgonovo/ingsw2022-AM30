package it.polimi.ingsw.client;

import it.polimi.ingsw.client.View.View;
import it.polimi.ingsw.messages.toClient.*;
import it.polimi.ingsw.messages.toClient.InvalidMoveMessages.*;
import it.polimi.ingsw.messages.toClient.StatesMessages.*;
import it.polimi.ingsw.messages.toServer.PingToServerMessage;

/**
 * ClientMessageHandler handles all messages received from the server
 */
public class ClientMessageHandler {

    private final Client client;
    private final View view;

    /**
     * Constructor ClientMessageHandler instantiates client and view
     * @param client client connected with the server
     * @param view view of the client
     */
    public ClientMessageHandler(Client client, View view){
        this.client=client;
        this.view=view;
    }

    /**
     * Method used to process PingToClientMessage
     * @param message message received from the server
     */
    public void process(PingToClientMessage message) {
        if (message.isPing()) {
            PingToServerMessage pong = new PingToServerMessage(false);
            client.sendMessage(pong);
        }
        else client.stopTimer();
    }

    /**
     * Method used to process InvalidAssistantMessage
     * @param message message received from the server
     */
    public void process(InvalidAssistantMessage message){
        view.printInvalidAssistant();
    }

    /**
     * Method used to process InvalidTurnMessage
     * @param message message received from the server
     */
    public void process(InvalidTurnMessage message){
        view.printInvalidTurn();
    }

    /**
     * Method used to process InvalidStopMessage
     * @param message message received from the server
     */
    public void process(InvalidStopMessage message){
        view.printInvalidStop();
    }

    /**
     * Method used to process OutOfCoinsMessage
     * @param message message received from the server
     */
    public void process(OutOfCoinsMessage message){
        view.printInvalidCoins();
    }

    /**
     * Method used to process InvalidCharacterMessage
     * @param message message received from the server
     */
    public void process(InvalidCharacterMessage message){
        view.printInvalidCharacter();
    }

    /**
     * Method used to process InvalidCloudMessage
     * @param message message received from the server
     */
    public void process(InvalidCloudMessage message){
        view.printInvalidCloud();
    }

    /**
     * Method used to process InvalidValueMessage
     * @param message message received from the server
     */
    public void process(InvalidValueMessage message){
        view.printInvalidValue();
    }

    /**
     * Method used to process InvalidStudentMessage
     * @param message message received from the server
     */
    public void process(InvalidStudentMessage message){
        view.printInvalidStudent();
    }

    public void process(InvalidStudentEffectMessage message){
        view.printInvalidStudent();
    }

    /**
     * Method used to process InvalidIslandMessage
     * @param message message received from the server
     */
    public void process(InvalidIslandMessage message){
        view.printInvalidIsland();
    }

    /**
     * Method used to process WizardsListMessage
     * @param message message received from the server
     */
    public void process(WizardsListMessage message){
        view.chooseWizard(message.getList());
    }

    /**
     * Method used to process DisconnectMessage
     * @param message message received from the server
     */
    public void process(DisconnectMessage message){
        client.handleDisconnection(message.getNickname(), message.isTimeout(),message.isWin());
    }

    /**
     * Method used to process InvalidWizardMessage
     * @param message message received from the server
     */
    public void process(InvalidWizardMessage message){
        view.printInvalidWizard();
    }

    /**
     * Method used to process PlayerStateMessage
     * @param message message received from the server
     */
    public void process(PlayerStateMessage message){
        view.nextMove(message.getState());
    }

    /**
     * Method used to process BoardUpdateMessage
     * @param message message received from the server
     */
    public void process(BoardUpdateMessage message){
        view.setBoard(message.getBoard());
    }

    /**
     * Method used to process SetTurnMessage
     * @param message message received from the server
     */
    public void process(SetTurnMessage message){
        client.setTurn(message.getNickname(), message.isassistantPhase());
    }

    /**
     * Method used to process StartGameMessage
     * @param message message received from the server
     */
    public void process(StartGameMessage message){
        view.printStartGame(message.isRestart());
    }

    /**
     * Method used to process NicknameMessage
     * @param message message received from the server
     */
    public void process(NicknameMessage message){
        view.chooseNickname(message.getCheck(),message.getReconnect());
    }

    /**
     * Method used to process ConnectMessage
     * @param message message received from the server
     */
    public void process(ConnectMessage message){
        view.printPlayerConnection(message.getNickname(),message.getReconnect());
    }

    /**
     * Method used to process PlanceUpdateMessage
     * @param message message received from the server
     */
    public void process(PlanceUpdateMessage message){
            view.setPlayers(message.getPlayers());
    }

    /**
     * Method used to process AssistantChoosedMessage
     * @param message message received from the server
     */
    public void process(AssistantChosenMessage message){
        view.printAssistantChosen(message.getNickname(), message.getAssistant());
    }

    /**
     * Method used to process StudentHallChoosedMessage
     * @param message message received from the server
     */
    public void process(StudentHallChosenMessage message){
        view.printStudentToHall(message.getNickname(),message.getStudent());
    }

    /**
     * Method used to process StudentIslandChoosedMessage
     * @param message message received from the server
     */
    public void process(StudentIslandChosenMessage message){
        view.printStudentToIsland(message.getNickname(),message.getStudent(),message.getIslandID());
    }

    /**
     * Method used to process CharacterChoosedMessage
     * @param message message received from the server
     */
    public void process(CharacterChosenMessage message){
        view.printCharacterChosen(message.getNickname(),message.getCharacter());
    }

    /**
     * Method used to process CloudChoosedMessage
     * @param message message received from the server
     */
    public void process(CloudChosenMessage message){
        view.printCloudChosen(message.getNickname(),message.getCloud());
    }

    /**
     * Method used to process MotherNatureMoveMessage
     * @param message message received from the server
     */
    public void process(MotherNatureMoveMessage message){
        view.printMotherNatureMovement(message.getNickname(), message.getIslandId());
    }

    /**
     * Method used to process WinInstantlyMessage
     * @param message message received from the server
     */
    public void process(WinInstantlyMessage message){
        view.printWinnerInstantly(message.getNickname(),message.getWinType());
    }

    /**
     * Method used to process WinEndRoundMessage
     * @param message message received from the server
     */
    public void process(WinEndRoundMessage message){
        view.printWinnerEndRound(message.getNickname(),message.getWinType());
    }

    /**
     * Method used to process WaitingForPlayersMessage
     * @param message message received from the server
     */
    public void process(WaitingForPlayersMessage message){
        view.printWaitingForPlayers(message.isLobby());
    }

    /**
     * Method used to process EffectHandlerUpdateMessage
     * @param message message received from the server
     */
    public void process(EffectHandlerUpdateMessage message){
        view.setEffectHandler(message.getEffectHandler());
    }

    /**
     * Method used to process SettingReconnectMessage
     * @param message message received from the server
     */
    public void process(SettingReconnectMessage message){
        client.setSettings(message.getGameMode(),message.getNumOfPlayers(),message.getWizard());
    }

    /**
     * Method used to process CorrectlyConnectedMessage
     * @param message message received from the server
     */
    public void process(CorrectlyConnectedMessage message){
        view.correctlyConnected();
    }

    /**
     * Method used to process NoGameMessage
     * @param message message received from the server
     */
    public void process(NoGameMessage message){
        view.noGameReconnect();
    }
}



