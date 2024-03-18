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
public class SpecialFood {
    private int row;
    private int col;
    
    SpecialFood(int numRows, int numCols) {
        generateRandomPosition(numRows, numCols);
    }
    

    private void generateRandomPosition(int numRows, int numCols) {
        Random random = new Random();
        row = random.nextInt(numRows);
        col = random.nextInt(numCols);
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight){
        Color color = Color.GREEN;
        Util.drawSquare(g, row, col, color, squareWidth, squareHeight);
        
        
    }
}
