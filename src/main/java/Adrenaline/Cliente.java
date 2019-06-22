package Adrenaline;

import Adrenaline.Server.model.Board;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outStream = null;
    private boolean isConnected = false;

    public Cliente() {

        try {

            socket = new Socket("localHost", 4445);
            System.out.println("Connected");



        } catch (SocketException se) {
            se.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void toServer() {

        while (!isConnected) {
            try {

                isConnected = true;
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
    }

    public void fromServer(){

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
        Cliente client = new Cliente();
        //client.toServer();
        client.fromServer();
    }
}