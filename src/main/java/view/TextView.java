package view;

import control.RemoteController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TextView extends UnicastRemoteObject implements RemoteView {

    /*
    private final Scanner in;
    private final RemoteController controller;

    public TextView(RemoteController controller) throws RemoteException {
        this.controller = controller;
        this.in = new Scanner(System.in);
    }
    */

    private final RemoteController controller;

    public TextView(RemoteController controller) throws RemoteException {
        this.controller = controller;
    }


    public void run() throws RemoteException{
        controller.getMessage(this);
        controller.sendMessage("ciao dal client");
    }

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

