package Adrenaline.Client.view;

import Adrenaline.Client.control.ClientController;
import Adrenaline.Server.model.Board;

import java.util.Scanner;

public class CView {
    private Scanner fromKeyBoard;
    // ----- The view is composed with the controller (strategy)
    private final ClientController controller;

    private String userInput() {
        return fromKeyBoard.nextLine();
    }
    private Integer userNumInput() {
        return fromKeyBoard.nextInt();
    }



    public CView(ClientController controller) {
        this.controller = controller;
        this.fromKeyBoard = new Scanner(System.in);
    }

    public void chooseBoardPhase() {
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();
        controller.createBoard(variation);
    }

    public void choosePUDeckPhase() {
        System.out.println("Creating PUDeck");
        controller.createPUDeck();
    }

}
