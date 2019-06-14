package Adrenaline.Client.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.*;

public class AdrenalineView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage){
        primaryStage.setTitle("Adrenaline");
        GridPane rootNode = new GridPane();
        Scene myScene = new Scene(rootNode, 300, 200);
        Image background = new Image("adrenaline.jpg");
        ImageView showBackground = new ImageView(background);
        rootNode.getChildren().add(showBackground);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}