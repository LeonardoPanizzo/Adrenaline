package Adrenaline.Server.control;

import Adrenaline.Client.view.ClientRemoteInt;
import Adrenaline.Server.model.*;
import Adrenaline.ClientHandler;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class BiController extends UnicastRemoteObject implements RemoteBiCon {//, RequestHandler {

    //private ServerController controller;
    //private ClientHandler handler;

    private Vector<RmiClient> clients;
    String line = "---------------------------------------------\n";
    private Board board;
    private Player[] players;
    private int specificuser;   //number of the player that is linked to this thread
    private int onetogo;        //contains the number of the player that is doing his final frenesy
    private int turn=0;
    private int defense=-1;
    private int donedefense=-1;
    private int respawn=-1;
    private int donerespawn=-1;
    private int specialturn=0;  //how many times are the information changed
    private Player attacker=null;
    private Player finalplayer=null;
    private int [] myplayers = {6, 6, 6, 6, 6};
    private int counter = -1;
    private boolean boardready=false;
    private boolean finalround=false;




    //todo:qui è la parte di gestione del server più chatter
    public BiController(int spec) throws RemoteException {
        super();
        clients = new Vector<RmiClient>();
        //parte del costruttore del model
        this.board = null;
        this.players = null;
        this.specificuser=spec;
        /*
        try {
            this.handler = new ClientHandler();
            this.controller = new ServerController(handler);
        } catch (IOException e){

            e.printStackTrace();
        }
        */
    }

    /**
     * Start the RMI Registry
     */
    public static void startRMIRegistry() {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String sayHello(String ClientName) throws RemoteException {
        System.out.println(ClientName + " sent a message");
        return "Hello " + ClientName + " from group chat server";
    }

    /**
     * Send a string ( the latest post, mostly )
     * to all connected clients
     */
    public void updateChat(String name, String nextPost) throws RemoteException {
        String message = name + " : " + nextPost + "\n";
        sendToAll(message);
    }

    /**
     * Receive a new client remote reference
     */
    @Override
    public void passIDentity(RemoteRef ref) throws RemoteException {
        //System.out.println("\n" + ref.remoteToString() + "\n");
        try {
            System.out.println(line + ref.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end passIDentity

    /**
     * Receive a new client and display details to the console
     * send on to register method
     */
    @Override
    public void registerListener(String[] details) throws RemoteException {
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(details[0] + " has joined the game");
        System.out.println(details[0] + "'s hostname : " + details[1]);

        counter++;
        System.out.println(counter);
        if(counter>=2){
            Player[] ps=new Player[3];
            PowerupDeck pwr=new PowerupDeck();
            for(int i=0; i<=counter; i++){
                ps[i]=new Player(i,pwr);
            }
            setPlayers(ps);
        }

        /*
        boolean loop = true;
        while (loop)
        for(int i = 0 ; i<=4; i++){
            if(myplayers[i] == 6){
                myplayers[i] = i;
                this.specificuser=myplayers[i];
                System.out.println(this.specificuser);
                break;
            }
        }*/

        //System.out.println(details[0] + "'sRMI service : " + details[2]);
        //registerChatter(details);
    }

    /**
     * register the clients interface and store it in a reference for
     * future messages to be sent to, ie other members messages of the chat session.
     * send a test message for confirmation / test connection
     *
     * @param details
     */
    private void registerChatter(String[] details) {
        try {

            String client = getClientHost();

            ClientRemoteInt nextClient = (ClientRemoteInt) Naming.lookup("//" + client + "/view");

            clients.addElement(new RmiClient(details[0], nextClient));

            nextClient.messageFromServer("[Server] : Hello " + details[0] + " you are now free to chat.\n");

            sendToAll("[Server] : " + details[0] + " has joined the group.\n");

            updateUserList();

            //nextClient.messageFromServer("ciao");

        } catch (RemoteException | MalformedURLException | NotBoundException | ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update all clients by remotely invoking their
     * updateUserList RMI method
     */
    private void updateUserList() {
        String[] currentUsers = getUserList();
        for (RmiClient c : clients) {
            try {
                c.getClient().updateUserList(currentUsers);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * generate a String array of current users
     *
     * @return
     */
    private String[] getUserList() {
        // generate an array of current users
        String[] allUsers = new String[clients.size()];
        for (int i = 0; i < allUsers.length; i++) {
            allUsers[i] = clients.elementAt(i).getName();
        }
        return allUsers;
    }

    /**
     * Send a message to all users
     *
     * @param newMessage
     */
    public void sendToAll(String newMessage) {
        for (RmiClient c : clients) {
            try {
                c.getClient().messageFromServer(newMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * remove a client from the list, notify everyone
     */
    @Override
    public void leaveChat(String userName) throws RemoteException {

        for (RmiClient c : clients) {
            if (c.getName().equals(userName)) {
                System.out.println(line + userName + " left the chat session");
                System.out.println(new Date(System.currentTimeMillis()));
                clients.remove(c);
                break;
            }
        }
        if (!clients.isEmpty()) {
            updateUserList();
        }
    }

    /**
     * A method to send a private message to selected clients
     * The integer array holds the indexes (from the clients vector)
     * of the clients to send the message to
     */
    @Override
    public void sendPM(int[] privateGroup, String privateMessage) throws RemoteException {
        RmiClient pc;
        for (int i : privateGroup) {
            pc = clients.elementAt(i);
            pc.getClient().messageFromServer(privateMessage);
        }
    }

    public void createBoard(Integer boardNumber) {

        //LE PRINT AVVENGONO SUL SERVER

        System.out.println("Board creating...");

        try {
            new Board(boardNumber);
            sendToAll("[Server] Board created");
            System.out.println("Board created");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("[Error] Index out of bounds");
        }

    }//this.controller.createBoard(boardNumber);}

    //todo:qui è la parte del server del gioco

    public int finalplayernumber(){
        return this.finalplayer.getNumber();
    }

    public int getSpecialturn(){
        return specialturn;
    }

    //public void setSpecialturn(int specificuser){
    //    specialturn[specificuser]=false;
    //}

    public int getDefense(){
        return defense;
    }

    public void setDefense(int specificuser){
        donedefense=specificuser;
    }

    public int getRespawnturn(){
        return respawn;
    }

    public void setRespawnturn(int specificuser){
        donerespawn=specificuser;
    }

    /*public boolean isMyturn(int specificuser){
        return playersturn[specificuser];
    }*/

    private Player getPlayerByNumber(int nplayer) {
        Player actualPlayer = null;
        for (int i = 0; i < players.length; i++) {
            if (nplayer == players[i].getNumber()) {
                actualPlayer = players[i];
            }
        }
        return actualPlayer;
    }

    /**
     * return the powerups the player wants to use in a payment
     *
     * @param p
     * @return
     */
    /*private PowerupCard[] playerpowerup(Player p) {
        PowerupCard[] selected = new PowerupCard[3];
        PowerupCard[] owned = p.getPowerup();
        boolean correctinput = true;      //used to checks if the user insert the same powerup twice
        Scanner keyboard = new Scanner(System.in);
        for (int j = 0; j < 3; j++) {
            System.out.println(j + ". " + owned[j].getName() + "\n");
        }
        int i = 0;
        char c;
        int[] choosen = new int[3];
        for (int j = 0; j < 3; j++) {
            choosen[j] = -1;
        }
        do {
            System.out.println("Select the powerup you want to use, n to stop\n");
            c = keyboard.next().charAt(0);
            if (c >= '0' && c <= '2') {
                choosen[i] = Character.getNumericValue(c);
                i++;
            }
        } while (c != 'n' || i != 3);
        for (int j = 0; j < i - 1 && correctinput; j++) {
            if (choosen[j] != -1) {
                correctinput = choosen[j] != choosen[j + 1];
            }
        }
        for (int j = 0; j < i && correctinput; j++) {
            selected[j] = owned[choosen[j]];
        }
        return selected;
    }*/

    /**
     * Asks the user to insert some positions that can be used in movement, attack, respawn...
     *
     * @return
     */
    /*private Position[] getplayermovement() {
        Scanner keyboard = new Scanner(System.in);
        Position temp;
        ArrayList<Position> moves = new ArrayList<Position>();
        int x, y;
        char c, k;
        do {
            System.out.println("\nDo you want to insert x,y coordinate?y/n\n");
            k = keyboard.next().charAt(0);
            if (k == 'y') {
                System.out.println("Insert x coordinate\n");
                k = keyboard.next().charAt(0);
                x = Character.getNumericValue(k);
                System.out.println("Insert y coordinate\n");
                k = keyboard.next().charAt(0);
                y = Character.getNumericValue(k);
                if (x >= 0 && x <= 2 && y >= 0 && y <= 3) {
                    temp = board.getBoard()[x][y];
                    moves.add(temp);
                }
            }
        } while (k != 'n');
        Position[] positions = new Position[moves.size()];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = moves.get(i);
        }
        return positions;
    }*/

    /**
     * The user choose which weapon to use
     **/
    /*private WeaponCard playerSelectWeapons(Player p) {
        WeaponCard[] weapons = p.getWeapons().clone();
        WeaponCard weapon = null;
        Scanner keyboard = new Scanner(System.in);
        char c;
        for (int i = 0; i < weapons.length; i++) {
            System.out.println(i + ". " + weapons[i].getName() + "\n");
        }
        do {
            System.out.println("Select the weapon you want to use\n");
            c = keyboard.next().charAt(0);
            if (c >= '0' && c <= '3') {
                if (weapons[Character.getNumericValue(c)] != null) {
                    weapon = weapons[Character.getNumericValue(c)];
                }
            }
        } while (weapon == null);
        return weapon;
    }*/

    /**
     * if true the player doesnt have the space to pickup a weapon
     */
    /*private boolean hasNoSpaceweapon(Player p) {     //if true the player doesnt have the space to pickup a weapon
        WeaponCard[] weapons = p.getWeapons();
        int i = 0;
        boolean emptyspace = false;
        for (; i < weapons.length & !emptyspace; i++) {
            emptyspace = weapons[i] == null;
        }
        return emptyspace;
    }*/

    /*private boolean hasTargettingScope(Player p) {
        boolean found = false;
        for (int i = 0; i < p.getPowerup().length && !found; i++) {
            if (p.getPowerup()[i].getName().equals("targeting scope")) {
                found = true;
            }
        }
        return found;
    }*/

    private boolean hasTagbackGrenade(Player p) {
        boolean found = false;
        for (int i = 0; i < p.getPowerup().length && !found; i++) {
            if (p.getPowerup()[i].getName().equals("tagback grenade")) {
                found = true;
            }
        }
        return found;
    }

    /*private char chooseammo(Player p){
        Scanner keyboard=new Scanner(System.in);
        char ammo;
        int[] owned=p.getAmmo();
        do{
            System.out.println("Select a ammunition\nb.blue, quantity:"+owned[0]+"\ny.yellow, quantity:"+owned[1]+"\nr.red, quantity:"+owned[2]+"\n");
            ammo=keyboard.next().charAt(0);
        }while(ammo!='b' && ammo!='y' && ammo!='r');
        return ammo;
    }*/

    /*private void pickweapon(Player player) {
        char c;
        int i = 0;
        int weaponposition;     //position of the wanted weapon
        int[] positionpowerup = new int[3];
        PowerupCard[] payment = new PowerupCard[]{null, null, null};
        WeaponCard[] weapons = player.getPosition().showWeapons();
        Scanner keyboard = new Scanner(System.in);
        WeaponCard tempWeapon = null;
        char wposition;     //the position of the weapon that the player wants to discard
        boolean changeweapon = hasNoSpaceweapon(player);  //used to see if the player has the space to pickup a weapon
        boolean done = false;     //true when the player pickup a weapon
        if (changeweapon) {       //if the player has no space a weapon is saved in tempWeapon
            System.out.println("\nWhich weapon you want to discard?\n");
            for (int j = 0; j < player.getWeapons().length; j++) {
                System.out.println(j + ". " + player.getWeapons()[j].getName() + "\n");
            }
            do {
                wposition = keyboard.next().charAt(0);
            } while (wposition < '0' || wposition > '2');
            tempWeapon = player.getWeapons()[Character.getNumericValue(wposition)];
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
            if (player.grabWeaponCard(weapons[weaponposition])) {
                System.out.println("\n Weapon added\n");
                if (changeweapon) {
                    tempWeapon.reload();
                    player.getPosition().giveWeapon(tempWeapon);
                }
            } else {
                System.out.println("\nWeapon not taken\n");
                if (changeweapon) {
                    player.addWeapon(tempWeapon);   //discarded weapon given back to the player
                }
            }
        } else if (c == 'y') {
            payment = playerpowerup(player);
            if (player.grabWeaponCard(weapons[weaponposition], payment)) {
                System.out.println("\n Weapon added\n");
                if (changeweapon) {
                    tempWeapon.reload();
                    player.getPosition().giveWeapon(tempWeapon);
                }
            } else {
                System.out.println("\nWeapon not taken\n");
                if (changeweapon) {
                    player.addWeapon(tempWeapon);   //discarded weapon given back to the player
                }
            }
        }
    }*/

    /*private void reloadWeapon(Player p) {
        PowerupCard[] payment;
        WeaponCard weaponToReload;
        boolean correct = false;
        char c;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nWhat weapons you want to reaload?\n");
        for (int i = 0; i < p.getWeapons().length; i++) {
            if (p.getWeapons()[i] != null) {
                System.out.println(i + ". " + p.getWeapons()[i].getName() + "\n");
            }
        }
        do {
            System.out.println("Insert weapon number\n");
            c = keyboard.next().charAt(0);
            if (c >= '0' && c < '3') {
                if (p.getWeapons()[Character.getNumericValue(c)] != null) {
                    correct = true;
                }
            }
        } while (!correct);
        weaponToReload = p.getWeapons()[Character.getNumericValue(c)];
        System.out.println("\nDo you want to use powerups in the payment?y/n\n");
        c = keyboard.next().charAt(0);
        if (c == 'y') {
            payment = playerpowerup(p);
            p.reload(weaponToReload, payment);
        } else {
            p.reload(weaponToReload);
        }
    }*/

    /*private void pickup(Player player) {
        boolean respawn = player.getPosition().isRespawnPoint();   //checks if it is respawn point
        if (respawn) {
            pickweapon(player);   //This function checks if the user wants to play with ammos or not
        } else {
            player.grabAmmoCard();
        }
    }*/

    /*private int getMode1() {
        int mode1 = -1;
        char c;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nSelect the effect that you want to use\n");
        do {
            c = keyboard.next().charAt(0);
            if (c >= '0' && c < '3') {
                mode1 = Character.getNumericValue(c);
            }
        } while (mode1 != -1);
        return mode1;
    }

    private int[] getMode2() {
        int[] mode2 = new int[5];
        int i = 0;
        Scanner keyboard = new Scanner(System.in);
        char c;
        System.out.println("\nInsert the effects in the order that you want to use them, n to stop\n");
        do {
            c = keyboard.next().charAt(0);
            if (c >= '0' && c < '3') {
                mode2[i] = Character.getNumericValue(c);
                i++;
            }
        } while (c != 'n' && i < 5);
        int[] mode = new int[i];
        for (int j = 0; j < i; j++) {
            mode[j] = mode2[j];
        }
        return mode;
    }*/



    /*private Player[] playerstoattack(Player p) {
        Player[] toattack = new Player[5];
        Scanner keyboard = new Scanner(System.in);
        char c;
        int i = 0;
        System.out.println("\nWhat players you want to attack\n");
        for (int j = 0; j < players.length; j++) {
            if (p.getNumber() != players[j].getNumber() && players[j].getLife()>0) {
                System.out.println(j + ". " + players[j].getName() + "\n");
            }
        }
        do {
            System.out.println("Select the number, n tostop\n");
            c = keyboard.next().charAt(0);
            if (c >= '0' && c < '5') {
                toattack[i] = players[Character.getNumericValue(c)];
            }
        } while (c != 'n' && i < 5);
        Player[] playertoreturn = new Player[i];
        for (int j = 0; j < i; j++) {
            playertoreturn[j] = toattack[j];
        }
        return playertoreturn;
    }*/

    /**
     * Used to ask the movements to the player, used to get the positions before the action move and the action grab
     * After is true if tha action is composed (es. move and grab) so the movement part doesnt count in action
     *
     * @param p
     * @param maxp
     */
    /*private void moveplayer(Player p, int maxp, boolean after) {
        Position[] positions = getplayermovement();
        if (positions.length <= maxp) {
            if (after) {
                p.moveAndGrab(positions);
            } else {
                p.move(positions);
            }
        }
    }*/

    /**
     * Used for the action of shooting, used after the request of movement of attacker ecc
     *
     * @param p
     */
    /*private void shoot(Player p) {
        boolean attacked=false;
        this.attacker=p;
        WeaponCard weapon = playerSelectWeapons(p);
        PowerupCard[] payment = playerpowerup(p);
        Position[] position = getplayermovement();
        Player[] toattack = playerstoattack(p);
        char c;
        Scanner keyboard=new Scanner(System.in);
        if (weapon.hasoptional()) {   //true when theres optional effect
            int[] mode2 = getMode2();
            attacked=p.shot(weapon, toattack, 0, mode2, position, payment);
        } else {                      //false when there is only one effect used
            int mode1 = getMode1();
            attacked=p.shot(weapon, toattack, mode1, null, position, payment);
        }
        if(attacked && hasTargettingScope(p)){  //if the attack is successful and the user has targettingscope the player has the opportunity of using that powerup
            System.out.println("Do you want to use targetting scope? y to yes");
            c=keyboard.next().charAt(0);
            if(c=='y'){
                System.out.println("Select one targeting scope");
                do {
                    payment=playerpowerup(p);
                }while(payment.length!=1 || !payment[0].getName().equals("targeting scope"));
                c=chooseammo(p);
                p.usePowerup(payment[0],toattack[0],null,c);
            }
        }
        if(attacked){
            for(int i=0;i<toattack.length; i++){
                if(this.hasTagbackGrenade(toattack[i])){
                    this.defense[toattack[i].getNumber()]=true;
                    do{
                        //wait for the player to use or not tagaback grenade
                    }while(this.defense[toattack[i].getNumber()]==true);
                }
            }
        }
        this.attacker=null;
    }*/

    /*public void useTagbackGrenade(int specificuser){
        Player p=getPlayerByNumber(specificuser);
        System.out.println("Select one tagback grenade");
        PowerupCard[] pwr;
        do {
            pwr=playerpowerup(p);
        }while(pwr.length!=1 || !pwr[0].getName().equals("tagback grenade"));
        char c=chooseammo(p);
        p.usePowerup(pwr[0],attacker,null,c);
    }*/

    /*public void reload(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        char c;
        Scanner keyboard = new Scanner(System.in);
        do {
            System.out.println("\nDo you want to charge a weapon?y/n\n");
            c = keyboard.next().charAt(0);
            if (c == 'y') {
                reloadWeapon(p);
            }
        } while (c != 'n');
    }*/

    /*public void moveandgrab(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 1;
        if (p.getLife() <= 8) {
            max = 2;
        }
        moveplayer(p, max, true);
        pickup(p);
    }*/

    /**
     * Used in the final frenesy when the player has the option for one action
     *
     * @param playernumber
     */
    /*public void moveandgrabfinal1(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 3;
        moveplayer(p, max, true);
        pickup(p);
    }*/

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     * @param playernumber
     */
    /*public void moveandgrabfinal2(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 2;
        moveplayer(p, max, true);
        pickup(p);
    }*/

    /**
     * Used when the player just wants to move, without picking up
     *
     * @param playernumber
     */
    /*public void move(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 3;
        if (p.getLife() <= 8) {
            max = 3;
        }
        moveplayer(p, max, false);
    }*/

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     * @param playernumber
     */
    /*public void movefinal2(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 4;
        moveplayer(p, max, false);
    }*/

    /*public void attack(int playernumber) {
        Scanner keyboard = new Scanner(System.in);
        char c;
        Player p = getPlayerByNumber(playernumber);
        if (p.getLife() <= 8) {
            System.out.println("Do you want to move one step before shooting? y to yes any other button to no");
            c = keyboard.next().charAt(0);
            if (c == 'y') {
                moveplayer(p, 1, true);
            }
        }
        shoot(p);
    }*/

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     * @param playernumber
     */
    /*public void attackfinal2(int playernumber) {
        Scanner keyboard = new Scanner(System.in);
        char c;
        Player p = getPlayerByNumber(playernumber);
        System.out.println("Do you want to move one before shooting? y to yes any other button to no");
        c = keyboard.next().charAt(0);
        if (c == 'y') {
            moveplayer(p, 1, true);
        }
        reload(playernumber);
        shoot(p);
    }*/

    /**
     * Used in the final frenesy when the player has the option for one action
     *
     * @param playernumber
     */
    /*public void attackfinal1(int playernumber) {
        Scanner keyboard = new Scanner(System.in);
        char c;
        Player p = getPlayerByNumber(playernumber);
        System.out.println("Do you want to move two or less steps before shooting? y to yes any other button to no");
        c = keyboard.next().charAt(0);
        if (c == 'y') {
            moveplayer(p, 2, true);
        }
        reload(playernumber);
        shoot(p);
    }*/

    /*public void usePowerup(int playernumber){
        Player p=getPlayerByNumber(playernumber);
        Player[] m;
        PowerupCard[] pwd;
        Position[] pos;
        char a;
        do{
            System.out.println("\nSelect one powerup\n");
            pwd=playerpowerup(p);
        }while(pwd.length!=1);
        if(pwd[0].getName().equals("Newton")){
            do{                         //used to select the player that the user wants to move
                m=playerstoattack(p);
            }while(m.length!=1);
            do{                         //used to select the steps the the players will do
                System.out.println("Select the positions");
                pos=getplayermovement();
            }while(pos.length>=3);
            a=chooseammo(p);            //used for the user to choose which ammo to use
            p.usePowerup(pwd[0],m[0],pos,a);
        }else if(pwd[0].getName().equals("teleporter")){
            do{
                System.out.println("Select one position");
                pos=getplayermovement();
            }while(pos.length!=1);
            a=chooseammo(p);
            p.usePowerup(pwd[0],null,pos,a);
        }else{
            System.out.println("You can't use this powerup now");
        }
    }*/

    /*public void respawn(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        p.drawPowerup();
        if (p.isFirstRound()) {  //If it is the first turn the player needs to pickup two card, only one it is a normal respawn
            p.drawPowerup();
        }
        PowerupCard[] pwr;
        Position[] position;
        do {
            System.out.println("You need to select one power up that you want to use\n");
            pwr = playerpowerup(p);
        } while (pwr.length != 1);
        do {
            System.out.println("You need to select one position\n");
            position = getplayermovement();
        } while (position.length != 1);
        p.respawn(pwr[0], position[0]);
    }*/









    /**
     * Here is red a char instead of an int to create a more stable system (if the user send a letter as an input the program doesnt crash)
     */
    /*public void setBoard() {
        char c;
        int x;
        Scanner keyboard = new Scanner(System.in);
        do {
            System.out.println("\nChoose a number between 1 and 4 to select the board\n");
            c = keyboard.next().charAt(0);
            if (c >= '1' && c <= '4') {
                x = Character.getNumericValue(c);
                board = new Board(x);
                System.out.println("\nBoard created\n");
            }
        } while (c < '1' || c > '4');
        System.out.println("now in spread info");
        players[0].setRound(true);
        players[0].setAction(2);
        board.setRound(0);
    }*/






    public void endall(){
        //todo:???
    }

    //todo:after this final server

    public void reload(int specificuser, WeaponCard weapontoreaload){
        Player p=getPlayerByNumber(specificuser);
        p.reload(weapontoreaload);
    }

    public void reload(int specificuser, WeaponCard weapontoreaload, PowerupCard[] payment){
        Player p=getPlayerByNumber(specificuser);
        p.reload(weapontoreaload,payment);
    }

    public boolean isReady(){
        return this.boardready;
    }

    public void setPositions(Position[][] pos){
        board.setBoard(pos);
    }

    public void setSkull(Vector<Integer> a){
        board.setSkullVector(a);
    }

    public void setTurnA(int x){
        for(int i=0; i<3; i++){
            if(x==i) {
                players[i].setRound(true);
                System.out.println("Player "+i+" is round -> "+players[i].isRound());
            }else{
                players[i].setRound(false);
                System.out.println("Player "+i+" is round -> "+players[i].isRound());
            }
        }
    }

    public void setPlayersA(Player [] pp){
        this.players=pp;
        System.out.println("--------------------------------------------\n");

        System.out.println(players[0]);
        System.out.println("--------------------------------------------\n");

        System.out.println(players[1]);

        System.out.println("--------------------------------------------\n");

        System.out.println(players[2]);
    }

    public boolean shoot(int specificuser, WeaponCard weapon,Player[] toattack,int mode1, int[]mode2,Position[] pos,PowerupCard[] payment){
        boolean done;
        Player p=getPlayerByNumber(specificuser);
        this.attacker=p;
        done=p.shot(weapon,toattack,mode1,mode2,pos,payment);
        if(done){
            for(int i=0;i<toattack.length; i++){
                if(this.hasTagbackGrenade(toattack[i])){
                    this.defense=toattack[i].getNumber();
                    do{
                        //wait for the player to use or not tagaback grenade
                    }while(donedefense!=toattack[i].getNumber());
                }
            }
        }
        this.attacker=null;
        return done;
    }

    public void spreadinfo(){
        this.specialturn=specialturn+1;
    }

    public Position[][] getPositions(){
        return board.getBoard();
    }

    public Vector<Integer> getSkull(){
        return board.getSkulls();
    }

    public int getVariation(){
        return board.getVariation();
    }

    public boolean grabWeapon(int specificuser, WeaponCard weapon){
        Player p=getPlayerByNumber(specificuser);
        boolean done;
        done=p.grabWeaponCard(weapon);
        return done;
    }

    public boolean grabWeapon(int specificuser, WeaponCard weapon, PowerupCard[] payment){
        Player p=getPlayerByNumber(specificuser);
        boolean done;
        done=p.grabWeaponCard(weapon, payment);
        return done;
    }

    public void discardWeapon(int specificuser, WeaponCard weapon){
        Player p=getPlayerByNumber(specificuser);
        p.discardWeapon(weapon);
    }

    public void addWeapon(int specificuser, WeaponCard weapon){
        Player p=getPlayerByNumber(specificuser);
        p.addWeapon(weapon);
    }

    public void usePowerup(int specificuser, PowerupCard pwr, Position[] pos,char a){
        Player p=getPlayerByNumber(specificuser);
        p.usePowerup(pwr,null,pos,a);
    }

    public void useTagBackGrenade(int specificuser, PowerupCard pwr,char a){
        Player p=getPlayerByNumber(specificuser);
        p.usePowerup(pwr,attacker,null,a);
    }

    /**
     * If the user discarded a weapon to pick a new one up the old one is left in player.position with this function
     * @param weapon
     */
    public void weaponToGround(Position p, WeaponCard weapon){
        p.giveWeapon(weapon);
    }

    public boolean grabAmmo(int specificuser){
        boolean done;
        Player p=getPlayerByNumber(specificuser);
        done=p.grabAmmoCard();
        return done;
    }

    public void setBoard(int x){
        this.board=new Board(x);
        System.out.println(board.myToString());
        System.out.println("now in spread info");
        players[0].setRound(true);
        players[0].setAction(2);
        board.setRound(0);
        specialturn=specialturn+1;
    }

    public String myToBoard(){return board.myToString();}

    public Board getBoard() {
        return this.board;
    }

    public Player getPlayers(int i) {
        return this.players[i];
    }

    public int getNumber(){
        return counter;
    }

    public int getTurn(){
        return turn;
    }

    public void setPlayers(Player[] p) {
        this.players = p;
        this.setPlayersTurn();
        boardready=true;
    }

    private void setPlayersTurn(){
        this.turn=0;
        players[0].setAction(2);
    }

    private void startNewTurn(){
        this.turn=turn+1;
        if(!finalround) {
            players[turn % players.length].setAction(2);
        }else{
            if(players[turn%players.length].getNumber()>finalplayer.getNumber()){
                players[turn % players.length].setAction(2);
            }else{
                players[turn % players.length].setAction(1);
            }
        }
    }

    public void endturn(int playernumber){
        int[] points;
        Player p = getPlayerByNumber(playernumber);
        p.endOfRound();
        for(int i=0; i<players.length;i++){
            if(players[i].getLife()<=0){
                points=players[i].givePoints();
                for(int j=0; j<points.length; j++){
                    players[j].setScore(points[j]);
                }
            }
        }
        for(int i=0; i<players.length; i++){
            if(players[i].getLife()<=0){
                this.respawn=i;
                do{
                    //wait for the player to use or not tagaback grenade
                }while(!(donerespawn==i));
                this.respawn=-1;
                this.donerespawn=-1;
            }
        }
        board.setFinalRound();
        if(board.isFinalRound()){
            this.finalround=true;
            if(finalplayer==null){
                this.finalplayer=p;
            }
        }
        board.setRound(turn+1);
        startNewTurn();
    }

    /*public void endfinalturn(int playernumber){
        this.onetogo=(this.onetogo+1)%players.length;
        if(onetogo>finalplayer.getNumber()){
            players[onetogo].setAction(2);
        }else{
            players[onetogo].setAction(1);
        }
    }*/

    /*public int getOnetogo(){
        return onetogo;
    }*/

    public boolean moveplayer(int specificuser,boolean after,Position[] movements){
        Player p=getPlayerByNumber(specificuser);
        boolean done=false;
        if(after){
            done=p.moveAndGrab(movements);
        }else{
            done=p.move(movements);
        }
    return done;
    }

    public boolean respawn(int specificuser, PowerupCard pwr, Position pos){
        boolean done;
        Player p=getPlayerByNumber(specificuser);
        done=p.respawn(pwr,pos);
        return done;
    }
}

    /*
    public void createBoard(Integer boardNumber) {
        /
        try {
            new Board(boardNumber);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }/

        //LE PRINT AVVENGONO SUL SERVER

        System.out.println("Board creating...");

        try {
            new Board(boardNumber);
            sendToAll("[Server] Board created");
            System.out.println("Board created");

        } catch (IndexOutOfBoundsException e){
            System.out.println("[Error] Index out of bounds");
        }

    }//this.controller.createBoard(boardNumber);}


    public void sendToAll(String newMessage){
        for(RmiClient c : clients){
            try {
                c.getClient().messageFromServer(newMessage);
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
*/






    /*
    @Override
    public Response handle(CreateBoardRequest request) {
        Integer variation = request.variation;
        return new BoardResponse(new Board(variation));
    }

    @Override
    public Response handle(CreatePUDeckRequest request){

        return new PUDeckResponse(new PowerupDeck());
    }

    public Response createBoard(Integer boardNumber) throws RemoteException {
        return new BoardResponse(new Board(boardNumber));
    }
    */

/*
    public void createBoard(Integer boardNumber) throws RemoteException {this.controller.createBoard(boardNumber);}

    public void sendMessage(String message)throws RemoteException {this.controller.sendMessage(message);} //from TextView to Controller

    public String getMessage(RemoteView view) throws RemoteException {this.controller.getMessage(view);} //from Controller to TextView

    public void registerClient(RemoteView client) throws RemoteException {this.controller.registerClient(client);}

    public Object[] createDecks() throws RemoteException {return this.controller.createDecks();}
*/


    /*
    public BiController(int port){

        if (port == 8000) {
            //socket
        }

        if (port == 1337) {
            //rmi

        }

    }
*/