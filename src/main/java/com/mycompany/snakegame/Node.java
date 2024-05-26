/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snakegame;

/**
 * Representa un nodo en el tablero del juego Snake.
 * Un nodo se define por sus coordenadas de fila y columna.
 * Se utiliza para representar partes del cuerpo de la serpiente y la posición de la comida.
 * 
 */
public class Node {
    private int row;
    private int col;

    /**
     * Crea una nueva instancia de Node con las coordenadas especificadas.
     * 
     * @param row la fila del nodo
     * @param col la columna del nodo
     */
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Obtiene la fila del nodo.
     * 
     * @return la fila del nodo
     */
    public int getRow() {
        return row;
    }

    /**
     * Obtiene la columna del nodo.
     * 
     * @return la columna del nodo
     */
    public int getCol() {
        return col;
    }

    /**
     * Establece la fila del nodo.
     * 
     * @param row la nueva fila del nodo
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Establece la columna del nodo.
     * 
     * @param col la nueva columna del nodo
     */
    public void setCol(int col) {
        this.col = col;
    }
}
