package model;


import org.w3c.dom.ranges.RangeException;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileWriter;
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
                this.skulls[x] = -1;


            this.board = new Position[4][3];
            this.round = 1;
            this.variation = num;
            this.finalRound = false;


            //get the map variation


            JSONParser parser = new JSONParser();


            try{

//"src/main/resources/" + Integer.toString(num) + ".json"
                Object obj = parser.parse(new FileReader("src/main/resources/1.json"));

                JSONObject jsonObject = (JSONObject) obj;
                System.out.println(jsonObject);


                /*FileReader reader = new FileReader("src/main/resources/" + Integer.toString(num) + ".txt");

                System.out.println(reader); //pointer

                reader.close();


                String name = (String) jsonObject.get("name");
            System.out.println(name);*/



                }

            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File missing");

            } catch (IOException e) {
                e.printStackTrace();

            } catch (ParseException e) {
                e.printStackTrace();
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

    public int[] getSkulls() {
        return skulls;
    }

    public void setSkulls(int[] skulls) {
        this.skulls = skulls;
    }


    public boolean isFinalRound() {
        return finalRound;
    }


   // skulls are a vector of -1

    public void setFinalRound() {

        int skullsNum = 0;
        for (int i=0; i<getSkulls().length; i++)//getSkulls())
            if (getSkulls()[i] == -1){
                skullsNum = skullsNum + 1;
            }
        if (skullsNum == 0) {
            this.finalRound = true;
        }
    }


    public static Position[][] getBoard() {
        return board;
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

        if (isFinalRound()){


        }


    };


}
