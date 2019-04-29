package model;


import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.lang.model.type.NullType;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Board {

    //private	static int[] skulls;
    private	static Vector<Integer> skulls;
    private	static Position[][] board;
    private	int round;
    private int variation;
    private boolean finalRound;



    public Board(int num) /*throws org.json.simple.parser.ParseException, java.io.FileNotFoundException, java.io.IOException*/ {    //le eccezioni sono gestite all'interno della classe. quindi non vengono gestite qui e non si propagano. Non serve il throws

        boolean range = (num <= 4) && (num > 0);

        if(range){


            //player 1 starts the game


            //this.skulls = new int[8];
            this.skulls = new Vector<Integer>(0);
            //-1 means that a skull is present, it will be replaced by a player's id
            //for (int x : skulls)
                //this.skulls[x] = -1;
                //this.skulls.add(x,-1);
            for (int i =0; i < 8; i++)
            this.skulls.add(i, -1);


            this.board = new Position[3][4];
            this.round = 0;
            this.variation = num;
            this.finalRound = false;


            //get the map variation


            JSONParser parser = new JSONParser();


            try {

                //"src/main/resources/1.json"
                //"src/main/resources/" + Integer.toString(num) + ".json"

                Object obj = parser.parse(new FileReader("src/main/resources/" + Integer.toString(num) + ".json"));

                JSONObject jsonObject = (JSONObject) obj;
                System.out.println(jsonObject);

                JSONArray i = (JSONArray) jsonObject.get("i");
                JSONArray j = (JSONArray) jsonObject.get("j");
                JSONArray room = (JSONArray) jsonObject.get("room");
                JSONArray door = (JSONArray) jsonObject.get("door");
                JSONArray reset = (JSONArray) jsonObject.get("resetPoint");


                Iterator<Integer> iteratorI = i.iterator();
                Iterator<Integer> iteratorJ = j.iterator();
                Iterator<String> iteratorRoom = room.iterator();
                Iterator<Boolean> iteratorDoor = door.iterator();
                Iterator<Boolean> iteratorReset = reset.iterator();


                while (iteratorI.hasNext() && iteratorJ.hasNext() ) {

                    int x = 0;
                    this.board[( (Number) iteratorI.next() ).intValue()][( (Number) iteratorJ.next() ).intValue()] = new Position(( (Number) iteratorI.next() ).intValue(), ( (Number) iteratorJ.next() ).intValue(), iteratorRoom.next().charAt(x), iteratorDoor.next(), iteratorReset.next());
                    x++;

                    //this.board[iteratorI.next()][iteratorJ.next()] = new Position(1, 1, 'b', true, true);


                }


            }catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File missing");

            } catch (IOException e) {
                e.printStackTrace();

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        else {

            try {
            } catch (IndexOutOfBoundsException e){

                e.printStackTrace();

                System.out.println("Out of range board");

            }
        }

    }



    //useful for endGame() and scoring

    //public int[] getSkulls() {
    public Vector<Integer> getSkulls(){
        return this.skulls;
    }


    /**
     *
     *
     * @param skulls
     */

    
    public void setSkulls(int skulls) {
        int index = this.skulls.indexOf(-1);
        this.skulls.set(index, skulls);
    }


    public boolean isFinalRound() {
        return finalRound;
    }


   // skulls are a vector of -1

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

    /* inutile perché già definita nel costruttore
    public void setVariation(int variation) {
        this.variation = variation;
    }
    */


    public void endGame(){

        skulls = getSkulls();

        if (isFinalRound()){

            System.out.println("THE END");

        }


    }

    public void giveRestoreRound(){}

}