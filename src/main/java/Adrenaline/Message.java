package Adrenaline;

import java.io.Serializable;

public class Message implements Serializable {
    private final String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}
