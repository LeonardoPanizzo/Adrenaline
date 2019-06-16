package Adrenaline.Client.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.image.*;

public class AdrenalineView extends Application {


    int count = 0;

    public static void main(String[] args) {
        launch(args);
    }

    String playerName = new String();
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

        primaryStage.setScene(myScene);
        primaryStage.show();

        rootNode = new StackPane();
        Scene secondScene = new Scene(rootNode, 1000, 600);

        Label player1 = new Label("You");

        user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                playerName = user.getText();
                //System.out.println("The name is: "+name);
                primaryStage.setScene(secondScene);
                player1.setText(playerName);
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playerName = user.getText();
                //System.out.println("The name is: "+name);
                primaryStage.setScene(secondScene);
                player1.setText(playerName);
            }
        });

        //todo:creare sala d'attesa: impostare che il nome dei giocatori compare
        VBox lobby = new VBox(10);
        player1.setTextFill(Color.YELLOW);
        player1.setFont(Font.font("", FontWeight.BOLD, 20));
        Label player2 = new Label("Player 2");
        player2.setTextFill(Color.GRAY);
        player2.setFont(Font.font("", FontWeight.BOLD, 20));
        Label player3 = new Label("Player 3");
        player3.setTextFill(Color.GREEN);
        player3.setFont(Font.font("", FontWeight.BOLD, 20));
        Label player4 = new Label("Player 4");
        player4.setTextFill(Color.VIOLET);
        player4.setFont(Font.font("", FontWeight.BOLD, 20));
        Label player5 = new Label("Player 5");
        player5.setTextFill(Color.BLUE);
        player5.setFont(Font.font("", FontWeight.BOLD, 20));
        lobby.setAlignment(Pos.CENTER);
        lobby.getChildren().addAll(player1,player2,player3,player4,player5);
        Image lobbyBack = new Image(AdrenalineView.class.getResource("/lobbyback.jpg").toExternalForm());
        ImageView lobbybackground = new ImageView(lobbyBack);
        Label wait = new Label("Waiting for other players...");
        wait.setFont(Font.font("", FontWeight.BOLD, 20));
        wait.setTextFill(Color.WHITE);
        FlowPane waiting = new FlowPane(10,10);
        waiting.setAlignment(Pos.BOTTOM_RIGHT);
        waiting.getChildren().add(wait);
        rootNode.getChildren().add(lobbybackground);
        rootNode.getChildren().addAll(lobby, waiting);

        rootNode = new StackPane();
        Scene boardScene = new Scene(rootNode, 1000, 600);

        //Todo: quando il timer scade si passa alla schermata di gioco
        //si va avanti temporaneamente con click del mouse
        secondScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                count += me.getClickCount();
             //   System.out.println("Count is "+count);
                if(count == 1)
                    wait.setText("Click to Play");
                else if(count > 1)
                    primaryStage.setScene(boardScene);
            }
        });

        GridPane playerView = new GridPane();
        playerView.getColumnConstraints().add(new ColumnConstraints(209));
        playerView.getColumnConstraints().add(new ColumnConstraints(209));
        playerView.getColumnConstraints().add(new ColumnConstraints(209));
        playerView.getColumnConstraints().add(new ColumnConstraints(209));

        //board 1
        Image x0y0 = new Image(AdrenalineView.class.getResource("/1v0-0.png").toExternalForm());
        ImageView X0Y0 = new ImageView(x0y0);
        StackPane xx0yy0 = new StackPane(X0Y0);
        playerView.add(xx0yy0, 0,0);
        Image x0y1 = new Image(AdrenalineView.class.getResource("/1v0-1.png").toExternalForm());
        ImageView X0Y1 = new ImageView(x0y1);
        StackPane xx0yy1 = new StackPane(X0Y1);
        playerView.add(xx0yy1, 1,0);
        Image x0y2 = new Image(AdrenalineView.class.getResource("/1v0-2.png").toExternalForm());
        ImageView X0Y2 = new ImageView(x0y2);
        StackPane xx0yy2 = new StackPane(X0Y2);
        playerView.add(xx0yy2, 2,0);
        Image x0y3 = new Image(AdrenalineView.class.getResource("/1v0-3.png").toExternalForm());
        ImageView X0Y3 = new ImageView(x0y3);
        StackPane xx0yy3 = new StackPane(X0Y3);
        playerView.add(xx0yy3, 3,0);
        Image x1y0 = new Image(AdrenalineView.class.getResource("/1v1-0.png").toExternalForm());
        ImageView X1Y0 = new ImageView(x1y0);
        StackPane xx1yy0 = new StackPane(X1Y0);
        playerView.add(xx1yy0, 0,1);
        Image x1y1 = new Image(AdrenalineView.class.getResource("/1v1-1.png").toExternalForm());
        ImageView X1Y1 = new ImageView(x1y1);
        StackPane xx1yy1 = new StackPane(X1Y1);
        playerView.add(xx1yy1, 1,1);
        Image x1y2 = new Image(AdrenalineView.class.getResource("/1v1-2.png").toExternalForm());
        ImageView X1Y2 = new ImageView(x1y2);
        StackPane xx1yy2 = new StackPane(X1Y2);
        playerView.add(xx1yy2, 2,1);
        Image x1y3 = new Image(AdrenalineView.class.getResource("/1v1-3.png").toExternalForm());
        ImageView X1Y3 = new ImageView(x1y3);
        StackPane xx1yy3 = new StackPane(X1Y3);
        playerView.add(xx1yy3, 3,1);
        Image x2y0 = new Image(AdrenalineView.class.getResource("/1v2-0.png").toExternalForm());
        ImageView X2Y0 = new ImageView(x2y0);
        StackPane xx2yy0 = new StackPane(X2Y0);
        playerView.add(xx2yy0, 0,2);
        Image x2y1 = new Image(AdrenalineView.class.getResource("/1v2-1.png").toExternalForm());
        ImageView X2Y1 = new ImageView(x2y1);
        StackPane xx2yy1 = new StackPane(X2Y1);
        playerView.add(xx2yy1, 1,2);
        Image x2y2 = new Image(AdrenalineView.class.getResource("/1v2-2.png").toExternalForm());
        ImageView X2Y2 = new ImageView(x2y2);
        StackPane xx2yy2 = new StackPane(X2Y2);
        playerView.add(xx2yy2, 2,2);
        Image x2y3 = new Image(AdrenalineView.class.getResource("/1v2-3.png").toExternalForm());
        ImageView X2Y3 = new ImageView(x2y3);
        StackPane xx2yy3 = new StackPane(X2Y3);
        playerView.add(xx2yy3, 3,2);

        BorderPane view = new BorderPane();
        rootNode.getChildren().add(view);

        VBox players = new VBox(18);
        final String SEE = new String("See Details");
        Image play2 = new Image(AdrenalineView.class.getResource("/player2.png").toExternalForm());
        ImageView plays2 = new ImageView(play2);
        Button btn2 = new Button(SEE, plays2);
        btn2.setContentDisplay(ContentDisplay.RIGHT);
        Image play3 = new Image(AdrenalineView.class.getResource("/player3.png").toExternalForm());
        ImageView plays3 = new ImageView(play3);
        Button btn3 = new Button(SEE, plays3);
        btn3.setContentDisplay(ContentDisplay.RIGHT);
        Image play4 = new Image(AdrenalineView.class.getResource("/player4.png").toExternalForm());
        ImageView plays4 = new ImageView(play4);
        Button btn4 = new Button(SEE, plays4);
        btn4.setContentDisplay(ContentDisplay.RIGHT);
        Image play5 = new Image(AdrenalineView.class.getResource("/player5.png").toExternalForm());
        ImageView plays5 = new ImageView(play5);
        Button btn5 = new Button("See Details", plays5);
        btn5.setContentDisplay(ContentDisplay.RIGHT);

        GridPane you = new GridPane();
        you.getColumnConstraints().add(new ColumnConstraints(150));
        you.getColumnConstraints().add(new ColumnConstraints(150));

        Image your = new Image(AdrenalineView.class.getResource("/player1.png").toExternalForm());
        ImageView yourp = new ImageView(your);
        you.add(yourp, 0,0);

        VBox ammo1 = new VBox(20);
        Image blueI = new Image(AdrenalineView.class.getResource("/blue.png").toExternalForm());
        ImageView blueIV = new ImageView(blueI);
        Label blue = new Label("Blue Ammo:", blueIV);
        Image yellowI = new Image(AdrenalineView.class.getResource("/yellow.png").toExternalForm());
        ImageView yellowIV = new ImageView(yellowI);
        Label yellow = new Label("Yellow Ammo:", yellowIV);
        Image redI = new Image(AdrenalineView.class.getResource("/red.png").toExternalForm());
        ImageView redIV = new ImageView(redI);
        Label red = new Label("Blue Ammo:", redIV);
        ammo1.getChildren().addAll(blue, yellow, red);
        you.add(ammo1, 1, 0);

        VBox ammo2 = new VBox(20);
        Image blueI2 = new Image(AdrenalineView.class.getResource("/blue.png").toExternalForm());
        ImageView blueIV2 = new ImageView(blueI2);
        Label bAmmo = new Label("1", blueIV2);
        bAmmo.setContentDisplay(ContentDisplay.RIGHT);
        Image yellowI2 = new Image(AdrenalineView.class.getResource("/yellow.png").toExternalForm());
        ImageView yellowIV2 = new ImageView(yellowI2);
        Label yAmmo = new Label("1", yellowIV2);
        yAmmo.setContentDisplay(ContentDisplay.RIGHT);
        Image redI2 = new Image(AdrenalineView.class.getResource("/red.png").toExternalForm());
        ImageView redIV2 = new ImageView(redI2);
        Label rAmmo = new Label("1", redIV2);
        rAmmo.setContentDisplay(ContentDisplay.RIGHT);
        ammo2.getChildren().addAll(bAmmo, yAmmo, rAmmo);
        you.add(ammo2, 2, 0);

        players.getChildren().addAll(btn2, btn3, btn4, btn5);
        players.setAlignment(Pos.TOP_CENTER);

        view.setRight(players);
        BorderPane.setAlignment(players, Pos.CENTER);
        view.setLeft(playerView);
        BorderPane.setAlignment(playerView, Pos.TOP_LEFT);
        view.setBottom(you);
        BorderPane.setAlignment(you, Pos.BOTTOM_CENTER);
    }
}