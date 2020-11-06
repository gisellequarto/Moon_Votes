package org.academiadecodigo.tailormoons.game;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private File questionsFile = new File("assets/questions.txt");
    private List<String> questions = new LinkedList<>();

    private boolean started = false;

    public Game() {
        init();
    }

    public void init() {

        try {
            FileReader fileReader = new FileReader(questionsFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                questions.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("This file does not exist");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        started = true;
    }

    public boolean hasStarted() {
        return started;
    }
}
