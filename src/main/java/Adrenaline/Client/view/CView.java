package Adrenaline.Client.view;

import Adrenaline.Client.control.ClientController;
import Adrenaline.Server.control.BiController;
import Adrenaline.Server.control.RemoteBiCon;
import Adrenaline.Server.control.RemoteController;
import Adrenaline.Server.model.Board;

import java.rmi.RemoteException;
import java.util.Scanner;

public class CView {
    private Scanner fromKeyBoard;
    // ----- The view is composed with the controller (strategy)
    private final ClientController controller;
    private final RemoteBiCon rmiController;

    private String userInput() {
        return fromKeyBoard.nextLine();
    }
    private Integer userNumInput() {
        return fromKeyBoard.nextInt();
    }



    public CView(ClientController controller) {
        this.controller = controller;
        this.rmiController = null;
        this.fromKeyBoard = new Scanner(System.in);
    }

    public CView(RemoteBiCon controller){
        this.controller = null;
        this.rmiController = controller;
        this.fromKeyBoard = new Scanner(System.in);
    }

    public void chooseBoardPhase() {
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();
        try {
            rmiController.createBoard(variation);

        } catch (RemoteException e){
            e.printStackTrace();
        }

        try {

            controller.createBoard(variation); // CHIAMA CONTROLLER CHE E NULL

        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void choosePUDeckPhase() {
        System.out.println("Creating PUDeck");
        controller.createPUDeck();
    }

}
