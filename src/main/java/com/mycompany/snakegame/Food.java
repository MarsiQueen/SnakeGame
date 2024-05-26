package com.mycompany.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Clase que representa la comida en el juego Snake.
 * La comida aparece en posiciones aleatorias dentro de la matriz del tablero.
 * Se dibuja en el tablero con un color específico.
 */
public class Food {
    
    private int row;
    private int col;

    /**
     * Constructor que inicializa la posición de la comida en una posición aleatoria.
     *
     * @param numRows El número de filas del tablero.
     * @param numCols El número de columnas del tablero.
     */
    public Food(int numRows, int numCols) {
        generateRandomPosition(numRows, numCols);
    }

    /**
     * Genera una posición aleatoria para la comida dentro de los límites del tablero.
     *
     * @param numRows El número de filas del tablero.
     * @param numCols El número de columnas del tablero.
     */
    public void generateRandomPosition(int numRows, int numCols) {
        Random random = new Random();
        row = random.nextInt(numRows);
        col = random.nextInt(numCols);
    }

    /**
     * Dibuja la comida en el tablero.
     *
     * @param g El objeto Graphics utilizado para el dibujo.
     * @param squareWidth El ancho de cada celda del tablero.
     * @param squareHeight El alto de cada celda del tablero.
     */
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        Color color = Color.BLUE;
        Util.drawSquare(g, row, col, color, squareWidth, squareHeight);
    }

    /**
     * Obtiene la fila actual de la comida.
     *
     * @return La fila actual de la comida.
     */
    public int getRow() {
        return row;
    }

    /**
     * Obtiene la columna actual de la comida.
     *
     * @return La columna actual de la comida.
     */
    public int getCol() {
        return col;
    }
}
