package org.academiadecodigo.tailormoons.moonvotes.client.gui;

import org.academiadecodigo.simplegraphics.graphics.Text;

public class ServerArea {

    Text serverBox = new Text(200, 100, "");
    String text;

    public ServerArea() {
    }

    public void showServerBox() {
        serverBox.setFont(20);
        serverBox.draw();
    }

    public void setServerBox(String text) {
        this.text = text;
        serverBox.setText(text);
    }

}
