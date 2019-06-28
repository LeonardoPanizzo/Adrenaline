package Adrenaline.Server.model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Scanner;

public class Model{//todo: dovrebbero gli attributi essere static?
    private static Board board;
    private static PowerupDeck pud;
    private static Player[] players;


    private Player getPlayerByNumber(int nplayer){
        Player actualPlayer=null;
        for(int i=0; i<players.length; i++){
            if(nplayer==players[i].getNumber()){
                actualPlayer=players[i];
            }
        }
        return actualPlayer;
    }

    /**
     * return the powerups the player wants to use in a payment
     * @param p
     * @return
     */
    private PowerupCard[] playerpowerup(Player p){
        PowerupCard[] selected=new PowerupCard[3];
        PowerupCard[] owned=p.getPowerup();
        boolean correctinput=true;      //used to checks if the user insert the same powerup twice
        Scanner keyboard=new Scanner(System.in);
        for(int j=0; j<3; j++){
            System.out.println(j+". "+owned[j].getName()+"\n");
        }
        int i=0;
        char c;
        int[] choosen=new int[3];
        for(int j=0;j<3;j++){
            choosen[j]=-1;
        }
        do{
            System.out.println("Select the powerup you want to use, n to stop\n");
            c=keyboard.next().charAt(0);
            if(c>='0' && c<='2'){
                choosen[i]=Character.getNumericValue(c);
                i++;
            }
        }while(c!='n' || i!=3);
        for(int j=0; j<i-1 && correctinput; j++){
            if(choosen[j]!=-1) {
                correctinput = choosen[j] != choosen[j + 1];
            }
        }
        for(int j=0; j<i && correctinput; j++){
            selected[j]=owned[choosen[j]];
        }
        return selected;
    }

    private Position[] getplayermovement(){
        Scanner keyboard=new Scanner(System.in);
        Position temp;
        ArrayList<Position> moves=new ArrayList<Position>();
        int x,y;
        char c,k;
        do{
           System.out.println("\nDo you want to insert x,y coordinate?y/n\n");
           k=keyboard.next().charAt(0);
           if(k=='y'){
               System.out.println("Insert x coordinate\n");
               k=keyboard.next().charAt(0);
               x=Character.getNumericValue(k);
               System.out.println("Insert y coordinate\n");
               k=keyboard.next().charAt(0);
               y=Character.getNumericValue(k);
               if(x>=0 && x<=2 && y>=0 && y<=3){
                   temp=board.getBoard()[x][y];
                   moves.add(temp);
               }
           }
        }while(k!='n');
        Position[] positions = new Position[moves.size()];
        for(int i=0; i<positions.length; i++){
            positions[i]=moves.get(i);
        }
        return positions;
    }

    /**
     * Here is red a char instead of an int to create a more stable system (if the user send a letter as an input the program doesnt crash)
     */
    public void setBoard(){
        char c;
        int x;
        Scanner keyboard=new Scanner(System.in);
        do {
            System.out.println("\nChoose a number between 1 and 4 to select the board\n");
            c=keyboard.next().charAt(0);
            if(c>='1'&& c<='4'){
                x=Character.getNumericValue(c);
                board = new Board(x);
                System.out.println("\nBoard created\n");
            }
        }while(c<'1'||c>'4');
    }

    private boolean hasNoSpaceweapon(Player p){     //if true the player doesnt have the space to pickup a weapon
        WeaponCard[] weapons=p.getWeapons();
        int i=0;
        boolean emptyspace=false;
        for(;i<weapons.length & !emptyspace;i++){
            emptyspace=weapons[i]==null;
        }
        return emptyspace;
    }
    
    private void pickweapon(Player player){
        char c;
        int i = 0;
        int weaponposition;     //position of the wanted weapon
        int[] positionpowerup = new int[3];
        PowerupCard[] payment = new PowerupCard[]{null, null, null};
        WeaponCard[] weapons = player.getPosition().showWeapons();
        Scanner keyboard = new Scanner(System.in);
        WeaponCard tempWeapon=null;
        char wposition;     //the position of the weapon that the player wants to discard
        boolean changeweapon=hasNoSpaceweapon(player);  //used to see if the player has the space to pickup a weapon
        boolean done=false;     //true when the player pickup a weapon
        if(changeweapon){       //if the player has no space a weapon is saved in tempWeapon
            System.out.println("\nWhich weapon you want to discard?\n");
            for(int j=0; j<player.getWeapons().length; j++){
                System.out.println(j+". "+player.getWeapons()[j].getName()+"\n");
            }
            do{
                wposition=keyboard.next().charAt(0);
            }while(wposition<'0'||wposition>'2');
            tempWeapon=player.getWeapons()[Character.getNumericValue(wposition)];
            player.discardWeapon(tempWeapon);
        }
        do {
            System.out.println("Choose the weapon you want to pick\n");
            for (int j = 0; j < weapons.length; j++) {
                if (weapons[j] != null) {
                    System.out.println(j + ". " + weapons[j].getName() + "\n");
                }
            }
            c = keyboard.next().charAt(0);
        } while (c < '0' || c > '3');
        weaponposition = Character.getNumericValue(c);
        do {
            System.out.println("Do you want to use powerups to pay?y/n");
            c = keyboard.next().charAt(0);
        } while (c != 'n' || c != 'y');
        if (c == 'n') {
            if(player.grabWeaponCard(weapons[weaponposition])){
                System.out.println("\n Weapon added\n");
                if(changeweapon){
                    tempWeapon.reload();
                    player.getPosition().giveWeapon(tempWeapon);
                }
            }else{
                System.out.println("\nWeapon not taken\n");
                if(changeweapon){
                    player.addWeapon(tempWeapon);   //discarded weapon given back to the player
                }
            }
        } else if (c == 'y') {
            payment=playerpowerup(player);
            if(player.grabWeaponCard(weapons[weaponposition], payment)){
                System.out.println("\n Weapon added\n");
                if(changeweapon){
                    tempWeapon.reload();
                    player.getPosition().giveWeapon(tempWeapon);
                }
            }else{
                System.out.println("\nWeapon not taken\n");
                if(changeweapon){
                    player.addWeapon(tempWeapon);   //discarded weapon given back to the player
                }
            }
        }
    }

    private void reloadWeapon(Player p){
        PowerupCard[] payment;
        WeaponCard weaponToReload;
        boolean correct=false;
        char c;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nWhat weapons you want to reaload?\n");
        for(int i=0; i<p.getWeapons().length; i++){
            if(p.getWeapons()[i]!=null) {
                System.out.println(i + ". " + p.getWeapons()[i].getName()+"\n");
            }
        }
        do{
            System.out.println("Insert weapon number\n");
            c=keyboard.next().charAt(0);
            if(c>='0' && c<'3'){
                if(p.getWeapons()[Character.getNumericValue(c)]!=null){
                    correct=true;
                }
            }
        }while(!correct);
        weaponToReload=p.getWeapons()[Character.getNumericValue(c)];
        System.out.println("\nDo you want to use powerups in the payment?y/n\n");
        c=keyboard.next().charAt(0);
        if(c=='y'){
            payment=playerpowerup(p);
            p.reload(weaponToReload, payment);
        }else{
            p.reload(weaponToReload);
        }
    }

    public void reload(int playernumber){
        Player p=getPlayerByNumber(playernumber);
        char c;
        Scanner keyboard = new Scanner(System.in);
        do{
            System.out.println("\nDo you want to charge a weapon?y/n\n");
            c=keyboard.next().charAt(0);
            if(c=='y'){
                reloadWeapon(p);
            }
        }while(c!='n');
    }

    public void pickup(int playernumber){
        boolean respawn=players[playernumber].getPosition().isRespawnPoint();   //checks if it is respawn point
        Player actualPlayer=getPlayerByNumber(playernumber);
        if(actualPlayer!=null) {
            if (respawn) {
                pickweapon(actualPlayer);   //This function checks if the user wants to play with ammos or not
            } else {
                actualPlayer.grabAmmoCard();
            }
        }
    }

    public Board getBoard(){
        return board;
    }

    public Player[] getPlayers(){
        return players;
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


//todo qui c'è la prima versione del model del client
/*
package Adrenaline.Client.model;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;
import Adrenaline.Server.model.PowerupCard;
import Adrenaline.Server.model.WeaponCard;

import java.util.Scanner;

public class Model{
    private Board board;
    private Player player;
    private Player[] players;

    /**
     * Action is a char and not an integer so the system is more robust
     * @return
     */
/*
public void chooseAction(){
    char action;
    Scanner keyboard=new Scanner(System.in);
    do {
        System.out.println("choose what action you want to do \n0. print information\n1.move\n2.pick up\n3.shoot\n4.use powerup");
        action=keyboard.next().charAt(0);
        if(action>='0' && action<='4'){
            switch (action){
                case '0':
                    printInfo();
                    break;
                case '1':
                    setmovement();
                    break;
                case '2':
                    pickup();
                    break;
                case '3':
                    shoot();
                    break;
                case '4':
                    usePowerUp();
                    break;
            }
        }
    }while(action<'0' || action>'4');//todo:aggiungere anche il controllo sul numero player.action
}

    public void printInfo(){
        System.out.println(board.getRound());
        System.out.println(player.completeString());//information about themselves
        for(int i=0; i<players.length; i++){        //information about other players
            System.out.println(players[i].toString());
        }
    }

    public void setmovement(){//todo: vedere se passare come parametro il numero di passi che è possibile fare
        //todo:obtain the positions and then send them to the server
    }

    public void pickup(){
        //todo:prendere i movimenti
        //server.pickup(player.getnumber());
    }

    public void shoot(){
        //todo:obtain the information for the attack, per i parametri non passati impostare oggetti a null, se i comandi sono sbalgiati si torna nel metodo chooseaction
    }

    public void usePowerUp(){
        //todo: selezionare il powerup da usare e fare il controllo se è un powerup che si può usare in quel momento
        //todo: poi impostare i parametri non utilizzati da quel powerup con oggettti null
    }

    public void selectreload(){
        WeaponCard[] weapons=player.getWeapons();
        //      PowerupCard[] useammo=
    }

    public void updateInformation(Board b, Player p, Player[] ps){
        this.board=b;
        this.player=p;
        this.players=ps;
    }

    /**
     * created to avoid cloned code
     * Used to see if a player has a specific powerup, the one used before attaccking and the one after you received an attack
     * @return
     */
/*
    private boolean hasThisPowerup(String x){
        PowerupCard[] power;
        power=player.getPowerup();
        boolean hasone=false;
        for(int i=0;i<power.length && !hasone; i++){
            hasone=power[i].getName().equals(x);
        }
        return hasone;
    }

    public boolean hasTargetingScope(){
        return hasThisPowerup("targeting scope");
    }

    public boolean hasTagnackGrenade(){
        return hasThisPowerup("tagback grenade");
    }

    public static void main (String args[]) {
        //todo parte in cui viene stabilita la connessione

    }
}

 */