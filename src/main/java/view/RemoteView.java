package view;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteView extends Remote {
    void error(String message) throws RemoteException;
    void ack(String message) throws RemoteException;
}
