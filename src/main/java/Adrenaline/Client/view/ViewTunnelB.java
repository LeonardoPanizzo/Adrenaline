package Adrenaline.Client.view;

import Adrenaline.Client.control.ControlTunnelClient;
import Adrenaline.Client.control.TunnelA;
import Adrenaline.MyClient;
import Adrenaline.Server.control.RemoteBiCon;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ViewTunnelB extends UnicastRemoteObject implements Serializable {

    private Scanner fromKeyBoard;
    // ----- The view is composed with the controller (strategy)
    private TunnelA controller;

    private String userInput() {
        return fromKeyBoard.nextLine();
    }
    private Integer userNumInput() {
        return fromKeyBoard.nextInt();
    }



    public ViewTunnelB(RemoteBiCon controller) throws RemoteException {

        this.fromKeyBoard = new Scanner(System.in);

        this.chooseBoardPhase();
    }


    public void chooseBoardPhase(){
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();

        controller.createBoard(variation);
    }

    public void choosePUDeckPhase() {
        System.out.println("Creating PUDeck");
        controller.createPUDeck();
    }

}
