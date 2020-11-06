package org.academiadecodigo.tailormoons.client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket clientSocket;

    public Client(String serverAddress, int serverPort) throws IOException {
        this.clientSocket = new Socket(serverAddress, serverPort);
    }

    public void start() throws IOException {
        Thread clientSend = new Thread(new ClientSend(clientSocket));
        Thread clientReceive = new Thread(new ClientReceive(clientSocket));

        clientSend.start();
        clientReceive.start();
    }
}
