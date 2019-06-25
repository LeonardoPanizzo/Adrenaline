package Adrenaline.Client.view;

import Adrenaline.Server.model.*;
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

    private int moveCounter = 0;
    private int moveAndGrabCounter = 0;
    private int count = 0;
    private StackPane xx0yy0 = new StackPane();
    private StackPane xx0yy1 = new StackPane();
    private StackPane xx0yy2 = new StackPane();
    private StackPane xx0yy3 = new StackPane();

    private StackPane xx1yy0 = new StackPane();
    private StackPane xx1yy1 = new StackPane();
    private StackPane xx1yy2 = new StackPane();
    private StackPane xx1yy3 = new StackPane();

    private StackPane xx2yy0 = new StackPane();
    private StackPane xx2yy1 = new StackPane();
    private StackPane xx2yy2 = new StackPane();
    private StackPane xx2yy3 = new StackPane();

    private Button bxx0yy0 = new Button("Move there");
    private Button bxx0yy1 = new Button("Move there");
    private Button bxx0yy2 = new Button("Move there");
    private Button bxx0yy3 = new Button("Move there");
    private Button bxx1yy0 = new Button("Move there");
    private Button bxx1yy1 = new Button("Move there");
    private Button bxx1yy2 = new Button("Move there");
    private Button bxx1yy3 = new Button("Move there");
    private Button bxx2yy0 = new Button("Move there");
    private Button bxx2yy1 = new Button("Move there");
    private Button bxx2yy2 = new Button("Move there");
    private Button bxx2yy3 = new Button("Move there");

    private Button gxx0yy0 = new Button("Grab");
    private Button gxx0yy1 = new Button("Grab");
    private Button gxx0yy2 = new Button("Grab");
    private Button gxx0yy3 = new Button("Grab");
    private Button gxx1yy0 = new Button("Grab");
    private Button gxx1yy1 = new Button("Grab");
    private Button gxx1yy2 = new Button("Grab");
    private Button gxx1yy3 = new Button("Grab");
    private Button gxx2yy0 = new Button("Grab");
    private Button gxx2yy1 = new Button("Grab");
    private Button gxx2yy2 = new Button("Grab");
    private Button gxx2yy3 = new Button("Grab");


    private int lifeValue = 0;
    private Board board = new Board(1);
    private int boardNumber;
    private Player[] playersInGame = new Player[5];     //Players that play the game
    private int yourID;                                 //Client Player's ID
    private PowerupDeck pwd = new PowerupDeck();
    private Player me = new Player(1, pwd);

    private Label actualLife = new Label(String.valueOf(lifeValue));

    private Label[][] playersButtons1 = new Label[3][4];
    private Label[][] playersButtons2 = new Label[3][4];
    private Label[][] playersButtons3 = new Label[3][4];
    private Label[][] playersButtons4 = new Label[3][4];
    private Label[][] playersButtons5 = new Label[3][4];

    private Button power1 = new Button();
    private Button power2 = new Button();
    private Button power3 = new Button();

    private Button weapon1 = new Button();
    private Button weapon2 = new Button();
    private Button weapon3 = new Button();

    private Button[][] grabButtons = new Button[3][4];

    public AdrenalineView(Board board, Player me, Player[] players){
        this.me = me;
        this.playersInGame = players;
        this.board = board;
        this.boardNumber = board.getVariation();
        this.yourID = me.getNumber();
    }

    public AdrenalineView(){
        this.me = new Player(1, pwd);
        this.yourID = me.getNumber();
        this.playersInGame[yourID] = me;
        this.board = new Board(1);
        this.boardNumber = board.getVariation();
    };

    public static void main(String[] args) {
        launch(args);
    }

    String playerName = new String();

    @Override
    public void start(Stage primaryStage) {

        playersInGame[yourID] = me; //todo: alla fine è da rimuovere

        String blueAmmo = String.valueOf(me.getAmmo('b'));
        String yellowAmmo = String.valueOf(me.getAmmo('y'));
        String redAmmo = String.valueOf(me.getAmmo('r'));

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
        powerupCards.getChildren().addAll(power1, power2, power3);

        PowerupCard one = new PowerupCard("teleporter", 'y');       //todo da eliminare
        PowerupCard two = new PowerupCard("Newton", 'b');
        PowerupCard three = new PowerupCard("tagback grenade", 'r');
        PowerupCard[] array = {one, null, three};
        me.setPowerup(array);

        WeaponCard ones = new WeaponCard("T.H.O.R", null, null);       //todo da eliminare
        WeaponCard twos = new WeaponCard("Newton", null, null);
        WeaponCard threes = new WeaponCard("Power Glove", null, null);
        WeaponCard[] arrays = {ones, null, threes};
        me.setWeapons(arrays);

        me.setAction(2);

        power1.setGraphic(showPowerUp(me.getPowerup()[0]));
        power1.setContentDisplay(ContentDisplay.TOP);
        power1.setText("Select");
        if (me.getPowerup()[0] == null)
            power1.setDisable(true);

        power2.setGraphic(showPowerUp(me.getPowerup()[1]));
        power2.setContentDisplay(ContentDisplay.TOP);
        power2.setText("Select");
        if (me.getPowerup()[1] == null)
            power2.setDisable(true);

        power3.setGraphic(showPowerUp(me.getPowerup()[2]));
        power3.setContentDisplay(ContentDisplay.TOP);
        power3.setText("Select");
        if (me.getPowerup()[2] == null)
            power3.setDisable(true);

        weapon1.setGraphic(showWeapons(me.getWeapons()[0]));
        weapon1.setContentDisplay(ContentDisplay.TOP);
        weapon1.setText("Select");
        if (me.getWeapons()[0] == null)
            weapon1.setDisable(true);

        weapon2.setGraphic(showWeapons(me.getWeapons()[1]));
        weapon2.setContentDisplay(ContentDisplay.TOP);
        weapon2.setText("Select");
        if (me.getWeapons()[1] == null)
            weapon2.setDisable(true);

        weapon3.setGraphic(showWeapons(me.getWeapons()[2]));
        weapon3.setContentDisplay(ContentDisplay.TOP);
        weapon3.setText("Select");
        if (me.getWeapons()[2] == null)
            weapon3.setDisable(true);

        HBox weaponCards = new HBox(10);   //root node
        weaponCards.setAlignment(Pos.CENTER);
        Scene weapon = new Scene(weaponCards, 400, 200);
        Stage weap = new Stage();
        weap.setTitle("Weapon Cards");
        weap.setScene(weapon);
        weaponCards.getChildren().addAll(weapon1, weapon2, weapon3);

        //Event effects
        respawnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                redResBtn.setVisible(true);
                yellowResBtn.setVisible(true);
                blueResBtn.setVisible(true);
            }
        });

        //labels to show player 1 position
        Image p1 = new Image(AdrenalineView.class.getResource("/p1.png").toExternalForm());
        ImageView p100 = new ImageView(p1);
        Label p1x0y0 = new Label("", p100);
        p1x0y0.setVisible(false);
        xx0yy0.getChildren().add(p1x0y0);

        ImageView p101 = new ImageView(p1);
        Label p1x0y1 = new Label("", p101);
        p1x0y1.setVisible(false);
        xx0yy1.getChildren().add(p1x0y1);

        ImageView p102 = new ImageView(p1);
        Label p1x0y2 = new Label("", p102);
        p1x0y2.setVisible(false);
        xx0yy2.getChildren().add(p1x0y2);

        ImageView p103 = new ImageView(p1);
        Label p1x0y3 = new Label("", p103);
        p1x0y3.setVisible(false);
        xx0yy3.getChildren().add(p1x0y3);

        ImageView p110 = new ImageView(p1);
        Label p1x1y0 = new Label("", p110);
        xx1yy0.getChildren().add(p1x1y0);
        p1x1y0.setVisible(false);

        ImageView p111 = new ImageView(p1);
        Label p1x1y1 = new Label("", p111);
        xx1yy1.getChildren().add(p1x1y1);
        p1x1y1.setVisible(false);

        ImageView p112 = new ImageView(p1);
        Label p1x1y2 = new Label("", p112);
        xx1yy2.getChildren().add(p1x1y2);
        p1x1y2.setVisible(false);

        ImageView p113 = new ImageView(p1);
        Label p1x1y3 = new Label("", p113);
        p1x1y3.setVisible(false);
        xx1yy3.getChildren().add(p1x1y3);

        ImageView p120 = new ImageView(p1);
        Label p1x2y0 = new Label("", p120);
        xx2yy0.getChildren().add(p1x2y0);
        p1x2y0.setVisible(false);

        ImageView p121 = new ImageView(p1);
        Label p1x2y1 = new Label("", p121);
        xx2yy1.getChildren().add(p1x2y1);
        p1x2y1.setVisible(false);

        ImageView p122 = new ImageView(p1);
        Label p1x2y2 = new Label("", p122);
        xx2yy2.getChildren().add(p1x2y2);
        p1x2y2.setVisible(false);

        ImageView p123 = new ImageView(p1);
        Label p1x2y3 = new Label("", p123);
        p1x2y3.setVisible(false);
        xx2yy3.getChildren().add(p1x2y3);

        StackPane.setAlignment(p1x0y0, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x0y1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x0y2, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x0y3, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x1y0, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x1y1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x1y2, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x1y3, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x2y0, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x2y1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x2y2, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(p1x2y3, Pos.BOTTOM_LEFT);

        playersButtons1[0][0] = p1x0y0;
        playersButtons1[0][1] = p1x0y1;
        playersButtons1[0][2] = p1x0y2;
        playersButtons1[0][3] = p1x0y3;
        playersButtons1[1][0] = p1x1y0;
        playersButtons1[1][1] = p1x1y1;
        playersButtons1[1][2] = p1x1y2;
        playersButtons1[1][3] = p1x1y3;
        playersButtons1[2][0] = p1x2y0;
        playersButtons1[2][1] = p1x2y1;
        playersButtons1[2][2] = p1x2y2;
        playersButtons1[2][3] = p1x2y3;

        //buttons to show player 2 position
        Image p2 = new Image(AdrenalineView.class.getResource("/p2.png").toExternalForm());
        ImageView p200 = new ImageView(p2);
        Label p2x0y0 = new Label("", p200);
        p2x0y0.setVisible(false);
        xx0yy0.getChildren().add(p2x0y0);

        ImageView p201 = new ImageView(p2);
        Label p2x0y1 = new Label("", p201);
        p2x0y1.setVisible(false);
        xx0yy1.getChildren().add(p2x0y1);

        ImageView p202 = new ImageView(p2);
        Label p2x0y2 = new Label("", p202);
        p2x0y2.setVisible(false);
        xx0yy2.getChildren().add(p2x0y2);

        ImageView p203 = new ImageView(p2);
        Label p2x0y3 = new Label("", p203);
        p2x0y3.setVisible(false);
        xx0yy3.getChildren().add(p2x0y3);

        ImageView p210 = new ImageView(p2);
        Label p2x1y0 = new Label("", p210);
        xx1yy0.getChildren().add(p2x1y0);
        p2x1y0.setVisible(false);

        ImageView p211 = new ImageView(p2);
        Label p2x1y1 = new Label("", p211);
        xx1yy1.getChildren().add(p2x1y1);
        p2x1y1.setVisible(false);

        ImageView p212 = new ImageView(p2);
        Label p2x1y2 = new Label("", p212);
        xx1yy2.getChildren().add(p2x1y2);
        p2x1y2.setVisible(false);

        ImageView p213 = new ImageView(p2);
        Label p2x1y3 = new Label("", p213);
        p2x1y3.setVisible(false);
        xx1yy3.getChildren().add(p2x1y3);

        ImageView p220 = new ImageView(p2);
        Label p2x2y0 = new Label("", p220);
        xx2yy0.getChildren().add(p2x2y0);
        p2x2y0.setVisible(false);

        ImageView p221 = new ImageView(p2);
        Label p2x2y1 = new Label("", p221);
        xx2yy1.getChildren().add(p2x2y1);
        p2x2y1.setVisible(false);

        ImageView p222 = new ImageView(p2);
        Label p2x2y2 = new Label("", p222);
        xx2yy2.getChildren().add(p2x2y2);
        p2x2y2.setVisible(false);

        ImageView p223 = new ImageView(p2);
        Label p2x2y3 = new Label("", p223);
        p2x2y3.setVisible(false);
        xx2yy3.getChildren().add(p2x2y3);

        StackPane.setAlignment(p2x0y0, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x0y1, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x0y2, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x0y3, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x1y0, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x1y1, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x1y2, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x1y3, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x2y0, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x2y1, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x2y2, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(p2x2y3, Pos.BOTTOM_CENTER);

        playersButtons2[0][0] = p2x0y0;
        playersButtons2[0][1] = p2x0y1;
        playersButtons2[0][2] = p2x0y2;
        playersButtons2[0][3] = p2x0y3;
        playersButtons2[1][0] = p2x1y0;
        playersButtons2[1][1] = p2x1y1;
        playersButtons2[1][2] = p2x1y2;
        playersButtons2[1][3] = p2x1y3;
        playersButtons2[2][0] = p2x2y0;
        playersButtons2[2][1] = p2x2y1;
        playersButtons2[2][2] = p2x2y2;
        playersButtons2[2][3] = p2x2y3;

        //buttons to show player 3 position
        Image p3 = new Image(AdrenalineView.class.getResource("/p3.png").toExternalForm());
        ImageView p300 = new ImageView(p3);
        Label p3x0y0 = new Label("", p300);
        p3x0y0.setVisible(false);
        xx0yy0.getChildren().add(p3x0y0);

        ImageView p301 = new ImageView(p3);
        Label p3x0y1 = new Label("", p301);
        p3x0y1.setVisible(false);
        xx0yy1.getChildren().add(p3x0y1);

        ImageView p302 = new ImageView(p3);
        Label p3x0y2 = new Label("", p302);
        p3x0y2.setVisible(false);
        xx0yy2.getChildren().add(p3x0y2);

        ImageView p303 = new ImageView(p3);
        Label p3x0y3 = new Label("", p303);
        p3x0y3.setVisible(false);
        xx0yy3.getChildren().add(p3x0y3);

        ImageView p310 = new ImageView(p3);
        Label p3x1y0 = new Label("", p310);
        xx1yy0.getChildren().add(p3x1y0);
        p3x1y0.setVisible(false);

        ImageView p311 = new ImageView(p3);
        Label p3x1y1 = new Label("", p311);
        xx1yy1.getChildren().add(p3x1y1);
        p3x1y1.setVisible(false);

        ImageView p312 = new ImageView(p3);
        Label p3x1y2 = new Label("", p312);
        xx1yy2.getChildren().add(p3x1y2);
        p3x1y2.setVisible(false);

        ImageView p313 = new ImageView(p3);
        Label p3x1y3 = new Label("", p313);
        p3x1y3.setVisible(false);
        xx1yy3.getChildren().add(p3x1y3);

        ImageView p320 = new ImageView(p3);
        Label p3x2y0 = new Label("", p320);
        xx2yy0.getChildren().add(p3x2y0);
        p3x2y0.setVisible(false);

        ImageView p321 = new ImageView(p3);
        Label p3x2y1 = new Label("", p321);
        xx2yy1.getChildren().add(p3x2y1);
        p3x2y1.setVisible(false);

        ImageView p322 = new ImageView(p3);
        Label p3x2y2 = new Label("", p322);
        xx2yy2.getChildren().add(p3x2y2);
        p3x2y2.setVisible(false);

        ImageView p323 = new ImageView(p3);
        Label p3x2y3 = new Label("", p323);
        p3x2y3.setVisible(false);
        xx2yy3.getChildren().add(p3x2y3);

        StackPane.setAlignment(p3x0y0, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x0y1, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x0y2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x0y3, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x1y0, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x1y1, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x1y2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x1y3, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x2y0, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x2y1, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x2y2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(p3x2y3, Pos.BOTTOM_RIGHT);

        playersButtons3[0][0] = p3x0y0;
        playersButtons3[0][1] = p3x0y1;
        playersButtons3[0][2] = p3x0y2;
        playersButtons3[0][3] = p3x0y3;
        playersButtons3[1][0] = p3x1y0;
        playersButtons3[1][1] = p3x1y1;
        playersButtons3[1][2] = p3x1y2;
        playersButtons3[1][3] = p3x1y3;
        playersButtons3[2][0] = p3x2y0;
        playersButtons3[2][1] = p3x2y1;
        playersButtons3[2][2] = p3x2y2;
        playersButtons3[2][3] = p3x2y3;

        //buttons to show player 4 position
        Image p4 = new Image(AdrenalineView.class.getResource("/p4.png").toExternalForm());
        ImageView p400 = new ImageView(p4);
        Label p4x0y0 = new Label("", p400);
        p4x0y0.setVisible(false);
        xx0yy0.getChildren().add(p4x0y0);

        ImageView p401 = new ImageView(p4);
        Label p4x0y1 = new Label("", p401);
        p4x0y1.setVisible(false);
        xx0yy1.getChildren().add(p4x0y1);

        ImageView p402 = new ImageView(p4);
        Label p4x0y2 = new Label("", p402);
        p4x0y2.setVisible(false);
        xx0yy2.getChildren().add(p4x0y2);

        ImageView p403 = new ImageView(p4);
        Label p4x0y3 = new Label("", p403);
        p4x0y3.setVisible(false);
        xx0yy3.getChildren().add(p4x0y3);

        ImageView p410 = new ImageView(p4);
        Label p4x1y0 = new Label("", p410);
        xx1yy0.getChildren().add(p4x1y0);
        p4x1y0.setVisible(false);

        ImageView p411 = new ImageView(p4);
        Label p4x1y1 = new Label("", p411);
        xx1yy1.getChildren().add(p4x1y1);
        p4x1y1.setVisible(false);

        ImageView p412 = new ImageView(p4);
        Label p4x1y2 = new Label("", p412);
        xx1yy2.getChildren().add(p4x1y2);
        p4x1y2.setVisible(false);

        ImageView p413 = new ImageView(p4);
        Label p4x1y3 = new Label("", p413);
        p4x1y3.setVisible(false);
        xx1yy3.getChildren().add(p4x1y3);

        ImageView p420 = new ImageView(p4);
        Label p4x2y0 = new Label("", p420);
        xx2yy0.getChildren().add(p4x2y0);
        p4x2y0.setVisible(false);

        ImageView p421 = new ImageView(p4);
        Label p4x2y1 = new Label("", p421);
        xx2yy1.getChildren().add(p4x2y1);
        p4x2y1.setVisible(false);

        ImageView p422 = new ImageView(p4);
        Label p4x2y2 = new Label("", p422);
        xx2yy2.getChildren().add(p4x2y2);
        p4x2y2.setVisible(false);

        ImageView p423 = new ImageView(p4);
        Label p4x2y3 = new Label("", p423);
        p4x2y3.setVisible(false);
        xx2yy3.getChildren().add(p4x2y3);

        StackPane.setAlignment(p4x0y0, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x0y1, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x0y2, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x0y3, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x1y0, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x1y1, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x1y2, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x1y3, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x2y0, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x2y1, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x2y2, Pos.CENTER_RIGHT);
        StackPane.setAlignment(p4x2y3, Pos.CENTER_RIGHT);

        playersButtons4[0][0] = p4x0y0;
        playersButtons4[0][1] = p4x0y1;
        playersButtons4[0][2] = p4x0y2;
        playersButtons4[0][3] = p4x0y3;
        playersButtons4[1][0] = p4x1y0;
        playersButtons4[1][1] = p4x1y1;
        playersButtons4[1][2] = p4x1y2;
        playersButtons4[1][3] = p4x1y3;
        playersButtons4[2][0] = p4x2y0;
        playersButtons4[2][1] = p4x2y1;
        playersButtons4[2][2] = p4x2y2;
        playersButtons4[2][3] = p4x2y3;

        //buttons to show player 5 position
        Image p5 = new Image(AdrenalineView.class.getResource("/p5.png").toExternalForm());
        ImageView p500 = new ImageView(p5);
        Label p5x0y0 = new Label("", p500);
        p5x0y0.setVisible(false);
        xx0yy0.getChildren().add(p5x0y0);

        ImageView p501 = new ImageView(p5);
        Label p5x0y1 = new Label("", p501);
        p5x0y1.setVisible(false);
        xx0yy1.getChildren().add(p5x0y1);

        ImageView p502 = new ImageView(p5);
        Label p5x0y2 = new Label("", p502);
        p5x0y2.setVisible(false);
        xx0yy2.getChildren().add(p5x0y2);

        ImageView p503 = new ImageView(p5);
        Label p5x0y3 = new Label("", p503);
        p5x0y3.setVisible(false);
        xx0yy3.getChildren().add(p5x0y3);

        ImageView p510 = new ImageView(p5);
        Label p5x1y0 = new Label("", p510);
        xx1yy0.getChildren().add(p5x1y0);
        p5x1y0.setVisible(false);

        ImageView p511 = new ImageView(p5);
        Label p5x1y1 = new Label("", p511);
        xx1yy1.getChildren().add(p5x1y1);
        p5x1y1.setVisible(false);

        ImageView p512 = new ImageView(p5);
        Label p5x1y2 = new Label("", p512);
        xx1yy2.getChildren().add(p5x1y2);
        p5x1y2.setVisible(false);

        ImageView p513 = new ImageView(p5);
        Label p5x1y3 = new Label("", p513);
        p5x1y3.setVisible(false);
        xx1yy3.getChildren().add(p5x1y3);

        ImageView p520 = new ImageView(p5);
        Label p5x2y0 = new Label("", p520);
        xx2yy0.getChildren().add(p5x2y0);
        p5x2y0.setVisible(false);

        ImageView p521 = new ImageView(p5);
        Label p5x2y1 = new Label("", p521);
        xx2yy1.getChildren().add(p5x2y1);
        p5x2y1.setVisible(false);

        ImageView p522 = new ImageView(p5);
        Label p5x2y2 = new Label("", p522);
        xx2yy2.getChildren().add(p5x2y2);
        p5x2y2.setVisible(false);

        ImageView p523 = new ImageView(p5);
        Label p5x2y3 = new Label("", p523);
        p5x2y3.setVisible(false);
        xx2yy3.getChildren().add(p5x2y3);

        StackPane.setAlignment(p5x0y0, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x0y1, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x0y2, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x0y3, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x1y0, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x1y1, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x1y2, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x1y3, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x2y0, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x2y1, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x2y2, Pos.CENTER_LEFT);
        StackPane.setAlignment(p5x2y3, Pos.CENTER_LEFT);

        playersButtons5[0][0] = p5x0y0;
        playersButtons5[0][1] = p5x0y1;
        playersButtons5[0][2] = p5x0y2;
        playersButtons5[0][3] = p5x0y3;
        playersButtons5[1][0] = p5x1y0;
        playersButtons5[1][1] = p5x1y1;
        playersButtons5[1][2] = p5x1y2;
        playersButtons5[1][3] = p5x1y3;
        playersButtons5[2][0] = p5x2y0;
        playersButtons5[2][1] = p5x2y1;
        playersButtons5[2][2] = p5x2y2;
        playersButtons5[2][3] = p5x2y3;

        //respawn buttons
        blueResBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                power.show();
                power1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[0], board.getBoard()[0][2]);
                        power.close();
                        if(res) {
                            playersButtons1[0][2].setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });

                power2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[1], board.getBoard()[0][2]);
                        power.close();
                        if(res) {
                            p1x0y2.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });

                power3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[2], board.getBoard()[0][2]);
                        power.close();
                        if(res) {
                            p1x0y2.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });

            }
        });

        redResBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                power.show();
                power1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[0], board.getBoard()[1][0]);
                        power.close();
                        if(res) {
                            p1x1y0.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });

                power2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[1], board.getBoard()[1][0]);
                        power.close();
                        if(res) {
                            p1x1y0.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });

                power3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[2], board.getBoard()[1][0]);
                        power.close();
                        if(res) {
                            p1x1y0.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });
            }
        });

        yellowResBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                power.show();
                power1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[0], board.getBoard()[2][3]);
                        power.close();
                        if(res) {
                            p1x2y3.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });

                power2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[1], board.getBoard()[2][3]);
                        power.close();
                        if(res) {
                            p1x2y3.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });

                power3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boolean res = me.respawn(me.getPowerup()[2], board.getBoard()[2][3]);
                        power.close();
                        if(res) {
                            p1x2y3.setVisible(true);
                            redResBtn.setVisible(false);
                            yellowResBtn.setVisible(false);
                            blueResBtn.setVisible(false);
                            if (me.getLife() != 0) {
                                respawnBtn.setDisable(true);
                                updateLifeValue();
                                updatePowerUpValue();
                            }
                        }
                    }
                });
            }
        });

        //weapon button
        btnWeap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                weap.show();
            }
        });

        //powerup button
        btnPower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                power.show();
            }
        });

        //move
        bxx0yy0.setVisible(false);
        bxx0yy1.setVisible(false);
        bxx0yy2.setVisible(false);
        bxx0yy3.setVisible(false);
        bxx1yy0.setVisible(false);
        bxx1yy1.setVisible(false);
        bxx1yy2.setVisible(false);
        bxx1yy3.setVisible(false);
        bxx2yy0.setVisible(false);
        bxx2yy1.setVisible(false);
        bxx2yy2.setVisible(false);
        bxx2yy3.setVisible(false);

        xx0yy0.getChildren().add(bxx0yy0);
        xx0yy1.getChildren().add(bxx0yy1);
        xx0yy2.getChildren().add(bxx0yy2);
        xx0yy3.getChildren().add(bxx0yy3);
        xx1yy0.getChildren().add(bxx1yy0);
        xx1yy1.getChildren().add(bxx1yy1);
        xx1yy2.getChildren().add(bxx1yy2);
        xx1yy3.getChildren().add(bxx1yy3);
        xx2yy0.getChildren().add(bxx2yy0);
        xx2yy1.getChildren().add(bxx2yy1);
        xx2yy2.getChildren().add(bxx2yy2);
        xx2yy3.getChildren().add(bxx2yy3);

        moveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(boardNumber==1){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);
                }
                else if(boardNumber == 2){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx0yy3.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);
                }
                else if(boardNumber == 3){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy0.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);
                }
                else if(boardNumber == 4){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx0yy3.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy0.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);
                }
                bxx0yy0.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[0][0]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);System.out.println("end Move in 00 counter "+me.getAction());

                        }
                    }
                });

                bxx0yy1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[0][1]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);System.out.println("end Move in 00 counter "+me.getAction());

                        }
                    }
                });

                bxx0yy2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[0][2]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);System.out.println("end Move in 00 counter "+me.getAction());

                        }
                    }
                });

                bxx0yy3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[0][3]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);System.out.println("end Move in 00 counter "+me.getAction());

                        }
                    }
                });

                bxx1yy0.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[1][0]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

                bxx1yy1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[1][1]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

                bxx1yy2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[1][2]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

                bxx1yy3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[1][3]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

                bxx2yy0.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[2][0]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

                bxx2yy1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[2][1]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

                bxx2yy2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[2][2]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

                bxx2yy3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[2][3]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && moveCounter<2)||(ok && moveCounter<3 && board.isFinalRound())){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveCounter++;
                            action++;
                            me.setAction(action);
                        }
                        else if(ok){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                        }
                    }
                });

            }
        });

        //move&grab

        gxx0yy0.setVisible(false);
        gxx0yy1.setVisible(false);
        gxx0yy2.setVisible(false);
        gxx0yy3.setVisible(false);
        gxx1yy0.setVisible(false);
        gxx1yy1.setVisible(false);
        gxx1yy2.setVisible(false);
        gxx1yy3.setVisible(false);
        gxx2yy0.setVisible(false);
        gxx2yy1.setVisible(false);
        gxx2yy2.setVisible(false);
        gxx2yy3.setVisible(false);

        StackPane.setAlignment(gxx0yy0, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx0yy1, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx0yy2, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx0yy3, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx1yy0, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx1yy1, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx1yy2, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx1yy3, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx2yy0, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx2yy1, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx2yy2, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gxx2yy3, Pos.BOTTOM_CENTER);

        xx0yy0.getChildren().add(gxx0yy0);
        xx0yy1.getChildren().add(gxx0yy1);
        xx0yy2.getChildren().add(gxx0yy2);
        xx0yy3.getChildren().add(gxx0yy3);
        xx1yy0.getChildren().add(gxx1yy0);
        xx1yy1.getChildren().add(gxx1yy1);
        xx1yy2.getChildren().add(gxx1yy2);
        xx1yy3.getChildren().add(gxx1yy3);
        xx2yy0.getChildren().add(gxx2yy0);
        xx2yy1.getChildren().add(gxx2yy1);
        xx2yy2.getChildren().add(gxx2yy2);
        xx2yy3.getChildren().add(gxx2yy3);

        moveAndGrabBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(boardNumber==1){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);

                    gxx0yy0.setVisible(true);
                    gxx0yy1.setVisible(true);
                    gxx0yy2.setVisible(true);
                    gxx1yy0.setVisible(true);
                    gxx1yy1.setVisible(true);
                    gxx1yy2.setVisible(true);
                    gxx1yy3.setVisible(true);
                    gxx2yy1.setVisible(true);
                    gxx2yy2.setVisible(true);
                    gxx2yy3.setVisible(true);
                }
                else if(boardNumber == 2){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx0yy3.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);

                    gxx0yy0.setVisible(true);
                    gxx0yy1.setVisible(true);
                    gxx0yy2.setVisible(true);
                    gxx0yy3.setVisible(true);
                    gxx1yy0.setVisible(true);
                    gxx1yy1.setVisible(true);
                    gxx1yy2.setVisible(true);
                    gxx1yy3.setVisible(true);
                    gxx2yy1.setVisible(true);
                    gxx2yy2.setVisible(true);
                    gxx2yy3.setVisible(true);
                }
                else if(boardNumber == 3){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy0.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);

                    gxx0yy0.setVisible(true);
                    gxx0yy1.setVisible(true);
                    gxx0yy2.setVisible(true);
                    gxx1yy0.setVisible(true);
                    gxx1yy1.setVisible(true);
                    gxx1yy2.setVisible(true);
                    gxx1yy3.setVisible(true);
                    gxx2yy0.setVisible(true);
                    gxx2yy1.setVisible(true);
                    gxx2yy2.setVisible(true);
                    gxx2yy3.setVisible(true);
                }
                else if(boardNumber == 4){
                    bxx0yy0.setVisible(true);
                    bxx0yy1.setVisible(true);
                    bxx0yy2.setVisible(true);
                    bxx0yy3.setVisible(true);
                    bxx1yy0.setVisible(true);
                    bxx1yy1.setVisible(true);
                    bxx1yy2.setVisible(true);
                    bxx1yy3.setVisible(true);
                    bxx2yy0.setVisible(true);
                    bxx2yy1.setVisible(true);
                    bxx2yy2.setVisible(true);
                    bxx2yy3.setVisible(true);

                    gxx0yy0.setVisible(true);
                    gxx0yy1.setVisible(true);
                    gxx0yy2.setVisible(true);
                    gxx0yy3.setVisible(true);
                    gxx1yy0.setVisible(true);
                    gxx1yy1.setVisible(true);
                    gxx1yy2.setVisible(true);
                    gxx1yy3.setVisible(true);
                    gxx2yy0.setVisible(true);
                    gxx2yy1.setVisible(true);
                    gxx2yy2.setVisible(true);
                    gxx2yy3.setVisible(true);
                }
                bxx0yy0.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Position[] move = new Position[]{board.getBoard()[0][0]};
                        int[] coordinate = me.getPosition().getCoordinate();
                        boolean ok = me.move(move);
                        int action = me.getAction();
                        if((ok && lifeValue<8)&&moveAndGrabCounter==0){
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);
                            moveAndGrabCounter++;
                            action++;
                            me.setAction(action);

                            //todo finire funzioni di grab
                        }
                        else if(ok && lifeValue>8){
                            bxx0yy0.setVisible(false);
                            bxx0yy1.setVisible(false);
                            bxx0yy2.setVisible(false);
                            bxx0yy3.setVisible(false);
                            bxx1yy0.setVisible(false);
                            bxx1yy1.setVisible(false);
                            bxx1yy2.setVisible(false);
                            bxx1yy3.setVisible(false);
                            bxx2yy0.setVisible(false);
                            bxx2yy1.setVisible(false);
                            bxx2yy2.setVisible(false);
                            bxx2yy3.setVisible(false);

                            moveBtn.setDisable(true);

                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(false);
                            coordinate = me.getPosition().getCoordinate();
                            playersButtons1[coordinate[0]][coordinate[1]].setVisible(true);System.out.println("end Move in 00 counter "+me.getAction());

                        }
                    }
                });

            }
        });


    }

    //A method to create ImageView for powerup
    public ImageView showPowerUp(PowerupCard powerup) {

        if(powerup != null) {
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
            } else if (color == 'b') {
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
            } else if (color == 'y') {
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
        }
        else{
            Image empty = new Image(AdrenalineView.class.getResource("/power.png").toExternalForm());
            ImageView emptyIV = new ImageView(empty);
            return emptyIV;
        }
        return null;
    }

    public void updateLifeValue(){
        lifeValue = me.getLife();
        actualLife.setText(String.valueOf(lifeValue));
    }

    public void updatePowerUpValue(){
        power1.setGraphic(showPowerUp(me.getPowerup()[0]));
        power1.setContentDisplay(ContentDisplay.TOP);
        power1.setText("Select");
        if (me.getPowerup()[0] == null)
            power1.setDisable(true);

        power2.setGraphic(showPowerUp(me.getPowerup()[1]));
        power2.setContentDisplay(ContentDisplay.TOP);
        power2.setText("Select");
        if (me.getPowerup()[1] == null)
            power2.setDisable(true);

        power3.setGraphic(showPowerUp(me.getPowerup()[2]));
        power3.setContentDisplay(ContentDisplay.TOP);
        power3.setText("Select");
        if (me.getPowerup()[2] == null)
            power3.setDisable(true);
    }

    //a method to create ImageView for weapons
    public ImageView showWeapons(WeaponCard weap) {

        if(weap != null) {
            String name = weap.getName();

            switch (name) {
                case "Cyberblade":
                    Image cyb = new Image(AdrenalineView.class.getResource("/cyb.png").toExternalForm());
                    ImageView cybIV = new ImageView(cyb);
                    return cybIV;
                case "ElectroScythe":
                    Image es = new Image(AdrenalineView.class.getResource("/es.png").toExternalForm());
                    ImageView esIV = new ImageView(es);
                    return esIV;

                case "Flamethrower":
                    Image ft = new Image(AdrenalineView.class.getResource("/ft.png").toExternalForm());
                    ImageView ftIV = new ImageView(ft);
                    return ftIV;

                case "Furnace":
                    Image fur = new Image(AdrenalineView.class.getResource("/fur.png").toExternalForm());
                    ImageView furIV = new ImageView(fur);
                    return furIV;

                case "Grenade Launcher":
                    Image gl = new Image(AdrenalineView.class.getResource("/gl.png").toExternalForm());
                    ImageView glIV = new ImageView(gl);
                    return glIV;

                case "HeatSeeker":
                    Image hs = new Image(AdrenalineView.class.getResource("/hs.png").toExternalForm());
                    ImageView hsIV = new ImageView(hs);
                    return hsIV;

                case "Hellion":
                    Image hel = new Image(AdrenalineView.class.getResource("/hel.png").toExternalForm());
                    ImageView helIV = new ImageView(hel);
                    return helIV;

                case "LockRifle":
                    Image lr = new Image(AdrenalineView.class.getResource("/lr.png").toExternalForm());
                    ImageView lrIV = new ImageView(lr);
                    return lrIV;

                case "Machine Gun":
                    Image mg = new Image(AdrenalineView.class.getResource("/mg.png").toExternalForm());
                    ImageView mgIV = new ImageView(mg);
                    return mgIV;

                case "PlasmaGun":
                    Image pg = new Image(AdrenalineView.class.getResource("/pg.png").toExternalForm());
                    ImageView pgIV = new ImageView(pg);
                    return pgIV;

                case "Power Glove":
                    Image pow = new Image(AdrenalineView.class.getResource("/pow.png").toExternalForm());
                    ImageView powIV = new ImageView(pow);
                    return powIV;

                case "Railgun":
                    Image rail = new Image(AdrenalineView.class.getResource("/rail.png").toExternalForm());
                    ImageView railIV = new ImageView(rail);
                    return railIV;

                case "Rocket Launcher":
                    Image rl = new Image(AdrenalineView.class.getResource("/rl.png").toExternalForm());
                    ImageView rlIV = new ImageView(rl);
                    return rlIV;

                case "Shock Wave":
                    Image sw = new Image(AdrenalineView.class.getResource("/sw.png").toExternalForm());
                    ImageView swIV = new ImageView(sw);
                    return swIV;

                case "Shotgun":
                    Image sg = new Image(AdrenalineView.class.getResource("/sg.png").toExternalForm());
                    ImageView sgIV = new ImageView(sg);
                    return sgIV;

                case "SledgeHammer":
                    Image sh = new Image(AdrenalineView.class.getResource("/sh.png").toExternalForm());
                    ImageView shIV = new ImageView(sh);
                    return shIV;

                case "T.H.O.R":
                    Image th = new Image(AdrenalineView.class.getResource("/th.png").toExternalForm());
                    ImageView thIV = new ImageView(th);
                    return thIV;

                case "Tractor Beam":
                    Image tb = new Image(AdrenalineView.class.getResource("/tb.png").toExternalForm());
                    ImageView tbIV = new ImageView(tb);
                    return tbIV;

                case "Vortex Cannon":
                    Image vc = new Image(AdrenalineView.class.getResource("/vc.png").toExternalForm());
                    ImageView vcIV = new ImageView(vc);
                    return vcIV;

                case "Whisper":
                    Image whis = new Image(AdrenalineView.class.getResource("/whis.png").toExternalForm());
                    ImageView whisIV = new ImageView(whis);
                    return whisIV;

                case "ZX-2":
                    Image zx = new Image(AdrenalineView.class.getResource("/zx.png").toExternalForm());
                    ImageView zxIV = new ImageView(zx);
                    return zxIV;
            }
        }
        else{
            Image empty = new Image(AdrenalineView.class.getResource("/weap.png").toExternalForm());
            ImageView emptyIV = new ImageView(empty);
            return emptyIV;
        }
        return null;
    }
}
