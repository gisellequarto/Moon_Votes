package org.academiadecodigo.tailormoons.server;

import org.academiadecodigo.tailormoons.server.game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private Vector<UserHandler> users = new Vector<>();

    private Vector<String> answers = new Vector<>();
    private Vector<String> playerNames = new Vector<>();

    private ServerSocket serverSocket;
    private Socket client;
    private Game game = new Game(this);

    private int playerCounter;
    private int numberOfPlayers;

    public Server(String port, int numberOfPlayers) throws IOException {
        serverSocket = new ServerSocket(Integer.parseInt(port));
        this.numberOfPlayers = numberOfPlayers;
    }

    public void start() {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        while (!serverSocket.isClosed() && !game.hasStarted()) {

            if (playerCounter < numberOfPlayers) {

                try {
                    client = serverSocket.accept();
                    playerCounter++;

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

    public UserHandler getRandomPlayer() {

        Random rand = new Random();
        UserHandler randomElement = users.get(rand.nextInt(users.size()));

        return randomElement;
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

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

        int i = 0;

        for (UserHandler user : users) {
            counter += user.getPoints();

            if ((i % 4 == 0) && (i != 0)) {
                result.append("_");
            }

            result.append(user.getName()).append(": ").append(user.getPoints()).append(" ");
            i++;
        }

        for (UserHandler user : users) {
            user.setPoints(0);
        }

        nullVotes = answers.size() - counter;

        result.append("Unknown: ").append(nullVotes);
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

    public void removeUser(UserHandler user) {
        users.remove(user);
    }

    public Vector<String> getPlayerNames() {
        return playerNames;
    }
}
