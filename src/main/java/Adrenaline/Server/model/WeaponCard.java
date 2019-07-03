package Adrenaline.Server.model;

import java.io.Serializable;

public class WeaponCard implements Serializable {
    protected String name;
    protected char[] costs;
    protected char[] costEffects;
    protected boolean hasoptionaleffect;
    protected boolean loaded;


    public WeaponCard(String name, char[] costs, char[] costEffects, boolean has) {
        this.name = name;
        this.costs = costs;
        this.costEffects=costEffects;
        this.hasoptionaleffect=has;
        loaded = true;
    }

    public String getName() {
        return name;
    }

    public void reload(){
        this.loaded=true;
    }

    public char[] getCostTaking(){
        char[] temp=new char[costs.length-1];
        for(int i=1; i<costs.length; i++)
            temp[i-1]=costs[i];
        return temp;
    }

    public boolean hasoptional(){
        return hasoptionaleffect;
    }

    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        //viene chiamato il metodo delle sottoclassi
        return false;
    }

    public char[] getCostReloading(){
        return costs.clone();           //andrea: altrimenti si ritorna l'attributo e può essere modificato!
    }

    /**
     * give one damage to all the players in movements, used only by WCFurnace and WCShockwave
     * @param attacker
     * @param movements
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

    public boolean isPaid(Player p, PowerupCard[] payment){ //valid only for the weapon with only an addiotional effect or only one option
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



    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}