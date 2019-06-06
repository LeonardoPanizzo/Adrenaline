package Adrenaline.Server.control;

import Adrenaline.Client.view.RemoteView;
import Adrenaline.Server.model.*;

import java.io.FileNotFoundException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


//TEROICAMENTE NON DEVE extends UnicastRemoteObject !!!


// DEVO AVERE GESTIONE REQUEST!

public class Controller {//extends UnicastRemoteObject implements RemoteController {

    //private final Map<String, RemoteView> views = new HashMap<>();
    private ArrayList<RemoteView> clients;


    public Controller() throws RemoteException {
        super();
        clients = new ArrayList<>();
    }

    public ArrayList<RemoteView> getClients() {
        return clients;
    }

    public synchronized void registerClient(RemoteView client) throws RemoteException{

        this.clients.add(client);

        int id = this.clients.indexOf(client);

        Object[] temp=createDecks();
        PowerupDeck PUDeck=(PowerupDeck) temp[0];
        WeaponDeck WDeck=(WeaponDeck) temp[1];

        new Player(id, PUDeck);

        System.out.println("Player " + id + " created\n");
        ack("Your Player id is: " + id + "\n");
    }

    public void createBoard(Integer boardNumber) throws RemoteException, FileNotFoundException {

        //if (board ancora non creata per questo game)
        Board board = new Board(boardNumber);
        System.out.println("Board created");

    }

    public Object[] createDecks() throws RemoteException{
        PowerupDeck PUDeck= new PowerupDeck();
        WeaponDeck WDeck = new WeaponDeck();

        return new Object[] {PUDeck, WDeck};
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }


/*
    String login(String username, RemoteView view) throws RemoteException{

        String token = database.login(username);
        views.put(token, view);
        view.ack("Logging in as @" + username);

        return token;
    }
*/


    public String getMessage(RemoteView view) throws RemoteException {
        //String message = "ciao dal controller";

        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            message = scanner.nextLine();
            try {
                view.ack("[Server] " + message);
                return message;
                //break;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        //Adrenaline.Client.view.ack(message);
    }


    public void ack(String message) {
        System.out.println(message);
    }

    /*
    public void getMessage() {
        //String message = "ciao dal controller";

        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            message = scanner.nextLine();
            this.ack("[Server] " + message);
            break;
            }
        }
*/


    /*
    private ArrayList<RemoteView> clients;

    public synchronized void registerClient(RemoteView client) throws RemoteException{
        this.clients.add(client);

    }
    */


}