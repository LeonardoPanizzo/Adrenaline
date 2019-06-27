package Adrenaline;

import Adrenaline.Server.model.Board;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Clientj {


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

