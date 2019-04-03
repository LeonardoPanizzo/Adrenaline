package model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard{

    @Test

    public void BoardTest() throws java.io.IOException{


        //assertEquals(1, b1.getVariation());


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Board b1 = new Board(1);

        assertEquals(false, b1.isFinalRound());

        int[] x = {1,1,2,3,0,2,3,2};

        b1.setSkulls(x);

        assertEquals(x, b1.getSkulls());

        b1.setFinalRound(b1);

        assertEquals(true, b1.isFinalRound());

        String expectedOutput1  = "11\n"; // Notice the \n for new line.

        assertEquals(expectedOutput1, outContent.toString());


        Board b2 = new Board(2);

        String expectedOutput2  = "File missing\nFile missing\n"; // Notice the \n for new line.

        assertEquals(expectedOutput2, outContent.toString());



       // assertEquals("11", new Board(1));
    }


}