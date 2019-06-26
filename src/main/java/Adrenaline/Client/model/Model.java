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
    public void chooseAction(){
        char action;
        Scanner keyboard=new Scanner(System.in);
        do {
            System.out.println("choose what action you want to do \n0. print information\n1.move\n2.pick up\n3.shoot\n4.use powerup");
            action=keyboard.next().charAt(0);

        }while(action<'0' || action>'4');
    }

    public void printInfo(){
        //todo: print to CLI the information of the board and of the players
    }

    public void setmovement(){
        //todo:obtain the positions and then send them to the server
    }

    public void pickup(){
        //todo
    }

    public void shoot(){
        //todo:obtain the information for the attack, per i parametri non passati impostare oggetti a null, se i comandi sono sbalgiati si torna nel metodo chooseaction
    }

    public void usePowerUp(){
        //todo: selezionare il powerup da usare e fare il controllo se è un powerup che si può usare in quel momento
        //todo: poi impostare i parametri non utilizzati da quel powerup con oggettti null
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
