package control;

import model.Board;
import view.RemoteView;

import java.rmi.*;
import java.rmi.server.*;



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

    //public void getMessage(){System.out.println("ciao dal controller");};


    public void getMessage(RemoteView view) throws RemoteException {
        String message = "ciao dal controller";
        view.ack(message);
    }





}