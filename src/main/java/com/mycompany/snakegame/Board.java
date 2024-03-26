/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snakegame;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author alu10427472
 */
public class Board extends javax.swing.JPanel {

    public static final int NUM_COLS = 30;
    public static final int NUM_ROWS = 40;
    
    private static final int SPECIAL_FOOD_INTERVAL = 10000;
    
    private int[][] matrix;
    private int currentRow;
    private int currentCol;
    private Snake snake;
    private KeyAdapter keyAdapter;
    private Food food;
    private SpecialFood specialFood;
    private Timer time;
    private Node node;
    private Timer specialFoodTimer;
    
    
    
    
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
    
    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        snake.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.setDirection(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        snake.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.setDirection(Direction.DOWN);
                        break;
                    default:
                        break;
                }
                repaint();
                
            
        }
    }
    
    public void moveSnake(){
        checkFoodCollision();
        
        if (snake.isAtBoundary(NUM_ROWS, NUM_COLS)){
            snake.stopMoving();
            return;
        }
        
        snake.move();
        
    }
    
    public void checkFoodCollision(){
        Node head = snake.getBody().get(0);
        if (head.getRow() == food.getRow() && head.getCol() == food.getCol()) {
            food.generateRandomPosition(NUM_ROWS, NUM_COLS);
            snake.incrementNodesToGrow(1);
        }
        if (head.getRow() == specialFood.getRow() && head.getCol() == specialFood.getCol()){
            specialFood.generateRandomPosition(NUM_ROWS, NUM_COLS);
            snake.incrementNodesToGrow(3);
        }
    }
    
    /*public boolean canMove(Snake snake, int row, int col) {
        Node head = snake.getBody().get(0);
        int headRow = head.getRow();
        int headCol = head.getCol();
        
        if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLS)  {
            System.out.println("X");
            return false;
            
        }
        
        /*if (Node node : snake.getBody()) {
            if (node.getRow() == row && node.getCol() == col) {
            return false;
            }
        }*/
        

    
        
        /*return true;
    }*/
    
    
    /*public void checkCollision(){
        Node snakeHead = snake.getBody().get(0);
        if (snakeHead.getRow() < 0 || snakeHead.getRow() >= NUM_ROWS ||
                snakeHead.getCol() < 0 || snakeHead.getCol() >= NUM_COLS){
            snake.stopMoving();
        }
    }*/
    
    

    /**
     * Creates new form Board
     */
    public Board() {
        snake = new Snake();
        food = new Food((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLS));
        specialFood = new SpecialFood((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLS));
        keyAdapter = new MyKeyAdapter();
        setFocusable(true);
        addKeyListener(keyAdapter);
        time = new Timer(200, e -> {
           //snake.move();
           moveSnake();
           repaint();
        });
        time.start();
        specialFoodTimer = new Timer(SPECIAL_FOOD_INTERVAL, e -> {
            if (specialFood == null){
                specialFood.generateRandomPosition(NUM_ROWS, NUM_COLS);
                repaint();
            }
        });
        specialFoodTimer.start();
        initGame();
        
        repaint();
       
    }
    
    
    
    public int getSquareWidth() {
        return getWidth() / NUM_COLS;
    }
    
    public int getSquareHeigth() {
        return getHeight() / NUM_ROWS;
    }

    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (snake != null) {
            snake.paint(g, getSquareWidth(), getSquareHeigth());
            
        }
        food.paint(g, getSquareWidth(), getSquareHeigth());
        
        specialFood.paint(g, getSquareWidth(), getSquareHeigth());
        
        
        Toolkit.getDefaultToolkit().sync(); //Para evitar que vaya a saltos el snake
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

    private void initGame() {

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
