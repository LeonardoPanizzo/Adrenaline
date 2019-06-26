package Adrenaline;

import Adrenaline.Server.model.Board;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Servere implements Runnable {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outStream = null;

/*
    public Servere() {

        try {
            serverSocket = new ServerSocket(4445);
            socket = serverSocket.accept();
            System.out.println("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    public Servere(Socket socket) {
        this.socket = socket;
    }

    public void toClient() {

        try {

            socket.getInputStream();

            outStream = new ObjectOutputStream(socket.getOutputStream());
            //Scanner scanner = new Scanner(System.in);
            //Integer boardVariation = scanner.nextInt();

            //for (int i=0; i<10; i++) {
            Board board = new Board(1);

                /*Class class = new Class(param);
                System.out.println("Object to be written = " + class);
                outputStream.writeObject(class);
                */
            System.out.println("Object to be written = " + board);
            outStream.writeObject(board);
            //}
            //outputStream.write(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromClient() {

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


    /*
    public static void main(String[] args) {
        Servere server = new Servere();
        //server.fromClient();
        server.toClient();
    }
    */

    public void run(){
        //server.fromClient();
        this.toClient();
    }
}