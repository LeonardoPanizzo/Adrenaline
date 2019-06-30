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
    private static Board board;
    private static PowerupDeck pud;
    private static Player[] players;

    //todo:qui è la parte di gestione del server più chatter
    public BiController() throws RemoteException {
        super();
        clients = new Vector<RmiClient>();
        //parte del costruttore del model
        board = null;
        pud = null;
        players = null;
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
        System.out.println(details[0] + " has joined the chat session");
        System.out.println(details[0] + "'s hostname : " + details[1]);
        //System.out.println(details[0] + "'sRMI service : " + details[2]);
        registerChatter(details);
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
            ClientRemoteInt nextClient = (ClientRemoteInt) Naming.lookup("rmi://localhost/view");

            clients.addElement(new RmiClient(details[0], nextClient));

            nextClient.messageFromServer("[Server] : Hello " + details[0] + " you are now free to chat.\n");

            sendToAll("[Server] : " + details[0] + " has joined the group.\n");

            updateUserList();

            //nextClient.messageFromServer("ciao");

        } catch (RemoteException | MalformedURLException | NotBoundException e) {
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
    private PowerupCard[] playerpowerup(Player p) {
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
    }

    /**
     * Asks the user to insert some positions that can be used in movement, attack, respawn...
     *
     * @return
     */
    private Position[] getplayermovement() {
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
    }

    /**
     * The user choose which weapon to use
     **/
    private WeaponCard playerSelectWeapons(Player p) {
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
    }

    /**
     * if true the player doesnt have the space to pickup a weapon
     */
    private boolean hasNoSpaceweapon(Player p) {     //if true the player doesnt have the space to pickup a weapon
        WeaponCard[] weapons = p.getWeapons();
        int i = 0;
        boolean emptyspace = false;
        for (; i < weapons.length & !emptyspace; i++) {
            emptyspace = weapons[i] == null;
        }
        return emptyspace;
    }

    private boolean hasTargettingScope(Player p) {
        boolean found = false;
        for (int i = 0; i < p.getPowerup().length && !found; i++) {
            if (p.getPowerup()[i].getName().equals("targeting scope")) {
                found = true;
            }
        }
        return found;
    }

    private boolean hasTagbackGrenade(Player p) {
        boolean found = false;
        for (int i = 0; i < p.getPowerup().length && !found; i++) {
            if (p.getPowerup()[i].getName().equals("tagback grenade")) {
                found = true;
            }
        }
        return found;
    }

    private char chooseammo(Player p){
        Scanner keyboard=new Scanner(System.in);
        char ammo;
        int[] owned=p.getAmmo();
        do{
            System.out.println("Select a ammunition\nb.blue, quantity:"+owned[0]+"\ny.yellow, quantity:"+owned[1]+"\nr.red, quantity:"+owned[2]+"\n");
            ammo=keyboard.next().charAt(0);
        }while(ammo!='b' && ammo!='y' && ammo!='r');
        return ammo;
    }

    private void pickweapon(Player player) {
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
    }

    private void reloadWeapon(Player p) {
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
    }

    private void pickup(Player player) {
        boolean respawn = player.getPosition().isRespawnPoint();   //checks if it is respawn point
        if (respawn) {
            pickweapon(player);   //This function checks if the user wants to play with ammos or not
        } else {
            player.grabAmmoCard();
        }
    }

    private int getMode1() {
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
    }

    private Player[] playerstoattack(Player p) {
        Player[] toattack = new Player[5];
        Scanner keyboard = new Scanner(System.in);
        char c;
        int i = 0;
        System.out.println("\nWhat players you want to attack\n");
        for (int j = 0; j < players.length; j++) {
            if (p.getNumber() != players[j].getNumber()) {
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
    }

    /**
     * Used to ask the movements to the player, used to get the positions before the action move and the action grab
     * After is true if tha action is composed (es. move and grab) so the movement part doesnt count in action
     *
     * @param p
     * @param maxp
     */
    private void moveplayer(Player p, int maxp, boolean after) {
        Position[] positions = getplayermovement();
        if (positions.length <= maxp) {
            if (after) {
                p.moveAndGrab(positions);
            } else {
                p.move(positions);
            }
        }
    }

    /**
     * Used for the action of shooting, used after the request of movement of attacker ecc
     *
     * @param p
     */
    private void shoot(Player p) {
        WeaponCard weapon = playerSelectWeapons(p);
        PowerupCard[] payment = playerpowerup(p);
        Position[] position = getplayermovement();
        Player[] toattack = playerstoattack(p);
        if (weapon.hasoptional()) {   //true when theres optional effect
            int[] mode2 = getMode2();
            p.shot(weapon, toattack, 0, mode2, position, payment);
        } else {                      //false when there is only one effect used
            int mode1 = getMode1();
            p.shot(weapon, toattack, mode1, null, position, payment);
        }
    }

    public void reload(int playernumber) {
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
    }

    public void moveandgrab(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 1;
        if (p.getLife() <= 8) {
            max = 2;
        }
        moveplayer(p, max, true);
        pickup(p);
    }

    /**
     * Used in the final frenesy when the player has the option for one action
     *
     * @param playernumber
     */
    public void moveandgrabfinal1(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 3;
        moveplayer(p, max, true);
        pickup(p);
    }

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     * @param playernumber
     */
    public void moveandgrabfinal2(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 2;
        moveplayer(p, max, true);
        pickup(p);
    }

    /**
     * Used when the player just wants to move, without picking up
     *
     * @param playernumber
     */
    public void move(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 3;
        if (p.getLife() <= 8) {
            max = 3;
        }
        moveplayer(p, max, false);
    }

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     * @param playernumber
     */
    public void movefinal2(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        int max = 4;
        moveplayer(p, max, false);
    }

    public void attack(int playernumber) {
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
    }

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     * @param playernumber
     */
    public void attackfinal2(int playernumber) {
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
    }

    /**
     * Used in the final frenesy when the player has the option for one action
     *
     * @param playernumber
     */
    public void attackfinal1(int playernumber) {
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
    }

    public void usePowerup(int playernumber){
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
    }

    public void respawn(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        p.drawPowerup();
        if (p.isFinalRound()) {  //If it is the first turn the player needs to pickup two card, only one it is a normal respawn
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
    }

    public void endturn(int playernumber) {
        Player p = getPlayerByNumber(playernumber);
        p.endOfRound();
        //todo: controllari quanti giocatori sono morti, per quelli morti chiamare il respawn e la distribuzione punti
    }

    public void setPlayers(Player[] p) {
        this.players = p;
    }

    public void setDeck(PowerupDeck pd) {
        this.pud = pd;
    }

    /**
     * Here is red a char instead of an int to create a more stable system (if the user send a letter as an input the program doesnt crash)
     */
    public void setBoard() {
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
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
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