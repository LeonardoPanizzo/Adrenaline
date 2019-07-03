package Adrenaline.Server.model;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Board class is the virtual representation of the physical map
 *
 */
public class Board implements Serializable {

    //private	static int[] skulls;
    private	static Vector<Integer> skulls;
    private static Position[][] board;
    private	int round;
    private int variation;
    private boolean finalRound;


    /**
     * is Board's constructor
     * num represents the number of the map
     * Board's attributes are filled by reading a JSON file
     * @param num
     * @see 1.json
     */
    public Board(int num){
        if(num>=1 && num <=4) {
            this.skulls=new Vector<Integer>(0);
            for (int i =0; i < 8; i++)
                this.skulls.add(i, -1); //-1 means that a skull is present, it will be replaced by a player's id
            this.round = 0;
            this.variation = num;
            this.finalRound = false;
            SupportPosition[] support;
            AmmoDeck deck1 = new AmmoDeck();
            WeaponDeck deck2 = new WeaponDeck();
            board = new Position[3][4];
            String path = "src/main/resources/room" + num + ".json";
            System.out.println("Variation: "+variation);
            try {
                JsonReader reader = new JsonReader(new FileReader(path));
                support = new Gson().fromJson(reader, SupportPosition[].class);
                for (int i = 0; i < support.length; i++) {
                    board[support[i].geti()][support[i].getj()] = new Position(support[i], deck1, deck2);
                }
                if (num == 1) {
                    board[0][0].setLinks(board[1][0]);
                    board[0][2].setLinks(board[1][2]);
                    board[1][0].setLinks(board[0][0]);
                    board[1][1].setLinks(board[2][1]);
                    board[1][2].setLinks(board[1][3]);
                    board[1][2].setLinks(board[0][2]);
                    board[1][3].setLinks(board[1][2]);
                    board[2][3].setLinks(board[2][2]);
                    board[2][1].setLinks(board[1][1]);
                    board[2][2].setLinks(board[2][3]);
                } else if (num == 2) {
                    board[0][0].setLinks(board[1][0]);
                    board[0][2].setLinks(board[0][3]);
                    board[0][2].setLinks(board[1][2]);
                    board[0][3].setLinks(board[0][2]);
                    board[0][3].setLinks(board[1][3]);
                    board[1][0].setLinks(board[0][0]);
                    board[1][1].setLinks(board[2][1]);
                    board[2][1].setLinks(board[1][1]);
                    board[2][1].setLinks(board[2][2]);
                    board[1][2].setLinks(board[0][2]);
                    board[1][3].setLinks(board[0][3]);
                    board[2][2].setLinks(board[2][1]);
                } else if (num == 3) {
                    board[0][0].setLinks(board[0][1]);
                    board[1][0].setLinks(board[2][0]);
                    board[0][1].setLinks(board[0][0]);
                    board[0][1].setLinks(board[1][1]);
                    board[0][2].setLinks(board[1][2]);
                    board[1][1].setLinks(board[0][1]);
                    board[1][1].setLinks(board[2][1]);
                    board[1][2].setLinks(board[0][2]);
                    board[1][2].setLinks(board[1][3]);
                    board[2][0].setLinks(board[1][0]);
                    board[2][1].setLinks(board[1][1]);
                    board[2][2].setLinks(board[2][3]);
                    board[1][3].setLinks(board[1][2]);
                    board[2][3].setLinks(board[2][2]);
                } else if (num == 4) {
                    board[0][0].setLinks(board[0][1]);
                    board[1][0].setLinks(board[2][0]);
                    board[0][1].setLinks(board[0][0]);
                    board[0][1].setLinks(board[1][1]);
                    board[0][2].setLinks(board[0][3]);
                    board[0][2].setLinks(board[1][2]);
                    board[0][3].setLinks(board[0][2]);
                    board[0][3].setLinks(board[1][3]);
                    board[1][1].setLinks(board[0][1]);
                    board[1][1].setLinks(board[2][1]);
                    board[2][0].setLinks(board[1][0]);
                    board[2][1].setLinks(board[1][1]);
                    board[2][1].setLinks(board[2][2]);
                    board[1][2].setLinks(board[0][2]);
                    board[1][3].setLinks(board[0][3]);
                    board[2][2].setLinks(board[2][1]);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (NumberFormatException t) {
                t.printStackTrace();
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Method to convert the board into a string form.
     *
     * @return a board in string format
     */
    public String myToString(){
        String info="board:\n";
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                if(board[i][j]!=null){
                    info=info+board[i][j].toString()+"  ";
                }
            }
            info=info+"\n";
        }
        return info;
    }

    //useful for endGame() and scoring

    /**
     * returns the vector representing the order of kills
     * @return this.skulls
     */
    //public int[] getSkulls() {
    public Vector<Integer> getSkulls(){
        return this.skulls;
    }


    /**
     * substitutes -1 with the player's id who did the last kill
     *
     * @param skulls
     */

    
    public void setSkulls(int skulls) {
        int index = this.skulls.indexOf(-1);
        this.skulls.set(index, skulls);
    }


    /**
     * checks if it's the final round
     * @return finalRound
     */
    public boolean isFinalRound() {
        return finalRound;
    }


   // skulls are a vector of -1

    /**
     * if the conditions occurs (last element of skulls != -1)
     * sets finalRound = true
     */
    public void setFinalRound() {
/*
        int skullsNum = 0;
        for (int i=0; i<getSkulls().length; i++)//getSkulls())
            if (getSkulls()[i] == -1){
                skullsNum = skullsNum + 1;
            }
        if (skullsNum == 0) {
            this.finalRound = true;
        }
        else
            this.finalRound = false;
    }
*/

        int skullsNum = 0;
        for (int i=0; i<getSkulls().size(); i++)//getSkulls())
            if (getSkulls().get(i) == -1){
                skullsNum = skullsNum + 1;
            }
        if (skullsNum == 0) {
            this.finalRound = true;
        }
        else
            this.finalRound = false;
    }

    /**
     * return the board as matrix of positions
     * @return board
     */
    public static Position[][] getBoard() {
        return board;
    }


    /**
     * gives the round number
     * @return round
     */
// to give round
    public int getRound() {
        return round;
    }



    /**
     * set round equals to the input number
     * @param round
     */
    public void setRound(int round) {
        this.round = round;
    }


// variation of the map

    /**
     * is the map variation (1 to 4)
     * @return variation
     * @see 1.json
     */
    public int getVariation() {
        return variation;
    }

    /* inutile perché già definita nel costruttore
    public void setVariation(int variation) {
        this.variation = variation;
    }
    */


    /**
     * end the game
     */
    public void endGame(){

        skulls = getSkulls();

        if (isFinalRound()){

            System.out.println("THE END");

        }


    }

    /**
     *
     */
    public void giveRestoreRound(){}

}