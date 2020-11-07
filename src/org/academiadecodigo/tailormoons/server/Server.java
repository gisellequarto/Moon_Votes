package org.academiadecodigo.tailormoons.server;

import org.academiadecodigo.tailormoons.game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT_NUMBER = 6924;

    private Vector<UserHandler> users = new Vector<>();
    private PrintWriter out;
    private BufferedReader in;

    private Vector<String> answers = new Vector<>();
    private Vector<String> playerNames = new Vector<>();

    private ServerSocket serverSocket;
    private Socket client;
    private Game game = new Game(this);

    private int numberOfPlayers;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);
    }

    public void start() {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        while (!serverSocket.isClosed() && !game.hasStarted()) {

            if (numberOfPlayers < 2) {

                try {
                    client = serverSocket.accept();
                    numberOfPlayers++;

                    UserHandler user = new UserHandler(this, client);
                    users.add(user);
                    executorService.execute(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isAllReady()) {
                if (playerNames.size() != users.size())
                    for (UserHandler user : users) {
                        playerNames.add(user.getName());
                    }
                game.start();
            }
        }
    }

    public void broadcast(String message) {

        for (UserHandler user : users) {
            user.print(message);
        }
    }

    public boolean existsName(String name) {

        for (UserHandler user : users) {
            if (name.equals(user.getName())) {
                return true;
            }
        }
        return false;
    }

    public void getTheAnswers() {

        for (UserHandler user : users) {

            try {
                answers.add(user.getTheAnswer());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        giveResult();
    }

    private void giveResult() {
        int nullVotes = 0;
        int counter = 0;
        StringBuilder result = new StringBuilder();

        for (String answer : answers) {
            for (UserHandler userHandler : users) {
                if (answer.equals(userHandler.getName())) {
                    userHandler.incrementPoints();
                }
            }
        }


        for (UserHandler user : users) {
            counter += user.getPoints();
            result.append(user.getName()).append(": ").append(user.getPoints()).append(" ");
        }

        for (UserHandler user : users) {
            user.setPoints(0);
        }

        nullVotes = answers.size() - counter;

        result.append("Null Votes: ").append(nullVotes);
        answers.clear();
        broadcast(result.toString());


    }

    private boolean isAllReady() {

        for (UserHandler user : users) {
            if (!user.isReady()) {
                return false;
            }
        }

        return true;
    }

    public void removeUser(UserHandler user){
        users.remove(user);
    }

    public Vector<String> getPlayerNames() {
        return playerNames;
    }
}
