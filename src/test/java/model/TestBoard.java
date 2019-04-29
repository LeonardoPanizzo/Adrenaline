package model;


import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;


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


        //TODO capire perchè x != b1.getSkulls() e testare set skulls


        int[] ww = {-1,-1,-1,-1,-1,-1,-1,-1};
/*
        for (int temp : ww){
            int i=0;
            assertEquals(temp, b1.getSkulls()[i]);
            i++;
        }
*/
        Iterator v = b1.getSkulls().iterator();
        int pp =0;
        while (v.hasNext()){
            assertEquals(ww[pp], v.next());
            pp++;
        }
        /*
        for (int temp : ww){
            int i=0;
            assertEquals(temp, b1.getSkulls().get(i));
            i++;
        }
*/
        b1.setSkulls(2);

        //boolean k = Arrays.equals(b1.getSkulls(), b1.getSkulls());

        int [] gg = {2,-1,-1,-1,-1,-1,-1,-1};

        int i = 0;
        for (int temp : gg){
            //int i=0;
            assertEquals(temp, b1.getSkulls().get(i));
            i++;
        }


        b1.setSkulls(4);
        int [] gg1 = {2,4,-1,-1,-1,-1,-1,-1};

        int j = 0;
        for (int temp : gg1){
            //int j=0;
            assertEquals(temp, b1.getSkulls().get(j));
            j++;
        }

        b1.setSkulls(3);
        int [] gg2 = {2,4,3,-1,-1,-1,-1,-1};

        int c = 0;
        for (int temp : gg2){
            //int j=0;
            assertEquals(temp, b1.getSkulls().get(c));
            c++;
        }


        //boolean k1 = Arrays.equals(gg1, b1.getSkulls());

        //assertEquals(true, k); //<------- it must be true

        b1.getSkulls();
        //assertEquals("ww", outContent.toString());

        //assertEquals(8, b1.getSkulls().length);
        //assertEquals(8, ww);


        b1.setFinalRound();

        //assertEquals(false, b1.isFinalRound());



        /*
        int y = 0;

        b1.setSkulls(y);

        b1.setFinalRound();

        assertEquals(true, b1.isFinalRound());
*/


        String expectedOutput1  =
                "{\"door\":[true,false,true,true,true,true,true,true,true,true],\"i\":[0,0,0,1,1,1,1,2,2,2],\"j\":[0,1,2,0,1,2,3,3,1,2],\"room\":[\"b\",\"b\",\"b\",\"r\",\"r\",\"r\",\"y\",\"y\",\"w\",\"w\"],\"resetPoint\":[true,false,true,true,false,false,false,true,false,true]}\n"; // Notice the \n for new line.



        assertEquals(expectedOutput1, outContent.toString());


        //b1.setSkulls(2);
        //assertEquals("ww", outContent.toString());



        //getRound()

        Board b3 = new Board(1);

        ExpectedException e1 = ExpectedException.none();

        e1.expect(FileNotFoundException.class);


        b3.getRound();
        int expectedOutput3 = 0;

        assertEquals(expectedOutput3, b3.getRound());

        b3.setRound(3);
        expectedOutput3 = 3;
        assertEquals(expectedOutput3, b3.getRound());


    }

}