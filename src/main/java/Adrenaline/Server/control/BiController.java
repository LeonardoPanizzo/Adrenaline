package Adrenaline.Server.control;

import Adrenaline.Server.model.Response;
import Adrenaline.ClientHandler;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.PowerupDeck;
import Adrenaline.Server.model.RequestHandler;
import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;
import Adrenaline.Server.model.commands.PUDeckResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class BiController extends UnicastRemoteObject implements RemoteBiCon{//, RequestHandler {

    //private ServerController controller;
    //private ClientHandler handler;


    private Vector<RmiClient> clients;


    public BiController () throws RemoteException {
       super();
       clients = new Vector<RmiClient>();
        /*
        try {
            this.handler = new ClientHandler();
            this.controller = new ServerController(handler);
        } catch (IOException e){

            e.printStackTrace();
        }
        */
    }

    public void createBoard(Integer boardNumber) {
        /*
        try {
            new Board(boardNumber);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }*/

        //LE PRINT AVVENGONO SUL SERVER

        System.out.println("Board creating...");

        try {
            new Board(boardNumber);
            sendToAll("[Server] Board created");
            System.out.println("Board created");

        } catch (IndexOutOfBoundsException e){
            System.out.println("[Error] Index out of bounds");
        }

    }//this.controller.createBoard(boardNumber);}


    public void sendToAll(String newMessage){
        for(RmiClient c : clients){
            try {
                c.getClient().messageFromServer(newMessage);
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    @Override
    public Response handle(CreateBoardRequest request) {
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
    */

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
