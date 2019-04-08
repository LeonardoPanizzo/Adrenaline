package model;

public class Action {

    private int counterAction;
    private String name;

    public Action(){
        this.counterAction = 2;
        //todo: to ask how connect this element to Player
    }

    public void move (Position p){
        //todo: set player's position in p
    }

    public void attack (){
        //todo: select a weaponCard from player.weapons to use
    }

    public WeaponCard[] chooseWeapon(){
        //todo: return player.position.arms
        return
    }
}
