package Adrenaline;

import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
    static int interval;
    static Timer timer;
    boolean  off;

    public Countdown(int seconds) {

        int delay = 1000;
        int period = 1000;
        timer = new Timer();

        interval = seconds;

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println(setInterval());

            }
        }, delay, period);
    }

    private int setInterval() {
        off = false;
        if (interval == 1){
            timer.cancel();
            off = true;

            return --interval;
        }else{
            off = false;
            return --interval;

        }
    }

    public boolean finish(){

        return off;
    }
}

