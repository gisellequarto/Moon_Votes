package org.academiadecodigo.tailormoons.moonvotes.client;

import org.academiadecodigo.tailormoons.moonvotes.client.gui.Grid;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSend implements Runnable {

    private final Socket clientSocket;
    private PrintWriter out;
    private Grid grid;

    public ClientSend(Socket clientSocket, Grid grid) throws IOException {
        this.clientSocket = clientSocket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.grid = grid;
    }

    @Override
    public void run() {

        while (!clientSocket.isClosed()) {

            if (grid.getUserArea().answerSent()) {
                String message = grid.getUserArea().getAnswer();
                out.println(message);
                grid.getUserArea().setAnswerSent(false);
            }
        }
    }

}

