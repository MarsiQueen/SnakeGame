/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snakegame;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * Panel principal del juego Snake.
 * Administra la lógica del juego, dibuja la serpiente y los alimentos, y gestiona la entrada del usuario.
 */
public class Board extends javax.swing.JPanel {

    public static final int NUM_COLS = 30;
    public static final int NUM_ROWS = 40;
    private static final int SPECIAL_FOOD_INTERVAL = 30;
    
    private int[][] matrix;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    private Timer gameTimer;
    private TimerInterface timerInterface;
    private boolean firstTime = true;
    private int timerCount = 0;
    private Game game;
    private boolean gamePaused = false;
    
    

    
    /**
     * Constructor del panel del juego.
     * Inicializa la serpiente, los alimentos, y configura el temporizador del juego.
     */
    public Board() {
        initializeGameElements();
        configureKeyAdapter();
        configureTimers();
        initializeGame();
    }
    
    
     /**
     * Inicializa los elementos del juego.
     */
    private void initializeGameElements() {
        snake = new Snake();
        food = new Food((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLS));
        specialFood = new SpecialFood((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLS));
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
    }
    
    
    /**
     * Configura el adaptador de teclas para controlar la dirección de la serpiente.
     */
    private void configureKeyAdapter() {
        addKeyListener(new MyKeyAdapter());
    }

     /**
     * Configura los temporizadores del juego.
     */
    private void configureTimers() {
        gameTimer = new Timer(200, e -> moveSnake());
        gameTimer.start();
    }
    
    
    /**
     * Asigna el objeto TimerInterface.
     * @param timerInterface El objeto TimerInterface a asignar.
     */
    public void setTimerInterface(TimerInterface timerInterface) {
        this.timerInterface = timerInterface;
    }
    
    /**
     * Inicializa el juego y resetea los contadores y el temporizador.
     */
    private void initializeGame() {
        if (firstTime && timerInterface != null) {
        timerInterface.reset();
        timerInterface.start();
        firstTime = false;
    }
    repaint();
}
    
    
    /**
     * Pausa el juego.
     */
    private void pauseGame() {
        gamePaused = true;
    }

    /**
     * Reanuda el juego.
     */
    private void resumeGame() {
        gamePaused = false;
    }

    /**
     * Asigna el objeto Game.
     * @param game El objeto Game a asignar.
     */
    public void setGame(Game game) {
        this.game = game;
    }
     
    
    
     /**
     * Mueve la serpiente, verifica colisiones y redibuja el tablero.
     */
    private void moveSnake() {
        if (!gamePaused) {
            checkFoodCollision();
            if (snake.isAtBoundary(NUM_ROWS, NUM_COLS) || snake.isSelfCollision()) {
                handleGameOver();
            } else {
                snake.move();
                repaint();
            }
        }
    }
    
    
    /**
     * Verifica las colisiones de la serpiente con la comida.
     */
    private void checkFoodCollision() {
        if (timerCount == SPECIAL_FOOD_INTERVAL) {
            specialFood.setPresent(true);
            specialFood.respawn(NUM_ROWS, NUM_COLS);
            timerCount = 0;
        }

        Node head = snake.getBody().get(0);
        if (head.getRow() == food.getRow() && head.getCol() == food.getCol()) {
            food.generateRandomPosition(NUM_ROWS, NUM_COLS);
            snake.incrementNodesToGrow(1);
        }
        if (specialFood != null && specialFood.isPresent() && head.getRow() == specialFood.getRow() && head.getCol() == specialFood.getCol()) {
            specialFood.setPresent(false);
            timerCount = 0;
            snake.incrementNodesToGrow(3);
        }

        timerCount++;
    }
    
    
    /**
     * Maneja la lógica de fin del juego.
     */
    private void handleGameOver() {
        snake.stopMoving();
        timerCount = 0;
        timerInterface.stop();
        int option = JOptionPane.showOptionDialog(null, "¡HAS PERDIDO!", "Fin del Juego", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Reiniciar", "Salir"}, "Reiniciar");

        if (option == 0) {
            resetGame();
            game.resetTime();
        } else {
            System.exit(0);
        }
    }
    
    
        /**
     * Reinicia el juego reseteando la serpiente y los alimentos.
     */
    void resetGame() {
        snake = new Snake();
        food.generateRandomPosition(NUM_ROWS, NUM_COLS);
        specialFood.respawn(NUM_ROWS, NUM_COLS);
        repaint();
    }

    
     /**
     * Devuelve el ancho de cada celda del tablero.
     * @return El ancho de la celda.
     */
    public int getSquareWidth() {
        return getWidth() / NUM_COLS;
    }

    /**
     * Devuelve la altura de cada celda del tablero.
     * @return La altura de la celda.
     */
    public int getSquareHeight() {
        return getHeight() / NUM_ROWS;
    }
    
    /**
     * Pinta los elementos del juego en el tablero.
     * @param g El contexto gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (snake != null) {
            snake.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (food != null) {
            food.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (specialFood != null) {
            specialFood.paint(g, getSquareWidth(), getSquareHeight());
        }
        Toolkit.getDefaultToolkit().sync(); // Para evitar que vaya a saltos el snake
    }
    
    /**
     * Adaptador de teclas para controlar la dirección de la serpiente.
     */
    private class MyKeyAdapter extends KeyAdapter {
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
