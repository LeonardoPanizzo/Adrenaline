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

    void setBoard()throws RemoteException;

    void spreadinfo()throws RemoteException;

    Board getBoard()throws RemoteException;

    Player[] getPlayers()throws RemoteException;

    void endfinalturn(int playernumber)throws RemoteException;

    int getOnetogo()throws RemoteException;

    void movefinal2(int playernumber)throws RemoteException;

    void moveandgrabfinal1(int playernumber)throws RemoteException;

    void moveandgrabfinal2(int playernumber)throws RemoteException;

    void attackfinal2(int playernumber)throws RemoteException;

    void attackfinal1(int playernumber)throws RemoteException;

    int finalplayernumber()throws RemoteException;

    boolean isMyturn(int specificuser)throws RemoteException;

    boolean getSpecialturn(int specificuser)throws RemoteException;

    boolean getDefense(int specificuser)throws RemoteException;

    boolean getRespawnturn(int specificuser)throws RemoteException;

    void setDefense(int specifcuser)throws RemoteException;

    void setSpecialturn(int specificuser)throws RemoteException;

    void setRespawnturn(int specificuser)throws RemoteException;

    void useTagbackGrenade(int specificuser)throws RemoteException;

    void move(int playernumber)throws RemoteException;

    void moveandgrab(int playernumber)throws RemoteException;

    void attack(int playernumber)throws RemoteException;

    void usePowerup(int playernumber)throws RemoteException;

    void reload(int playernumber)throws RemoteException;

    void endturn(int playernumber)throws RemoteException;

    int getNumber()throws RemoteException;

    void respawn(int playernumber)throws RemoteException;

    /*
    void createBoard(Integer boardNumber) throws RemoteException;

    void sendToAll(String message) throws RemoteException;
    */
}
