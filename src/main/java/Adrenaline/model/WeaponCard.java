package Adrenaline.model;

public class WeaponCard {
    protected String name;
    protected char[] costs;
    protected char[] costEffects;
    protected boolean loaded;


    public WeaponCard(String name, char[] costs, char[] costEffects) {
        this.name = name;
        this.costs = costs;
        this.costEffects=costEffects;
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

    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        //viene chiamato il metodo delle sottoclassi
        return false;
    }

    public char[] getCostReloading(){
        return costs.clone();           //andrea: altrimenti si ritorna l'attributo e può essere modificato!
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
        return paid;
    }



    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}