package Adrenaline.Client.view;

import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;
import Adrenaline.Server.model.PowerupCard;
import Adrenaline.Server.model.PowerupDeck;
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
    public void start(Stage primaryStage) {

        Board board = new Board(1);
        int boardNumber = board.getVariation(); //todo: prendere il valore della board scelta

        Player[] playersInGame = new Player[5];     //Players that play the game
        int yourID = 1;                             //Client Player's ID
        //todo: da inizializzare con il numero vero e il vero powerup deck
        PowerupDeck pwd = new PowerupDeck();
        Player me = new Player(1, pwd);
        playersInGame[yourID] = me; //todo: alla fine è da rimuovere

        String blueAmmo = String.valueOf(me.getAmmo('b'));
        String yellowAmmo = String.valueOf(me.getAmmo('y'));
        String redAmmo = String.valueOf(me.getAmmo('r'));

        int lifeValue = me.getLife();

        me.setPlayersDamage(0, 2); //todo da eliminare
        me.setRound(true);
        me.setName("Andre");

        int[] damagesBy = new int[4];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            if (i != yourID) {
                damagesBy[k] = me.getPlayersDamage()[i][1];
                k++;
            }
        }

        String play = null;
        for (int i = 0; i < 5; i++) {
            if (playersInGame[i] != null && playersInGame[i].isRound()) {
                if (i == yourID) {
                    play = new String("It's your Turn");
                } else
                    play = new String("It's " + playersInGame[i].getName() + " Round");
            }
        }


        StackPane xx0yy0 = new StackPane();
        StackPane xx0yy1 = new StackPane();
        StackPane xx0yy2 = new StackPane();
        StackPane xx0yy3 = new StackPane();

        StackPane xx1yy0 = new StackPane();
        StackPane xx1yy1 = new StackPane();
        StackPane xx1yy2 = new StackPane();
        StackPane xx1yy3 = new StackPane();

        StackPane xx2yy0 = new StackPane();
        StackPane xx2yy1 = new StackPane();
        StackPane xx2yy2 = new StackPane();
        StackPane xx2yy3 = new StackPane();

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
        lobby.getChildren().addAll(player1, player2, player3, player4, player5);
        Image lobbyBack = new Image(AdrenalineView.class.getResource("/lobbyback.jpg").toExternalForm());
        ImageView lobbybackground = new ImageView(lobbyBack);
        Label wait = new Label("Waiting for other players...");
        wait.setFont(Font.font("", FontWeight.BOLD, 20));
        wait.setTextFill(Color.WHITE);
        FlowPane waiting = new FlowPane(10, 10);
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
                if (count == 1)
                    wait.setText("Click to Play");
                else if (count > 1)
                    primaryStage.setScene(boardScene);
            }
        });

        GridPane playerView = new GridPane();
        playerView.getColumnConstraints().add(new ColumnConstraints(209));
        playerView.getColumnConstraints().add(new ColumnConstraints(209));
        playerView.getColumnConstraints().add(new ColumnConstraints(209));
        playerView.getColumnConstraints().add(new ColumnConstraints(209));

        //board 1
        if (boardNumber == 1) {
            Image x0y0 = new Image(AdrenalineView.class.getResource("/1v0-0.png").toExternalForm());
            ImageView X0Y0 = new ImageView(x0y0);
            xx0yy0 = new StackPane(X0Y0);
            playerView.add(xx0yy0, 0, 0);

            Image x0y1 = new Image(AdrenalineView.class.getResource("/1v0-1.png").toExternalForm());
            ImageView X0Y1 = new ImageView(x0y1);
            xx0yy1 = new StackPane(X0Y1);
            playerView.add(xx0yy1, 1, 0);

            Image x0y2 = new Image(AdrenalineView.class.getResource("/1v0-2.png").toExternalForm());
            ImageView X0Y2 = new ImageView(x0y2);
            xx0yy2 = new StackPane(X0Y2);
            playerView.add(xx0yy2, 2, 0);

            Image x0y3 = new Image(AdrenalineView.class.getResource("/1v0-3.png").toExternalForm());
            ImageView X0Y3 = new ImageView(x0y3);
            xx0yy3 = new StackPane(X0Y3);
            playerView.add(xx0yy3, 3, 0);

            Image x1y0 = new Image(AdrenalineView.class.getResource("/1v1-0.png").toExternalForm());
            ImageView X1Y0 = new ImageView(x1y0);
            xx1yy0 = new StackPane(X1Y0);
            playerView.add(xx1yy0, 0, 1);

            Image x1y1 = new Image(AdrenalineView.class.getResource("/1v1-1.png").toExternalForm());
            ImageView X1Y1 = new ImageView(x1y1);
            xx1yy1 = new StackPane(X1Y1);
            playerView.add(xx1yy1, 1, 1);

            Image x1y2 = new Image(AdrenalineView.class.getResource("/1v1-2.png").toExternalForm());
            ImageView X1Y2 = new ImageView(x1y2);
            xx1yy2 = new StackPane(X1Y2);
            playerView.add(xx1yy2, 2, 1);

            Image x1y3 = new Image(AdrenalineView.class.getResource("/1v1-3.png").toExternalForm());
            ImageView X1Y3 = new ImageView(x1y3);
            xx1yy3 = new StackPane(X1Y3);
            playerView.add(xx1yy3, 3, 1);

            Image x2y0 = new Image(AdrenalineView.class.getResource("/1v2-0.png").toExternalForm());
            ImageView X2Y0 = new ImageView(x2y0);
            xx2yy0 = new StackPane(X2Y0);
            playerView.add(xx2yy0, 0, 2);

            Image x2y1 = new Image(AdrenalineView.class.getResource("/1v2-1.png").toExternalForm());
            ImageView X2Y1 = new ImageView(x2y1);
            xx2yy1 = new StackPane(X2Y1);
            playerView.add(xx2yy1, 1, 2);

            Image x2y2 = new Image(AdrenalineView.class.getResource("/1v2-2.png").toExternalForm());
            ImageView X2Y2 = new ImageView(x2y2);
            xx2yy2 = new StackPane(X2Y2);
            playerView.add(xx2yy2, 2, 2);

            Image x2y3 = new Image(AdrenalineView.class.getResource("/1v2-3.png").toExternalForm());
            ImageView X2Y3 = new ImageView(x2y3);
            xx2yy3 = new StackPane(X2Y3);
            playerView.add(xx2yy3, 3, 2);
        }
        //board 2
        else if (boardNumber == 2) {
            Image x0y0 = new Image(AdrenalineView.class.getResource("/1v0-0.png").toExternalForm());
            ImageView X0Y0 = new ImageView(x0y0);
            xx0yy0 = new StackPane(X0Y0);
            Image x0y1 = new Image(AdrenalineView.class.getResource("/3v0-1.png").toExternalForm());

            ImageView X0Y1 = new ImageView(x0y1);
            xx0yy1 = new StackPane(X0Y1);

            Image x0y2 = new Image(AdrenalineView.class.getResource("/2v0-2.png").toExternalForm());
            ImageView X0Y2 = new ImageView(x0y2);
            xx0yy2 = new StackPane(X0Y2);

            Image x0y3 = new Image(AdrenalineView.class.getResource("/2v0-3.png").toExternalForm());
            ImageView X0Y3 = new ImageView(x0y3);
            xx0yy3 = new StackPane(X0Y3);

            playerView.add(xx0yy0, 0, 0);
            playerView.add(xx0yy1, 1, 0);
            playerView.add(xx0yy2, 2, 0);
            playerView.add(xx0yy3, 3, 0);

            Image x1y0 = new Image(AdrenalineView.class.getResource("/1v1-0.png").toExternalForm());
            ImageView X1Y0 = new ImageView(x1y0);
            xx1yy0 = new StackPane(X1Y0);

            Image x1y1 = new Image(AdrenalineView.class.getResource("/3v1-1.png").toExternalForm());
            ImageView X1Y1 = new ImageView(x1y1);
            xx1yy1 = new StackPane(X1Y1);

            Image x1y2 = new Image(AdrenalineView.class.getResource("/2v1-2.png").toExternalForm());
            ImageView X1Y2 = new ImageView(x1y2);
            xx1yy2 = new StackPane(X1Y2);

            Image x1y3 = new Image(AdrenalineView.class.getResource("/2v1-3.png").toExternalForm());
            ImageView X1Y3 = new ImageView(x1y3);
            xx1yy3 = new StackPane(X1Y3);

            playerView.add(xx1yy0, 0, 1);
            playerView.add(xx1yy1, 1, 1);
            playerView.add(xx1yy2, 2, 1);
            playerView.add(xx1yy3, 3, 1);

            Image x2y0 = new Image(AdrenalineView.class.getResource("/3v2-0.png").toExternalForm());
            ImageView X2Y0 = new ImageView(x2y0);
            xx2yy0 = new StackPane(X2Y0);
            playerView.add(xx2yy0, 0, 2);

            Image x2y1 = new Image(AdrenalineView.class.getResource("/3v2-1.png").toExternalForm());
            ImageView X2Y1 = new ImageView(x2y1);
            xx2yy1 = new StackPane(X2Y1);

            Image x2y2 = new Image(AdrenalineView.class.getResource("/2v2-2.png").toExternalForm());
            ImageView X2Y2 = new ImageView(x2y2);
            xx2yy2 = new StackPane(X2Y2);

            Image x2y3 = new Image(AdrenalineView.class.getResource("/2v2-3.png").toExternalForm());
            ImageView X2Y3 = new ImageView(x2y3);
            xx2yy3 = new StackPane(X2Y3);

            playerView.add(xx2yy1, 1, 2);
            playerView.add(xx2yy2, 2, 2);
            playerView.add(xx2yy3, 3, 2);
        }
        //board 3
        else if (boardNumber == 3) {
            Image x0y0 = new Image(AdrenalineView.class.getResource("/2v0-0.png").toExternalForm());
            ImageView X0Y0 = new ImageView(x0y0);
            xx0yy0 = new StackPane(X0Y0);
            Image x0y1 = new Image(AdrenalineView.class.getResource("/2v0-1.png").toExternalForm());

            ImageView X0Y1 = new ImageView(x0y1);
            xx0yy1 = new StackPane(X0Y1);

            Image x0y2 = new Image(AdrenalineView.class.getResource("/1v0-2.png").toExternalForm());
            ImageView X0Y2 = new ImageView(x0y2);
            xx0yy2 = new StackPane(X0Y2);

            Image x0y3 = new Image(AdrenalineView.class.getResource("/1v0-3.png").toExternalForm());
            ImageView X0Y3 = new ImageView(x0y3);
            xx0yy3 = new StackPane(X0Y3);

            playerView.add(xx0yy0, 0, 0);
            playerView.add(xx0yy1, 1, 0);
            playerView.add(xx0yy2, 2, 0);
            playerView.add(xx0yy3, 3, 0);

            Image x1y0 = new Image(AdrenalineView.class.getResource("/2v1-0.png").toExternalForm());
            ImageView X1Y0 = new ImageView(x1y0);
            xx1yy0 = new StackPane(X1Y0);

            Image x1y1 = new Image(AdrenalineView.class.getResource("/4v1-1.png").toExternalForm());
            ImageView X1Y1 = new ImageView(x1y1);
            xx1yy1 = new StackPane(X1Y1);

            Image x1y2 = new Image(AdrenalineView.class.getResource("/1v1-2.png").toExternalForm());
            ImageView X1Y2 = new ImageView(x1y2);
            xx1yy2 = new StackPane(X1Y2);

            Image x1y3 = new Image(AdrenalineView.class.getResource("/1v1-3.png").toExternalForm());
            ImageView X1Y3 = new ImageView(x1y3);
            xx1yy3 = new StackPane(X1Y3);

            playerView.add(xx1yy0, 0, 1);
            playerView.add(xx1yy1, 1, 1);
            playerView.add(xx1yy2, 2, 1);
            playerView.add(xx1yy3, 3, 1);

            Image x2y0 = new Image(AdrenalineView.class.getResource("/2v2-0.png").toExternalForm());
            ImageView X2Y0 = new ImageView(x2y0);
            xx2yy0 = new StackPane(X2Y0);
            playerView.add(xx2yy0, 0, 2);

            Image x2y1 = new Image(AdrenalineView.class.getResource("/4v2-1.png").toExternalForm());
            ImageView X2Y1 = new ImageView(x2y1);
            xx2yy1 = new StackPane(X2Y1);

            Image x2y2 = new Image(AdrenalineView.class.getResource("/1v2-2.png").toExternalForm());
            ImageView X2Y2 = new ImageView(x2y2);
            xx2yy2 = new StackPane(X2Y2);

            Image x2y3 = new Image(AdrenalineView.class.getResource("/1v2-3.png").toExternalForm());
            ImageView X2Y3 = new ImageView(x2y3);
            xx2yy3 = new StackPane(X2Y3);

            playerView.add(xx2yy1, 1, 2);
            playerView.add(xx2yy2, 2, 2);
            playerView.add(xx2yy3, 3, 2);
        }
        //board 4
        else if (boardNumber == 4) {
            Image x0y0 = new Image(AdrenalineView.class.getResource("/2v0-0.png").toExternalForm());
            ImageView X0Y0 = new ImageView(x0y0);
            xx0yy0 = new StackPane(X0Y0);
            Image x0y1 = new Image(AdrenalineView.class.getResource("/2v0-1.png").toExternalForm());

            ImageView X0Y1 = new ImageView(x0y1);
            xx0yy1 = new StackPane(X0Y1);

            Image x0y2 = new Image(AdrenalineView.class.getResource("/2v0-2.png").toExternalForm());
            ImageView X0Y2 = new ImageView(x0y2);
            xx0yy2 = new StackPane(X0Y2);

            Image x0y3 = new Image(AdrenalineView.class.getResource("/2v0-3.png").toExternalForm());
            ImageView X0Y3 = new ImageView(x0y3);
            xx0yy3 = new StackPane(X0Y3);

            playerView.add(xx0yy0, 0, 0);
            playerView.add(xx0yy1, 1, 0);
            playerView.add(xx0yy2, 2, 0);
            playerView.add(xx0yy3, 3, 0);

            Image x1y0 = new Image(AdrenalineView.class.getResource("/2v1-0.png").toExternalForm());
            ImageView X1Y0 = new ImageView(x1y0);
            xx1yy0 = new StackPane(X1Y0);

            Image x1y1 = new Image(AdrenalineView.class.getResource("/2v1-1.png").toExternalForm());
            ImageView X1Y1 = new ImageView(x1y1);
            xx1yy1 = new StackPane(X1Y1);

            Image x1y2 = new Image(AdrenalineView.class.getResource("/2v1-2.png").toExternalForm());
            ImageView X1Y2 = new ImageView(x1y2);
            xx1yy2 = new StackPane(X1Y2);

            Image x1y3 = new Image(AdrenalineView.class.getResource("/2v1-3.png").toExternalForm());
            ImageView X1Y3 = new ImageView(x1y3);
            xx1yy3 = new StackPane(X1Y3);

            playerView.add(xx1yy0, 0, 1);
            playerView.add(xx1yy1, 1, 1);
            playerView.add(xx1yy2, 2, 1);
            playerView.add(xx1yy3, 3, 1);

            Image x2y0 = new Image(AdrenalineView.class.getResource("/2v2-0.png").toExternalForm());
            ImageView X2Y0 = new ImageView(x2y0);
            xx2yy0 = new StackPane(X2Y0);
            playerView.add(xx2yy0, 0, 2);

            Image x2y1 = new Image(AdrenalineView.class.getResource("/2v2-1.png").toExternalForm());
            ImageView X2Y1 = new ImageView(x2y1);
            xx2yy1 = new StackPane(X2Y1);

            Image x2y2 = new Image(AdrenalineView.class.getResource("/2v2-2.png").toExternalForm());
            ImageView X2Y2 = new ImageView(x2y2);
            xx2yy2 = new StackPane(X2Y2);

            Image x2y3 = new Image(AdrenalineView.class.getResource("/2v2-3.png").toExternalForm());
            ImageView X2Y3 = new ImageView(x2y3);
            xx2yy3 = new StackPane(X2Y3);

            playerView.add(xx2yy1, 1, 2);
            playerView.add(xx2yy2, 2, 2);
            playerView.add(xx2yy3, 3, 2);
        }

        BorderPane view = new BorderPane();
        rootNode.getChildren().add(view);

        VBox players = new VBox(16);
        players.setAlignment(Pos.CENTER);
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
        you.setHgap(10);
        you.getColumnConstraints().add(new ColumnConstraints(150));
        you.getColumnConstraints().add(new ColumnConstraints(140));
        you.getColumnConstraints().add(new ColumnConstraints(70));
        you.getColumnConstraints().add(new ColumnConstraints(110));
        you.getColumnConstraints().add(new ColumnConstraints(100));
        you.getColumnConstraints().add(new ColumnConstraints(90));
        you.getColumnConstraints().add(new ColumnConstraints(185));
        you.getColumnConstraints().add(new ColumnConstraints(185));
        you.getColumnConstraints().add(new ColumnConstraints(240));

        Image your = new Image(AdrenalineView.class.getResource("/player1.png").toExternalForm());
        ImageView yourp = new ImageView(your);
        you.add(yourp, 0, 0);

        VBox ammo1 = new VBox(20);
        ammo1.setAlignment(Pos.CENTER_LEFT);
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
        ammo2.setAlignment(Pos.CENTER_LEFT);
        Image blueI2 = new Image(AdrenalineView.class.getResource("/blue.png").toExternalForm());
        ImageView blueIV2 = new ImageView(blueI2);
        Label bAmmo = new Label(blueAmmo, blueIV2);
        bAmmo.setContentDisplay(ContentDisplay.RIGHT);
        Image yellowI2 = new Image(AdrenalineView.class.getResource("/yellow.png").toExternalForm());
        ImageView yellowIV2 = new ImageView(yellowI2);
        Label yAmmo = new Label(yellowAmmo, yellowIV2);
        yAmmo.setContentDisplay(ContentDisplay.RIGHT);
        Image redI2 = new Image(AdrenalineView.class.getResource("/red.png").toExternalForm());
        ImageView redIV2 = new ImageView(redI2);
        Label rAmmo = new Label(redAmmo, redIV2);
        rAmmo.setContentDisplay(ContentDisplay.RIGHT);
        ammo2.getChildren().addAll(bAmmo, yAmmo, rAmmo);
        you.add(ammo2, 2, 0);

        FlowPane life = new FlowPane(10, 10);
        life.setAlignment(Pos.CENTER);
        Label lifeL = new Label("Your life is: ");
        Label actualLife = new Label(String.valueOf(lifeValue));
        Image respI = new Image(AdrenalineView.class.getResource("/respawn.png").toExternalForm());
        ImageView respIV = new ImageView(respI);
        Button respawnBtn = new Button("Respawn", respIV);
        life.getChildren().addAll(lifeL, actualLife, respawnBtn);
        you.add(life, 3, 0);

        int damagesValue2 = damagesBy[0];
        int damagesValue3 = damagesBy[1];
        int damagesValue4 = damagesBy[2];
        int damagesValue5 = damagesBy[3];

        VBox damages1 = new VBox(40);
        damages1.setAlignment(Pos.CENTER);

        Image dam2 = new Image(AdrenalineView.class.getResource("/damage2.png").toExternalForm());
        ImageView dam2IV = new ImageView(dam2);
        Label damagesByP2 = new Label("X " + damagesValue2, dam2IV);
        damages1.getChildren().add(damagesByP2);

        Image dam3 = new Image(AdrenalineView.class.getResource("/damage3.png").toExternalForm());
        ImageView dam3IV = new ImageView(dam3);
        Label damagesByP3 = new Label("X " + damagesValue3, dam3IV);
        damages1.getChildren().add(damagesByP3);

        VBox damages2 = new VBox(40);
        damages2.setAlignment(Pos.CENTER);

        Image dam4 = new Image(AdrenalineView.class.getResource("/damage4.png").toExternalForm());
        ImageView dam4IV = new ImageView(dam4);
        Label damagesByP4 = new Label("X " + damagesValue4, dam4IV);
        damages2.getChildren().add(damagesByP4);

        Image dam5 = new Image(AdrenalineView.class.getResource("/damage5.png").toExternalForm());
        ImageView dam5IV = new ImageView(dam5);
        Label damagesByP5 = new Label("X " + damagesValue5, dam5IV);
        damages2.getChildren().add(damagesByP5);

        you.add(damages1, 4, 0);
        you.add(damages2, 5, 0);

        Image weapons = new Image(AdrenalineView.class.getResource("/weap.png").toExternalForm());
        ImageView weaponsIV = new ImageView(weapons);
        Button btnWeap = new Button("See Weapons", weaponsIV);
        btnWeap.setContentDisplay(ContentDisplay.RIGHT);
        you.add(btnWeap, 6, 0);

        Image powerUp = new Image(AdrenalineView.class.getResource("/power.png").toExternalForm());
        ImageView powerUpIV = new ImageView(powerUp);
        Button btnPower = new Button("See Power Up", powerUpIV);
        btnPower.setContentDisplay(ContentDisplay.RIGHT);
        you.add(btnPower, 7, 0);

        //todo: inserire il nome vero dell'azione
        String action = "action that is done. prova acratteri lunghi prova acratteri lunghi prova acratteri lunghi prova acratteri lunghi";
        VBox events = new VBox(10);
        Label round = new Label("Player Round:");
        round.setFont(Font.font("", FontWeight.BOLD, 12));
        Label player = new Label(play);
        Label actions = new Label("Action/s:");
        actions.setFont(Font.font("", FontWeight.BOLD, 12));
        Label event = new Label("Action: " + action);
        event.setWrapText(true);
        events.getChildren().addAll(round, player, actions, event);
        you.add(events, 8, 0);

        VBox movements = new VBox(5);
        movements.setAlignment(Pos.CENTER);
        Image moveI = new Image(AdrenalineView.class.getResource("/move.png").toExternalForm());
        ImageView moveIV = new ImageView(moveI);
        Button moveBtn = new Button("Move", moveIV);
        moveBtn.setContentDisplay(ContentDisplay.LEFT);
        Image moveAndGrabI = new Image(AdrenalineView.class.getResource("/moveAndGrab.png").toExternalForm());
        ImageView moveAndGrabIV = new ImageView(moveAndGrabI);
        Button moveAndGrabBtn = new Button("Move&Grab", moveAndGrabIV);
        moveAndGrabBtn.setContentDisplay(ContentDisplay.LEFT);
        Image shotI = new Image(AdrenalineView.class.getResource("/shot.png").toExternalForm());
        ImageView shotIV = new ImageView(shotI);
        Button shotBtn = new Button("Shot", shotIV);
        shotBtn.setContentDisplay(ContentDisplay.LEFT);
        Image endRound = new Image(AdrenalineView.class.getResource("/endRound.png").toExternalForm());
        ImageView endRoundIV = new ImageView(endRound);
        Button endRoundBtn = new Button("End Round", endRoundIV);
        endRoundBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        movements.getChildren().addAll(moveBtn, moveAndGrabBtn, shotBtn, endRoundBtn);
        you.add(movements, 9, 0);

        players.getChildren().addAll(btn2, btn3, btn4, btn5);
        players.setAlignment(Pos.TOP_CENTER);

        view.setRight(players);
        BorderPane.setAlignment(players, Pos.CENTER);
        view.setLeft(playerView);
        BorderPane.setAlignment(playerView, Pos.TOP_LEFT);
        view.setBottom(you);
        BorderPane.setAlignment(you, Pos.BOTTOM_CENTER);

        Button redResBtn = new Button("Respawn there");
        xx1yy0.getChildren().add(redResBtn);
        Button yellowResBtn = new Button("Respawn there");
        xx2yy3.getChildren().add(yellowResBtn);
        Button blueResBtn = new Button("Respawn there");
        xx0yy2.getChildren().add(blueResBtn);
        redResBtn.setVisible(false);
        yellowResBtn.setVisible(false);
        blueResBtn.setVisible(false);

        HBox powerupCards = new HBox(10);   //root node
        powerupCards.setAlignment(Pos.CENTER);
        Scene powerup = new Scene(powerupCards, 400, 200);
        Stage power = new Stage();
        power.setTitle("PowerUp Cards");
        power.setScene(powerup);
        Button power1 = new Button();
        Button power2 = new Button();
        Button power3 = new Button();
        powerupCards.getChildren().addAll(power1, power2, power3);

        PowerupCard one = new PowerupCard("teleporter", 'b');
        PowerupCard two = new PowerupCard("Newton", 'b');
        PowerupCard three = new PowerupCard("tagback grenade", 'y');
        PowerupCard[] array = {one, two, three};
        me.setPowerup(array);

        if (me.getPowerup()[0] != null) {
            power1.setGraphic(showPowerUp(me.getPowerup()[0]));
            power1.setContentDisplay(ContentDisplay.TOP);
            power1.setText("Select");
        }

        if (me.getPowerup()[1] != null) {
            power2.setGraphic(showPowerUp(me.getPowerup()[1]));
            power2.setContentDisplay(ContentDisplay.TOP);
            power2.setText("Select");
        }

        if (me.getPowerup()[2] != null) {
            power3.setGraphic(showPowerUp(me.getPowerup()[2]));
            power3.setContentDisplay(ContentDisplay.TOP);
            power3.setText("Select");
        }

            //Event effects
            respawnBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    redResBtn.setVisible(true);
                    yellowResBtn.setVisible(true);
                    blueResBtn.setVisible(true);
                }
            });

        Image p102 = new Image(AdrenalineView.class.getResource("/p1.png").toExternalForm());
        ImageView p102IV = new ImageView(p102);
        Label p1x0y2 = new Label("", p102IV);
        p1x0y2.setVisible(false);
        xx0yy2.getChildren().add(p1x0y2);

        //todo: add all button

        blueResBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                power.show();
                power1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        me.respawn(me.getPowerup()[0], board.getBoard()[0][2]);
                        power.close();
                        p1x0y2.setVisible(true);
                        redResBtn.setVisible(false);
                        yellowResBtn.setVisible(false);
                        blueResBtn.setVisible(false);
                    }
                });
            }
        });

        //todo set all respawn button
    }

    //A method to create ImageView for powerup
    public ImageView showPowerUp(PowerupCard powerup) {
        String name = powerup.getName();
        char color = powerup.getColour();

        if (color == 'r') {
            switch (name) {
                case "teleporter":
                    Image tele = new Image(AdrenalineView.class.getResource("/redtele.png").toExternalForm());
                    ImageView teleIV = new ImageView(tele);
                    return teleIV;

                case "Newton":
                    Image raggio = new Image(AdrenalineView.class.getResource("/redraggio.png").toExternalForm());
                    ImageView raggioIV = new ImageView(raggio);
                    return raggioIV;

                case "tagback grenade":
                    Image venom = new Image(AdrenalineView.class.getResource("/redvenom.png").toExternalForm());
                    ImageView venomIV = new ImageView(venom);
                    return venomIV;

                case "targeting scope":
                    Image mirino = new Image(AdrenalineView.class.getResource("/redmirino.png").toExternalForm());
                    ImageView mirinoIV = new ImageView(mirino);
                    return mirinoIV;
            }
        }

        else if (color == 'b') {
            switch (name) {
                case "teleporter":
                    Image tele = new Image(AdrenalineView.class.getResource("/bluetele.png").toExternalForm());
                    ImageView teleIV = new ImageView(tele);
                    return teleIV;

                case "Newton":
                    Image raggio = new Image(AdrenalineView.class.getResource("/blueraggio.png").toExternalForm());
                    ImageView raggioIV = new ImageView(raggio);
                    return raggioIV;

                case "tagback grenade":
                    Image venom = new Image(AdrenalineView.class.getResource("/bluevenom.png").toExternalForm());
                    ImageView venomIV = new ImageView(venom);
                    return venomIV;

                case "targeting scope":
                    Image mirino = new Image(AdrenalineView.class.getResource("/bluemirino.png").toExternalForm());
                    ImageView mirinoIV = new ImageView(mirino);
                    return mirinoIV;
            }
        }

        else if (color == 'y') {
            switch (name) {
                case "teleporter":
                    Image tele = new Image(AdrenalineView.class.getResource("/yellowtele.png").toExternalForm());
                    ImageView teleIV = new ImageView(tele);
                    return teleIV;

                case "Newton":
                    Image raggio = new Image(AdrenalineView.class.getResource("/yellowraggio.png").toExternalForm());
                    ImageView raggioIV = new ImageView(raggio);
                    return raggioIV;

                case "tagback grenade":
                    Image venom = new Image(AdrenalineView.class.getResource("/yellowvenom.png").toExternalForm());
                    ImageView venomIV = new ImageView(venom);
                    return venomIV;

                case "targeting scope":
                    Image mirino = new Image(AdrenalineView.class.getResource("/yellowmirino.png").toExternalForm());
                    ImageView mirinoIV = new ImageView(mirino);
                    return mirinoIV;
            }
        }
        return null;
    }
}
