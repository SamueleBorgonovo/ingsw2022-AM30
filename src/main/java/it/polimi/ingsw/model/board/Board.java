package it.polimi.ingsw.model.board;

import it.polimi.ingsw.controller.virtualView.BoardView;
import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.controller.virtualView.IslandView;
import it.polimi.ingsw.exceptions.OutOfCoinsException;
import it.polimi.ingsw.model.effects.*;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;

import java.util.ArrayList;

/**
 * Class used to initialize and manage clouds, archipelago, characters bag
 * and coins reserve of the game
 */
public class Board {
    private static final int NUMBER_OF_COINS = 20;
    private static final int TOTAL_STUDENTS_COLOR_BAG = 26;
    private static final int INITIAL_STUDENTS_COLOR_BAG = 2;
    private static final int NUMBER_OF_CHARACTERS = 3;

    private final ArrayList<Cloud> clouds = new ArrayList<>();
    private final Archipelago archipelago;
    private final ArrayList<Student> bag = new ArrayList<>();
    private int coinReserve;
    private final ArrayList<Characters> characters = new ArrayList<>();

    /**
     * Constructor of the Board Class that initialize the archipelago, the clouds,
     * the characters, the bag and the coins reserve based on the game mode chosen and
     * the number of players in the game
     * @param gameMode game mode of the game
     * @param numOfPlayers number of players in the game
     */
    public Board(GameMode gameMode, int numOfPlayers){
        ArrayList<Effect> effects = new ArrayList<>();
        int i, randomIndex;

        for (Student student : Student.values())
            for (i = 0; i < INITIAL_STUDENTS_COLOR_BAG; i++)
                bag.add(student);
        archipelago = new Archipelago();
        for (Island island : archipelago.getIslands()){
            if (island.getIslandID() != archipelago.getMotherNature().isOn() && island.getIslandID() != (((archipelago.getMotherNature().isOn() +5) %12) +1))
                island.addStudent(this.getAndRemoveRandomBagStudent(1).get(0));
        }
        for (Student student : Student.values())
            for (i = 0; i < TOTAL_STUDENTS_COLOR_BAG - INITIAL_STUDENTS_COLOR_BAG; i++)
                bag.add(student);
        for (i = 1; i <= numOfPlayers; i++ )
            clouds.add(new Cloud(i));
        for (Cloud cloud : clouds)
            cloud.setStudents(this.getAndRemoveRandomBagStudent(numOfPlayers + 1));
        if (gameMode == GameMode.EXPERTMODE){
            coinReserve = NUMBER_OF_COINS - numOfPlayers;
            effects.add(new Effect1());
            effects.add(new Effect2());
            effects.add(new Effect3());
            effects.add(new Effect4());
            effects.add(new Effect5());
            effects.add(new Effect6());
            effects.add(new Effect7());
            effects.add(new Effect8());
            effects.add(new Effect9());
            effects.add(new Effect10());
            effects.add(new Effect11());
            effects.add(new Effect12());
            for (i = 0; i < NUMBER_OF_CHARACTERS; i++){
                randomIndex = (int) (Math.random() * effects.size());
                characters.add(new Characters(effects.get(randomIndex)));
                effects.remove(randomIndex);
            }
        }
    }

    /**
     * Method used to get the number of student in the bag
     * @return the size of the bag
     */
    public int getNumOfStudentsBag() {
        return bag.size();
    }

    /**
     * Method used to add some students to the bag
     * @param Students students to be added to the bag
     */
    public void addStudentBag(ArrayList<Student> Students){
        bag.addAll(Students);
    }

    /**
     * Method used to get some student from the bag and then remove them from the bag
     * @param NumOfStudents number of students to get and the remove
     * @return students removed
     */
    public ArrayList<Student> getAndRemoveRandomBagStudent(int NumOfStudents) {
        ArrayList<Student> toReturn = new ArrayList<>();
        int index;

        for (int counter = 0; counter < NumOfStudents; counter++) {
            if (bag.size() > 0) {
                index = (int) (Math.random() * bag.size());
                toReturn.add(bag.get(index));
                bag.remove(index);
            }
        }
        return toReturn;
    }

    /**
     * Method used to get the number of coins in the reserve
     * @return the number of coins in the coins reserve
     */
    public int getCoinReserve(){
        return coinReserve;
    }

    /**
     * Method used to add some coins in the coins reserve
     * @param coins number of coins to be added to the reserve
     */
    public void addCoinsToReserve(int coins){
        coinReserve = coinReserve + coins;
    }

    /**
     * Method used to remove some coins from the coins reserve
     * @param coins number of coins to be removed from the reserve
     * @throws OutOfCoinsException if coins to remove are more than the coins in the reserve
     */
    public void removeCoinsFromReserve(int coins) throws OutOfCoinsException {
        if(coinReserve < coins)
            throw new OutOfCoinsException();
        else
            coinReserve = coinReserve - coins;
    }

    /**
     * Method used to get the character
     * @return characters
     */
    public ArrayList<Characters> getCharacters(){
        return characters;
    }

    /**
     * Method used to get the archipelago
     * @return archipelago
     */
    public Archipelago getArchipelago(){
        return archipelago;
    }

    /**
     * Method used to get clouds
     * @return clouds
     */
    public ArrayList<Cloud> getClouds(){
        return clouds;
    }

    /**
     * Method used to get the cloud by its cloudID
     * @param cloudID cloudID to look for in clouds
     * @return the cloud found or null
     */
    public Cloud getCloud(int cloudID){
        for(Cloud cloud : clouds)
            if(cloud.getCloudID()==cloudID)
                return cloud;
        return null;
    }

    /**
     * Method used to create a simplified and serializable version of Board Class
     * which can be sent to the client
     * @return the simplified and serializable class
     */
    public BoardView getBoardView(){
        ArrayList<CloudView> cloudsViews = new ArrayList<>();
        ArrayList<IslandView> islandsView = new ArrayList<>();
        ArrayList<CharacterView> characterViews = new ArrayList<>();

        for(Cloud cloud : this.clouds)
            cloudsViews.add(cloud.getCloudView());
        for(Island island : this.getArchipelago().getIslands())
             islandsView.add(island.getIslandView());
        for(Characters character : this.getCharacters())
            characterViews.add(character.getCharacterView());
        return new BoardView(cloudsViews, islandsView,this.getArchipelago().getMotherNature().isOn(),characterViews);
    }
}