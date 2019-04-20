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

            int courentPlayer = 0;
            while(!board.isFinalRound()){
                courentPlayer = board.getRound() % 5;
                while(players[courentPlayer] == null)                   //Needed when the players are not five
                    courentPlayer = (courentPlayer + 1) % 5;

                players[courentPlayer].setRound(true);                              //todo dare il controllo al controller per le azioni del giocatore
                players[courentPlayer].setRound(false);                             //todo ridare il controllo al model

                if(players[courentPlayer].getMadeDamage() != 0){                    //if the player makes damages, the method gives them to the other players.
                    int [] damagedPlayers = players[courentPlayer].getDamagedPlayers();
                    for(int k : damagedPlayers){
                        for(int damage = 0; damage < players[courentPlayer].getMadeDamage(); damage++)
                            players[damagedPlayers[k]].receivedDamages(courentPlayer);
                        if(players[damagedPlayers[k]].getLife() == 0){
                            board.setSkulls(damagedPlayers[k]);
                        }
                    }
                }

                for (int player = 0; player<5; player++){
                    if (players[player].getLife() < 1){                 //when a player die, we update skulls in board

                    }
                }
            }
        }
        catch (IOException exception){exception.printStackTrace();}
    }
}
