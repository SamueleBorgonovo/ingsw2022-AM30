package src.main.java.org.example;
import java.util.ArrayList;

public class Dashboard {
    private int coins;
    private ArrayList<Assistant> assistantCards = new ArrayList<>();

    public Dashboard(int coins, ArrayList<Assistant> assistantCards) {
        this.coins = coins;
        this.assistantCards = assistantCards;
    }

    public int getCoins(){
        return coins;
    }

    public void addCoins(){
        coins++;
    }

    public void removeCoins(){
        coins--;
    }

    public ArrayList<Assistant> getAssistantCards() {
        return assistantCards;
    }

    public void removeAssistant(Assistant assistant){
        assistantCards.remove(assistant);
    }

    public int getValueAssistant (Assistant assistant){
        return assistant.getValueAssistant();
    }

    public int getMovementAssistant (Assistant assistant){
        return assistant.getMovementAssistant();
    }

}
