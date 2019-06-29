package Adrenaline.Server.control;

import Adrenaline.Client.view.ClientRemoteInt;
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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Vector;

public class BiController extends UnicastRemoteObject implements RemoteBiCon {//, RequestHandler {

    //private ServerController controller;
    //private ClientHandler handler;


    private Vector<RmiClient> clients;
    String line = "---------------------------------------------\n";



    public BiController() throws RemoteException {
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




    /**
     * Start the RMI Registry
     */
    public static void startRMIRegistry() {
        try{
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI Server ready");
        }
        catch(RemoteException e) {
            e.printStackTrace();
        }
    }


    public String sayHello(String ClientName) throws RemoteException {
        System.out.println(ClientName + " sent a message");
        return "Hello " + ClientName + " from group chat server";
    }


    /**
     * Send a string ( the latest post, mostly )
     * to all connected clients
     */
    public void updateChat(String name, String nextPost) throws RemoteException {
        String message = name + " : " + nextPost + "\n";
        sendToAll(message);
    }

    /**
     * Receive a new client remote reference
     */
    @Override
    public void passIDentity(RemoteRef ref) throws RemoteException {
        //System.out.println("\n" + ref.remoteToString() + "\n");
        try {
            System.out.println(line + ref.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end passIDentity


    /**
     * Receive a new client and display details to the console
     * send on to register method
     */
    @Override
    public void registerListener(String[] details) throws RemoteException {
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(details[0] + " has joined the chat session");
        System.out.println(details[0] + "'s hostname : " + details[1]);
        //System.out.println(details[0] + "'sRMI service : " + details[2]);
        registerChatter(details);
    }


    /**
     * register the clients interface and store it in a reference for
     * future messages to be sent to, ie other members messages of the chat session.
     * send a test message for confirmation / test connection
     *
     * @param details
     */
    private void registerChatter(String[] details) {
        try {
            ClientRemoteInt nextClient = (ClientRemoteInt) Naming.lookup("rmi://localhost/view");

            clients.addElement(new RmiClient(details[0], nextClient));

            nextClient.messageFromServer("[Server] : Hello " + details[0] + " you are now free to chat.\n");

            sendToAll("[Server] : " + details[0] + " has joined the group.\n");

            updateUserList();

            //nextClient.messageFromServer("ciao");

        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update all clients by remotely invoking their
     * updateUserList RMI method
     */
    private void updateUserList() {
        String[] currentUsers = getUserList();
        for (RmiClient c : clients) {
            try {
                c.getClient().updateUserList(currentUsers);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * generate a String array of current users
     *
     * @return
     */
    private String[] getUserList() {
        // generate an array of current users
        String[] allUsers = new String[clients.size()];
        for (int i = 0; i < allUsers.length; i++) {
            allUsers[i] = clients.elementAt(i).getName();
        }
        return allUsers;
    }


    /**
     * Send a message to all users
     *
     * @param newMessage
     */
    public void sendToAll(String newMessage) {
        for (RmiClient c : clients) {
            try {
                c.getClient().messageFromServer(newMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * remove a client from the list, notify everyone
     */
    @Override
    public void leaveChat(String userName) throws RemoteException {

        for (RmiClient c : clients) {
            if (c.getName().equals(userName)) {
                System.out.println(line + userName + " left the chat session");
                System.out.println(new Date(System.currentTimeMillis()));
                clients.remove(c);
                break;
            }
        }
        if (!clients.isEmpty()) {
            updateUserList();
        }
    }


    /**
     * A method to send a private message to selected clients
     * The integer array holds the indexes (from the clients vector)
     * of the clients to send the message to
     */
    @Override
    public void sendPM(int[] privateGroup, String privateMessage) throws RemoteException {
        RmiClient pc;
        for (int i : privateGroup) {
            pc = clients.elementAt(i);
            pc.getClient().messageFromServer(privateMessage);
        }
    }

    public void createBoard(Integer boardNumber) {

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


    /*
    public void createBoard(Integer boardNumber) {
        /
        try {
            new Board(boardNumber);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }/

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
*/






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
