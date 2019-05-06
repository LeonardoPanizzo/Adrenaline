package Adrenaline;

public class FinalServer {

    public static void main(String[] args){

        new RmiServer().start();
        new SocketServer(1337).start();

        //TODO dati mutua esclusione

    }
}
