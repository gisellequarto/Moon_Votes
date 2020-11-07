package org.academiadecodigo.tailormoons.client;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.client.gui.ServerArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceive implements Runnable {

    private final Socket clientSocket;
    private final BufferedReader in;
    private Text text;
    private ServerArea serverArea;

    public ClientReceive(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            serverArea = new ServerArea();
    }

    @Override
    public void run() {

        while (!clientSocket.isClosed()){
            try {
                String serverMessage = in.readLine();
                serverArea.setServerBox(serverMessage);
                serverArea.showServerBox();

            } catch (IOException e) {
                System.out.println("Connection lost.");
            }
        }
    }
}
