/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snakegame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alu10427472
 */
public class Util {

    /**
     * Dibuja un cuadrado en el contexto gráfico proporcionado.
     * 
     * @param g            El objeto Graphics en el que se dibujará el cuadrado.
     * @param row          La fila en la que se ubicará el cuadrado.
     * @param col          La columna en la que se ubicará el cuadrado.
     * @param color        El color con el que se dibujará el cuadrado.
     * @param squareWidth  El ancho del cuadrado.
     * @param squareHeight La altura del cuadrado.
     */
    public static void drawSquare(Graphics g, int row, int col, Color color,
            int squareWidth, int squareHeight) {
        
        // Calcula las coordenadas de inicio del cuadrado
        int x = col * squareWidth;
        int y = row * squareHeight;

        // Establece el color del cuadrado
        g.setColor(color);

        // Dibuja el cuadrado
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);

        // Establece el color para los bordes más claros
        g.setColor(color.brighter());

        // Dibuja las líneas superiores e izquierdas del borde del cuadrado
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);

        // Establece el color para los bordes más oscuros
        g.setColor(color.darker());

        // Dibuja las líneas inferiores y derechas del borde del cuadrado
        g.drawLine(x + 1, y + squareHeight - 1, x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1, y + squareHeight - 1, x + squareWidth - 1, y + 1);
    }
}

