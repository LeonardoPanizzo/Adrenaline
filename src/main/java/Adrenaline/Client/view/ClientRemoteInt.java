package Adrenaline.Client.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRemoteInt extends Remote {

    void messageFromServer(String message) throws RemoteException;

    void updateUserList(String[] currentUsers) throws RemoteException;


}
