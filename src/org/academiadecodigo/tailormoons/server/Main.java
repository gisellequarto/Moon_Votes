package org.academiadecodigo.tailormoons.server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if (args.length<1){
            System.out.println("Wrong usage. java -jar <port>");
            return;
        }

        try {
            Server sv = new Server(args[0]);
            sv.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
