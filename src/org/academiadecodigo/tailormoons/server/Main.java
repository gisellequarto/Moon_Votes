package org.academiadecodigo.tailormoons.server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Server sv = new Server();
            sv.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
