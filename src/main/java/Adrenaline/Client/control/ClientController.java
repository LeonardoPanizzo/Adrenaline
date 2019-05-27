package Adrenaline.Client.control;

import Adrenaline.Client.model.Response;
import Adrenaline.Client.model.ResponseHandler;
import Adrenaline.Client.view.CView;
import Adrenaline.ClientContext;
import Adrenaline.MyClient;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;

import java.io.IOException;


public class ClientController implements ResponseHandler {
    //reference to networking layer
    private final MyClient client;
    //private Thread receiver;

    // the view
    private final CView view;

    private Board currentBoard;
    private PowerupDeck currentPUDeck;

    public ClientController(MyClient client) {
        this.client = client;
        this.view = new CView(this);
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
    public void startMessaging() {
        // start a receiver thread
        receiver = new Thread(
                () -> {
                    Response response;
                    do {
                        response = client.nextResponse();
                        if (response != null) {
                            response.handle(this);
                        }
                    } while (response != null);
                }
        );
        receiver.start();
    }
*/


    public void run() throws IOException {
        view.chooseBoardPhase();

        //view.choosePUDeckPhase();

        //receiver.interrupt();
    }

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
