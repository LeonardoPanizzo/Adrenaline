package Adrenaline.Client.view;

import Adrenaline.Client.control.ControlTunnelClient;

import java.util.Scanner;

public class FinalView {

    private ControlTunnelClient controller;

    private Scanner fromKeyBoard;
    private Integer userNumInput() {
        return fromKeyBoard.nextInt();
    }


    public FinalView(ControlTunnelClient controller){
        this.controller = controller;
    }

    public void chooseBoardPhase() {
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();

        controller.createBoard(variation);
    }

}
