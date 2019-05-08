package Adrenaline.model;

/**
 * Using this powerup a player can move another player's figure of 1 or 2 squares in one direction
 */

public class PUNewton extends PowerupCard{

    public PUNewton (char colour, Board b){
        super("Newton", colour, b);
    }

    @Override
    public boolean use(Player p1, Player p2, Position[] squares){
        boolean done=false;
        if(squares.length==1){ //in case p2 is moved of only one square
            if(p2.getPosition().reachable(squares[0])) {
                p2.setPosition(squares[0]);
                done=true;
            }
        }else if(squares.length==2){//in case p2 is moved of two squares
            if(p2.getPosition().reachable(squares[0]) && squares[0].reachable(squares[1])){
                int[] actualp=p2.getPosition().getCoordinate();
                int[] firstp=squares[0].getCoordinate();
                int[] secondp=squares[1].getCoordinate();
                if((actualp[0]==firstp[0] && firstp[0]==secondp[0]) || (actualp[1]==firstp[1] && firstp[1]==secondp[1])){//checks if they are one direction (if an object is moving in only one direction one of the coordinate must stay the same
                    p2.setPosition(squares[1]);
                    done=true;
                }
            }
        }
        return done;
    }

}