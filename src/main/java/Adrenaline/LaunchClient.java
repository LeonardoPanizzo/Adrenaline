package Adrenaline;


import Adrenaline.Client.control.ClientController;


import java.io.IOException;



public class LaunchClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        if (args.length == 0) {
            System.err.println("Provide host:port please");
            return;
        }

        String[] tokens = args[0].split(":");

        if (tokens.length < 2) {
            throw new IllegalArgumentException("Bad formatting: " + args[0]);
        }

        String host = tokens[0];
        int port = Integer.parseInt(tokens[1]);
*/

        String host = "127.0.0.1";
        int port = 8000;
        System.out.println("aa");

        MyClient client = new MyClient(host, port);
        System.out.println("bb");

        client.init();
        System.out.println("cc");
        ClientController controller = new ClientController(client);
        controller.run();

        client.close();
    }
}
