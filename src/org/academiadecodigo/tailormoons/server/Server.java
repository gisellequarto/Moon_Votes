package org.academiadecodigo.tailormoons.server;

import org.academiadecodigo.tailormoons.game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT_NUMBER = 6924;

    private Vector<UserHandler> users = new Vector<>();
    private PrintWriter out;
    private BufferedReader in;

    private ServerSocket serverSocket;
    private Socket client;
    private Game game = new Game();

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);
    }

    public void start() {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        Thread readyChecker = new Thread(new GameStart(this, game));

        while (!serverSocket.isClosed() && !game.hasStarted()) {
            try {
                client = serverSocket.accept();

                UserHandler user = new UserHandler(this, client);
                users.add(user);
                executorService.execute(user);
            } catch (IOException e) {
                e.printStackTrace();
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

    private boolean isAllReady() {

        for (UserHandler user : users) {
            if (!user.isReady()) {
                return false;
            }
        }

        return true;
    }

    public Vector<UserHandler> getUsers() {
        return users;
    }
}
