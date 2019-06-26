package Adrenaline.Server.model;


import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard{

    @Test

    public void BoardTest() throws org.json.simple.parser.ParseException, java.io.FileNotFoundException, java.io.IOException{

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        //IndexOutOfBoundsException

        //Board b0 = new Board(10);

        ExpectedException exception = ExpectedException.none();

        exception.expect(IndexOutOfBoundsException.class);


        //getVariation(), isFinalRound(), JSON

        Board b1 = new Board(1);

        assertEquals(false, b1.isFinalRound());

        assertEquals(1, b1.getVariation());

        // setSkulls()

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

        int [] gg = {2,-1,-1,-1,-1,-1,-1,-1};

        int i = 0;
        for (int temp : gg){
            //int i=0;
            assertEquals(temp, b1.getSkulls().get(i));
            i++;
        }


        b1.setSkulls(0);
        int [] gg1 = {2,0,-1,-1,-1,-1,-1,-1};

        int j = 0;
        for (int temp : gg1){
            //int j=0;
            assertEquals(temp, b1.getSkulls().get(j));
            j++;
        }

        b1.setSkulls(3);
        int [] gg2 = {2,0,3,-1,-1,-1,-1,-1};

        int c = 0;
        for (int temp : gg2){
            //int j=0;
            assertEquals(temp, b1.getSkulls().get(c));
            c++;
        }

        assertEquals(8, b1.getSkulls().size());

        b1.setFinalRound();

        assertEquals(false, b1.isFinalRound());


        b1.setSkulls(3);
        b1.setSkulls(3);
        b1.setSkulls(3);
        b1.setSkulls(3);
        b1.setSkulls(3);

        b1.setFinalRound();

        assertEquals(true, b1.isFinalRound());



        String expectedOutput1  =
                "{\"door\":[true,false,true,true,true,true,true,true,true,true],\"i\":[0,0,0,1,1,1,1,2,2,2],\"j\":[0,1,2,0,1,2,3,3,1,2],\"room\":[\"b\",\"b\",\"b\",\"r\",\"r\",\"r\",\"y\",\"y\",\"w\",\"w\"],\"resetPoint\":[false,false,true,true,false,false,false,true,false,false]}\n"; // Notice the \n for new line.



        //assertEquals(expectedOutput1, outContent.toString());


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


        // JSON

        Board b4 = new Board(4);
        assertEquals(4, b4.getVariation());

        Board b5 = new Board(2);
        assertEquals(2, b5.getVariation());

        Board b6 = new Board(3);
        assertEquals(3, b6.getVariation());



    }

}