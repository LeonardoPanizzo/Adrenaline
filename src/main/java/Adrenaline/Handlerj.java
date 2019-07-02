package Adrenaline;

import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Info;
import Adrenaline.Server.model.Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class Handlerj extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    final Model model;
    final int nplayer;//todo:per usare questa classe è necessario avere salvato il valore del player con cui dialoga


    // Constructor
    public Handlerj(Socket s, DataInputStream dis, DataOutputStream dos, Model m) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.model=m;
        nplayer=0;
    }


    public void toClient() {

        ObjectOutputStream outStream = null;

        try {

            outStream = new ObjectOutputStream(s.getOutputStream());

            Board board = new Board(1);

                /*Class class = new Class(param);
                System.out.println("Object to be written = " + class);
                outputStream.writeObject(class);
                */

            System.out.println("Object to be written = " + board);
            outStream.writeObject(board);

            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //todo: da chiamare dopo ogni azione e inviare a tutti i giocatori
    /*public void informationToClient(){
        ObjectOutputStream out=null;
        try{
            out=new ObjectOutputStream(s.getOutputStream());
            Info info=new Info(model.getBoard(), model.getPlayers());
            out.writeObject(info);
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }*/

    @Override
    public void run()
    {
        String received;
        String toreturn;
        while (true)
        {
            try {

                // Ask user what he wants
                dos.writeUTF("Select an action [StringBoard | Board]..\n"+
                        "Type Exit to terminate connection.");

                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("Exit"))
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // creating Date object
                Date date = new Date();
                Board board = new Board(1);
                // todo oggetti devono essere passati, non creati qui

                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "StringBoard" :
                        toreturn = board.myToString();//fordate.format(date); //RITORNA STRINGA DELLA BOARD, NON L'OGGETTO
                        dos.writeUTF(toreturn);
                        break;

                    case "Board":
                        this.toClient();
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
