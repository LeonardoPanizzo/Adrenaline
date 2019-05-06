package Adrenaline.model;

public class Model {/*
    public static void main (String args[]){
        int numBoard;
        System.out.println("Scegli il tabellone");
        try{
            numBoard = System.in.read();
            Board board = new Board(numBoard);                          //Board is created
            PowerupDeck PUDeck= new PowerupDeck();                      //PowerUp deck is created
            WeaponDeck WDeck = new WeaponDeck();                        //Weapon deck is created
            int playerNumber =0;
            Player players[] = new Player[5];
            //todo: imostare time out per uscire dal loop e iniziare la partita
            boolean timer = true;
            while(timer){
                System.out.println("Attendo giocatore...");
                players[playerNumber] = new Player (playerNumber);
                if(playerNumber == 4)
                    timer = false;
                playerNumber++;
                System.out.println("Giocatore" + playerNumber + " connesso");
            }                                                           //Players are created
            players[0].setFirstPlayer(true);                            //Player in the firs position is the first one
            int round = board.getRound();
            int currentPlayer = 0;
            while(!board.isFinalRound()){
                currentPlayer = round % 5;
                while(players[currentPlayer] == null)                   //Needed when the players are not five
                    currentPlayer = (currentPlayer + 1) % 5;

                players[currentPlayer].setRound(true);                              //todo dare il controllo al controller per le azioni del giocatore
                players[currentPlayer].setRound(false);                             //todo ridare il controllo al Adrenaline.model

                if(players[currentPlayer].getMadeDamage() != 0){                    //if the player makes damages, the method gives them to the other players.
                    int [] damagedPlayers = players[currentPlayer].getDamagedPlayers();
                    for(int k : damagedPlayers){
                        for(int damage = 0; damage < players[currentPlayer].getMadeDamage(); damage++)
                            players[damagedPlayers[k]].receivedDamages(currentPlayer);
                        if(players[damagedPlayers[k]].getLife() < 1) {                  //the damaged player die
                            board.setSkulls(players[currentPlayer].getNumber());
                            int [] points = players[damagedPlayers[k]].givePoints();    //points are assigned to each player
                            for(int i=0; i<5; i++){
                                players[i].setScore(points[i]);
                            }
                        }
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
                for(int f=0; f<players.length; f++){
                    if(players[f] != null){
                        players[f].setFinalRound(true);
                        players[f].setRound(true);
                        //todo passare i controllo al controller del giocatore
                    }
                }
            }
        }
        catch (IOException exception){exception.printStackTrace();}
    }
    */



    /*
    *
    *
    ---- CONNECTIVITY TESTS ----
    *
    *



    Client client = new Client("127.0.0.1", 1337);
        try {
        client.startClient();
    } catch (IOException e) {
        System.err.println(e.getMessage());
    }

    */

}
