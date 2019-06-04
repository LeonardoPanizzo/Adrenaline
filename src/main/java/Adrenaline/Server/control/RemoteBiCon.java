package Adrenaline.Server.control;

import Adrenaline.Client.model.Response;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteBiCon extends Remote {

    Response createBoard(Integer boardNumber) throws RemoteException;
}
