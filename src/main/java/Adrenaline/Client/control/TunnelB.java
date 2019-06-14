package Adrenaline.Client.control;

import Adrenaline.Client.view.CView;
import Adrenaline.Server.control.RemoteBiCon;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// RMI!!

public class TunnelB extends UnicastRemoteObject {

    private final RemoteBiCon controller;
    private CView tunnel;



    public TunnelB (RemoteBiCon controller) throws RemoteException {
        this.controller = controller;
        this.tunnel = new CView(this.controller);
    }

    public void start() {
        this.tunnel.chooseBoardPhase();
    }


    public void ack(String message) {
        System.out.println(message);
    }
}
