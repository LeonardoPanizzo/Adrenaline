package Adrenaline.Client.view;

import Adrenaline.Server.control.RemoteBiCon;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;
import Adrenaline.Server.model.PowerupDeck;

import java.io.Serializable;

public class Prova2 implements Serializable {
    public static Board board;
    public static Player me;
    public static Player[] players;
    public static RemoteBiCon serverIF;

  //  public static void setName() {
  //      players[0].setName("Miriam");
  //      players[1].setName("Andrea");
  //      players[2].setName("leo");
  //      players[3].setName("paolo");
  //      players[4].setName("rani");
  //  }

    public static void setBoard(Board b){
        board = b;
    }

    public static void setMe(Player me) {
        Prova2.me = me;
    }

    public static void setPlayers(Player[] players) {
        Prova2.players = players;
    }

    public static void setServerIF(RemoteBiCon serverIF) {
        Prova2.serverIF = serverIF;
    }
}
