package Adrenaline.Server.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class test {
    @Test
    public void constructorTest(){
        try {
            String path="src/main/resources/student.json";
            BufferedReader buff= new BufferedReader(new FileReader(path));
            Gson gson=new Gson();
            Student a=gson.fromJson(buff, Student.class);
            System.out.println(a.getName());
            System.out.println(a.getAge());
        }catch (NumberFormatException t)
        {
            t.printStackTrace();
        }catch (FileNotFoundException e){
            System.out.println("aa");
        }
    }
}
