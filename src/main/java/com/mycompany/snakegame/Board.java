/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snakegame;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author alu10427472
 */
public class Board extends javax.swing.JPanel {

    public static final int NUM_COLS = 30;
    public static final int NUM_ROWS = 40;
    
    private int[][] matrix;
    private int currentRow;
    private int currentCol;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    
    
    
    
    private boolean gamePaused = false;

    private void pauseGame(){
        gamePaused = true;
    }
    
    private void resumeGame() {
        gamePaused = false;
    }
     
    
    
    public void printMatrix(int numRows, int numCols) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println("");
        }
        
    }
    
    /*class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!gamePaused){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (canMove(currentShape, currentRow, currentCol - 1)) {
                            currentCol--;
                    }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (canMove(currentShape, currentRow, currentCol + 1)) {
                            currentCol++;
                    }
                        break;
                    case KeyEvent.VK_UP:
                        if (canMove(snake, currentRow - 1, currentCol)) {
                            currentRow--;
                    }
        
                        break;
                    case KeyEvent.VK_DOWN:
                        if (canMove(currentShape, currentRow + 1, currentCol)) {
                            currentRow++;
                        }
                        //dropShape();
                        break;
                    case KeyEvent.VK_P:
                        if (!gamePaused) {
                            pauseGame();
                        } else {
                            resumeGame();
                        }
                        break;
                    default:
                        break;
                }
                repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                resumeGame();
                repaint();
            }
        }
    } */  

    /**
     * Creates new form Board
     */
    public Board() {
        snake = new Snake();
        food = new Food((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLS));
        specialFood = new SpecialFood((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLS));
        
       
    }
    
    
    
    public int getSquareWidth() {
        return getWidth() / NUM_COLS;
    }
    
    public int getSquareHeigth() {
        return getHeight() / NUM_ROWS;
    }

    
    @Override
    public void paintComponent(Graphics g){
        //super.paint(g);
        if (snake != null) {
            snake.paint(g, getSquareWidth(), getSquareHeigth());
            
        }
        food.paint(g, getSquareWidth(), getSquareHeigth());
        
        specialFood.paint(g, getSquareWidth(), getSquareHeigth());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
