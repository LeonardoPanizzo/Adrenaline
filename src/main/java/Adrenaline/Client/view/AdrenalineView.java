package Adrenaline.Client.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.image.*;

public class AdrenalineView extends Application {

    String name = new String();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage){
        primaryStage.setTitle("Adrenaline");
        StackPane rootNode = new StackPane();
        Scene myScene = new Scene(rootNode, 1000, 600);
        Image background = new Image(AdrenalineView.class.getResource("/adrenalina.jpg").toExternalForm());
        ImageView showBackground = new ImageView(background);
        rootNode.getChildren().add(showBackground);

        Image top = new Image(AdrenalineView.class.getResource("/Title.png").toExternalForm());
        ImageView showTitle = new ImageView(top);
        Label title = new Label("Title", showTitle);
        title.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        FlowPane titleNode = new FlowPane();
        titleNode.setAlignment(Pos.TOP_CENTER);
        titleNode.getChildren().add(title);
        rootNode.getChildren().add(titleNode);

        TextField user = new TextField();
        FlowPane username = new FlowPane(10, 0);
        username.setAlignment(Pos.CENTER);
        Label insert = new Label("Insert User Name");
        insert.setFont(Font.font("", FontWeight.BOLD, 12));
        insert.setTextFill(Color.BLACK);
        username.getChildren().addAll(insert, user);
        rootNode.getChildren().add(username);

        Button btn = new Button("Play");
        username.getChildren().add(btn);

        user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                name = user.getText();
                System.out.println("The name is: "+name);
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name = user.getText();
                System.out.println("The name is: "+name);
            }
        });

        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}