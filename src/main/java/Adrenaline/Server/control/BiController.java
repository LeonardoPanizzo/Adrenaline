package Adrenaline.Server.control;

import Adrenaline.Client.model.Response;
import Adrenaline.Client.model.ResponseHandler;
import Adrenaline.Client.view.RemoteView;
import Adrenaline.ClientHandler;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;
import Adrenaline.Server.model.Request;
import Adrenaline.Server.model.RequestHandler;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BiController extends UnicastRemoteObject implements RemoteBiCon, RequestHandler {

    private ServerController controller;
    private ClientHandler handler;


    public BiController () throws RemoteException {
        try {
            this.handler = new ClientHandler();
            this.controller = new ServerController(handler);
        } catch (IOException e){

            e.printStackTrace();
        }
        this.handler = null;
        this.controller = new ServerController();
    }

    @Override
    public Response handle(CreateBoardRequest request){
        Integer variation = request.variation;
        return new BoardResponse(new Board(variation));
    }

    @Override
    public Response handle(CreatePUDeckRequest request){

        return new PUDeckResponse(new PowerupDeck());
    }

    public Response createBoard(Integer boardNumber) throws RemoteException {

        return new BoardResponse(new Board(boardNumber));
    }

/*
    public void createBoard(Integer boardNumber) throws RemoteException {this.controller.createBoard(boardNumber);}

    public void sendMessage(String message)throws RemoteException {this.controller.sendMessage(message);} //from TextView to Controller

    public String getMessage(RemoteView view) throws RemoteException {this.controller.getMessage(view);} //from Controller to TextView

    public void registerClient(RemoteView client) throws RemoteException {this.controller.registerClient(client);}

    public Object[] createDecks() throws RemoteException {return this.controller.createDecks();}
*/


    /*
    public BiController(int port){

        if (port == 8000) {
            //socket
        }

        if (port == 1337) {
            //rmi

        }

    }
*/


}
