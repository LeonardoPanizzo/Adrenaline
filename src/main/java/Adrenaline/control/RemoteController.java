package Adrenaline.control;

import Adrenaline.view.RemoteView;

import  java.rmi.*;


// SERVER INT... client vuole fare azione di scrittura

//view server lato rmi

public interface RemoteController extends Remote {

    void createBoard(Integer boardNumber) throws RemoteException;

    void sendMessage(String message)throws RemoteException; //from TextView to Controller

    String getMessage(RemoteView view) throws RemoteException; //from Controller to TextView

    void registerClient(RemoteView client) throws RemoteException;

    Object[] createDecks() throws RemoteException;



        //String login(String username, RemoteView view) throws RemoteException;

    //void getMessage() throws RemoteException;

    //void registerClient(RemoteView client) throws RemoteException;


    /*


    clientInt
    void retrieveMessage(String message) throws RemoteException;


    public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {

    private ChatServerIF chatServer;
    private String name = null;

    protected ChatClient(String name, ChatServerIF chatServer) throws RemoteException{
        this.name = name;
        this.chatServer = chatServer;
        chatServer.registerChatClient(this);
    }

    public void retrieveMessage(String message) throws RemoteException{
        System.out.println(message);
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true){
            message = scanner.nextLine();
            try {
                chatServer.broadcastMessage(name + ": " + message);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

}

-------------------------

    serverInt
    void registerChatClient(ChatClientIF chatClient) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;

    ----------------- ChatServer

    public class ChatServer extends UnicastRemoteObject implements ChatServerIF {

    private static final long serialVersionUID = 1L;
    private ArrayList<ChatClientIF> chatClients;

    protected ChatServer() throws RemoteException{

        chatClients = new ArrayList<ChatClientIF>();

    }

    public synchronized void registerChatClient(ChatClientIF chatClient) throws RemoteException{
        this.chatClients.add(chatClient);
    }

    public synchronized void broadcastMessage(String message) throws RemoteException {
        int i = 0;
        while (i < chatClients.size()){
            chatClients.get(i++).retrieveMessage(message);
        }
    };

}

    */



}
