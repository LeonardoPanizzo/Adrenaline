package Adrenaline;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class FinalServer {

    public static void main(String[] args) throws IOException {

        //array list controller



        try {
            new RmiServer().execute();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //new SocketServer(1337).start();


        ChatServer server = new ChatServer(8000);

        try {
            server.run();
        } finally {
            server.close();
        }

        //TODO dati mutua esclusione

    }
}
