package org.academiadecodigo.tailormoons.game;

import org.academiadecodigo.tailormoons.server.Server;
import org.academiadecodigo.tailormoons.server.UserHandler;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class Game {

    private File questionsFile = new File("assets/questions.txt");
    private LinkedList<String> questions = new LinkedList<>();

    private Server sv;
    private boolean started = false;

    public Game(Server sv) {

        this.sv = sv;
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

        shuffle();

        started = true;
        int rounds = 1;

        while (hasStarted()) {
            if (!isMultipleof5(rounds)) {
                rounds++;
                pickAQuestion();
                getTheAnswer();
                continue;
            }

            rounds++;

            aPlayerMakesTheQuestion();
            getTheAnswer();
        }

    }

    private void aPlayerMakesTheQuestion() {
        UserHandler player = sv.getRandomPlayer();
        player.print("Its your turn to pick a Question!");
        try {
            sv.broadcast(player.pickQuestion());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isMultipleof5(int n) {

        while (n > 0) {
            n = n - 5;
        }

        return n == 0;
    }

    private void getTheAnswer() {
        sv.getTheAnswers();
    }

    public boolean hasStarted() {
        return started;
    }

    private void shuffle() {
        Collections.shuffle(questions);
    }

    private void pickAQuestion() {

        sv.broadcast(questions.getFirst());
        questions.poll();
    }
}
