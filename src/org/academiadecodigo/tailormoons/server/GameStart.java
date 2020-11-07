package org.academiadecodigo.tailormoons.server;

import org.academiadecodigo.tailormoons.game.Game;

public class GameStart implements Runnable {

    private Server sv;
    private Game game;

    public GameStart(Server server, Game game) {

        sv = server;
        this.game = game;
    }

    @Override
    public void run() {

        boolean allReady = false;

        while (!allReady) {
            for (UserHandler user : sv.getUsers()) {
                if (user.isReady()) {
                    allReady = true;
                }

                allReady = false;
            }
        }

        game.start();
    }
}
