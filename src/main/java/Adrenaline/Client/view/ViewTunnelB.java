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

    public void updateInfo(Board b, Player[] users){
        this.board=b;
        Player[] ps= new Player[5];    //support for variable players
        for(int i=0;i<users.length;i++){
            if(users[i].getNumber()!=number){
                ps[i]=users[i];
            }else{
                p=users[i];
            }
        }
    }

    public void mmain(){
        //ricevere i dati dal  server con il metodo update info
        if(p.getNumber()==0){
            serverIF.setBoard();
        }
        Scanner keyboard= new Scanner(System.in);
        char c;
        do{
            if(p.isRound()){
                System.out.println("\nWhat action you want to make?\n0.print info\n1.move\n2.move and grab\n3.shoot\n4.use powerup\n");
                c=keyboard.next().charAt(0);
                switch(c){
                    case '0':
                        System.out.println(board.myToString());
                        break;
                    case '1':
                        serverIF.move(p.getNumber());
                        break;
                    case '2':
                        serverIF.moveandgrab(p.getNumber());
                        break;
                    case '3':
                        serverIF.attack(p.getNumber());
                        break;
                    case '4':

                        break;
                    default:
                        System.out.println("\nInsert a number between 0 and 4\n");
                }
                if(p.getAction()==0){
                    System.out.println("\nDo you want to reload any weapon?\n");
                }
            }
        }while(board.isFinalRound());
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
