package org.academiadecodigo.tailormoons.client.gui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        String name = JOptionPane.showInputDialog("Insert your name:");
        String ready = JOptionPane.showInputDialog("Are you ready? [y/n]");

        Grid grid = new Grid();
        ServerArea serverBox = new ServerArea();
        serverBox.setServerBox("Who would destroy the bootcamp with Ucal?");
        UserArea user = new UserArea();
        KeyboardListener listener = new KeyboardListener(user);
        listener.init();

    }

}
