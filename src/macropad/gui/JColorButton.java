/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macropad.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;

/**
 *
 * @author Matt
 */
public class JColorButton extends JButton {
    
    private Color color;
    
    private JColorButton(String text){
        this(text, new Color(0, 255, 0));
    }
    
    private JColorButton(String text, Color color){
        super(text);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setPaint(color);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();

        super.paintComponent(g);
    }
    
    public void setButtonColor(Color color){
        this.color = color;
    }
    
    public Color getButtonColor(){
        return color;
    }
}
