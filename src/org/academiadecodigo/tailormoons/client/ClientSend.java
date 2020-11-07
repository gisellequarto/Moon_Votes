package org.academiadecodigo.tailormoons.client;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.tailormoons.client.gui.Grid;
import org.academiadecodigo.tailormoons.client.gui.KeyboardListener;
import org.academiadecodigo.tailormoons.client.gui.UserArea;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
                System.out.println("client" + message);
                out.println(message);
                grid.getUserArea().setAnswerSent(false);
            }
        }
    }

}

