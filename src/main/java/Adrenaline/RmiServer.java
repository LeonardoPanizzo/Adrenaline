package Adrenaline;

import Adrenaline.control.Controller;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;


public class RmiServer extends Thread {

    public void execute() throws RemoteException, MalformedURLException {

        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        Controller obj = new Controller();

        Naming.rebind("10.169.146.242/controller", obj);
        System.out.println("PeerServer bound in registry");
    }

    public void run() {
        try {
            execute();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}