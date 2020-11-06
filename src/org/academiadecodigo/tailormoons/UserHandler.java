package org.academiadecodigo.tailormoons;

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

    public UserHandler(Server sv, Socket client) throws IOException {

        this.sv = sv;
        this.client = client;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {

        askForName();

        out.println("acabou");
    }

    private void askForName() {

        out.print("[System]: Welcome, please enter your name. ");
        out.flush();
        while (true) {
            try {
                String name = in.readLine();
                if (!name.isBlank() && !sv.existsName(name)) {
                    this.name = name;
                    break;
                }

                out.println("[System]: This name already exists, please insert a new one");
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
}
