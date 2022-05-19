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

public class Graphic {

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

    public void printArchipelago(ArrayList<IslandView> islands, int motherNature) {
        //for (int i = 0; i < 50; ++i) System.out.println();
        int numOfIslands = islands.size();
        int i;

        System.out.print("╭───");
        for(i=0; i<numOfIslands-2; i++)
            System.out.print("────");
        System.out.print("╴ISLANDS╶");
        for(i=0; i<numOfIslands-2; i++)
            System.out.print("────");
        System.out.println("───╮");
        System.out.print("│");
        for(IslandView island : islands) {
            if(motherNature == island.getIslandID()) {
                if(island.getIslandID()>1 && island.getIslandID()<10)
                    System.out.print(Colors.ANSI_RED.getCode() + "    " + island.getIslandID() + Colors.ANSI_RESET.getCode());
                else
                    System.out.print(Colors.ANSI_RED.getCode() + "   " + island.getIslandID() + Colors.ANSI_RESET.getCode());
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
                System.out.print(Colors.values()[island.getTower().ordinal()+5].getCode() + "▲ " + Colors.ANSI_RESET.getCode());
            else
                System.out.print("△ ");
        }
        System.out.println("│");
        for(Student student : Student.values()){
            for(IslandView island : islands) {
                i=0;
                for(Student s : island.getStudents())
                    if(s.ordinal()== student.ordinal())
                        i++;
                System.out.print("│ " + i + " : ");
                System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
            }
            System.out.println("│");
        }
        System.out.print("╰───────");
        for(i=1; i<numOfIslands; i++)
            System.out.print("────────");
        System.out.println("╯");
    }

    public void printPlances(ArrayList<PlayerView> players){
        int i;
        int studentEntrance = 0;

        for(PlayerView player : players){
            System.out.print("╭");
            for(i=0; i<(35 - player.getNickname().length())/2; i++)
                System.out.print("─");
            System.out.print("╴" + player.getNickname() + "╶");
            for(i=0; i<(35 - player.getNickname().length())/2 + (37 - player.getNickname().length())%2; i++)
                System.out.print("─");
            System.out.print("╮ ");
        }
        System.out.println();
        for(i=0; i< players.size();i++)
            System.out.print("│  E             H            P    T  │ ");
        System.out.println();
        for(Student student : Student.values()) {
            for (PlayerView player : players) {
                System.out.print("│ ");
                if (studentEntrance < player.getPlance().getEntrance().size())
                    System.out.print(Colors.values()[player.getPlance().getEntrance().get(studentEntrance).ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("  ");
                if (studentEntrance + 1 < player.getPlance().getEntrance().size())
                    System.out.print(Colors.values()[player.getPlance().getEntrance().get(studentEntrance + 1).ordinal()].getCode() + "●" + Colors.ANSI_RESET.getCode());
                else
                    System.out.print(" ");
                System.out.print(" │ ");
                for(i=0; i<10; i++){
                    if(i < player.getPlance().getNumberOfStudentHall(student))
                        System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                    else
                        System.out.print("◯ ");
                }
                System.out.print("│ ");
                if(player.getPlance().getProfessors().contains(Professor.values()[student.ordinal()]))
                    System.out.print(Colors.values()[student.ordinal()].getCode() + "■ " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("□ ");
                System.out.print("│ ");
                if (studentEntrance < player.getPlance().getNumoftowers())
                    System.out.print(Colors.values()[player.getPlance().getTower().ordinal() + 5].getCode() + "▲ " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("  ");
                if (studentEntrance + 1 < player.getPlance().getNumoftowers())
                    System.out.print(Colors.values()[player.getPlance().getTower().ordinal() + 5].getCode() + "▲ " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("  ");
                System.out.print("│ ");
            }
            System.out.println();
            studentEntrance += 2;
        }
        for(PlayerView player : players)
            System.out.print("╰─────────────────────────────────────╯ ");
        System.out.println();
        for(PlayerView player : players)
            if(player.getCoins()>=0)
                System.out.print("COINS : " + player.getCoins() + "                               ");
        if(players.get(0).getCoins()>=0)
            System.out.println();
        System.out.println();
    }

    public void printAssistants(ArrayList<Assistant> assistants, Wizard wizard){
        int i;

        for(i=1; i<=assistants.size();i++){
            if(i<10)
                System.out.print("╭────╴" + i + "╶────╮ ");
            else
                System.out.print("╭───╴" + i + "╶────╮ ");
        }
        System.out.println();
        for(Assistant assistant : assistants){
            System.out.print("│");
            for(i=0; i<(11-assistant.toString().length())/2;i++)
                System.out.print(" ");
            if(wizard.equals(Wizard.WIZARD_GREEN))
                System.out.print(Colors.values()[wizard.ordinal()].getCode() + assistant + Colors.ANSI_RESET.getCode());
            else
                System.out.print(Colors.values()[wizard.ordinal() + 1].getCode() + assistant + Colors.ANSI_RESET.getCode());
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
            System.out.print("╰───────────╯ ");
        System.out.println();
    }

    public void printClouds(ArrayList<CloudView> clouds){
        int i;

        System.out.print("╭────");
        for(i=0; i<clouds.size()-2; i++)
            System.out.print("────");
        System.out.print("╴CLOUDS╶");
        for(i=0; i<clouds.size()-2; i++)
            System.out.print("────");
        System.out.println("───╮");
        System.out.print("│");
        for(CloudView cloud : clouds) {
            if(cloud.isChoosen()) {
                if(cloud.getCloudID()>1 && cloud.getCloudID()<10)
                    System.out.print(Colors.ANSI_WHITE.getCode() + "    " + cloud.getCloudID() + "   " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print(Colors.ANSI_WHITE.getCode() + "   " + cloud.getCloudID() + "   " + Colors.ANSI_RESET.getCode());
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
                if(cloud.isChoosen()) {
                    System.out.print("│");
                    System.out.print(Colors.ANSI_WHITE.getCode() + " 0 : ● " + Colors.ANSI_RESET.getCode());
                } else {
                    i = 0;
                    for (Student s : cloud.getStudents())
                        if (s.ordinal() == student.ordinal())
                            i++;
                    System.out.print("│ " + i + " :");
                    System.out.print(Colors.values()[student.ordinal()].getCode() + " ● " + Colors.ANSI_RESET.getCode());
                }
            }
            System.out.println("│");
        }
        System.out.print("╰───────");
        for(i=1; i<clouds.size(); i++)
            System.out.print("────────");
        System.out.println("╯");
    }

    public void printCharacters(ArrayList<CharacterView> characters, EffectHandlerInterface effectHandler){
        int i;

        for(i=1; i<= characters.size();i++)
            System.out.print("╭────╴" + i + "╶────╮ ");
        System.out.println();

        for(CharacterView character : characters){
            System.out.print("│");
            for(i=0; i<(11-character.getName().length())/2;i++)
                System.out.print(" ");
            System.out.print(Colors.values()[character.getCost() + 10].getCode() + character.getName() + Colors.ANSI_RESET.getCode());
            for(i=0; i<(11-character.getName().length())/2 + (11-character.getName().length())%2;i++)
                System.out.print(" ");
            System.out.print("│ ");
        }
        System.out.println();

        for(CharacterView character : characters){
            if("JESTER".equals(character.getName())){
                System.out.print("│   ");
                for(i=0;i<3;i++)
                    System.out.print(Colors.values()[effectHandler.getEffect7students().get(i).ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                System.out.print("  │ ");
            } else if("MONK".equals(character.getName()) && effectHandler.getEffect1students().size()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getEffect1students().size();i++)
                    System.out.print(" ");
                for(Student student : effectHandler.getEffect1students())
                    System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                for(i=0; i<5-effectHandler.getEffect1students().size();i++)
                    System.out.print(" ");
                System.out.print("│ ");
            } else if("PRINCESS".equals(character.getName()) && effectHandler.getEffect11students().size()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getEffect11students().size();i++)
                    System.out.print(" ");
                for(Student student : effectHandler.getEffect11students())
                    System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
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
                        System.out.print(Colors.values()[effectHandler.getEffect7students().get(i).ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
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
            System.out.print("╰───────────╯ ");
        System.out.println();
    }

    public void printWizards(ArrayList<Wizard> wizards){
        int i;

        for(i=1; i<=wizards.size();i++){
            if(i<10)
                System.out.print("╭────╴" + i + "╶────╮ ");
            else
                System.out.print("╭───╴" + i + "╶────╮ ");
        }
        System.out.println();
        for(i=1; i<=wizards.size();i++)
            System.out.print("│  WIZARD   │ ");
        System.out.println();
        for(Wizard wizard : wizards) {
            System.out.print("│");
            switch (wizard) {
                case WIZARD_GREEN -> System.out.print(Colors.ANSI_GREEN.getCode() + "   GREEN   " + Colors.ANSI_RESET.getCode());
                case WIZARD_BLUE -> System.out.print(Colors.ANSI_BLUE.getCode() + "   BLUE    " + Colors.ANSI_RESET.getCode());
                case WIZARD_PINK -> System.out.print(Colors.ANSI_PURPLE.getCode() + "   PINK    " + Colors.ANSI_RESET.getCode());
                case WIZARD_YELLOW -> System.out.print(Colors.ANSI_BRIGHT_YELLOW.getCode() + "  YELLOW   " + Colors.ANSI_RESET.getCode());
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
            System.out.print("╰───────────╯ ");
        System.out.println();
    }
}


