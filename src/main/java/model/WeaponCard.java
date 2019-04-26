package model;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.util.Iterator;

public class WeaponCard {
    protected String name;
    protected char[] costs;
    protected Board board;

    public WeaponCard(){
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
        leonardo: il file che viene letto per creare le carte ha più senso metterlo in weaponDeck che tiene il riferimento
        di tutte le carte, non qui dove si lavora con una singola carta*/
    }
}
