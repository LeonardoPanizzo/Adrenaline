package Adrenaline;

import Adrenaline.Server.model.Response;
import Adrenaline.Server.control.ServerController;
import Adrenaline.Client.model.Request;
import Adrenaline.Server.model.commands.MessageNotification;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable, MessageReceivedObserver {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean stop;
    private InputStream is;
    private InputStreamReader in1;
    private BufferedReader bf1;

    private ServerController controller;

    public ClientHandler(Socket s) throws IOException {

        this.socket = s;
        /*
        this.in1 = new InputStreamReader(s.getInputStream());
        this.bf1 = new BufferedReader(in1);

        String str = bf1.readLine();
        System.out.println("client " + str);
        */


/*
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
*/
        //this.out.flush();
        //this.in = new ObjectInputStream(s.getInputStream());


        //this.controller = new ServerController(); //<-------- se rmi devo
    }

    /*
    public ClientHandler() throws IOException {
        this.socket = null;
        this.out = null;
        this.in = null;
        this.controller = new ServerController(); //<-------- se rmi devo
    }
*/
    private void printError(String message) {
        System.err.println(">>> ERROR@" + socket.getRemoteSocketAddress() + ": " + message);
    }

    public void respond(Response response) {
        try {
            System.out.println("Response\n");
            out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(response);
            out.flush();
        } catch (IOException e) {
            printError("IO - " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println(socket.getRemoteSocketAddress() + "\n");
        try {



            //controller = new ServerController();
            do {
                in = new ObjectInputStream(socket.getInputStream());

                System.out.println("Request\n");

                Request x = (Request) in.readObject();
                Response response = x.handle(controller);
                if (response != null) {
                    respond(response);
                }
            } while (!stop);

        } catch (Exception e) {
            System.out.println("run2\n");

            printError(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        close();
    }

    public void stop() {
        stop = true;
    }

    private void close() {
        stop = true;
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                printError("Errors in closing - " + e.getMessage());
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                printError("Errors in closing - " + e.getMessage());
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            printError("Errors in closing - " + e.getMessage());
        }
    }

    // --- Directly forward notifications to clients


    @Override
    public void onMessage(Message message) {
        respond(new MessageNotification(message));
    }


}
