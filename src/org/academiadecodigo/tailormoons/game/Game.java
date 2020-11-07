package org.academiadecodigo.tailormoons.game;

import org.academiadecodigo.tailormoons.server.Server;
import org.academiadecodigo.tailormoons.server.UserHandler;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private File questionsFile = new File("assets/questions.txt");
    private LinkedList<String> questions = new LinkedList<>();


    Server sv;

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
        int rounds = 0;
        while (hasStarted()){

            if(rounds < 5){
                rounds++;

                pickAQuestion();

                getTheAnswer();

                continue;
            }
           // aPlayerMakesTheQuestion();
        }
    }

    private void getTheAnswer() {
        sv.getTheAnswers();
    }

    public boolean hasStarted() {
        return started;
    }

    private void shuffle(){
        Collections.shuffle(questions);
    }

    private void pickAQuestion(){
        sv.broadcast(questions.getFirst());
        questions.poll();
    }
}
