package model;

import control.Controller;
import control.RemoteController;

import java.net.MalformedURLException;
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
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {

        /*
        Controller controller = new Controller();

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("controller", controller);

        System.out.println("[System] Server is ready.\n");
        */


        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }

        //Instantiate RmiServer

        Controller obj = new Controller();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/controller", obj);
        System.out.println("PeerServer bound in registry");



    }
}
