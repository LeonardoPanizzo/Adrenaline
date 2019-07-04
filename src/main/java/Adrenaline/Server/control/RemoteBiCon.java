package Adrenaline.Server.control;

import Adrenaline.Server.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.util.Vector;

public interface RemoteBiCon extends Remote {

    void setTurnA(int x) throws RemoteException;

    void setSkull(Vector<Integer> a) throws RemoteException;

    void setPositions(Position[][] pos) throws RemoteException;

    void setPlayersA(Player[] pp) throws RemoteException;

    void updateChat(String userName, String chatMessage)throws RemoteException;

    void passIDentity(RemoteRef ref)throws RemoteException;

    void registerListener(String[] details)throws RemoteException;

    void leaveChat(String userName)throws RemoteException;

    Position[][] getPositions() throws  RemoteException;

    Vector<Integer> getSkull() throws  RemoteException;

    int getVariation() throws RemoteException;

    void sendPM(int[] privateGroup, String privateMessage)throws RemoteException;

    void createBoard(Integer boardNumber) throws RemoteException;

    //void setBoard()throws RemoteException;

    String myToBoard() throws RemoteException;


    void spreadinfo()throws RemoteException;

    Board getBoard()throws RemoteException;

    Player getPlayers(int i)throws RemoteException;

    //void endfinalturn(int playernumber)throws RemoteException;

    //int getOnetogo()throws RemoteException;

    //void movefinal2(int playernumber)throws RemoteException;

    //void moveandgrabfinal1(int playernumber)throws RemoteException;

    //void moveandgrabfinal2(int playernumber)throws RemoteException;

    //void attackfinal2(int playernumber)throws RemoteException;

    //void attackfinal1(int playernumber)throws RemoteException;

    int finalplayernumber()throws RemoteException;

    //boolean isMyturn(int specificuser)throws RemoteException;

    int getTurn()throws  RemoteException;

    int getSpecialturn()throws RemoteException;

    int getDefense()throws RemoteException;

    int getRespawnturn()throws RemoteException;

    void setDefense(int specifcuser)throws RemoteException;

    //void setSpecialturn(int specificuser)throws RemoteException;

    void setRespawnturn(int specificuser)throws RemoteException;

    //void useTagbackGrenade(int specificuser)throws RemoteException;

    //void move(int playernumber)throws RemoteException;

    //void moveandgrab(int playernumber)throws RemoteException;

    //void attack(int playernumber)throws RemoteException;

    //void usePowerup(int playernumber)throws RemoteException;

    //void reload(int playernumber)throws RemoteException;

    void endturn(int playernumber)throws RemoteException;

    int getNumber()throws RemoteException;

    //void respawn(int playernumber)throws RemoteException;

    //todo:after this final interface

    void reload(int specificuser, WeaponCard weapontoreaload, PowerupCard[] payment)throws RemoteException;

    void reload(int specificuser, WeaponCard weapontoreaload)throws RemoteException;

    boolean shoot(int specificuser, WeaponCard weapon,Player[] toattack,int mode1, int[]mode2,Position[] pos,PowerupCard[] payment)throws RemoteException;

    void usePowerup(int specificuser, PowerupCard pwr, Position[] pos,char a)throws RemoteException;

    void useTagBackGrenade(int specificuser, PowerupCard pwr,char a)throws RemoteException;

    boolean grabWeapon(int specificuser, WeaponCard weapon)throws RemoteException;

    boolean grabWeapon(int specificuser, WeaponCard weapon, PowerupCard[] payment)throws RemoteException;

    void discardWeapon(int specificuser, WeaponCard weapon)throws RemoteException;

    void addWeapon(int specificuser, WeaponCard weapon)throws RemoteException;

    void weaponToGround(Position p, WeaponCard weapon)throws RemoteException;

    boolean grabAmmo(int specificuser) throws RemoteException;

    boolean respawn(int specificuser, PowerupCard pwr, Position pos) throws RemoteException;

    void setBoard(int b) throws RemoteException;

    boolean isReady() throws RemoteException;

    boolean moveplayer(int specificuser, boolean after, Position[] movements) throws RemoteException;

    /*
    void createBoard(Integer boardNumber) throws RemoteException;

    void sendToAll(String message) throws RemoteException;
    */
}
