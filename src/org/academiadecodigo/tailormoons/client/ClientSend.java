package org.academiadecodigo.tailormoons.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend implements Runnable {

    private final Socket clientSocket;
    private PrintWriter out;

    public ClientSend(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {

        Scanner in = new Scanner(System.in);

        while (!clientSocket.isClosed()){
            String message = in.nextLine();
            out.println(message);
        }
    }
}
