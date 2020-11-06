package org.academiadecodigo.tailormoons.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceive implements Runnable {

    private final Socket clientSocket;
    private final BufferedReader in;

    public ClientReceive(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {

        while (!clientSocket.isClosed()){
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                System.out.println("Connection lost.");
            }
        }
    }
}
