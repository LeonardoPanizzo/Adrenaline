package Adrenaline;

import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;


// singleton

public class ClientContext {
    private static ClientContext instance;
    private Board currentBoard;
    private PowerupDeck currentPUDeck;

    private ClientContext() {
    }


    public static synchronized ClientContext get() {
        if (instance == null) {
            instance = new ClientContext();
        }

        return instance;
    }

    public PowerupDeck getCurrentPUDeck() {
        return currentPUDeck;
    }

    public void setCurrentPUDeck(PowerupDeck currentPUDeck) {
        this.currentPUDeck = currentPUDeck;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

}
