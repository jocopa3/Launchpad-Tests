/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JButton;
import com.jocopa3.macropad.MacroPad;

/**
 *
 * @author Matt
 */
public class LaunchpadButton extends JColorButton {

    private final int id;
    private final MacroPad pad;
    private int hue = 13;
    private LColor color;

    public LaunchpadButton(int id, MacroPad pad) {
        super();

        this.id = id;
        this.pad = pad;
        this.color = new LColor();

        pad.setColor(id, color);
    }

    public int getID() {
        return id;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int h) {
        hue = h;
        color.setHue(h);
        pad.setColor(id, color);
        setBackground(color.asColor());
    }

    public void incrementHue() {
        hue = (hue + 1) % 16;
        color.setHue(hue);
        setBackground(color.asColor());
    }

    public void decrementHue() {
        hue = hue == 0 ? 15 : hue - 1;
        color.setHue(hue);
        setBackground(color.asColor());
    }

    public LColor getColor() {
        return color;
    }

    public void setColor(LColor color) {
        this.color = color;
        setBackground(color.asColor());
        //setForeground(color.asColor());
        //setOpaque(true);
        //setBorderPainted(false);
    }
}
