package Adrenaline.control;

import Adrenaline.model.Board;
import Adrenaline.model.Player;
import Adrenaline.model.PowerupDeck;
import Adrenaline.model.WeaponDeck;
import Adrenaline.view.RemoteView;
import Adrenaline.view.TextView;

import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


public class Controller extends UnicastRemoteObject implements RemoteController {

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

     //   new Player(id);   todo: usare nuovo costruttore di player: prende il numero giocatore e il mazzo dei power up

        System.out.println("Player " + id + " created\n");
        ack("Your Player id is: " + id + "\n");
    }

    public void createBoard(Integer boardNumber) throws RemoteException{

        //if (board ancora non creata per questo game)
        Board board = new Board(boardNumber);
        System.out.println("Board created");

    }

    public void createDecks() throws RemoteException{
     //   PowerupDeck PUDeck= new PowerupDeck(); todo: usare nuovo costruttore: prende una board come parametro
        WeaponDeck WDeck = new WeaponDeck();
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

        //Adrenaline.view.ack(message);
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