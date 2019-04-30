package model;

//import model.Board;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private int port;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        Server server = new Server(1337);

        try {
            server.startServer();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public void startServer() throws IOException {

        // apro una porta TCP
        serverSocket = new ServerSocket(port);
        System.out.println("Server socket ready on port: " + port);
        // resto in attesa di una connessione
        Socket socket = serverSocket.accept();
        System.out.println("Received client connection");

        int numBoard;
        System.out.println("Scegli il tabellone");

        Scanner in = new Scanner(socket.getInputStream());
        while (true) {
            Integer line = in.nextInt();
            if (line.equals(1) || line.equals(2) || line.equals(3) || line.equals(4)) {
                numBoard = line;
                break;
            }
        }

        Board board = new Board(numBoard);
        //PowerupDeck PUDeck = new PowerupDeck();
        //WeaponDeck WDeck = new WeaponDeck();

        System.out.println(board.getSkulls());
        System.out.println("Closing sockets");
        in.close();
        socket.close();

        serverSocket.close();


    }
}
            /*
            int playerNumber = 0;
            Player players[] = new Player[5];
            //todo: imostare time out per uscire dal loop e iniziare la partita
            boolean timer = true;
            while (timer) {
                System.out.println("Attendo giocatore...");
                players[playerNumber] = new Player(playerNumber);
                if (playerNumber == 4)
                    timer = false;
                playerNumber++;
                System.out.println("Giocatore" + playerNumber + " connesso");
            }                                                           //Players are created
            players[0].setFirstPlayer(true);                            //Player in the firs position is the first one
            int round = board.getRound();
            int currentPlayer = 0;
            while (!board.isFinalRound()) {
                currentPlayer = round % 5;
                while (players[currentPlayer] == null)                   //Needed when the players are not five
                    currentPlayer = (currentPlayer + 1) % 5;

                players[currentPlayer].setRound(true);                              //todo dare il controllo al controller per le azioni del giocatore
                players[currentPlayer].setRound(false);                             //todo ridare il controllo al model

                if (players[currentPlayer].getMadeDamage() != 0) {                    //if the player makes damages, the method gives them to the other players.
                    int[] damagedPlayers = players[currentPlayer].getDamagedPlayers();
                    for (int k : damagedPlayers) {
                        for (int damage = 0; damage < players[currentPlayer].getMadeDamage(); damage++)
                            players[damagedPlayers[k]].receivedDamages(currentPlayer);
                        if (players[damagedPlayers[k]].getLife() < 1) {                  //the damaged player die
                            board.setSkulls(players[currentPlayer].getNumber());
                            int[] points = players[damagedPlayers[k]].givePoints();    //points are assigned to each player
                            for (int i = 0; i < 5; i++) {
                                players[i].setScore(points[i]);
                            }
                        }
                        if (players[damagedPlayers[k]].getLife() == -1) {             //the current player has made overkill
                            board.setSkulls(damagedPlayers[k]);
                            int[] marks = players[currentPlayer].getMarksReceived();
                            marks[damagedPlayers[k]]++;
                            players[currentPlayer].setMarksReceived(marks);
                            marks = players[damagedPlayers[k]].getMarksGiven();     //current player received 1 mark from the damaged player with life = -1
                            marks[currentPlayer]++;
                            players[damagedPlayers[k]].setMarksGiven(marks);        //player damaged's marks given are updated
                        }
                    }
                }
                board.setRound(round++);        //we go to the next round
                board.setFinalRound();
            }
            if (board.isFinalRound()) {
                //todo implementare le funzioni del round finale
                for (int f = 0; f < players.length; f++) {
                    if (players[f] != null) {
                        players[f].setFinalRound(true);
                        players[f].setRound(true);
                        //todo passare i controllo al controller del giocatore
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}*/


        /*
        // apro una porta TCP
        serverSocket = new ServerSocket(port);
        System.out.println("Server socket ready on port: " + port);
        // resto in attesa di una connessione
        Socket socket = serverSocket.accept();
        System.out.println("Received client connection");
        // apro gli stream di input e output per leggere e scrivere
        // nella connessione appena ricevuta
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        // leggo e scrivo nella connessione finche' non ricevo "quit"
        while (true) {
            String line = in.nextLine();
            if (line.equals("quit")) {
                break;
            } else {
                out.println("Received: " + line);
                out.flush();
            }
        }

        // chiudo gli stream e il socket
        System.out.println("Closing sockets");
        in.close();
        out.close();
        socket.close();

        serverSocket.close();
        */
//}
//}
