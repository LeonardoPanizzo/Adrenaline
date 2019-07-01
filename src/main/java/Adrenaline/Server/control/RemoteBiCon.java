package Adrenaline.Server.control;

import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;
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

    void spreadinfo();

    Board getBoard();

    Player[] getPlayers();

    int getOnetogo();

    int finalplayernumber();

    boolean isMyturn(int specificuser);

    boolean getSpecialturn(int specificuser);

    boolean getDefense(int specificuser);

    boolean getRespawnturn(int specificuser);

    void setDefense(int specifcuser);

    void setSpecialturn(int specificuser);

    void setRespawnturn(int specificuser);

    void useTagbackGrenade(int specificuser);

    void move(int playernumber);

    void moveandgrab(int playernumber);

    void attack(int playernumber);

    void usePowerup(int playernumber);

    void reload(int playernumber);

    void endturn(int playernumber);

    int getNumber();

    void respawn(int playernumber);

    /*
    void createBoard(Integer boardNumber) throws RemoteException;

    void sendToAll(String message) throws RemoteException;
    */
}
