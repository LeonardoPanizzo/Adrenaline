package Adrenaline.Client.control;

import Adrenaline.Client.model.ResponseHandler;
import Adrenaline.Client.view.CView;
import Adrenaline.Client.view.ClientTunnel;
import Adrenaline.ClientContext;
import Adrenaline.MyClient;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientController implements ResponseHandler {
    //reference to networking layer
    private final MyClient client;
    private Socket connection;
    //private Thread receiver;

    // the view
    private final CView cView;
    private final ClientTunnel tunnelView;

    private Board currentBoard;
    private PowerupDeck currentPUDeck;

    public ClientController(MyClient client, String host, int port) throws UnknownHostException, IOException {
        this.client = client;
        this.connection = new Socket(host, port);

        if (this.connection.getInetAddress().isReachable(2)) { //socket
            this.cView = new CView(this);
            this.tunnelView = null;
        }
        else {
            this.tunnelView = new ClientTunnel(this);//new CView(this);
            this.cView = null;
        }
    }

    /*
    public ClientController(RemoteBiCon controller) throws IOException{

        this.client = new MyClient();
        this.connection = null;
        this.cView = null;
        this.tunnelView = null;
    }
*/

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
        cView.chooseBoardPhase();
        tunnelView.start();

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
