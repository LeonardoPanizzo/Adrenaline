package Adrenaline.Server.model;
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class testroom {
    @Test
    public void constructorTest(){
        SupportPosition[] a;
        int asd=4; //map choosen by the player
        AmmoDeck deck1= new AmmoDeck();
        WeaponDeck deck2=new WeaponDeck();
        Position[][] board= new Position[3][4];
        String path="src/main/resources/room"+asd+".json";
        try {
            JsonReader reader= new JsonReader(new FileReader(path));
            a=new Gson().fromJson(reader, SupportPosition[].class);
            for(int i=0; i<a.length; i++){
                System.out.println(a[i].geti());
                System.out.println(a[i].getj());
                System.out.println(a[i].getroom());
                System.out.println(a[i].getdoor());
                System.out.println(a[i].getrespawn());
                board[a[i].geti()][a[i].getj()]=new Position(a[i],deck1,deck2);
            }
        }catch (NumberFormatException t)
        {
            t.printStackTrace();
        }catch (FileNotFoundException e){
            System.out.println("aa");
        }

    }
}
