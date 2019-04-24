package model;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.util.Iterator;

public class WeaponCard {

    private String name;

    public WeaponCard(){
        try{
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/resources/" + this.name + ".json"));

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
    }
}
