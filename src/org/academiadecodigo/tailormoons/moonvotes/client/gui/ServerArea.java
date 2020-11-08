package org.academiadecodigo.tailormoons.moonvotes.client.gui;

import org.academiadecodigo.simplegraphics.graphics.Text;

public class ServerArea {

    private Text serverBox = new Text(200, 100, "");
    private Text serverBox2 = new Text(200, 140, "");
    private String text;
    private String text2;

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

    public void setServerBox2(){
        serverBox2.setText("");
    }

    public void setServerBoxDouble(String t){

        String[] lines = t.split("_");
        text = lines[0];
        text2 = lines[1];
        serverBox.setText(text);
        serverBox.setFont(20);
        serverBox.draw();
        serverBox2.setText(text2);
        serverBox2.setFont(20);
        serverBox2.draw();
    }

}
