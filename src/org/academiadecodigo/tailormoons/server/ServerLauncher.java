package org.academiadecodigo.tailormoons.server;

import java.io.IOException;

public class ServerLauncher {

    private static final String USAGE_MESSAGE = "Usage: java -jar MoonVotesServer.jar <port> <number of players>";

    public static void main(String[] args) {

        if (args.length<1){
            System.out.println(USAGE_MESSAGE);
            return;
        }

        try {
            Server sv = new Server(args[0], Integer.parseInt(args[1]));
            sv.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
