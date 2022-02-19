package ui;

import javax.swing.JButton;

public class ChessFigureButton extends JButton {
    private int position;

    public ChessFigureButton(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }
}
