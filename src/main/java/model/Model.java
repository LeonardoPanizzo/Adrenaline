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
            int round = board.getRound();
            int currentPlayer = 0;
            while(!board.isFinalRound()){
                currentPlayer = round % 5;
                while(players[currentPlayer] == null)                   //Needed when the players are not five
                    currentPlayer = (currentPlayer + 1) % 5;

                players[currentPlayer].setRound(true);                              //todo dare il controllo al controller per le azioni del giocatore
                players[currentPlayer].setRound(false);                             //todo ridare il controllo al model

                if(players[currentPlayer].getMadeDamage() != 0){                    //if the player makes damages, the method gives them to the other players.
                    int [] damagedPlayers = players[currentPlayer].getDamagedPlayers();
                    for(int k : damagedPlayers){
                        for(int damage = 0; damage < players[currentPlayer].getMadeDamage(); damage++)
                            players[damagedPlayers[k]].receivedDamages(currentPlayer);
                        if(players[damagedPlayers[k]].getLife() == 0)               //the damaged player die
                            board.setSkulls(damagedPlayers[k]);
                        if(players[damagedPlayers[k]].getLife() == -1){             //the current player has made overkill
                            board.setSkulls(damagedPlayers[k]);
                            int[] marks = players[currentPlayer].getMarksReceived();
                            marks[damagedPlayers[k]]++;
                            players[currentPlayer].setMarksReceived(marks);
                            marks = players[damagedPlayers[k]].getMarksGiven();     //current player received 1 mark from the damaged player with life = -1
                            marks[currentPlayer]++;
                            players[damagedPlayers[k]].setMarksGiven(marks);        //player damaged's marks given are updated
                        }
                    }
                }
                board.setRound(round++);        //we go to the next round
                board.setFinalRound();
            }
            if (board.isFinalRound()){
                //todo implementare le funzioni del round finale
            }
        }
        catch (IOException exception){exception.printStackTrace();}
    }
}
