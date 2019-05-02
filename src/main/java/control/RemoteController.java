package control;

import view.RemoteView;

import  java.rmi.*;


// SERVER INT... client vuole fare azione di scrittura

public interface RemoteController extends Remote {

    void createBoard(int num) throws RemoteException;

    void sendMessage(String message)throws RemoteException;

    void getMessage(RemoteView view) throws RemoteException;






}
