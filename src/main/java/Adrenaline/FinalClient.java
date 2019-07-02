package Adrenaline;

import Adrenaline.Client.view.ViewTunnelA;
import Adrenaline.Client.view.ViewTunnelB;
import Adrenaline.Server.control.BiController;
import Adrenaline.Server.control.RemoteBiCon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FinalClient {

    public static void useRmi() throws RemoteException, NotBoundException, MalformedURLException, AlreadyBoundException {


        Registry registry = LocateRegistry.getRegistry();

        System.out.println("Registry got");


        /*
        ViewTunnelB viewTunnelB = new ViewTunnelB("luca");

        registry.bind("view", viewTunnelB);*/

        //RemoteBiCon remoteBiCon = (RemoteBiCon) registry.lookup("controller");


        RemoteBiCon remoteBiCon = (RemoteBiCon) Naming.lookup("rmi://localhost/controller");//registry.lookup("controller");

        // //ip:port/controller

        System.out.println("Lookup done");


        //client chiama oggetto sul server

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter you username: ");
        String name = scanner.nextLine();

        ViewTunnelB viewTunnelB = new ViewTunnelB(name, remoteBiCon);
        Naming.rebind("rmi://localhost/view", viewTunnelB);

        String[] details = {name, "controller"};

        viewTunnelB.registerWithServer(details);

        viewTunnelB.createBoard(4);
        remoteBiCon.createBoard(1);
        remoteBiCon.leaveChat(name);



/*
        System.out.println("view start");


        ViewTunnelB viewTunnelB = new ViewTunnelB("luca");

        Naming.rebind("rmi://localhost/view", viewTunnelB);

        //registry.bind("view", viewTunnelB);

        System.out.println("view done");
*/

        /*
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

        RemoteBiCon controller = (RemoteBiCon) registry.lookup("controller"); //   //localhost/controller

*/
        /*
        BiController remoteBiCon = new BiController();
        registry.bind("controller", remoteBiCon);
*/
        //RemoteController controller = (RemoteController) registry.lookup("controller");


        //System.out.println("[System] Client is ready.\n");

/*
        System.out.print("[System] Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
*/

        //new ViewTunnelB(controller);
        //new ViewTunnelB("pp");




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


    public static void main(String[] args) throws RemoteException, NotBoundException, IOException, AlreadyBoundException {

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
