package Adrenaline;

import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Info;
import Adrenaline.Server.model.Player;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Clientj {
    private static Board board;
    private static Player[] players;
    private static Player player;


    public static void fromServer(Socket s){
        try {
            ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());
            Board board = (Board) inStream.readObject();
            System.out.println("Object received = " + board);

        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void printInfo(){
        System.out.println(board.getRound());
        System.out.println(player.completeString());//information about themselves
        for(int i=0; i<players.length; i++){        //information about other players
            System.out.println(players[i].toString());
        }
    }

    public static void setmovement(){//todo: vedere se passare come parametro il numero di passi che è possibile fare
        //todo:obtain the positions and then send them to the server
    }

    public static void pickup(){
        //todo:prendere i movimenti
        //server.pickup(player.getnumber());
    }

    public static void shoot(){
        //todo:obtain the information for the attack, per i parametri non passati impostare oggetti a null, se i comandi sono sbalgiati si torna nel metodo chooseaction
    }

    public static void usePowerUp(){
        //todo: selezionare il powerup da usare e fare il controllo se è un powerup che si può usare in quel momento
        //todo: poi impostare i parametri non utilizzati da quel powerup con oggettti null
    }


    public static void chooseAction(){
        char action;
        Scanner keyboard=new Scanner(System.in);
        do {
            System.out.println("choose what action you want to do \n0. print information\n1.move\n2.pick up\n3.shoot\n4.use powerup");
            action=keyboard.next().charAt(0);
            if(action>='0' && action<='4'){
                switch (action){
                    case '0':
                        printInfo();
                        break;
                    case '1':
                        setmovement();
                        break;
                    case '2':
                        pickup();
                        break;
                    case '3':
                        shoot();
                        break;
                    case '4':
                        usePowerUp();
                        break;
                }
            }
        }while(action<'0' || action>'4');//todo:aggiungere anche il controllo sul numero player.action
    }

    public static void upodateInfo(Socket s){
        try {
            ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());
            Info info=(Info) inStream.readObject();
            board=info.getBoard();
            
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException
    {
        try {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true) {
                label:
                {
                    System.out.println(dis.readUTF());
                    String tosend = scn.nextLine();
                    dos.writeUTF(tosend);

                    if (tosend.equals("Exit")) {
                        System.out.println("Closing this connection : " + s);
                        s.close();
                        System.out.println("Connection closed");
                        break;

                    }

                    if (tosend.equals("Board")) {
                        fromServer(s);
                        break label;

                    }

                    // printing action as requested by client

                    else {
                        String received = dis.readUTF();
                        System.out.println(received);
                    }
                }
            }

                // closing resources
                scn.close();
                dis.close();
                dos.close();

        } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

