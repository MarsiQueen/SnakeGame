/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

/**
 * Representa la serpiente en el juego Snake.
 * Maneja la lógica de movimiento, crecimiento y colisiones.
 * 
 */
public class Snake {
    
    private List<Node> body;
    private Direction direction;
    private int nodesToGrow;
    private boolean isMoving;
    private Direction currentDirection;
    private Direction desiredDirection;
    
    /**
     * Crea una nueva instancia de Snake con un tamaño inicial y una dirección.
     */
    public Snake() {
        body = new ArrayList<>();
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 1));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 2));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 3));
        direction = Direction.RIGHT;
        nodesToGrow = 0;
        isMoving = true;
    }

    /**
     * Obtiene la dirección actual de la serpiente.
     * 
     * @return la dirección actual
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Establece una nueva dirección para la serpiente si no es la opuesta a la actual.
     * 
     * @param direction la nueva dirección
     */
    public void setDirection(Direction direction) {
        if ((this.direction == Direction.UP && direction != Direction.DOWN) ||
            (this.direction == Direction.DOWN && direction != Direction.UP) ||
            (this.direction == Direction.RIGHT && direction != Direction.LEFT) ||
            (this.direction == Direction.LEFT && direction != Direction.RIGHT)) {
            this.direction = direction;
            this.desiredDirection = direction;
        }
    }

    /**
     * Incrementa el número de nodos que la serpiente debe crecer.
     * 
     * @param numNodes número de nodos a crecer
     */
    public void incrementNodesToGrow(int numNodes) {
        nodesToGrow += numNodes;
    }

    /**
     * Obtiene el cuerpo de la serpiente.
     * 
     * @return la lista de nodos que componen el cuerpo de la serpiente
     */
    public List<Node> getBody() {
        return body;
    }

    /**
     * Dibuja la serpiente en el componente gráfico especificado.
     * 
     * @param g el contexto gráfico
     * @param squareWidth el ancho de cada cuadrado
     * @param squareHeight la altura de cada cuadrado
     */
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        boolean firstNode = true;
        for (Node node : body) {
            Color color = firstNode ? Color.RED : Color.YELLOW;
            Util.drawSquare(g, node.getRow(), node.getCol(), color, squareWidth, squareHeight);
            firstNode = false;
        }
    }

    /**
     * Mueve la serpiente en la dirección actual.
     */
    public void move() {
        Node head = body.get(0);
        int newRow = head.getRow();
        int newCol = head.getCol();
        
        switch (direction) {
            case UP:
                newRow--;
                break;
            case DOWN:
                newRow++;
                break;
            case RIGHT:
                newCol++;
                break;
            case LEFT:
                newCol--;
                break;
            default:
                throw new AssertionError();
        }

        Node newHead = new Node(newRow, newCol);
        body.add(0, newHead);
        
        if (nodesToGrow == 0) {
            body.remove(body.size() - 1);
        } else {
            nodesToGrow--;
        }
        
        if (desiredDirection != null) {
            currentDirection = desiredDirection;
            desiredDirection = null;
        }
    }

    /**
     * Detiene el movimiento de la serpiente.
     */
    public void stopMoving() {
        this.isMoving = false;
    }

    /**
     * Inicia el movimiento de la serpiente.
     */
    public void startMoving() {
        this.isMoving = true;
    }

   /**
    * Comprueba si la cabeza de la serpiente está en los límites del área de juego o ha salido de ellos.
    * @param numRows El número total de filas en el área de juego.
    * @param numCols El número total de columnas en el área de juego.
    * @return True si la cabeza de la serpiente está en los límites o ha salido de ellos, false en caso contrario.
    */
    public boolean isAtBoundary(int numRows, int numCols) {
        // Obtiene la posición de la cabeza de la serpiente
        Node head = body.get(0);
        int headRow = head.getRow(); // Coordenada Y de la cabeza
        int headCol = head.getCol(); // Coordenada X de la cabeza
    
        // Comprueba si la cabeza está en los límites o ha salido de ellos
        // Si cualquiera de estas condiciones se cumple, significa que la serpiente está en el límite del área de juego o ha salido de él
        return headRow <= 0 || headRow >= numRows + 4 || headCol <= 0 || headCol >= numCols;
    }


    /**
     * Verifica si la serpiente ha colisionado consigo misma.
     * 
     * @return true si hay colisión, false en caso contrario
     */
    public boolean isSelfCollision() {
        Node head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            Node node = body.get(i);
            if (head.getRow() == node.getRow() && head.getCol() == node.getCol()) {
                return true;
            }
        }
        return false;
    }
}
