package model;

import control.RemoteController;
import view.TextView;

import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;



public class RmiClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, IOException {

        //Registry registry = LocateRegistry.getRegistry();

        RemoteController controller = (RemoteController) Naming.lookup("//localhost/controller");

        //RemoteController controller = (RemoteController) registry.lookup("controller");


        System.out.println("[System] Client is ready.\n");

        new TextView(controller).run();




    }
}
