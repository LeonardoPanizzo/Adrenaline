package Adrenaline;

import java.io.Serializable;

public class Timing extends Thread implements Serializable {

    public boolean run(int seconds){
        long timestamp = java.time.Instant.now().getEpochSecond();

        boolean end = false;
        long theEnd = timestamp + seconds;

        while (!end){
            long newtime = java.time.Instant.now().getEpochSecond();
            if(newtime == theEnd) {
                end = true;
                System.out.println("ESCO");
            }
        }

        return true;
    }
}
