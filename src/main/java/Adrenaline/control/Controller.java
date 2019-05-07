package Adrenaline.control;

import Adrenaline.model.Board;
import Adrenaline.view.RemoteView;
import Adrenaline.view.TextView;

import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Controller extends UnicastRemoteObject implements RemoteController {

    //private final Map<String, RemoteView> views = new HashMap<>();


    public Controller() throws RemoteException {
        super();
    }

    public void createBoard(int num) throws RemoteException{

        Board board = new Board(num);
        System.out.println("Board created");

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


    public void getMessage(RemoteView view) throws RemoteException {
        //String message = "ciao dal controller";

        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            message = scanner.nextLine();
            try {
                view.ack("[Server] " + message);
                break;
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