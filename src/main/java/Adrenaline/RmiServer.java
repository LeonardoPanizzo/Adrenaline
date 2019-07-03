package Adrenaline;

import Adrenaline.Client.view.ClientRemoteInt;
import Adrenaline.Client.view.ViewTunnelB;
import Adrenaline.Server.control.BiController;
import Adrenaline.Server.control.Controller;
import Adrenaline.Server.control.RemoteBiCon;
import Adrenaline.Server.model.Player;
import Adrenaline.Server.model.PowerupCard;
import Adrenaline.Server.model.PowerupDeck;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;


public class RmiServer {

    public void execute() throws RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {

        int player = 0;
        BiController[] remoteBiCon = new BiController[5];
        Player[] players = new Player[5];
        PowerupDeck pwrd = new PowerupDeck();

        //BiController remoteBiCon = new BiController(player);


        //Registry registry = LocateRegistry.getRegistry();//"127.0.0.1", 1099);

        Registry registry = LocateRegistry.createRegistry(1099);

        System.out.println("Registry created");

        //entrano ed eseguono, parte il timer e quando sono in 5 o scatta timer parte secondo loop


        remoteBiCon[player] = new BiController(player);
        players[player] = new Player(player, pwrd);
        Naming.rebind("rmi://0.0.0.0:1099/controller", remoteBiCon[player]);
        //System.out.println("Binding done");


/*
        for (player = 0; player < 4; player++) {
            remoteBiCon[player] = new BiController(player);
            players[player] = new Player(player, pwrd);
            Naming.rebind("rmi://0.0.0.0:1099/controller", remoteBiCon[player]);

            //TODO timer

            // rmi://localhost/controller
            // //0.0.0.0:port/controller

            //registry.bind("controller", remoteBiCon);

            System.out.println("Binding done");
        }*/


/*
    private int [] myplayers = {6, 6, 6, 6, 6};

        for(int i = 0 ; i<=4; i++){
            if(myplayers[i] == 6){
                myplayers[i] = i;
                this.specificuser=myplayers[i];
                break;
            }
        }
 */

/*
        do {
            label:
            {
            for (player = 0; player < 4; player++) {

                if (player == 0){
                    remoteBiCon[player] = new BiController(player);
                    players[player] = new Player(player, pwrd);
                    Naming.rebind("rmi://0.0.0.0:1099/controller", remoteBiCon[player]);

                    //TODO timer

                    // rmi://localhost/controller
                    // //0.0.0.0:port/controller

                    //registry.bind("controller", remoteBiCon);

                    //System.out.println("Binding done");
                }

                else{

                        try {

                            if (RemoteServer.getClientHost().length() == player) {

                                System.out.println("DENTRO");


                                remoteBiCon[player] = new BiController(player);
                                players[player] = new Player(player, pwrd);


                                Naming.rebind("rmi://0.0.0.0:1099/controller", remoteBiCon[player]);

                                //TODO timer

                                // rmi://localhost/controller
                                // //0.0.0.0:port/controller

                                //registry.bind("controller", remoteBiCon);

                                System.out.println("Binding done");
                            }
                        } catch (ServerNotActiveException e) {
                            break label;
                        }
                    }
                }
            break;
            }

        }while (true);

        */

        System.out.println("SONO QUI");


        for(int i=0; i<1; i++){

            remoteBiCon[i].setPlayers(players);
            System.out.println("SONO QUI-2");

        }



/*
        System.out.println("view start");


        ClientRemoteInt view = (ClientRemoteInt) Naming.lookup("rmi://localhost/view");//registry.lookup("controller");

        System.out.println("view done");
        */


/*
        ViewTunnelB viewTunnelB = (ViewTunnelB) registry.lookup("view");

        String[] name = {"luca", "dada"};

        viewTunnelB.registerWithServer(name);//, "id"));
*/


        /*
        Registry reg;
        try {
            reg = LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
            return;
        }


        BiController obj = new BiController();
        // DEVO USARE UNA CLASSE CHE NON SIA IL CONTROLLER MA UN QUALCOSA CHE SE HO RMI ACCEDO AL CONTROLLER
        // ALTRIMENTI USO SOCKET

        reg.rebind("controller", obj);
        System.out.println("PeerServer bound in registry");
        */
    }

    /*
    public void run() {
        try {
            execute();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    */
}