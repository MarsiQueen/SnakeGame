package com.mycompany.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SpecialFood {
    private int row;
    private int col;
    private boolean present;

    public SpecialFood(int numRows, int numCols) {
        this.present = true;
        respawn(numRows, numCols);
    }

    public void respawn(int numRows, int numCols) {
        if (present) {
            Random random = new Random();
            row = random.nextInt(numRows);
            col = random.nextInt(numCols);
        }
    }

    public void paint(Graphics g, int squareWidth, int squareHeight) {
        if (present) {
            Color color = Color.GREEN;
            Util.drawSquare(g, row, col, color, squareWidth, squareHeight);
        }
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
