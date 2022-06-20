package it.polimi.ingsw.controller.virtualView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class BoardView is used to send the updated board to the client from the server
 */
public class BoardView implements Serializable {
    private final ArrayList<CloudView> clouds;
    private final ArrayList<IslandView> islandViews;
    private final int motherNature;
    private final ArrayList<CharacterView> characters;

    /**
     * Constructor BoardView sets instances of all the attributes of the board
     * @param clouds board's clouds
     * @param islandViews board's islands
     * @param motherNature islandID of the motherNature position
     * @param characters board's characters
     */
    public BoardView(ArrayList<CloudView> clouds, ArrayList<IslandView> islandViews, int motherNature, ArrayList<CharacterView> characters) {
        this.clouds = clouds;
        this.islandViews = islandViews;
        this.motherNature = motherNature;
        this.characters = characters;
    }

    /**
     * Method getClouds returns the cloudViews
     * @return the cloudViews
     */
    public ArrayList<CloudView> getClouds() {
        return clouds;
    }

    /**
     * Method getIslandViews returns the islandViews
     * @return the islandViews
     */
    public ArrayList<IslandView> getIslandViews() {
        return islandViews;
    }

    /**
     * Method getMotherNature returns the motherNature's position
     * @return the motherNature's position
     */
    public int getMotherNature() {
        return motherNature;
    }

    /**
     * Method getCharacters returns the characterViews
     * @return the characterViews
     */
    public ArrayList<CharacterView> getCharacters() {
        return characters;
    }
}
