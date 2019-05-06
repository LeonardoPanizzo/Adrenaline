package Adrenaline;

import Adrenaline.control.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerClientHandler implements Runnable {
    private Socket socket;

    public ServerClientHandler(Socket socket) {
        this.socket = socket;
    }

/*
    public static void sendMessage(Socket socket, String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        //System.out.println(out);
    }


    public static void getMessage(Socket socket) throws IOException{
        Scanner in = new Scanner(socket.getInputStream());
        while (true) {
            Integer line = in.nextInt();
            if (line.equals(1) || line.equals(2) || line.equals(3) || line.equals(4)) {
                numBoard = line;
                break;
            }
        }


    public void run() {


        //sendMessage("has connected to the server."); //TODO method has connected
        Scanner in = new Scanner(socket.getInputStream());
        String message;
        while (true) {
            try {
                message = in.nextLine();
                sendMessage(this.socket, message + "\n");
                getMessage(this.socket);
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}*/




    public void run() {
        try {

            //Controller obj = new Controller();

            //obj.getMessage();


            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            // leggo e scrivo nella connessione finche' non ricevo "quit"

            while (true) {

                String line = in.nextLine();
                if (line.equals("quit")) {
                    break;
                } else {
                    System.out.println("Received: " + line);
                    out.println("Received: " + line);
                    out.flush();
                }
            }
            // chiudo gli stream e il socket
            in.close();
            //out.close();
            socket.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



}
