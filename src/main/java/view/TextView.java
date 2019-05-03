package view;

import control.RemoteController;

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


    /*
    private final Scanner in;
    private final RemoteController controller;

    public TextView(RemoteController controller) throws RemoteException {
        this.controller = controller;
        this.in = new Scanner(System.in);
    }
    */

    /*
    private final RemoteController controller;

    public TextView(RemoteController controller) throws RemoteException {
        this.controller = controller;
    }


    public void run() throws RemoteException{
        controller.getMessage(this);
        controller.sendMessage("ciao dal client");
    }

*/
    /*
    public void getMessage(String message) throws RemoteException{

        //controller.getMessage();
        System.out.println(message);

    }

*/
    public void ack(String message) throws RemoteException {
        System.out.println(message);
    }

}

