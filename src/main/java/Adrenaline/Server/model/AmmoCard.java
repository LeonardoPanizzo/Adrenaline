package Adrenaline.Server.model;

public class AmmoCard {
    private char[] value;

    public AmmoCard(char [] value){
        this.value=value;
    }

    public char[] getValue(){
        return value;
    }

    public void setValue(char[] value) {
        this.value = value;
    }

    public String toString(){
        String info=" ";
        for(int i=0; i<3; i++){     //every AmmoCard has 3 elements
            if(value[i]=='r'){
                info=info+"red ";
            }else if(value[i]=='b'){
                info=info+"blue ";
            } if(value[i]=='y'){
                info=info+"yellow ";
            } if(value[i]=='p'){
                info=info+"powerup ";
            }
        }
        return info;
    }
}
