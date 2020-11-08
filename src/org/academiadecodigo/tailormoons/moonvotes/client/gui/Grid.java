package org.academiadecodigo.tailormoons.moonvotes.client.gui;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {


    private Rectangle grid = new Rectangle(0, 0, 800, 600);
    private Picture background = new Picture(0, 0, "assets/background.png");
    private UserArea userArea;
    private KeyboardListener keyboardListener;

    public Grid() {
        launchGrid();

        userArea = new UserArea();
        keyboardListener = new KeyboardListener(userArea);
        keyboardListener.init();
    }

    public void launchGrid() {
        grid.setColor(Color.DARK_GRAY);
        grid.fill();
        background.draw();
    }

    public UserArea getUserArea() {
        return userArea;
    }

}

