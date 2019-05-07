package Adrenaline.view;

import Adrenaline.control.RemoteController;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class TextView extends UnicastRemoteObject implements RemoteView, Serializable {


    private final RemoteController remoteController;
    //private final Scanner in;

    private String name = null;

    public TextView(String name, RemoteController remoteController) throws RemoteException{
        this.name = name;
        this.remoteController = remoteController;
        //this.in = new Scanner(System.in);
        //remoteController.registerChatClient(this);
    }

    public void run() throws RemoteException{
        //remoteController.sendMessage("ciao dal client");
        //remoteController.registerClient(this);

/*
        String username;
        do {
            System.out.print("[System] Enter your name: ");
            username = in.nextLine();

            if (!username.isEmpty()){
                token = remoteController.login(username, this);
            }

        } while (username.isEmpty());
*/


        remoteController.sendMessage("[" + name + "] has connected to the server."); //TODO method has connected
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            message = scanner.nextLine();
            try {
                remoteController.sendMessage("[" + name + "]: " + message);
                remoteController.getMessage(this);
                //break;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    public void ack(String message) throws RemoteException {
        System.out.println(message);
    }

}

