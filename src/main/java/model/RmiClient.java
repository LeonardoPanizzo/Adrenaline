package model;

import control.RemoteController;
import view.TextView;

import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;


public class RmiClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, IOException {

        //Registry registry = LocateRegistry.getRegistry();

        RemoteController controller = (RemoteController) Naming.lookup("controller"); //   //localhost/controller

        //RemoteController controller = (RemoteController) registry.lookup("controller");


        System.out.println("[System] Client is ready.\n");

        System.out.print("[System] Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner. nextLine();

        new TextView(name, controller).run();


        /*

        String ServerURL = "rmi://127.0.0.1/RMIChat";
        RemoteController chatServer = (RemoteController) Naming.lookup(ServerURL);
        new Thread(new ChatClient(args[0], chatServer)).start();

         */

    }
}
