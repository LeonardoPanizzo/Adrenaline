package Adrenaline.Client.view;

import Adrenaline.Client.control.ControlTunnelClient;
import Adrenaline.Client.control.TunnelA;
import Adrenaline.MyClient;

import java.io.IOException;
import java.util.Scanner;

public class ViewTunnelA {

    private Scanner fromKeyBoard;
    // ----- The view is composed with the controller (strategy)
    private TunnelA controller;

    private String userInput() {
        return fromKeyBoard.nextLine();
    }
    private Integer userNumInput() {
        return fromKeyBoard.nextInt();
    }



    public ViewTunnelA(MyClient client, String host, int port) throws IOException{
        //try {
            this.controller = new TunnelA(client, host, port);
        /*} catch (IOException e){
            e.printStackTrace();
            this.controller = null;
        }*/
        //controller.run();

        this.fromKeyBoard = new Scanner(System.in);

        this.chooseBoardPhase();
    }


    public void chooseBoardPhase() {
        System.out.println("Enter board number: ");
        Integer variation = userNumInput();
        try {

            controller.createBoard(variation); // CHIAMA CONTROLLER CHE E NULL

        } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

    public void choosePUDeckPhase() {
        System.out.println("Creating PUDeck");
        controller.createPUDeck();
    }

}
