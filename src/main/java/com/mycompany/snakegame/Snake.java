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
 *
 * @author alu10427472
 */
public class Snake {
    
    private List<Node> body;
    private Direction direction;
    private int nodesToGrow;
    private boolean isMoving = true;
    private Board board;

    
    
    public Snake(){
        
        body = new ArrayList<Node>();
        body.add(new Node(Board.NUM_ROWS/2, Board.NUM_COLS/2));
        body.add(new Node(Board.NUM_ROWS/2, Board.NUM_COLS/2 - 1));
        body.add(new Node(Board.NUM_ROWS/2, Board.NUM_COLS/2 - 2));
        body.add(new Node(Board.NUM_ROWS/2, Board.NUM_COLS/2 - 3));
        direction = Direction.RIGHT;
        nodesToGrow = 0;
        this.isMoving = true;
        
    }

    
    
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    

    public void incrementNodesToGrow(int numNodes){
        nodesToGrow += numNodes;
    }
    
    public List<Node> getBody() {
        return body;
    }
    
    
    public void paint(Graphics g, int squareWidth, int squareHeight){
        boolean firstNode = true;
        Color color;
        for(Node node: body) {
            if(firstNode) {
                color = Color.red;
                firstNode = false;
            } else {
                color = Color.yellow;
            }
            Util.drawSquare(g, node.getRow(), node.getCol(), color, squareWidth, squareHeight);
            
        }
    }

    void move() {
        
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
        
        
        
   
    }
    
    
    public void stopMoving(){
        this.isMoving = false;
    }
    
    public void startMoving(){
        this.isMoving = true;
    }
    
   
    
    
    
    
}
