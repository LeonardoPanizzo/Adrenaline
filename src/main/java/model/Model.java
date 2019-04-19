package model;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Model {
    public static void main (String args[]){
        int numBoard;
        System.out.println("Scegli il tabellone");
        try{
            numBoard = System.in.read();
            Board board = new Board(numBoard);                          //Board creata

            Player players[] = new Player[5];
            //todo: imostare time out per uscire dal loop e iniziare la partita
            boolean timer = true;
            while(timer){
                System.out.println("Attendo giocatore...");
                int playerNumber = System.in.read();
                players[playerNumber] = new Player (playerNumber);
                if(playerNumber == 4)
                    timer = false;
            }                                                           //Array di giocatori creato

            while(!board.isFinalRound()){
                int i = 0;
                board.setRound(i);

                players[i].setRound(true);                              //todo dare il controllo al controller per le azioni del giocatore
                if(players[i].isMadeDamage() != 0){
                    for(int k : players[i].getDamagedPlayers()){
               //         for(int y; y<)
                        players[k].receivedDamages(i);
                    }
                }
            }
        }
        catch (IOException exception){exception.printStackTrace();}
    }
}
