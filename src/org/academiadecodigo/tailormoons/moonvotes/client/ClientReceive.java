package org.academiadecodigo.tailormoons.moonvotes.client;

import org.academiadecodigo.tailormoons.moonvotes.client.gui.ServerArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceive implements Runnable {

    private final Socket clientSocket;
    private final BufferedReader in;
    private ServerArea serverArea;

    public ClientReceive(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        serverArea = new ServerArea();
    }

    @Override
    public void run() {

        while (!clientSocket.isClosed()) {
            try {
                String serverMessage = in.readLine();
                if (serverMessage.contains("_")) {
                    serverArea.setServerBoxDouble(serverMessage);
                    continue;
                }
                serverArea.setServerBox2();
                serverArea.setServerBox(serverMessage);
                serverArea.showServerBox();

            } catch (IOException e) {
                System.out.println("Connection lost.");
            }
        }
    }
}