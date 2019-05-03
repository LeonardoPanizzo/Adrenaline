package control;

import model.Board;
import view.RemoteView;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;


public class Controller extends UnicastRemoteObject implements RemoteController {


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

        //view.ack(message);
    }


    /*
    public synchronized void broadcastMessage(String message) throws RemoteException {
        int i = 0;
        while (i < Clients.size()){
            Clients.get(i++).getMessage();
        }
    };*/

}