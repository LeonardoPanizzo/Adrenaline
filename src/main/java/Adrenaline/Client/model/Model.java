package Adrenaline.Client.model;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Player;
import Adrenaline.Server.model.PowerupCard;
import Adrenaline.Server.model.WeaponCard;

import java.util.Scanner;

public class Model{
    private Board board;
    private Player player;
    private Player[] players;

    /**
     * Action is a char and not an integer so the system is more robust
     * @return
     */
    public char chooseAction(){
        char action;
        Scanner keyboard=new Scanner(System.in);
        do {
            System.out.println("choose what action you want to do \n0. print information\n1.move\n2.pick up\n3.shoot\n4.use powerup");
            action=keyboard.next().charAt(0);
        }while(action<'0' || action>'4');
        return action;
    }

    public char chooseboard(){
        char board;
        Scanner keyboard=new Scanner(System.in);
        do {
            System.out.println("Which board do you want to use?");
            board=keyboard.next().charAt(0);
        }while(board<='0' || board>'4');
        return board;
    }

    public void selectreload(){
        WeaponCard[] weapons=player.getWeapons();
  //      PowerupCard[] useammo=
    }

    public void updateInformation(Board b, Player p, Player[] ps){
        this.board=b;
        this.player=p;
        this.players=ps;
    }

    /**
     * created to avoid cloned code
     * @return
     */
    private boolean hasThisPowerup(String x){
        PowerupCard[] power;
        power=player.getPowerup();
        boolean hasone=false;
        for(int i=0;i<power.length && !hasone; i++){
            hasone=power[i].getName().equals(x);
        }
        return hasone;
    }

    public boolean hasTargetingScope(){
        return hasThisPowerup("targeting scope");
    }

    public boolean hasTagnackGrenade(){
        return hasThisPowerup("tagback grenade");
    }

    public static void main (String args[]) {
        //todo parte in cui viene stabilita la connessione

    }
}
