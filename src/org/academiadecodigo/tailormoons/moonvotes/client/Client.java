package org.academiadecodigo.tailormoons.moonvotes.client;

import org.academiadecodigo.tailormoons.moonvotes.client.gui.Grid;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private Grid grid;

    public Client(String serverAddress, int serverPort) throws IOException {
        this.clientSocket = new Socket(serverAddress, serverPort);
        grid = new Grid();
    }

    public void start() throws IOException {


        Thread clientSend = new Thread(new ClientSend(clientSocket, grid));
        Thread clientReceive = new Thread(new ClientReceive(clientSocket));

        grid.launchGrid();

        clientSend.start();
        clientReceive.start();
    }
}
