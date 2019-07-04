package Adrenaline.Server.model;

import java.io.Serializable;

public class WeaponCard implements Serializable {
    protected String name;
    protected char[] costs;
    protected char[] costEffects;
    protected boolean hasoptionaleffect;
    protected boolean loaded;


    /**
     * WeaponCard's constructor. A weapon card has a name, a cost, the cost for each effect a boolean attribute to show if has an optional fire mode or not.
     *
     * @param name name of the weapon
     * @param costs cost to reload the weapon
     * @param costEffects cost to use effects
     * @param has true if it has an optional fire mode
     */
    public WeaponCard(String name, char[] costs, char[] costEffects, boolean has) {
        this.name = name;
        this.costs = costs;
        this.costEffects=costEffects;
        this.hasoptionaleffect=has;
        loaded = true;
    }

    /**
     * Method to take the card's name.
     *
     * @return the cards name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to reload the weapon.
     */
    public void reload(){
        this.loaded=true;
    }

    /**
     * Method to take the cost to take this from a position that's a respawn point.
     *
     * @return the cost to take this
     */
    public char[] getCostTaking(){
        char[] temp=new char[costs.length-1];
        for(int i=1; i<costs.length; i++)
            temp[i-1]=costs[i];
        return temp;
    }

    /**
     * Method to know if the card has an optional fire mode or not.
     *
     * @return true if this has an optional fire mode
     */
    public boolean hasoptional(){
        return hasoptionaleffect;
    }

    /**
     * Method to use the weapon. It uses the player who attacks, the fire mode (1 or 2 and each combo), the player or
     * the players who/whose are attacked, positions where move attacker, attacked or other effects. It also use powerup
     * to pay the ammo cost of the optional effects.
     *
     * @param attacker player who use the weapon
     * @param mode1 select the base mode o the alternative fire
     * @param mode2 select the optional mode and the sequence how they will be used
     * @param attackedPlayers player that will be damaged
     * @param movements position where move attacker, attacked or  position used for other effects
     * @param payment power up cards to eventually pay the cost of optional effects
     * @return
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        //later it is called from each weapon sub-class
        return false;
    }

    /**
     * Method to obtain the weapon's cost to reload it
     *
     * @return reloading cost
     */
    public char[] getCostReloading(){
        return costs.clone();
    }

    /**
     * give one damage to all the players in movements, used only by WCFurnace and WCShockwave.
     *
     * @param attacker the player who attacks
     * @param movements positions where players are damaged
     */
    protected void attackall(Player attacker, Position[] movements){
        Player[] temp;
        for(int i=0; i<movements.length; i++){
            temp=movements[i].getPlayers();
            for(int j=0; j<temp.length; j++){
                temp[j].receivedDamages(attacker);
            }
        }
    }

    /**
     * Method valid only for the weapon with only an additional effect or only one option. It controls if this
     * additional effect or the option is payed.
     *
     * @param p player who has ammo
     * @param payment power up cards are used to pay the ammo cost
     * @return
     */
    public boolean isPaid(Player p, PowerupCard[] payment){
        boolean paid=false;
        if(payment == null || payment.length==0){
            paid=p.updateAmmo(costEffects);
        }else{
            int[]costEff=new int[3];
            for(int i=0;i<3;i++)
                costEff[i]=0;
            for(int i=0;i<costEffects.length;i++){
                if(costEffects[i]=='b')
                    costEff[0]++;
                if(costEffects[i]=='y')
                    costEff[1]++;
                if(costEffects[i]=='r')
                    costEff[2]++;
            }
            for(int i=0; i<payment.length;i++){ //vedo i colori delle munizioni passate e li sottraggo dal costo
                if(payment[i].getColour()=='b')
                    costEff[0]--;
                if(payment[i].getColour()=='y')
                    costEff[1]--;
                if(payment[i].getColour()=='r')
                    costEff[2]--;
            }
            boolean consistentpayment=(costEff[0]>=0 && costEff[1]>=0 && costEff[2]>=0);//non posso avere costi negativi, succederebbe nel caso in cui passo una munizione di un colore non richiesto
            if(consistentpayment){
                if(p.updateAmmo(costEff)){
                    paid=p.updatePowerup(payment);
                }
            }
        }
        if(!paid)
            System.out.println("The extra cost is not pay");
        return paid;
    }

    /**
     * Method to control if all optional effects are payed by the player.
     *
     * @param p player who pays
     * @param payment power up cards are used to pay the ammo cost
     * @param mode mode that needs a payment
     * @return
     */
    public boolean isPaid(Player p, PowerupCard[] payment, int[] mode){
        boolean paid=false;
        int[] temporaryCost=new int[3];
        for(int i=0; i<3; i++)
            temporaryCost[i]=0;
        for(int i=0; i<mode.length; i++){ //after this we have a int[] with the cost of the optional effects
            if(costEffects[mode[i]]=='b')
                temporaryCost[0]++;
            if(costEffects[mode[i]]=='y')
                temporaryCost[1]++;
            if(costEffects[mode[i]]=='r')
                temporaryCost[2]++;
        }

        if(payment==null || payment.length==0){
            paid=p.updateAmmo(temporaryCost);
        }else{
            for(int i=0; i<payment.length; i++){
                if(payment[i].getColour()=='b')
                    temporaryCost[0]--;
                if(payment[i].getColour()=='y')
                    temporaryCost[1]--;
                if(payment[i].getColour()=='r')
                    temporaryCost[2]--;
            }
            boolean consistent=(temporaryCost[0]>=0 && temporaryCost[1]>=0 && temporaryCost[2]>=0);
            if(consistent){
                if(p.updateAmmo(temporaryCost))
                    paid=p.updatePowerup(payment);
            }
        }
        if(!paid)
            System.out.println("The extra cost is not pay");
        return paid;
    }

    /**
     * Method to see if a weapon is loaded or not.
     *
     * @return true if the weapon is loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Method to set loaded a weapon. It is used to reload a weapon.
     *
     * @param loaded set to true if the weapon is loaded
     */
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}