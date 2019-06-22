package Adrenaline.Client.view;

import Adrenaline.Server.control.RemoteBiCon;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ViewTunnelB extends UnicastRemoteObject implements ClientRemoteInt {

    private Scanner fromKeyBoard;
    // ----- The view is composed with the controller (strategy)
    private RemoteBiCon controller;

    private String userInput() {
        return fromKeyBoard.nextLine();
    }
    private Integer userNumInput() {
        return fromKeyBoard.nextInt();
    }



    public ViewTunnelB(RemoteBiCon controller) throws RemoteException {

        super();

        this.fromKeyBoard = new Scanner(System.in);

        this.controller = controller;

        //this.chooseBoardPhase();
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();


        this.chooseBoardPhase();
    }

    public void messageFromServer(String message) throws RemoteException {
        System.out.println(message);
    }

    public void chooseBoardPhase() throws RemoteException{
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();

        controller.createBoard(variation);
        //System.out.println("Board created");

    }
/*
    public void createBoard(Integer boardNumber) throws RemoteException{
        try {
            controller.createBoard(boardNumber);
        } catch (IndexOutOfBoundsException e){
            System.out.println("[Error] Index out of bounds"); //printato sul server
        }
    }

    public void choosePUDeckPhase() {
        System.out.println("Creating PUDeck");
        //controller.createPUDeck();
    }

    */

}
