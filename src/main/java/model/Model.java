package model;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Model {
    public static void main (String args[]){
        int numBoard;
        System.out.println("Scegli il tabellone");
        try{
            numBoard = System.in.read();
            Board board = new Board(numBoard);
        }catch (IOException exception){exception.printStackTrace();};

        Player players[] = new Player[5];
        //todo: imostare time out per uscire dal loop e iniziare la partita
        boolean timer = true;
        while(timer){
            System.out.println("Attendo giocatore...");
            try{
                int playerNumber = System.in.read();
                players[playerNumber] = new Player (playerNumber);
                if(playerNumber == 4)
                    timer = false;
            }catch (IOException exception){exception.printStackTrace();};
        }

        
    }
}
