package Adrenaline.Server.control;

import Adrenaline.Server.model.Response;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;

public interface RemoteBiCon extends Remote {

    void updateChat(String userName, String chatMessage)throws RemoteException;

    void passIDentity(RemoteRef ref)throws RemoteException;

    void registerListener(String[] details)throws RemoteException;

    void leaveChat(String userName)throws RemoteException;

    void sendPM(int[] privateGroup, String privateMessage)throws RemoteException;

    void createBoard(Integer boardNumber) throws RemoteException;

    void setBoard();

    void move(int playernumber);

    void moveandgrab(int playernumber);

    void attack(int playernumber);

    void usePowerup(int playernumber);

    void reload(int playernumber);

    void endturn(int playernumber);

    /*
    void createBoard(Integer boardNumber) throws RemoteException;

    void sendToAll(String message) throws RemoteException;
    */
}
