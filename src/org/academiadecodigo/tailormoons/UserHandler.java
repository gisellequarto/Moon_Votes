package org.academiadecodigo.tailormoons;

import java.net.ServerSocket;

public class UserHandler implements Runnable {

    private String name;
    private int points;
    private ServerSocket sv;


    @Override
    public void run() {

    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
}
