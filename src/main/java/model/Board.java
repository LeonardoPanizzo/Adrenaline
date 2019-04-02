package model;


public class Board {

    private	static int[] skulls;
    //private	static Vector<Integer> skulls;
    private	static Position[][] board;
    private	int round;
    private int variation;


    public Board(int num){

        //player 1 starts the game

        this.skulls = new int[8];
        //0 means that a skull is present, it will be replaced by a player's id
        for(int x : skulls)
            this.skulls[x]= 0;


        this.board = new Position[4][3];
        this.round = 1;
        this.variation = num;

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

    };


}
