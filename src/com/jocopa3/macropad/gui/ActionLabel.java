/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocopa3.macropad.gui;

import javax.swing.JLabel;
import com.jocopa3.macropad.ActionEvent;

/**
 *
 * @author Matt
 */
public class ActionLabel extends JLabel {
    private final ActionEvent action;
    
    public ActionLabel(ActionEvent event) {
        this.action = event;
    }
    
    @Override
    public String getText() {
        return action.toString();
    }
    
    @Override
    public void setText(String text) {
        return;
    }
}
