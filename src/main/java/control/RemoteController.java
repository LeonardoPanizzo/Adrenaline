package control;

import  java.rmi.*;

// contiene elenco metodi per remote invocation

public interface RemoteController extends Remote {
    //double getPrice(String description) throws RemoteException;
    void createBoard(int num) throws RemoteException;
}
