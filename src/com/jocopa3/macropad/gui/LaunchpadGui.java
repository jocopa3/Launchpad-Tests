/*
 * Not finished.
 */
package com.jocopa3.macropad.gui;

import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import static java.awt.Frame.NORMAL;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.jocopa3.macropad.*;

/**
 *
 * @author Matt
 */
public class LaunchpadGui implements WindowListener {
    private JFrame frame;
    private JPanel backgroundPanel, buttonPanel;
    private LaunchpadButton launchpadButtons[] = new LaunchpadButton[80];
    private MacroPad pad;

    private int width = 640;
    private int height = 640;
    
    public LaunchpadGui(MacroPad pad) {
        this.pad = pad;
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        frame.setSize(width+6, height+46);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        
        init(frame.getContentPane());
        //frame.setResizable(false);
    }

    public void init(Container pane) {
        
        // Used purely for looks
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(0x1E, 0x1E, 0x1E));
        backgroundPanel.setSize(width, height);
        backgroundPanel.setLayout(new BorderLayout()); // Eww, Absolute layout!
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pane.add(backgroundPanel, BorderLayout.CENTER);
        
        // Used for looks and to house all the buttons
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 51, 51));
        buttonPanel.setBounds(5, 5, width-10, height-10);
        
        GridLayout experimentLayout = new GridLayout(9,9);
        experimentLayout.setHgap(5);
        experimentLayout.setVgap(5);
        buttonPanel.setLayout(experimentLayout);
        
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        
        MouseAdapter buttonMouseListener = new MouseAdapter() {
                boolean mousePressed;

                @Override
                public void mousePressed(MouseEvent evt) {
                    int id = ((LaunchpadButton) evt.getComponent()).getID();
                    launchpadButtons[id].getModel().setArmed(true);
                    launchpadButtons[id].getModel().setPressed(true);
                    launchpadButtons[id].requestFocus(); // Fix issues with right-click not focusing
                    mousePressed = true;
                }

                @Override
                public void mouseReleased(MouseEvent evt) {
                    int id = ((LaunchpadButton) evt.getComponent()).getID();
                    launchpadButtons[id].getModel().setArmed(false);
                    launchpadButtons[id].getModel().setPressed(false);
                    
                    if (mousePressed) {
                        //int hue = launchpadButtons[id].getHue();
                        
                        if (SwingUtilities.isRightMouseButton(evt)) {
                            ActionEvent events[] = new ActionEvent[3];
                            events[0] = new ActionEvent(pad.robot, ActionType.KEYBOARD_EVENT, ActionEnums.PRESSED + KeyEvent.VK_A);
                            events[1] = new ActionEvent(pad.robot, ActionType.DELAY_EVENT, 100);
                            events[2] = new ActionEvent(pad.robot, ActionType.KEYBOARD_EVENT, ActionEnums.RELEASED + KeyEvent.VK_A);
                        
                            Action a = new Action(pad.robot, events);
                            ActionPanel panel = new ActionPanel(a);
                            
                            JOptionPane.showMessageDialog(frame, panel, "Edit Action", JOptionPane.PLAIN_MESSAGE);
                            //launchpadButtons[id].decrementHue()
                        } else {
                            //launchpadButtons[id].incrementHue();
                        }
                        
                        LColor col = launchpadButtons[id].getColor();
                        pad.setColor(id, col);
                    }
                    mousePressed = false;

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mousePressed = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    mousePressed = false;
                }
            };
        
        KeyAdapter t;
        
        int x = 5, y = 5;
        for (int i = launchpadButtons.length-8; i < launchpadButtons.length; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(51, 51, 51));
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.setLayout(new GridLayout(1,1));
                
            LaunchpadButton button = new LaunchpadButton(i, pad);
            button.setText(Integer.toString(i));
            button.setBounds(5, 5, 45, 45);
            button.setHue(13);

            if((i & 7) > 0) {
                x += 45;
            }
            
            button.setBounds(5, 5, 45, 45);
            button.addMouseListener(buttonMouseListener);
            
            button.addKeyListener(pad);
            launchpadButtons[i] = button;
            
            panel.add(button);
            buttonPanel.add(panel);
        }
        
        buttonPanel.add(new JLabel());
        
        x = 5; y = 55;
        for (int i = 0; i < launchpadButtons.length-8; i++) {
            int id = i;
            
            if(i%9 == 8)
                id = 64 + i/8 - 1;
            else
                id = i-i/9;
            
            LaunchpadButton button = new LaunchpadButton(id, pad);
            
            
            button.setText(Integer.toString(id));
            button.setHue(13);

            if((i & 7) == 0) {
                x = 5;
                y += 45;
            }else{
                x += 45;
            }
            
            button.setBounds(0, 0, 45, 45);
            button.addMouseListener(buttonMouseListener);
            button.addKeyListener(pad);
            
            if(i%9 == 8){
                JPanel panel = new JPanel();
                panel.setBackground(new Color(51, 51, 51));
                panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                panel.setLayout(new GridLayout(1,1));
                button.setBounds(5, 5, 45, 45);
                panel.add(button);
                buttonPanel.add(panel);
            } else 
                buttonPanel.add(button);
            
            launchpadButtons[id] = button;
        }
        
        pane.validate();
    }

    //public void launchpadButtonPressed(ActionEvent event) {
    //    LaunchpadButton button = (LaunchpadButton) event.getSource();
    //    int buttonID = button.getID();

    //    launchpadButtons[buttonID].setBackground(new Color(255, 255, 0));
    //    buttonPanel.revalidate();
    //    buttonPanel.repaint();

    //    System.out.println(buttonID);
    //}
    
    public LaunchpadButton getButton(int id) {
        return launchpadButtons[id];
    }
    
    public void setButton(int id, LaunchpadButton button) {
        launchpadButtons[id] = button;
    }
    
    public void toFront() {
        frame.setState(NORMAL);
        frame.toFront();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}