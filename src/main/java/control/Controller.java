package control;

import model.Board;

import java.rmi.*;
import java.rmi.server.*;

// db

public class Controller extends UnicastRemoteObject implements RemoteController {

    public Controller() throws RemoteException {
        super();
        //UnicastRemoteObject.exportObject(this, 0);
    }

    /*
    private Map<String, Double> prices;

    public SharedInterfaceImpl() throws RemoteException {
        prices = new HashMap<String, Double>();
        prices.put("Blackwell Toaster", 24.95);
        prices.put("ZapXpress Microwave Oven", 49.95);
        UnicastRemoteObject.exportObject(this, 0);
    }


    public double getPrice(String description) throws RemoteException {
        Double price = prices.get(description);
        return price == null ? 0 : price;
    }
*/
    public void createBoard(int num) throws RemoteException{

        Board board = new Board(num);
        System.out.println("Board created");

    }


}