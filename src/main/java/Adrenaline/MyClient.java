package Adrenaline;

import Adrenaline.Client.model.Response;
import Adrenaline.Server.model.Request;

import java.io.*;
import java.net.Socket;


public class MyClient {
    private final String host;
    private final Integer port;
    private Socket connection;
    private ObjectInputStream in;
    private ObjectOutputStream out;


    public MyClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.connection = new Socket(host, port);

    }

    public MyClient() throws IOException{
        this.host = null;
        this.port = null;
        connection = null;
        /*
        out = new ObjectOutputStream(System.out);
        in = new ObjectInputStream(System.in);
        */
    }


    public void init() throws IOException {
        /*
        connection = new Socket(host, port);
        out = new ObjectOutputStream(connection.getOutputStream());
        in = new ObjectInputStream(connection.getInputStream());
        */
        out = new ObjectOutputStream(connection.getOutputStream());
        in = new ObjectInputStream(connection.getInputStream());
    }


    public void close() throws IOException {
        in.close();
        out.close();
        connection.close();
    }



    public Response nextResponse() {
        try {
            return ((Response) in.readObject());
        } catch (IOException e) {
            System.err.println("Exception on network: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Wrong deserialization: " + e.getMessage());
        }

        return null;
    }

    public void request(Request request) {
        try {
            out.writeObject(request);
        } catch (IOException e) {
            System.err.println("Exception on network: " + e.getMessage());
        }
    }
}
