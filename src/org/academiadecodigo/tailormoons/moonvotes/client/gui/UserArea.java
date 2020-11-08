package org.academiadecodigo.tailormoons.moonvotes.client.gui;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public class UserArea {


    private Text userBox = new Text(180, 450, "");
    private StringBuilder builder;
    private boolean answerSent;

    public UserArea() {
        builder = new StringBuilder();
        showUserBox();
    }

    public void showUserBox() {
        userBox.setFont(40);
        userBox.draw();
    }

    public void setText(String character) {
        builder.append(character);
        userBox.setText(builder.toString());
        showUserBox();
    }

    public void eraseText() {
        if (builder.length() <= 0) {
            return;
        }
        int last = (builder.length() - 1);
        builder.deleteCharAt(last);
        userBox.setText(builder.toString());
        showUserBox();
    }

    public String getAnswer(){
        String answer = builder.toString();
        builder.delete(0, builder.length());
        setText("");
        return answer;
    }

    public void setAnswerSent(boolean answerSent) {
        this.answerSent = answerSent;
    }

    public boolean answerSent(){
        return answerSent;
    }

    public void pressed(int key) {
        switch (key) {
            case KeyboardEvent.KEY_SPACE:
                setText(" ");
                break;
            case KeyboardEvent.KEY_BACKSPACE:
                eraseText();
                break;
            case KeyboardEvent.KEY_ENTER:
                answerSent = true;
                break;
            case KeyboardEvent.KEY_A:
                setText("a");
                break;
            case KeyboardEvent.KEY_B:
                setText("b");
                break;
            case KeyboardEvent.KEY_C:
                setText("c");
                break;
            case KeyboardEvent.KEY_D:
                setText("d");
                break;
            case KeyboardEvent.KEY_E:
                setText("e");
                break;
            case KeyboardEvent.KEY_F:
                setText("f");
                break;
            case KeyboardEvent.KEY_G:
                setText("g");
                break;
            case KeyboardEvent.KEY_H:
                setText("h");
                break;
            case KeyboardEvent.KEY_I:
                setText("i");
                break;
            case KeyboardEvent.KEY_J:
                setText("j");
                break;
            case KeyboardEvent.KEY_K:
                setText("k");
                break;
            case KeyboardEvent.KEY_L:
                setText("l");
                break;
            case KeyboardEvent.KEY_M:
                setText("m");
                break;
            case KeyboardEvent.KEY_N:
                setText("n");
                break;
            case KeyboardEvent.KEY_O:
                setText("o");
                break;
            case KeyboardEvent.KEY_P:
                setText("p");
                break;
            case KeyboardEvent.KEY_Q:
                setText("q");
                break;
            case KeyboardEvent.KEY_R:
                setText("r");
                break;
            case KeyboardEvent.KEY_S:
                setText("s");
                break;
            case KeyboardEvent.KEY_T:
                setText("t");
                break;
            case KeyboardEvent.KEY_U:
                setText("u");
                break;
            case KeyboardEvent.KEY_V:
                setText("v");
                break;
            case KeyboardEvent.KEY_W:
                setText("w");
                break;
            case KeyboardEvent.KEY_X:
                setText("x");
                break;
            case KeyboardEvent.KEY_Y:
                setText("y");
                break;
            case KeyboardEvent.KEY_Z:
                setText("z");
                break;
        }
    }
}
