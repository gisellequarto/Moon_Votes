package org.academiadecodigo.tailormoons.moonvotes.client;

import java.io.IOException;

public class ClientLauncher {

    private static final String USAGE_MESSAGE = "Usage: java -jar MoonVotesClient.jar";

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println(USAGE_MESSAGE);
            return;
        }

        Client client = null;
        try {
            String serverAddress = args[0];
            int serverPort = Integer.parseInt(args[1]);

            client = new Client(serverAddress, serverPort);
            // String name = JOptionPane.showInputDialog("Insert your name:");
            //String ready = JOptionPane.showInputDialog("Are you ready? [y/n]");
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
