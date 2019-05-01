package model;

import control.Controller;

import java.rmi.*;
import java.rmi.registry.*;


/*
• Sulla macchina server avremo server/
        Server.class
        Impl.class
        SharedInterface.class
• Sulla macchina client avremo client/
        Client.class
        SharedInterface.class
*/


public class RmiServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.out.println("Constructing server implementation...");
        Controller myBoard = new Controller();

        //LocateRegistry.createRegistry(1099);

        System.out.println("Binding server implementation to registry...");
        Registry registry = LocateRegistry.createRegistry(1099);//LocateRegistry.getRegistry();

        registry.bind("myBoard", myBoard);
        System.out.println("Waiting for invocations from clients...");
    }
}
