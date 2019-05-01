package model;

import control.RemoteController;

import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;
import javax.naming.*;


/*
• Sulla macchina server avremo server/
        Server.class
        Impl.class
        SharedInterface.class
• Sulla macchina client avremo client/
        Client.class
        SharedInterface.class
*/


public class RmiClient {


    public static void main(String[] args) throws NamingException, RemoteException, NotBoundException, IOException {

        Registry registry = LocateRegistry.getRegistry();

        String remoteObjectName = "myBoard";
        RemoteController myBoard = (RemoteController) registry.lookup(remoteObjectName);

        int num = System.in.read();
        myBoard.createBoard(num);

    }
}
