package Adrenaline;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerClientHandler implements Runnable {
    private Socket socket;

    public ServerClientHandler(Socket socket) {
        this.socket = socket;
    }


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
