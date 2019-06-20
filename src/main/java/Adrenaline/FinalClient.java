package Adrenaline;

import Adrenaline.Client.view.ViewTunnelA;
import Adrenaline.Client.view.ViewTunnelB;
import Adrenaline.Server.control.RemoteBiCon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FinalClient {

    public static void useRmi() throws RemoteException, NotBoundException, MalformedURLException {

        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

        RemoteBiCon controller = (RemoteBiCon) registry.lookup("controller"); //   //localhost/controller

        //RemoteController controller = (RemoteController) registry.lookup("controller");


        System.out.println("[System] Client is ready.\n");

/*
        System.out.print("[System] Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
*/

        new ViewTunnelB(controller);



    }


    public static void useSocket() throws IOException {
        /*
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
*/
        //new SocketView


        String host = "127.0.0.1";
        int port = 8000;
        System.out.println("aa");

        MyClient client = new MyClient(host, port);
        System.out.println("bb");

        //client.init();
        //System.out.println("cc");

        //TODO crea view
        new ViewTunnelA(client,host,port);


    }


    public static void main(String[] args) throws RemoteException, NotBoundException, IOException {

        //FinalClient client = new FinalClient();

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
