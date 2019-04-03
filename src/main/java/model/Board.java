package model;


import org.w3c.dom.ranges.RangeException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Board {

    private	static int[] skulls;
    //private	static Vector<Integer> skulls;
    private	static Position[][] board;
    private	int round;
    private int variation;
    private boolean finalRound;



    public Board(int num) throws java.io.IOException {

        boolean range = (num <= 4) && (num > 0);

        if(range){


            //player 1 starts the game

            this.skulls = new int[8];
            //0 means that a skull is present, it will be replaced by a player's id
            for (int x : skulls)
                this.skulls[x] = 0;


            this.board = new Position[4][3];
            this.round = 1;
            this.variation = num;
            this.finalRound = false;


            //get the map variation

            try {

                FileReader reader = new FileReader("src/main/resources/" + Integer.toString(num) + ".txt");

                System.out.println(reader);

                reader.close();

            } catch ( IOException e) {

                System.out.println("File missing");
            }

        }

        else {

            System.out.println("Out of range board");

        }

    }


    //variation of maps

    //if this.variation == 1 {define board}




/*
    public Board selectBoard(int num) {

//todo fix input range

        Board var1 = new Board(num);
        Board var2 = new Board(num);
        Board var3 = new Board(num);
        Board var4 = new Board(num);

        if (num == 1) {
            return var1;
        }

        else if (num == 2) {
            return var2;
        }
        else if (num == 3) {
            return var3;
        }
        else {
            return var4;
        }

    }
*/


    //useful for endGame() and scoring

    public static int[] getSkulls() {
        return skulls;
    }

    public static void setSkulls(int[] skulls) {
        Board.skulls = skulls;
    }


    public boolean isFinalRound() {
        return finalRound;
    }

    public Board setFinalRound(Board b1) {

        int j = 0;
        for (int i=0; i<getSkulls().length; i++)//getSkulls())
            if (getSkulls()[i] == 0){
                j = j + 1;
            }
        if (j == 1) {
            b1.finalRound = true;
        }
        return b1;
    }


    public static Position[][] getBoard() {
        return board;
    }

    public static void setBoard(Position[][] board) {
        Board.board = board;
    }



// to give round
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }


// variation of the map

    public int getVariation() {
        return variation;
    }

    public void setVariation(int variation) {
        this.variation = variation;
    }



    public void endGame(){

        skulls = getSkulls();

        if (skulls[0] == 0){


        }


    };


}
