package Adrenaline.Server.model;


import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.util.*;


/**
 * Board class is the virtual representation of the physical map
 *
 */
public class Board {

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

            AmmoDeck ad = new AmmoDeck();       // ---\ metodi aggiunti per creare le posizioni
            WeaponDeck wd = new WeaponDeck();   // ---/
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


                Integer iteratorINext = null;
                Integer iteratorJNext = null;
                String iteratorRoomNext = null;
                Boolean iteratorDoorNext = null;
                Boolean iteratorResetNext = null;


                while (iteratorI.hasNext() && iteratorJ.hasNext() ) {

                    int x = 0;
                 //   this.board[( (Number) iteratorI.next() ).intValue()][( (Number) iteratorJ.next() ).intValue()] = new Position(( (Number) iteratorI.next() ).intValue(), ( (Number) iteratorJ.next() ).intValue(), iteratorRoom.next().charAt(x), iteratorDoor.next(), iteratorReset.next(), ad, wd);


                    iteratorINext = ((Number) iteratorI.next() ).intValue();
                    iteratorJNext = ( (Number) iteratorJ.next() ).intValue();
                    iteratorRoomNext = iteratorRoom.next();
                    iteratorDoorNext = iteratorDoor.next();
                    iteratorResetNext = iteratorReset.next();

                    this.board[( iteratorINext )][( iteratorJNext )] = new Position((  iteratorINext ), (  iteratorJNext ), iteratorRoomNext.charAt(x), iteratorDoorNext, iteratorResetNext, ad, wd);


                    x++;

                    //this.board[iteratorI.next()][iteratorJ.next()] = new Position(1, 1, 'b', true, true);
                }

                /* TODO setlinks look at PositionTest java.lang.NullPointerException at Adrenaline.Server.model.Position.setLinks(Position.java:72)


                 * TODO indexes not found in file.json <-----
                */


                if (num == 1){
                    //this.board[1][1].setLinks(this.board[1][3]);

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
                }

                if (num == 2){
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
                }

                if (num == 3){
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
                }

                if (num == 4) {
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