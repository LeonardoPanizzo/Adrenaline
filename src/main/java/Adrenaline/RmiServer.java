package Adrenaline;

import Adrenaline.Server.control.BiController;
import Adrenaline.Server.control.Controller;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;


public class RmiServer {

    public void execute() throws RemoteException, MalformedURLException {

        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }


        BiController obj = new BiController();
        // DEVO USARE UNA CLASSE CHE NON SIA IL CONTROLLER MA UN QUALCOSA CHE SE HO RMI ACCEDO AL CONTROLLER
        // ALTRIMENTI USO SOCKET

        Naming.rebind("//localhost/controller", obj);
        System.out.println("PeerServer bound in registry");
    }

    /*
    public void run() {
        try {
            execute();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    */
}