package Adrenaline.Client.view;

import Adrenaline.Server.control.RemoteBiCon;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ViewTunnelB extends UnicastRemoteObject implements ClientRemoteInt {

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

    //=====================================================================

    /**
     * Receive a string from the chat server
     * this is the clients RMI method, which will be used by the server
     * to send messages to us
     */
    @Override
    public void messageFromServer(String message) throws RemoteException {
        System.out.println(message);
    }

    /**
     * A method to update the display of users
     * currently connected to the server
     */
    @Override
    public void updateUserList(String[] currentUsers) throws RemoteException {

        if (currentUsers.length < 2) {
            System.out.println("current user length < 2");
        }
    }

    public void createBoard(Integer boardNumber) throws RemoteException {
        try {
            serverIF.createBoard(boardNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[Error] Index out of bounds"); //printato sul server
        }
    }

    private void setBoard(){
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

    public void updateInfo() throws RemoteException{
        this.board=serverIF.getBoard();
        Player[] ps= new Player[5];    //support for variable players
        Player[] users=serverIF.getPlayers();   //all the players
        for(int i=0;i<users.length;i++){
            if(users[i].getNumber()!=number){
                ps[i]=users[i];
            }else{
                p=users[i];
            }
        }
        players=ps;
    }

    public void mmain() throws RemoteException{
        boolean gotInfo=false;
        this.number=serverIF.getNumber();
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
        do{
            if(serverIF.isMyturn(number) && !serverIF.getSpecialturn(number)){  //tipical turn
                System.out.println("\nWhat action you want to make?\n0.print info\n1.move\n2.move and grab\n3.shoot\n4.use powerup\n");
                c=keyboard.next().charAt(0);
                switch(c){
                    case '0':
                        System.out.println(board.myToString());
                        //todo:add the print of the players
                        break;
                    case '1':
                        serverIF.move(number);
                        break;
                    case '2':
                        serverIF.moveandgrab(number);
                        break;
                    case '3':
                        serverIF.attack(number);
                        break;
                    case '4':
                        serverIF.usePowerup(number);
                        break;
                    default:
                        System.out.println("\nInsert a number between 0 and 4\n");
                }
                if(p.getAction()==0){
                    System.out.println("\nDo you want to reload any weapon?y to yes, any other button as no\n");
                    c=keyboard.next().charAt(0);
                    if(c=='y'){
                        serverIF.reload(number);
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
                    serverIF.useTagbackGrenade(number);
                }
                serverIF.setDefense(number);
            }
            if(serverIF.getSpecialturn(number)){    //it's time to get info
                this.updateInfo();
                serverIF.setSpecialturn(number);
            }
            if(serverIF.getRespawnturn(number)){
                serverIF.respawn(number);
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
                            //todo:add the print of the players
                            break;
                        case '1':
                            serverIF.movefinal2(number);
                            break;
                        case '2':
                            serverIF.moveandgrabfinal2(number);
                            break;
                        case '3':
                            serverIF.attackfinal2(number);
                            break;
                        case '4':
                            serverIF.usePowerup(number);
                            break;
                    }
                }else{
                    System.out.println("\nWhat final action you want to make?\n0.print info\n1.move and grab\n2.move, reload and shoot\n3.use powerup\n");
                    c=keyboard.next().charAt(0);
                    switch(c) {
                        case '0':
                            System.out.println(board.myToString());
                            //todo:add the print of the players
                            break;
                        case '1':
                            serverIF.moveandgrabfinal1(number);
                            break;
                        case '2':
                            serverIF.attackfinal1(number);
                            break;
                        case '3':
                            serverIF.usePowerup(number);
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
                    serverIF.useTagbackGrenade(number);
                }
                serverIF.setDefense(number);
            }
            if(serverIF.getRespawnturn(number)){
                serverIF.respawn(number);
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
