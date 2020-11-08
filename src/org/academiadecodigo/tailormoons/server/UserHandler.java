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

        out.println("[System]: Welcome, please enter your name. ");

        while (true) {
            try {
                String name = in.readLine();

                if (name.toLowerCase().equals("francisco")){
                    out.println("Please don't break the game!");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (!checkConnection(name)) {
                    return;
                }

                if (!name.isBlank() && !sv.existsName(name)) {
                    this.name = name;
                    return;
                }

                out.println("[System]: This name already exists or is blank.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void askReady() {

        out.println("[Game]: Are you ready? (y/n) ");

        while (true) {
            try {
                String answer = in.readLine();
                if (!checkConnection(answer)) {
                    return;
                }

                if (answer.toLowerCase().equals("y")) {
                    ready = true;
                    return;
                } else if (answer.toLowerCase().equals("n")) {
                    out.println("[Game]: Don't be a conas. Press y!");
                    continue;
                }

                out.println("[Game]: Invalid input (y/n)");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTheAnswer() throws IOException {

        while (true) {

            String result = in.readLine();

            if (sv.getPlayerNames().contains(result)) {
                System.out.println("accept");
                return result;
            }

            return "voto em branco";
        }
    }

    public boolean checkConnection(String input) {

        if (input == null) {
            sv.broadcast(getName() + " has left the chat \n");
            sv.removeUser(this);

            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        return true;
    }

    public String pickQuestion() throws IOException {
        return in.readLine();
    }

    public void print(String message) {
        out.println(message);
    }


    public void incrementPoints() {
        points++;
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
