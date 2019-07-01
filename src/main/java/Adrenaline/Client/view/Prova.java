package Adrenaline.Client.view;

import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;
import Adrenaline.Server.model.PowerupDeck;
import javafx.application.Application;

public class Prova {
    public static void main (String[] args){
        Prova2.setBoard();
        AdrenalineView gui = new AdrenalineView();
        gui.main(args);
        //AdrenalineView gui2 = new AdrenalineView(board, players[1], players);
    }
}
