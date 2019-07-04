package Adrenaline;

import java.io.Serializable;

public class MyTime implements Serializable {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
