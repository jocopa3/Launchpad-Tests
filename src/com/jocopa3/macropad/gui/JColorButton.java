package com.jocopa3.macropad.gui;

import com.rngtng.launchpad.LColor;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;

public class JColorButton extends JButton implements ActionListener, MouseListener {

    private boolean hovered = false;
    private boolean clicked = false;

    private Color normalColor = null;
    private Color lightColor = null;
    private Color darkColor = null;
    
    public JColorButton() {
        this("", new Color(127, 127, 127));
    }
    
    public JColorButton(String text) {
        this(text, new Color(127, 127, 127));
    }
    
    public JColorButton(String text, Color color) {
        super(text);
        setContentAreaFilled(false);
        
        setForeground(Color.BLACK);

        this.normalColor = color;
        this.lightColor = color.brighter();
        this.darkColor = color.darker();

        addActionListener(this);
        addMouseListener(this);
        setContentAreaFilled(false);
    }

    @Override
    public void setBackground(Color bg) {
        Color oldBg = getBackground();
        super.setBackground(bg);
        if ((oldBg != null) ? !oldBg.equals(bg) : ((bg != null) && !bg.equals(oldBg))) {
            // background already bound in AWT1.2
            this.normalColor = bg;
            this.darkColor = bg.darker();
            this.lightColor = bg.brighter();
            repaint();
        }
    }

    /**
     * Overpainting component, so it can have different colors
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        GradientPaint gp = null;

        if (clicked) {
            gp = new GradientPaint(0, 0, darkColor, 0, getHeight(), darkColor.darker());
        } else if (hovered) {
            gp = new GradientPaint(0, 0, lightColor, 0, getHeight(), lightColor.darker());
        } else {
            gp = new GradientPaint(0, 0, normalColor, 0, getHeight(), normalColor.darker());
        }

        g2d.setPaint(gp);

        // Draws the rounded opaque panel with borders
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // For High quality
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 7, 7);

        g2d.setColor(darkColor.darker().darker());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 7, 7);

        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        hovered = true;
        clicked = false;

        repaint();
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        hovered = false;
        clicked = false;

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        hovered = true;
        clicked = true;

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        hovered = true;
        clicked = false;

        repaint();
    }
}
