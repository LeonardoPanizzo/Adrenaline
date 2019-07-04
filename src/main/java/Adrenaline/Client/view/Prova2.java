package Adrenaline.Client.view;

import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;
import Adrenaline.Server.model.PowerupDeck;

public class Prova2 {
    public static Board board = new Board(1);
    public static PowerupDeck pw = new PowerupDeck();
    public static Player me = new Player(1, pw);
    public static Player[] players = new Player[]{new Player(0, pw), me, new Player(2, pw), new Player(3, pw), new Player(4, pw)};
    public static boolean counter = true;

    public static void setName() {
        players[0].setName("Miriam");
        players[1].setName("Andrea");
        players[2].setName("leo");
        players[3].setName("paolo");
        players[4].setName("rani");
    }

    public static void stop(){
        counter = false;
    }
}
