package Adrenaline.view;

import Adrenaline.control.RemoteController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class TextView extends UnicastRemoteObject implements RemoteView {


    private RemoteController remoteController;
    private String name = null;

    public TextView(String name, RemoteController remoteController) throws RemoteException{
        this.name = name;
        this.remoteController = remoteController;
        //remoteController.registerChatClient(this);
    }

    public void run() throws RemoteException{
        //remoteController.sendMessage("ciao dal client");
        //remoteController.registerClient(this);
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

