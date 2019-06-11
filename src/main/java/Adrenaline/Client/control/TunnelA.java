package Adrenaline.Client.control;

import Adrenaline.Client.view.CView;
import Adrenaline.Client.view.ControlTunnelClient;


// SOCKET!!

public class TunnelA implements ControlTunnelClient {

    private final ClientController controller;
    private CView tunnel;


    public TunnelA (ClientSocketController controller){
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
