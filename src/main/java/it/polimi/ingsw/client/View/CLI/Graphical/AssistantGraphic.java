package it.polimi.ingsw.client.View.CLI.Graphical;

import it.polimi.ingsw.model.player.Assistant;

public class AssistantGraphic extends Graphic {

    @Override
    public void draw(Assistant assistant){
        System.out.println("Assistant number:" + assistant.ordinal() + "," + assistant);
        System.out.println("Assistant value:" + assistant.getValue());
        System.out.println("Assistant movements:" + assistant.getMovement());
    }
}
