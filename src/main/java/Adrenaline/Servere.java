package Adrenaline;

import Adrenaline.Server.model.Board;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Servere {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;

    public Servere() {

        try {
            serverSocket = new ServerSocket(4445);
            socket = serverSocket.accept();
            System.out.println("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toClient(){

    }

    public void fromClient(){

            try {

                inStream = new ObjectInputStream(socket.getInputStream());
                //while (inStream.read() != 0) {

            /*Class class = (Class) inStream.readObject();
            System.out.println("Object received = " + class);
            socket.close();*/

                Board board = (Board) inStream.readObject();
                System.out.println("Object received = " + board);
                //}
                socket.close();

            } catch (SocketException se) {
                System.exit(0);
            } catch (ClassNotFoundException cn) {
                cn.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }


    public static void main(String[] args) {
        Servere server = new Servere();
        server.fromClient();
    }
}