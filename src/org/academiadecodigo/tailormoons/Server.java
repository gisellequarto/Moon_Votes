package org.academiadecodigo.tailormoons;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT_NUMBER = 6924;
    private ServerSocket serverSocket;
    private Socket client;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);
    }

    public void start() {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        while (!serverSocket.isClosed()) {

            UserHandler user = new UserHandler();
        }
    }
}
