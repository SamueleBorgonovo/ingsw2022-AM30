package it.polimi.ingsw.client.View.cli.Graphical;

import it.polimi.ingsw.controller.virtualView.CharacterView;
import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.controller.virtualView.IslandView;
import it.polimi.ingsw.controller.virtualView.PlayerView;
import it.polimi.ingsw.model.game.EffectHandlerInterface;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Wizard;
import java.util.ArrayList;

/**
 * Class used to draw in CLI some game object utilizing some characters in UTF-8
 */
public class Graphic {

    /**
     * Method used to print Eriatys Logo in CLI
     */
    public void printLogo() {
        String logo = """
                       
                 ▄▄▄▄▄▄▄▄▄▄▄▄▄▄            ▄▄
              ▄█▀▀  ▓██    ▀▀██           ▓███                        ▄
             ▐█▀   ▓██▌  ▄▄▄  ▀                                      ▓█
             ██    ▓██████▀▀█   ▓███▄████ ▓██▌   ▄██▀███ ▄███ ▓███ ▓████▌▄████ ▀███ ▓█▀▀▀██
             ▀██   ▓████    ▀    ▓██▀ █▀   ▓█▌  ▓█▌  ███  ▓███▀▓██  ▓██    ▀███  ▓█ ▓███▄▄
               ▀█▌ ▓███        ▄ ▓██       ▓█▌ ▓██▌ ▄███  ▓██  ▓██  ▓██     ▀███▄█   ▀█████
                   ▓██▌      ▄█▌ ▓██       ▓██ ▀████▀███▄ ▓██  ▓██▄ ▓██▄▄     ▓██   ▓▌  ▄██
                  ▄████▄▄▄▄███▀  ▀▀         ▀▀   ▀▀   ▀▀  ▀▀    ▀▀▀  ▀▀▀      ▓█    ▀▀▀▀▀
                                                                            ▄██
                                                                            ███   █
                                                                            ▀▀███▀▀
        """;
        System.out.println(logo);
    }

    /**
     * Method used to print islands in CLI. The island with Mother Nature is red
     * @param islands the islands to print
     * @param motherNature the number of the island that contains Mother Nature
     */
    public void printArchipelago(ArrayList<IslandView> islands, int motherNature) {
        int numOfIslands = islands.size();
        int i;

        printTopEdge(17 + 8*(numOfIslands-2), "ISLANDS");
        System.out.println();

        System.out.print("│");
        for(IslandView island : islands) {
            if(motherNature == island.getIslandID()) {
                if(island.getIslandID()>1 && island.getIslandID()<10)
                    System.out.print(Colors.RED.getCode() + "    " + island.getIslandID() + Colors.RESET.getCode());
                else
                    System.out.print(Colors.RED.getCode() + "   " + island.getIslandID() + Colors.RESET.getCode());
            } else {
                if(island.getIslandID()>1 && island.getIslandID()<10)
                    System.out.print("    " + island.getIslandID());
                else
                    System.out.print("   " + island.getIslandID());
            }
            if(island.isStop())
                System.out.print(" ✗ ");
            else
                System.out.print("   ");
        }
        System.out.println("│");
        for(IslandView island : islands){
            System.out.print("│ " + island.getNumOfTowers() + " : ");
            if(island.getTower()!=null)
                System.out.print(Colors.values()[island.getTower().ordinal()+5].getCode() + Symbols.TOWER_FULL.getCode() + " " + Colors.RESET.getCode());
            else
                System.out.print(Symbols.TOWER_EMPTY.getCode() + " ");
        }
        System.out.println("│");
        for(Student student : Student.values()){
            for(IslandView island : islands) {
                i=0;
                for(Student s : island.getStudents())
                    if(s.ordinal()== student.ordinal())
                        i++;
                System.out.print("│ " + i + " : ");
                System.out.print(Colors.values()[student.ordinal()].getCode() + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
            }
            System.out.println("│");
        }
        printBottomEdge(17 + 8*(numOfIslands-2));
        System.out.println();
    }

    /**
     * Method used to print the Players Plances in CLI
     * @param players the array that contains the information of each Player like board, nickname and coins
     */
    public void printPlances(ArrayList<PlayerView> players){
        int i;
        int studentEntrance = 0;

        for(PlayerView player : players)
            printTopEdge(39, player.getNickname());
        System.out.println();

        for(i=0; i< players.size();i++)
            System.out.print("│  E             H            P    T  │ ");
        System.out.println();

        for(Student student : Student.values()) {
            for (PlayerView player : players) {
                System.out.print("│ ");
                if (studentEntrance < player.getPlance().getEntrance().size())
                    System.out.print(Colors.values()[player.getPlance().getEntrance().get(studentEntrance).ordinal()].getCode() + "● " + Colors.RESET.getCode());
                else
                    System.out.print("  ");
                if (studentEntrance + 1 < player.getPlance().getEntrance().size())
                    System.out.print(Colors.values()[player.getPlance().getEntrance().get(studentEntrance + 1).ordinal()].getCode() + "●" + Colors.RESET.getCode());
                else
                    System.out.print(" ");
                System.out.print(" │ ");
                for(i=0; i<10; i++){
                    if(i < player.getPlance().getNumberOfStudentHall(student))
                        System.out.print(Colors.values()[student.ordinal()].getCode() + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
                    else
                        System.out.print(Symbols.STUDENT_EMPTY.getCode() + " ");
                }
                System.out.print("│ ");
                if(player.getPlance().getProfessors().contains(Professor.values()[student.ordinal()]))
                    System.out.print(Colors.values()[student.ordinal()].getCode() + Symbols.PROFESSOR_FULL.getCode() + " " + Colors.RESET.getCode());
                else
                    System.out.print(Symbols.PROFESSOR_EMPTY.getCode() + " ");
                System.out.print("│ ");
                if (studentEntrance < player.getPlance().numOfTowers())
                    System.out.print(Colors.values()[player.getPlance().getTower().ordinal() + 5].getCode() + "▲ " + Colors.RESET.getCode());
                else
                    System.out.print("  ");
                if (studentEntrance + 1 < player.getPlance().numOfTowers())
                    System.out.print(Colors.values()[player.getPlance().getTower().ordinal() + 5].getCode() + "▲ " + Colors.RESET.getCode());
                else
                    System.out.print("  ");
                System.out.print("│ ");
            }
            System.out.println();
            studentEntrance += 2;
        }

        for(i=0; i< players.size();i++)
            printBottomEdge(39);
        System.out.println();

        if(players.get(0).getCoins()>=0) {
            for (PlayerView player : players)
                    System.out.print("COINS : " + player.getCoins() + "                               ");
                System.out.println();
        }
        System.out.println();
    }

    /**
     * Method used to print the Assistant cards in CLI
     * @param assistants the assistants to print
     * @param wizard the wizard chosen by the player, used to change the color of the assistant's name
     */
    public void printAssistants(ArrayList<Assistant> assistants, Wizard wizard){
        int i;

        for(i=1; i<=assistants.size();i++)
            printTopEdge(13,String.valueOf(i));
        System.out.println();

        for(Assistant assistant : assistants){
            System.out.print("│");
            for(i=0; i<(11-assistant.toString().length())/2;i++)
                System.out.print(" ");
            if(wizard.equals(Wizard.WIZARD_GREEN))
                System.out.print(Colors.values()[wizard.ordinal()].getCode() + assistant + Colors.RESET.getCode());
            else
                System.out.print(Colors.values()[wizard.ordinal() + 1].getCode() + assistant + Colors.RESET.getCode());
            for(i=0; i<(11-assistant.toString().length())/2 + (11-assistant.toString().length())%2;i++)
                System.out.print(" ");
            System.out.print("│ ");
        }
        System.out.println();
        for(i=0; i<assistants.size();i++)
            System.out.print("│           │ ");
        System.out.println();

        for(Assistant assistant : assistants){
            if(assistant.getValue()<10)
                System.out.print("│  VAL : " + assistant.getValue() + "  │ ");
            else
                System.out.print("│  VAL : " + assistant.getValue() + " │ ");
        }
        System.out.println();

        for(Assistant assistant : assistants)
            System.out.print("│  MOV : " + assistant.getMovement() + "  │ ");
        System.out.println();

        for(i=0; i<assistants.size();i++)
            printBottomEdge(13);
        System.out.println();
    }

    /**
     * Method used to print clouds in CLI
     * @param clouds the clouds to print
     */
    public void printClouds(ArrayList<CloudView> clouds){
        int i;

        printTopEdge(17 + 8*(clouds.size()-2), "CLOUDS");
        System.out.println();

        System.out.print("│");
        for(CloudView cloud : clouds) {
            if(cloud.isChosen()) {
                if(cloud.getCloudID()>1 && cloud.getCloudID()<10)
                    System.out.print(Colors.WHITE.getCode() + "    " + cloud.getCloudID() + "   " + Colors.RESET.getCode());
                else
                    System.out.print(Colors.WHITE.getCode() + "   " + cloud.getCloudID() + "   " + Colors.RESET.getCode());
            } else {
                if(cloud.getCloudID()>1 && cloud.getCloudID()<10)
                    System.out.print("    " + cloud.getCloudID() + "   ");
                else
                    System.out.print("   " + cloud.getCloudID() + "   ");
            }
        }
        System.out.println("│");

        for(Student student : Student.values()){
            for(CloudView cloud : clouds) {
                if(cloud.isChosen()) {
                    System.out.print("│");
                    System.out.print(Colors.WHITE.getCode() + " 0 :" + " "  + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
                } else {
                    i = 0;
                    for (Student s : cloud.getStudents())
                        if (s.ordinal() == student.ordinal())
                            i++;
                    System.out.print("│ " + i + " :");
                    System.out.print(Colors.values()[student.ordinal()].getCode() + " "  + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
                }
            }
            System.out.println("│");
        }

        printBottomEdge(17 + 8*(clouds.size()-2));
        System.out.println();
    }

    /**
     * Method used to print the Character cards in CLI
     * @param characters the Characters to print
     * @param effectHandler contains more information about some Characters
     */
    public void printCharacters(ArrayList<CharacterView> characters, EffectHandlerInterface effectHandler){
        int i;

        for(i=1; i<= characters.size();i++)
            printTopEdge(13, String.valueOf(i));
        System.out.println();

        for(CharacterView character : characters){
            System.out.print("│");
            for(i=0; i<(11-character.getName().length())/2;i++)
                System.out.print(" ");
            System.out.print(Colors.values()[character.getCost() + 10].getCode() + character.getName() + Colors.RESET.getCode());
            for(i=0; i<(11-character.getName().length())/2 + (11-character.getName().length())%2;i++)
                System.out.print(" ");
            System.out.print("│ ");
        }
        System.out.println();

        for(CharacterView character : characters){
            if("JESTER".equals(character.getName())){
                System.out.print("│   ");
                for(i=0;i<3;i++)
                    System.out.print(Colors.values()[effectHandler.getEffect7students().get(i).ordinal()].getCode() + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
                System.out.print("  │ ");
            } else if("MONK".equals(character.getName()) && effectHandler.getEffect1students().size()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getEffect1students().size();i++)
                    System.out.print(" ");
                for(Student student : effectHandler.getEffect1students())
                    System.out.print(Colors.values()[student.ordinal()].getCode() + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
                for(i=0; i<5-effectHandler.getEffect1students().size();i++)
                    System.out.print(" ");
                System.out.print("│ ");
            } else if("PRINCESS".equals(character.getName()) && effectHandler.getEffect11students().size()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getEffect11students().size();i++)
                    System.out.print(" ");
                for(Student student : effectHandler.getEffect11students())
                    System.out.print(Colors.values()[student.ordinal()].getCode() + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
                for(i=0; i<5-effectHandler.getEffect11students().size();i++)
                    System.out.print(" ");
                System.out.print("│ ");
            } else if("GRANDMA".equals(character.getName()) && effectHandler.getNumofislandstops()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getNumofislandstops();i++)
                    System.out.print(" ");
                for(i=0; i<effectHandler.getNumofislandstops();i++)
                    System.out.print("✗ ");
                for(i=0; i<5-effectHandler.getNumofislandstops();i++)
                    System.out.print(" ");
                System.out.print("│ ");
            }
            else
                System.out.print("│           │ ");
        }
        System.out.println();

        for(CharacterView character : characters){
            if("JESTER".equals(character.getName())){
                System.out.print("│   ");
                for(i=3;i<6 && effectHandler.getEffect7students().size()!=0;i++) {
                    if (i < effectHandler.getEffect7students().size())
                        System.out.print(Colors.values()[effectHandler.getEffect7students().get(i).ordinal()].getCode() + Symbols.STUDENT_FULL.getCode() + " " + Colors.RESET.getCode());
                    else
                        System.out.print("  ");
                }
                System.out.print("  │ ");
            }
            else
                System.out.print("│           │ ");
        }
        System.out.println();

        for(CharacterView character : characters)
                System.out.print("│  COST : " + character.getCost() + " │ ");
        System.out.println();

        for(i=0; i<characters.size();i++)
            printBottomEdge(13);
        System.out.println();
    }

    /**
     * Method used to print Wizard cards in CLI
     * @param wizards the Wizards to print
     */
    public void printWizards(ArrayList<Wizard> wizards){
        int i;

        for(i=1; i<=wizards.size();i++)
            printTopEdge(13, String.valueOf(i));
        System.out.println();

        for(i=1; i<=wizards.size();i++)
            System.out.print("│  WIZARD   │ ");
        System.out.println();

        for(Wizard wizard : wizards) {
            System.out.print("│");
            switch (wizard) {
                case WIZARD_GREEN -> System.out.print(Colors.GREEN.getCode() + "   GREEN   " + Colors.RESET.getCode());
                case WIZARD_BLUE -> System.out.print(Colors.BLUE.getCode() + "   BLUE    " + Colors.RESET.getCode());
                case WIZARD_PINK -> System.out.print(Colors.PURPLE.getCode() + "   PINK    " + Colors.RESET.getCode());
                case WIZARD_YELLOW -> System.out.print(Colors.BRIGHT_YELLOW.getCode() + "  YELLOW   " + Colors.RESET.getCode());
            }
            System.out.print("│ ");
        }
        System.out.println();

        for(i=0; i<wizards.size();i++)
            System.out.print("│           │ ");
        System.out.println();

        for(i=0; i<wizards.size();i++)
            System.out.print("│           │ ");
        System.out.println();

        for(i=0; i<wizards.size();i++)
            printBottomEdge(13);
        System.out.println();
    }

    /**
     * Method used to print top edge of drawings in CLI
     * @param length the length of the edge
     * @param string the string to put in the center of the edge
     */
    private void printTopEdge(int length, String string){
        int i;

        System.out.print(Symbols.CORNER_TOP_LEFT.getCode());
        for(i=0; i<(length - 4 - string.length())/2; i++)
            System.out.print("─");
        System.out.print(Symbols.HALF_DASH_LEFT.getCode() + string + Symbols.HALF_DASH_RIGHT.getCode());
        for(i=0; i<(length - 4 - string.length())/2 + (length - 2 - string.length())%2; i++)
            System.out.print("─");
        System.out.print(Symbols.CORNER_TOP_RIGHT.getCode() + " ");
    }

    /**
     *  Method used to print bottom edge of drawings in CLI
     * @param length the length of the edge
     */
    private void printBottomEdge(int length){
        int i;

        System.out.print(Symbols.CORNER_DOWN_LEFT.getCode());
        for(i=0; i<length - 2; i++)
            System.out.print("─");
        System.out.print(Symbols.CORNER_DOWN_RIGHT.getCode() + " ");
    }
}


