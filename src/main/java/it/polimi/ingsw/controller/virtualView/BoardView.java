package it.polimi.ingsw.controller.virtualView;

import it.polimi.ingsw.model.board.Archipelago;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.Cloud;
import it.polimi.ingsw.model.game.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class BoardView implements Serializable {
    private ArrayList<CloudView> clouds = new ArrayList<>();
    private ArrayList<IslandView> islandViews;
    private int motherNature;
    private ArrayList<CharacterView> characters = new ArrayList<>();

    public BoardView(ArrayList<CloudView> clouds, ArrayList<IslandView> islandViews, int motherNature, ArrayList<CharacterView> characters) {
        this.clouds = clouds;
        this.islandViews = islandViews;
        this.motherNature = motherNature;
        this.characters = characters;
    }

    public ArrayList<CloudView> getClouds() {
        return clouds;
    }

    public ArrayList<IslandView> getIslandViews() {
        return islandViews;
    }

    public int getMotherNature() {
        return motherNature;
    }

    public ArrayList<CharacterView> getCharacters() {
        return characters;
    }
}
