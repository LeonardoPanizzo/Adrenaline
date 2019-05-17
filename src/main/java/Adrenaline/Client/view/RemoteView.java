package Adrenaline.Client.view;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


// CLIENT INT... client vuole fare azione di lettura

public interface RemoteView extends Remote {

    void ack(String message) throws RemoteException; //called by Controller to send TextView messages

}
