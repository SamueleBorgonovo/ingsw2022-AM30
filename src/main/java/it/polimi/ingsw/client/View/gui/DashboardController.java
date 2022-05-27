package it.polimi.ingsw.client.View.gui;

import it.polimi.ingsw.controller.virtualView.CloudView;
import it.polimi.ingsw.model.game.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardController {
    private ArrayList<CloudView> clouds;
    private GUI gui;
    @FXML
    public Pane cloud1Pane;

    @FXML
    public ImageView cloud1Student2;

    @FXML
    public ImageView cloud1Student4;

    @FXML
    public ImageView cloud1Student3;

    @FXML
    public ImageView cloud1Student1;

    @FXML
    public Pane cloud3Pane;

    @FXML
    public ImageView cloud3Student2;

    @FXML
    public ImageView cloud3Student4;

    @FXML
    public ImageView cloud3Student3;

    @FXML
    public ImageView cloud3Student1;

    @FXML
    public Pane cloud2Pane;

    @FXML
    public ImageView cloud2Student2;

    @FXML
    public ImageView cloud2Student4;

    @FXML
    public ImageView cloud2Student3;

    @FXML
    public ImageView cloud2Student1;

    @FXML
    public Pane assistantsPane;

    @FXML
    public ImageView lion;

    @FXML
    public ImageView ostrich;

    @FXML
    public ImageView cat;

    @FXML
    public ImageView eagle;

    @FXML
    public ImageView fox;

    @FXML
    public ImageView snake;

    @FXML
    public ImageView octopus;

    @FXML
    public ImageView dog;

    @FXML
    public ImageView elephants;

    @FXML
    public ImageView turtle;

    @FXML
    public Pane plancePane;

    @FXML
    public ImageView entranceStudent9;

    @FXML
    public ImageView entranceStudent6;

    @FXML
    public ImageView entranceStudent4;

    @FXML
    public ImageView entranceStudent2;

    @FXML
    public ImageView entranceStudent1;

    @FXML
    public ImageView entranceStudent5;

    @FXML
    public ImageView entranceStudent3;

    @FXML
    public ImageView entranceStudent7;

    @FXML
    public ImageView entranceStudent8;

    @FXML
    public HBox hallGreen;

    @FXML
    public ImageView hall1Student1;

    @FXML
    public ImageView hall1Student2;

    @FXML
    public ImageView hall1Student3;

    @FXML
    public ImageView hall1Student4;

    @FXML
    public ImageView hall1Student5;

    @FXML
    public ImageView hall1Student6;

    @FXML
    public ImageView hall1Student7;

    @FXML
    public ImageView hall1Student8;

    @FXML
    public ImageView hall1Student9;

    @FXML
    public ImageView hall1Student10;

    @FXML
    public HBox hallRed;

    @FXML
    public ImageView hall2Student1;

    @FXML
    public ImageView hall2Student2;

    @FXML
    public ImageView hall2Student3;

    @FXML
    public ImageView hall2Student4;

    @FXML
    public ImageView hall2Student5;

    @FXML
    public ImageView hall2Student6;

    @FXML
    public ImageView hall2Student7;

    @FXML
    public ImageView hall2Student8;

    @FXML
    public ImageView hall2Student9;

    @FXML
    public ImageView hall2Student10;

    @FXML
    public HBox hallYellow;

    @FXML
    public ImageView hall3Student1;

    @FXML
    public ImageView hall3Student2;

    @FXML
    public ImageView hall3Student3;

    @FXML
    public ImageView hall3Student4;

    @FXML
    public ImageView hall3Student5;

    @FXML
    public ImageView hall3Student6;

    @FXML
    public ImageView hall3Student7;

    @FXML
    public ImageView hall3Student8;

    @FXML
    public ImageView hall3Student9;

    @FXML
    public ImageView hall3Student10;

    @FXML
    public HBox hallPink;

    @FXML
    public ImageView hall4Student1;

    @FXML
    public ImageView hall4Student2;

    @FXML
    public ImageView hall4Student3;

    @FXML
    public ImageView hall4Student4;

    @FXML
    public ImageView hall4Student5;

    @FXML
    public ImageView hall4Student6;

    @FXML
    public ImageView hall4Student7;

    @FXML
    public ImageView hall4Student8;

    @FXML
    public ImageView hall4Student9;

    @FXML
    public ImageView hall4Student10;

    @FXML
    public HBox hallBlue;

    @FXML
    public ImageView hall5Student1;

    @FXML
    public ImageView hall5Student2;

    @FXML
    public ImageView hall5Student3;

    @FXML
    public ImageView hall5Student4;

    @FXML
    public ImageView hall5Student5;

    @FXML
    public ImageView hall5Student6;

    @FXML
    public ImageView hall5Student7;

    @FXML
    public ImageView hall5Student8;

    @FXML
    public ImageView hall5Student9;

    @FXML
    public ImageView hall5Student10;

    @FXML
    public ImageView professorBlue;

    @FXML
    public ImageView professorGreen;

    @FXML
    public ImageView professorRed;

    @FXML
    public ImageView professorYellow;

    @FXML
    public ImageView professorPink;

    @FXML
    public Label nicknameLabel;

    @FXML
    public Label playerStateLabel;

    @FXML
    public Label gameUpdateLabel;

    @FXML
    public Pane characterButtor;

    @FXML
    public Label characterButtonLabel;

    @FXML
    public HBox coinPane;

    @FXML
    public Label coinLabel;

    @FXML
    public Pane charactersPane;

    public void chosenCloud1(MouseEvent mouseEvent) {
        cloud2Pane.setDisable(true);
        cloud3Pane.setDisable(true);

    }

    public void chosenCloud3(MouseEvent mouseEvent) {
    }

    public void chosenCloud2(MouseEvent mouseEvent) {
    }

    public void chosenAssistantLion(MouseEvent mouseEvent) {
    }

    public void chosenAssistantOstrich(MouseEvent mouseEvent) {
    }

    public void chosenAssistantCat(MouseEvent mouseEvent) {
    }

    public void chosenAssistantEagle(MouseEvent mouseEvent) {
    }

    public void chosenAssistantFox(MouseEvent mouseEvent) {
    }

    public void chosenAssistantSnake(MouseEvent mouseEvent) {
    }

    public void chosenAssistantOctopus(MouseEvent mouseEvent) {
    }

    public void chosenAssistantDog(MouseEvent mouseEvent) {
    }

    public void chosenAssistantElephants(MouseEvent mouseEvent) {
    }

    public void chosenAssistantTurtle(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent9(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent6(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent4(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent2(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent1(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent5(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent3(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent7(MouseEvent mouseEvent) {
    }

    public void chosenEntranceStudent8(MouseEvent mouseEvent) {
    }

    public void chosenHallGreen(MouseEvent mouseEvent) {
    }

    public void chosenHallRed(MouseEvent mouseEvent) {
    }

    public void chosenHallYellow(MouseEvent mouseEvent) {
    }

    public void chosenHallPink(MouseEvent mouseEvent) {
    }

    public void chosenHallBlue(MouseEvent mouseEvent) {
    }

    public void clickCharacetrButton(MouseEvent mouseEvent) {
    }

    public void clickAssistabtButton(MouseEvent mouseEvent) {
    }

    //////////////////////////////////////////////////////////////

    public void setClouds(ArrayList<CloudView> clouds) {
        this.clouds = clouds;
    }

    public void showClouds(ArrayList<CloudView> clouds){
        cloud1Pane.setDisable(false);
        cloud1Pane.setOpacity(1);
        cloud2Pane.setDisable(false);
        cloud2Pane.setOpacity(1);
        cloud3Pane.setDisable(false);
        cloud3Pane.setOpacity(1);

        ImageView [][] matImageView= new ImageView[3][4];

        matImageView[0][0]=cloud1Student1;
        matImageView[0][1]=cloud1Student2;
        matImageView[0][2]=cloud1Student3;
        matImageView[1][0]=cloud2Student1;
        matImageView[1][1]=cloud2Student2;
        matImageView[1][2]=cloud2Student3;

        if(gui.getNumOfPlayers()==3) {
            matImageView[0][3] = cloud1Student4;
            matImageView[1][3] = cloud2Student4;
            matImageView[2][0] = cloud3Student1;
            matImageView[2][1] = cloud3Student2;
            matImageView[2][2] = cloud3Student3;
            matImageView[2][3] = cloud3Student4;
        }

        int i=0;
        for(CloudView  cloud: clouds) {
            int j = 0;
            for (Student student : cloud.getStudents()) {
                matImageView[i][j].setImage(this.getImageFromStudent(student));
                j++;
            }
            i++;
        }
        
        for(CloudView cloud2 : clouds)
            if(cloud2.isChoosen()){
                switch (cloud2.getCloudID()){
                    case (1) -> {
                        cloud1Pane.setDisable(true);
                        cloud1Pane.setOpacity(0.3);
                    }
                    case (2) -> {
                        cloud2Pane.setDisable(true);
                        cloud2Pane.setOpacity(0.3);
                    }
                    case (3) -> {
                        cloud3Pane.setDisable(true);
                        cloud3Pane.setOpacity(0.3);
                    }
                }
            }

    }

    public Image getImageFromStudent(Student student){
        Image studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("")));
        if (student==Student.GREEN)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img.STUDENT_GREEN.png")));
        else if (student==Student.RED)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img.STUDENT_RED.png")));
        else if (student==Student.YELLOW)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img.STUDENT_YELLOW.png")));
        else if (student==Student.BLUE)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img.STUDENT_BLUE.png")));
        else if (student==Student.PINK)
            studentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img.STUDENT_PINK.png")));

        return studentImage;
    }
}
