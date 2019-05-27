package Adrenaline;

import java.io.IOException;

public class ServerTry {

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer(8000);

        try {
            server.run();
        } finally {
            server.close();
        }
    }
}
