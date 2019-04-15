package model;


import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard{

    @Test

    public void BoardTest() throws org.json.simple.parser.ParseException, java.io.FileNotFoundException, java.io.IOException{

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        //IndexOutOfBoundsException

        Board b0 = new Board(10);

        ExpectedException exception = ExpectedException.none();

        exception.expect(IndexOutOfBoundsException.class);


        //getVariation(), isFinalRound(), JSON

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
                "{\"door\":[true,false,true,true,true,true,true,true,true,true],\"i\":[0,0,0,1,1,1,1,2,2,2],\"j\":[0,1,2,0,1,2,3,3,1,2],\"room\":[\"b\",\"b\",\"b\",\"r\",\"r\",\"r\",\"y\",\"y\",\"w\",\"w\"],\"resetPoint\":[true,false,true,true,false,false,false,true,false,true]}\n"; // Notice the \n for new line.



        assertEquals(expectedOutput1, outContent.toString());


        //setVariation()

        Board b3 = new Board(1);

        ExpectedException e1 = ExpectedException.none();

        e1.expect(FileNotFoundException.class);


        b3.setVariation(2);
        int expectedOutput3 = 2;


        assertEquals(expectedOutput3, b3.getVariation());

    }


}