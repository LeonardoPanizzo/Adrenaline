package Adrenaline;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer extends Thread {

    private static int port;
    private ServerSocket serverSocket;

    public SocketServer(int port){ this.port = port; }

    public void run(){
        SocketServer server = new SocketServer(1337);
        server.startServer();

        /*
        try {
            server.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); // porta non disponibile
            return;
        }
        System.out.println("Server ready");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ServerClientHandler(socket));
            } catch (IOException e) {
                break; // entrerei qui se serverSocket venisse chiuso
            }
        }
        executor.shutdown();
    }

    public void execute() throws IOException {

        serverSocket = new ServerSocket(port);
        System.out.println("Server socket ready on port: " + port);
        Socket socket = serverSocket.accept();
        System.out.println("Received client connection");


        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        // leggo e scrivo nella connessione finche' non ricevo "quit"
        while (true) {

            String line = in.nextLine();
            System.out.println("[Socket] Received: " + line);
            if (line.equals("quit")) {
                break;
            } else {
                //out.println("Received: " + line);
                out.flush();
            }
        }

        System.out.println("Closing sockets");
        socket.close();
        serverSocket.close();
    }
}