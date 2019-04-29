package model;

import java.util.Collections;
import java.util.Vector;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;

public class WeaponDeck {
    private int size;
    private Vector<WeaponCard> weaponDeck;
    //todo inserirò le carte qui dentro, nel costruttore

    public WeaponDeck(){
        size=0;
    }

    public void shuffle(){
        Collections.shuffle(weaponDeck);
    }

    public WeaponCard pickUpWeapon(){
        WeaponCard x=weaponDeck.elementAt(size);
        size++;
        if(size>=weaponDeck.capacity())       //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            size=0;
        return x;
    }

    /*try{
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/resources/WeaponCard.json"));

            JSONObject jsonObject = (JSONObject) obj;


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File missing");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       */

}
