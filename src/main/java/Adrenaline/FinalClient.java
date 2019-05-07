package Adrenaline;

import Adrenaline.control.RemoteController;
import Adrenaline.view.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FinalClient {

    public static void useRmi() throws RemoteException, NotBoundException, MalformedURLException {

        //Registry registry = LocateRegistry.getRegistry();

        RemoteController controller = (RemoteController) Naming.lookup("172.20.10.3/controller"); //   //localhost/controller

        //RemoteController controller = (RemoteController) registry.lookup("controller");


        System.out.println("[System] Client is ready.\n");


        System.out.print("[System] Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();


        new TextView(name, controller).run();

    }


    public static void useSocket() throws IOException {
        String ip = "127.0.0.1";
        int port = 1337;
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);

        try {
            while (true) {
                String inputLine = stdin.nextLine();
                socketOut.println(inputLine);
                socketOut.flush();
                String socketLine = socketIn.nextLine();
                System.out.println(socketLine);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Connection closed");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }


    public static void main(String[] args) throws RemoteException, NotBoundException, IOException {

        FinalClient client = new FinalClient();

        //System.out.print("[System] Choose between socket and rmi: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        if (name.equals("rmi")){
            System.out.println("[System] rmi loading...");
            //while (true)
            useRmi();
        }

        if (name.equals("socket")){
            System.out.println("[System] socket loading...");
            //while (true)
            useSocket();
        }



        /*

        String ServerURL = "rmi://127.0.0.1/RMIChat";
        RemoteController chatServer = (RemoteController) Naming.lookup(ServerURL);
        new Thread(new ChatClient(args[0], chatServer)).start();

         */

    }
}
