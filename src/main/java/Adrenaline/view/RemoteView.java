package Adrenaline.view;

import java.rmi.Remote;
import java.rmi.RemoteException;


// CLIENT INT... client vuole fare azione di lettura

public interface RemoteView extends Remote {

    void ack(String message) throws RemoteException;

}
