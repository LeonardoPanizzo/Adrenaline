package Adrenaline.Client.view;

import Adrenaline.Server.control.RemoteBiCon;
import Adrenaline.Server.model.*;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewTunnelB {

    private String hostName = "localhost";
    //private String serviceName = "GroupChatService";
    //private String clientServiceName;
    private String name;
    protected RemoteBiCon serverIF;
    protected boolean connectionProblem = false;
    private Board board;
    private Player p;           //the player of the user
    private Player[] players;   //all the others players
    private int number;         //number of the user, indicates his turn and which player he is


    /**
     * class constructor,
     * note may also use an overloaded constructor with
     * a port no passed in argument to super
     *
     * @throws RemoteException
     */
    public ViewTunnelB(String userName, RemoteBiCon serverIF) throws RemoteException {
        super();
        this.name = userName;
        this.serverIF = serverIF;
    }


    /**
     * pass our username, hostname and RMI service name to
     * the server to register out interest in joining the chat
     *
     * @param details
     */
    public void registerWithServer(String[] details) {
        try {
            serverIF.registerListener(details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createBoard(Integer boardNumber) throws RemoteException {
        try {
            serverIF.createBoard(boardNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[Error] Index out of bounds"); //printato sul server
        }
    }

    //=====================================================================

    /**
     * Receive a string from the chat server
     * this is the clients RMI method, which will be used by the server
     * to send messages to us
     */

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
     * return the powerups the player wants to use in a payment
     *
     * @return
     */
    private PowerupCard[] getplayerpowerup() {
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

    private char chooseammo(){
        Scanner keyboard=new Scanner(System.in);
        char ammo;
        int[] owned=p.getAmmo();
        do{
            System.out.println("Select a ammunition\nb.blue, quantity:"+owned[0]+"\ny.yellow, quantity:"+owned[1]+"\nr.red, quantity:"+owned[2]+"\n");
            ammo=keyboard.next().charAt(0);
        }while(ammo!='b' && ammo!='y' && ammo!='r');
        return ammo;
    }

    /**
     * Used to ask the movements to the player, used to get the positions before the action move and the action grab
     * After is true if tha action is composed (es. move and grab) so the movement part doesnt count in action
     *
     * @param maxp
     */
    private void moveplayer(int maxp, boolean after){
        Position[] positions = getplayermovement();
        boolean done;
        if (positions.length <= maxp) {
            try {
                done = serverIF.moveplayer(number, after, positions);
                if(done){
                    System.out.println("movents done");
                }else{
                    System.out.println("this positions aren't reachable");
                }
            }catch(Exception e){
                System.out.println("there is a problem in the connection with the server");
            }
        }else{
            System.out.println("you passed to many positions");
        }
    }

    private Player[] playerstoattack() {
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

    /**
     * if true the player doesnt have the space to pickup a weapon
     */
    private boolean hasNoSpaceweapon() {     //if true the player doesnt have the space to pickup a weapon
        WeaponCard[] weapons = p.getWeapons();
        int i = 0;
        boolean emptyspace = false;
        for (; i < weapons.length & !emptyspace; i++) {
            emptyspace = weapons[i] == null;
        }
        return emptyspace;
    }

    private void pickweapon(){
        char c;
        int i = 0;
        int weaponposition;     //position of the wanted weapon
        int[] positionpowerup = new int[3];
        PowerupCard[] payment = new PowerupCard[]{null, null, null};
        WeaponCard[] weapons = p.getPosition().showWeapons();
        Scanner keyboard = new Scanner(System.in);
        WeaponCard tempWeapon = null;
        char wposition;     //the position of the weapon that the player wants to discard
        boolean changeweapon = hasNoSpaceweapon();  //used to see if the player has the space to pickup a weapon
        boolean done = false;     //true when the player pickup a weapon
        if (changeweapon) {       //if the player has no space a weapon is saved in tempWeapon
            System.out.println("\nWhich weapon you want to discard?\n");
            for (int j = 0; j < p.getWeapons().length; j++) {
                System.out.println(j + ". " + p.getWeapons()[j].getName() + "\n");
            }
            do {
                wposition = keyboard.next().charAt(0);
            } while (wposition < '0' || wposition > '2');
            tempWeapon = p.getWeapons()[Character.getNumericValue(wposition)];
            try {
                serverIF.discardWeapon(number, tempWeapon);
            }catch (Exception e){
                System.out.println("there is a problem in the connection with the server");
            }
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
            try {
                if (serverIF.grabWeapon(number, weapons[weaponposition])) {
                    System.out.println("\n Weapon added\n");
                    if (changeweapon) {
                        tempWeapon.reload();
                        serverIF.weaponToGround(p.getPosition(),tempWeapon);
                    }
                } else {
                    System.out.println("\nWeapon not taken\n");
                    if (changeweapon) {
                        serverIF.addWeapon(number, tempWeapon);
                    }
                }
            }catch(Exception e){
                System.out.println("there is a problem in the connection with the server");
            }
        } else if (c == 'y') {
            try {
                payment = getplayerpowerup();
                if (serverIF.grabWeapon(number, weapons[weaponposition], payment)) {
                    System.out.println("\n Weapon added\n");
                    if (changeweapon) {
                        tempWeapon.reload();
                        serverIF.weaponToGround(p.getPosition(), tempWeapon);
                    }
                } else {
                    System.out.println("\nWeapon not taken\n");
                    if (changeweapon) {
                        serverIF.addWeapon(number, tempWeapon);   //discarded weapon given back to the player
                    }
                }
            }catch (Exception e){
                System.out.println("there is a problem in the connection with the server");
            }
        }
    }

    private boolean respawn(){
        boolean done=false;
        p.drawPowerup();
        if (p.isFirstRound()) {  //If it is the first turn the player needs to pickup two card, only one it is a normal respawn
            p.drawPowerup();
        }
        PowerupCard[] pwr;
        Position[] position;
        do {
            System.out.println("You need to select one power up that you want to use\n");
            pwr = getplayerpowerup();
        } while (pwr.length != 1);
        do {
            System.out.println("You need to select one position\n");
            position = getplayermovement();
        } while (position.length != 1);
        try {
            done=serverIF.respawn(number, pwr[0], position[0]);
        }catch(Exception e){
            System.out.println("there is a problem in the connection with the server");
        }
        return done;
    }

    private void setBoard() throws RemoteException{
        char c;
        int x;
        Scanner keyboard=new Scanner(System.in);
        do{
            System.out.println("\nChoose a number between 1 and 4 to select the board\n");
            c=keyboard.next().charAt(0);
            if(c>='1' && c<='4'){
                x=Character.getNumericValue(c);
                serverIF.setBoard(x);
            }
        }while(c<'1' || c>'4');
    }

    private void pickup(){
        boolean respawn = p.getPosition().isRespawnPoint();   //checks if it is respawn point
        if (respawn) {
            pickweapon();   //This function checks if the user wants to play with ammos or not
        } else {
            try {
                if(serverIF.grabAmmo(number)){
                    System.out.println("AmmoCard taken");
                }else{
                    System.out.println("Incorrect input");
                }
            }catch(Exception e){
                System.out.println("there is a problem in the connection with the server");
            }
        }
    }

    /**
     * The user choose which weapon to use
     **/
    private WeaponCard playerSelectWeapons() {
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

    private boolean hasTargettingScope() {
        boolean found = false;
        for (int i = 0; i < p.getPowerup().length && !found; i++) {
            if (p.getPowerup()[i].getName().equals("targeting scope")) {
                found = true;
            }
        }
        return found;
    }

    private void shoot(){
        boolean attacked=false;
        WeaponCard weapon=playerSelectWeapons();
        PowerupCard[] payment=getplayerpowerup();
        Position[] pos=getplayermovement();
        Player[] toattack=playerstoattack();
        char c;
        Scanner keyboard=new Scanner(System.in);
        try {
            if (weapon.hasoptional()) {
                int[] mode2 = getMode2();
                attacked=serverIF.shoot(number,weapon,toattack,0,mode2,pos,payment);
            } else {                      //false when there is only one effect used
                int mode1 = getMode1();
                attacked=serverIF.shoot(number,weapon,toattack,mode1,null,pos,payment);
            }
            if (attacked && hasTargettingScope()) {  //if the attack is successful and the user has targettingscope the player has the opportunity of using that powerup
                System.out.println("Do you want to use targetting scope? y to yes");
                c = keyboard.next().charAt(0);
                if (c == 'y') {
                    System.out.println("Select one targeting scope");
                    do {
                        payment = getplayerpowerup();
                    } while (payment.length != 1 || !payment[0].getName().equals("targeting scope"));
                    c = chooseammo();
                    p.usePowerup(payment[0], toattack[0], null, c);
                    serverIF.usePowerup(number,payment[0],null,c);
                }
            }
        }catch(Exception e){
            System.out.println("there is a problem in the connection with the server");
        }
    }

    private void reloadWeapon() {
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
        try {
            if (c == 'y') {
                payment = getplayerpowerup();
                serverIF.reload(number, weaponToReload, payment);
            } else {
                serverIF.reload(number, weaponToReload);
            }
        }catch(Exception e){
            System.out.println("there is a problem in the connection with the server");
        }
    }

    public void reload() {
        char c;
        Scanner keyboard = new Scanner(System.in);
        do {
            System.out.println("\nDo you want to charge a weapon?y/n\n");
            c = keyboard.next().charAt(0);
            if (c == 'y') {
                reloadWeapon();
            }
        } while (c != 'n');
    }

    private void attack(){
        Scanner keyboard = new Scanner(System.in);
        char c;
        if (p.getLife() <= 8) {
            System.out.println("Do you want to move one step before shooting? y to yes any other button to no");
            c = keyboard.next().charAt(0);
            if (c == 'y') {
                moveplayer( 1, true);
            }
        }
        //todo: shoot restituisce boolean per dire se l'attacco è andato a segno, se non lo è il giocatore torna nella posizione di partenza
        shoot();
    }

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     */
    public void attackfinal2() {
        Scanner keyboard = new Scanner(System.in);
        char c;
        System.out.println("Do you want to move one before shooting? y to yes any other button to no");
        c = keyboard.next().charAt(0);
        if (c == 'y') {
            moveplayer(1, true);
        }
        reload();
        shoot();
    }

    /**
     * Used in the final frenesy when the player has the option for one action
     *
     */
    public void attackfinal1() {
        Scanner keyboard = new Scanner(System.in);
        char c;
        System.out.println("Do you want to move two or less steps before shooting? y to yes any other button to no");
        c = keyboard.next().charAt(0);
        if (c == 'y') {
            moveplayer(2, true);
        }
        reload();
        shoot();
    }

    public void usePowerup(){
        Player[] m;
        PowerupCard[] pwd;
        Position[] pos;
        char a;
        do{
            System.out.println("\nSelect one powerup\n");
            pwd=getplayerpowerup();
        }while(pwd.length!=1);
        if(pwd[0].getName().equals("Newton")){
            do{                         //used to select the player that the user wants to move
                m=playerstoattack();
            }while(m.length!=1);
            do{                         //used to select the steps the the players will do
                System.out.println("Select the positions");
                pos=getplayermovement();
            }while(pos.length>=3);
            a=chooseammo();            //used for the user to choose which ammo to use
            p.usePowerup(pwd[0],m[0],pos,a);
        }else if(pwd[0].getName().equals("teleporter")){
            do{
                System.out.println("Select one position");
                pos=getplayermovement();
            }while(pos.length!=1);
            a=chooseammo();
            try {
                serverIF.usePowerup(number, pwd[0], pos, a);
            }catch(Exception e){
                System.out.println("there is a problem in the connection with the server");
            }

        }else{
            System.out.println("You can't use this powerup now");
        }
    }

    private void move(){
        int max=3;
        if(p.getLife()<=8){
            max=3; //todo: controllare
        }
        moveplayer(max,false);
    }

    private void moveandgrab(){
        int max = 1;
        if (p.getLife() <= 8) {
            max = 2;
        }
        moveplayer(max, true);
        pickup();
    }

    private void useTagbackGrenade(){
        System.out.println("Select one tagback grenade");
        PowerupCard[] pwr;
        do {
            pwr=getplayerpowerup();
        }while(pwr.length!=1 || !pwr[0].getName().equals("tagback grenade"));
        char c=chooseammo();
        try {
            serverIF.useTagBackGrenade(number, pwr[0], c);
        }catch(Exception e){
            System.out.println("there is a problem in the connection with the server");
        }
    }

    /**
     * Used in the final frenesy when the player has the option for one action
     *
     */
    public void moveandgrabfinal1() {
        int max = 3;
        moveplayer(max, true);
        pickup();
    }

    /**
     * Used in the final frenesy when the player has the option for two actions
     *
     */
    public void moveandgrabfinal2() {
        int max = 2;
        moveplayer(max, true);
        pickup();
    }

    public void updateInfo() throws RemoteException{
        this.board=serverIF.getBoard();
        Player[] ps= new Player[5];             //support for variable players
        //try {
            System.out.println("aaa1");
            System.out.println("LEN: " + serverIF.getPlayers().length);
            Player[] users = serverIF.getPlayers();   //all the players
            System.out.println("aaa2");
            for (int i = 0; i < users.length; i++) {
                System.out.println("bbb"+i);
                if (users[i].getNumber() != number) {
                    ps[i] = users[i];
                } else {
                    p = users[i];
                }
            }
            players = ps;
       // }catch(Exception e){
       //     System.out.println("error");
        //}
    }

    public void movefinal2() {
        int max = 4;
        moveplayer(max, false);
    }

    public void mmain() throws RemoteException{
        boolean gotInfo=false;
        boolean donerespawn=false;
        this.number=serverIF.getNumber();
        do{

        }while(!serverIF.isReady());
        if(number==0){
            this.setBoard();
        }
        serverIF.spreadinfo(number);
        do{
            if(number==0){
                this.updateInfo();
            }
            if(serverIF.getSpecialturn(number)){
                this.updateInfo();
            }
            if(this.board!=null && this.players!=null){
                gotInfo=true;
                serverIF.setSpecialturn(number);
            }
        }while(!gotInfo);
        gotInfo=false;
        Scanner keyboard= new Scanner(System.in);
        char c;
        //todo chiedere GUI o CLI
        do{
            if(serverIF.isMyturn(number) && !serverIF.getSpecialturn(number)){  //tipical turn
                System.out.println("\nWhat action you want to make?\n0.print info\n1.move\n2.move and grab\n3.shoot\n4.use powerup\n");
                c=keyboard.next().charAt(0);
                switch(c){
                    case '0':
                        System.out.println(board.myToString());
                        System.out.println(p.completeString());
                        for(int i=0; i<players.length; i++){
                            System.out.println(players[i].toString());
                        }
                        break;
                    case '1':
                        this.move();
                        break;
                    case '2':
                        moveandgrab();
                        break;
                    case '3':
                        attack();
                        break;
                    case '4':
                        usePowerup();
                        break;
                    default:
                        System.out.println("\nInsert a number between 0 and 4\n");
                }
                if(p.getAction()==0){
                    System.out.println("\nDo you want to reload any weapon?y to yes, any other button as no\n");
                    c=keyboard.next().charAt(0);
                    if(c=='y'){
                        reload();
                    }
                    serverIF.endturn(number);
                }
                serverIF.spreadinfo(number);  //after each action all the players get the information
                this.updateInfo();
            }
            if(serverIF.getDefense(number)){    //when this player can use tagback grenade
                System.out.println("Do you want to use tagback grenade?y to yes, any other button as no\n");
                c=keyboard.next().charAt(0);
                if(c=='y'){
                    useTagbackGrenade();
                }
                serverIF.setDefense(number);
            }
            if(serverIF.getSpecialturn(number)){    //it's time to get info
                this.updateInfo();
                serverIF.setSpecialturn(number);
            }
            if(serverIF.getRespawnturn(number)){
                do {
                    donerespawn=respawn();
                }while(!donerespawn);
                donerespawn=false;
                serverIF.setRespawnturn(number);
            }
        }while(!board.isFinalRound());
        do{
            if(serverIF.getOnetogo()==number){
                if(number>serverIF.finalplayernumber()){
                    System.out.println("\nWhat final actions you want to make?\n0.print info\n1.move\n2.move and grab\n3.move, reload and shoot\n4.use powerup\n");
                    c=keyboard.next().charAt(0);
                    switch(c) {
                        case '0':
                            System.out.println(board.myToString());
                            System.out.println(p.completeString());
                            for(int i=0; i<players.length; i++){
                                System.out.println(players[i].toString());
                            }
                            break;
                        case '1':
                            movefinal2();
                            break;
                        case '2':
                            moveandgrabfinal2();
                            break;
                        case '3':
                            attackfinal2();
                            break;
                        case '4':
                            usePowerup();
                            break;
                    }
                }else{
                    System.out.println("\nWhat final action you want to make?\n0.print info\n1.move and grab\n2.move, reload and shoot\n3.use powerup\n");
                    c=keyboard.next().charAt(0);
                    switch(c) {
                        case '0':
                            System.out.println(board.myToString());
                            System.out.println(board.myToString());
                            System.out.println(p.completeString());
                            for(int i=0; i<players.length; i++){
                                System.out.println(players[i].toString());
                            }
                            break;
                        case '1':
                            moveandgrabfinal1();
                            break;
                        case '2':
                            attackfinal1();
                            break;
                        case '3':
                            usePowerup();
                            break;
                    }
                }
                if(p.getAction()==0){
                    if (number == serverIF.finalplayernumber()) {
                        //serverIF.endall();
                    }else{
                        serverIF.endfinalturn(number);
                    }
                }
            }
            if(serverIF.getSpecialturn(number)){    //it's time to get info
                this.updateInfo();
                serverIF.setSpecialturn(number);
            }
            if(serverIF.getDefense(number)){    //when this player can use tagback grenade
                System.out.println("Do you want to use tagback grenade?y to yes, any other button as no\n");
                c=keyboard.next().charAt(0);
                if(c=='y'){
                    useTagbackGrenade();
                }
                serverIF.setDefense(number);
            }
            if(serverIF.getRespawnturn(number)){
                do {
                    donerespawn=respawn();
                }while(!donerespawn);
                donerespawn=false;
                serverIF.setRespawnturn(number);
            }
        }while(true);
    }
}

    /*
    private Scanner fromKeyBoard;
    // ----- The view is composed with the controller (strategy)
    private RemoteBiCon controller;

    private String userInput() {
        return fromKeyBoard.nextLine();
    }
    private Integer userNumInput() {
        return fromKeyBoard.nextInt();
    }



    public ViewTunnelB(RemoteBiCon controller) throws RemoteException {

        super();

        this.fromKeyBoard = new Scanner(System.in);

        this.controller = controller;

        //this.chooseBoardPhase();
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();


        this.chooseBoardPhase();
    }

    public void messageFromServer(String message) throws RemoteException {
        System.out.println(message);
    }

    public void chooseBoardPhase() throws RemoteException{
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();

        controller.createBoard(variation);
        //System.out.println("Board created");

    }
    */


/*
    public void createBoard(Integer boardNumber) throws RemoteException{
        try {
            controller.createBoard(boardNumber);
        } catch (IndexOutOfBoundsException e){
            System.out.println("[Error] Index out of bounds"); //printato sul server
        }
    }

    public void choosePUDeckPhase() {
        System.out.println("Creating PUDeck");
        //controller.createPUDeck();
    }

    */
