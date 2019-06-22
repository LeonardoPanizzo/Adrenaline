package Adrenaline.Server.control;

import Adrenaline.Client.view.ClientRemoteInt;

public class RmiClient {

    public String name;
    public ClientRemoteInt client;

    //constructor
    public RmiClient(String name, ClientRemoteInt client){
        this.name = name;
        this.client = client;
    }


    //getters and setters
    public String getName(){
        return name;
    }
    public ClientRemoteInt getClient(){
        return client;
    }


}
