package Adrenaline.Client.control;

import Adrenaline.Client.model.ResponseHandler;
import Adrenaline.Client.view.CView;
import Adrenaline.Client.view.ViewTunnelA;
import Adrenaline.Client.view.ViewTunnelClient;
import Adrenaline.ClientContext;
import Adrenaline.MyClient;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


// SOCKET!!

public class TunnelA implements ResponseHandler {
    //reference to networking layer
    private final MyClient client;
    private Socket connection;
    //private Thread receiver;


    private Board currentBoard;
    private PowerupDeck currentPUDeck;

    public TunnelA(MyClient client, String host, int port) throws IOException {
        this.client = client;
        //this.connection = new Socket(host, port);

        /*
        PrintWriter pr = new PrintWriter(connection.getOutputStream());
        pr.println("ciao");
        pr.flush();*/
    }


    public Board createBoard(Integer variation) {
        client.request(new CreateBoardRequest(variation));
        client.nextResponse().handle(this);
        return currentBoard;
    }

    public PowerupDeck createPUDeck() {
        client.request(new CreatePUDeckRequest());
        client.nextResponse().handle(this);
        return currentPUDeck;
    }


/*
    public void run() {
        view.chooseBoardPhase();

        //view.choosePUDeckPhase();

        //receiver.interrupt();
    }*/

    // -------------------------- Response handling

    @Override
    public void handle(BoardResponse boardResponse) {
        currentBoard = boardResponse.variation;
        ClientContext.get().setCurrentBoard(currentBoard);
    }

    @Override
    public void handle(PUDeckResponse puDeckResponse) {
        currentPUDeck = puDeckResponse.powerupDeck;
        ClientContext.get().setCurrentPUDeck(currentPUDeck);
    }
}
