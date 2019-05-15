package Adrenaline;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class FinalServer {

    public static void main(String[] args){

        //array list controller


        try {
            new RmiServer().execute();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new SocketServer(1337).start();

        //TODO dati mutua esclusione

    }
}
