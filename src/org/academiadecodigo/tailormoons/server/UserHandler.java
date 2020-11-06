package org.academiadecodigo.tailormoons.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserHandler implements Runnable {

    private Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private Server sv;

    private String name;
    private int points;
    private boolean ready;

    public UserHandler(Server sv, Socket client) throws IOException {

        this.sv = sv;
        this.client = client;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {

        askForName();

        askReady();

    }

    private void askForName() {

        out.printf("[System]: Welcome, please enter your name. ");

        while (true) {
            try {
                String name = in.readLine();
                if (!name.isBlank() && !sv.existsName(name)) {
                    this.name = name;
                    return;
                }

                out.println("[System]: This name already exists or is blank, please insert a new one");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void askReady() {

        out.printf("[Game]: Are you ready? (y/n) ");

        while (true) {
            try {
                String answer = in.readLine();
                if (answer.toLowerCase().equals("y")) {
                    ready = true;
                    return;
                } else if (answer.toLowerCase().equals("n")) {
                    out.printf("[Game]: Don't be a conas ");
                    continue;
                }

                out.println("[Game]: Invalid input (y/n)");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void print(String message) {
        out.println(message);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public boolean isReady() {
        return ready;
    }
}
