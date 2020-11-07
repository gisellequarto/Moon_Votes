package org.academiadecodigo.tailormoons.client;

import java.io.IOException;

public class ClientLauncher {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 6924;

    public static void main(String[] args) {

        Client client = null;
        try {
            client = new Client(SERVER_ADDRESS, SERVER_PORT);
            // String name = JOptionPane.showInputDialog("Insert your name:");
            //String ready = JOptionPane.showInputDialog("Are you ready? [y/n]");
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
