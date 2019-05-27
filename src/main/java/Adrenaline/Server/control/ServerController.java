package Adrenaline.Server.control;

import Adrenaline.Client.model.Response;
import Adrenaline.ClientHandler;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;
import Adrenaline.Server.model.RequestHandler;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;
import Adrenaline.ServerClientHandler;




public class ServerController implements RequestHandler {
    // reference to the networking layer
    private final ClientHandler clientHandler; //ServerClientHandler

    public ServerController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    // ------ Request handling


    @Override
    public Response handle(CreateBoardRequest request){

        Integer variation = request.variation;
        return new BoardResponse(new Board(variation));
    }

    @Override
    public Response handle(CreatePUDeckRequest request){

        return new PUDeckResponse(new PowerupDeck());
    }

}
