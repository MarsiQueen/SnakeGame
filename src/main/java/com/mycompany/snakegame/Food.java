/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author alu10427472
 */
public class Food {
    
    private int row;
    private int col;

    Food(int numRows, int numCols) {
        generateRandomPosition(numRows, numCols);
    }
    

    public void generateRandomPosition(int numRows, int numCols) {
        Random random = new Random();
        row = random.nextInt(numRows);
        col = random.nextInt(numCols);
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight){
        Color color = Color.BLUE;
        Util.drawSquare(g, row, col, color, squareWidth, squareHeight);
        
        
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    
    
}
