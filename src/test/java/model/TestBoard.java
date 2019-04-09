package model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard{

    @Test

    public void BoardTest() throws java.io.IOException{

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        // TODO: 2019-04-09 test su exception 
        //assertEquals("Out of range board", new Board(55));



        Board b1 = new Board(1);

        assertEquals(false, b1.isFinalRound());

        assertEquals(1, b1.getVariation());



        int[] x = {1,1,2,3,0,2,3,-1};

        b1.setSkulls(x);

        assertEquals(x, b1.getSkulls());

        b1.setFinalRound();

        assertEquals(false, b1.isFinalRound());



        int[] y = {1,1,2,3,0,2,3,0};

        b1.setSkulls(y);

        b1.setFinalRound();

        assertEquals(true, b1.isFinalRound());





        String expectedOutput1  =
                "{\"name\":\"mkyong.com\",\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"],\"age\":100}\n"; // Notice the \n for new line.



        assertEquals(expectedOutput1, outContent.toString());



/*
        Board b2 = new Board(2);


        String expectedOutput2  = "File missing\nFile missing\n";

        assertEquals(expectedOutput2, outContent.toString());
        */



        Board b3 = new Board(3);

        b3.setVariation(2);
        int expectedOutput3 = 2;

        assertEquals(expectedOutput3, b3.getVariation());





       // assertEquals("11", new Board(1));
    }


}