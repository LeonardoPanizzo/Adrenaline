package Adrenaline.Server.model;

import Adrenaline.Server.control.BiController;

import java.util.Scanner;
import Adrenaline.Server.model.Response;
import Adrenaline.ClientHandler;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;
import Adrenaline.Server.model.RequestHandler;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.rmi.RemoteException;

public class Model{//todo: dovrebbero gli attributi essere static?
    private static Board board;
    private static PowerupDeck pud;
    private static Player[] players;

    /**
     * Here is red a char instead of an int to create a more stable system (if the user send a letter as an input the program doesnt crash)
     */
    public void setBoard(){
        char c;
        int x;
        Scanner keyboard=new Scanner(System.in);
        do {
            System.out.println("Choose a number between 1 and 4 to select the board");
            c=keyboard.next().charAt(0);
            if(c>='1'&& c<='4'){
                x=Character.getNumericValue(c);
                board = new Board(x);
                System.out.println("Board created\n");
            }
        }while(c<'1'||c>'4');
    }

    public void pickup(int playernumber){
        //todo: mettere i giocatori con il numero che corrisponde con il numero nel vettore player
        //qui viene fatta la distizione fra la posizione con ammo e quella con armi
        Boolean respawn=players[playernumber].getPosition().isRespawnPoint();
        if(respawn){
            //todo: fare la modifica il grabWeaponCard e poi chiamare qui il metodo
        }else{
            players[playernumber].grabAmmoCard();
        }
    }


    public static void main (String args[]) {
        //todo parte in cui viene stabilita la connessione
        //todo: la gestione dei turni, alla fine del turno
        board.setFinalRound();
        board.isFinalRound();//usato per vedere la fine dei turni clasici quindi finisce il ciclo e poi si passa al turno finale
    }


        /*
        //todo stampe necessarie a inizio turno per il giocatore in CLI
        stampa(board.getround());
        stampa(players[se stesso].completeString())     //stampa le informazioni su se stesso
        for(int i=0; i<nplayers; i++){      //stampa le informazioni dei giocatori
            stampa(players[i].toString());
        }
        stampa(board.toString());

        //todo stampe necessarie per chiedere quale azione vuole fare
        tramite stampa viene chiesto che l'utente inserisca che azione vuole eseguire (viene letto un carattere per rendere il sistema robusto)
           1.move
           2.grab
           3.shoot
           4.use powerup
           5.reload
           la gestione delle azioni nel turno di un giocatore è spiegata sotto
         */

        /*Gestione turno di un giocatore
        while(!board.isfinalround()){
            board.setround(board.getround()+1);
            nround=board.getround();
            while(players[nround%5]==null)
                nround++;
            players[nround%5]=setround(true);
            while(players[nround%5].getaction()>0){
                //usare l'interfaccia con l'utente per chiedere quale azione vuole essere eseguita
                if(move){
                    //usare l'interfaccia per chiedere le posizioni e poi invocare il metodo player.move
                }else if(shot){
                    //usare l'interfaccia per chiedere i parametri del metodo
                    //vedere se l'attaccante ha mirino e vuole usarlo
                    //vedere se gli utenti attaccati hanno la granata venom e vogliono usarla
                }else if(grab){
                    //
                }else if(usepowerup){
                    //valido solo per raggio cinetico e teletrasporto
                }
            }//qua finisce il while con le mosse del giocatore
            //chiedere al giocatore se vuole ricaricare qualche arma
            players[nround%5].setround(false);
            board.setfinalround();
        }
        //qua inizia il turno finale
         */

        /*
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
            }
            //Players are created
            players[0].setFirstPlayer(true);                            //Player in the firs position is the first one
            int round = board.getRound();
            int currentPlayer = 0;
            while(!board.isFinalRound()){
                currentPlayer = round % 5;
                while(players[currentPlayer] == null)                   //Needed when the players are not five
                    currentPlayer = (currentPlayer + 1) % 5;

                players[currentPlayer].setRound(true);                              //todo dare il controllo al controller per le azioni del giocatore
                players[currentPlayer].setRound(false);                             //todo ridare il controllo al Adrenaline.Server.model

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