package Adrenaline.Client.view;

import Adrenaline.Server.model.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdrenalineView extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start (Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Adrenaline");

        //Setting the login gridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome!");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        sceneTitle.setFill(Color.FIREBRICK);
//        Image image = new Image(new FileInputStream("src\\main\\resources\\Title.png"));
//        ImageView imageView = new ImageView(image);

        grid.add(sceneTitle, 1, 0, 2, 1);
        Label userName = new Label( "User Name: ");
        userName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 11));

        grid.add(userName, 0, 2);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        Button btn = new Button("Start");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1,6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Game is starting...");

            }
        });

        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setScene(scene);
        scene.getStylesheets().add
                (AdrenalineView.class.getResource("/view.css").toExternalForm());
        primaryStage.show();
    }
}