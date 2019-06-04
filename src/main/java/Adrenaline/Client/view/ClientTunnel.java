package Adrenaline.Client.view;

import Adrenaline.Client.control.ClientController;
import Adrenaline.Server.control.RemoteBiCon;
import Adrenaline.Server.control.RemoteController;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientTunnel extends UnicastRemoteObject implements RemoteView, Serializable {

    private final ClientController controller;
    private final RemoteBiCon rmiController;
    private CView tunnel;


    public ClientTunnel (ClientController controller) throws RemoteException {
        this.rmiController = null;
        this.controller = controller;
        this.tunnel = new CView(this.controller);

    }

    public ClientTunnel (RemoteBiCon controller) throws RemoteException {
        this.rmiController = controller;
        this.controller = null;
        this.tunnel = new CView(this.rmiController);

    }

    public void start() {

        this.tunnel.chooseBoardPhase();

    }


    public void ack(String message) throws RemoteException {
        System.out.println(message);
    }



}
