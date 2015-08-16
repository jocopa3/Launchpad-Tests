/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macropad.gui;

import com.rngtng.launchpad.LColor;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import macropad.MacroPad;

/**
 *
 * @author Matt
 */
public class LaunchpadGui {

    private JFrame frame;
    private JPanel backgroundPanel, buttonPanel;
    private JButton launchpadButtons[] = new JButton[80];
    private MacroPad pad;

    public LaunchpadGui(MacroPad pad) {
        this.pad = pad;
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init(frame.getContentPane());

        frame.setSize(480, 480);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /*
     * I know I suck at making GUIs with Java's swing library, so don't nag me 
     * for bad practices; I'm too used to making GUIs with OpenGL.
     */
    public void init(Container pane) {
        pane.setLayout(null); // Absolute layout

        // Used purely for looks
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(0, 0, 0));
        backgroundPanel.setSize(480, 480);
        backgroundPanel.setLayout(null); // Oh look, more Absolute layout!
        pane.add(backgroundPanel);

        // Used for looks and to house all the buttons
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 51, 51));
        buttonPanel.setBounds(5, 5, 465, 460);
        buttonPanel.setLayout(null); // And again, Absolute layout!
        backgroundPanel.add(buttonPanel);

        int x = 5, y = 5;
        for (int i = 0; i < 64; i++) {
            launchpadButtons[i] = new LaunchpadButton(i);

            if(i > 0) {
                if((i & 7) == 0) {
                    x = 5;
                    y += 45;
                }else{
                    x += 45;
                }
            }
            launchpadButtons[i].setBackground(new Color(0, 255, 0));
            launchpadButtons[i].setBounds(x, y, 45, 45);
            
            
            
            
            //launchpadButtons[i].addActionListener(new ActionListener() {
            //    public void actionPerformed(ActionEvent evt) {
            //        launchpadButtonPressed(evt);
            //    }
            //});

            launchpadButtons[i].addMouseListener(new MouseAdapter() {
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
                        if (SwingUtilities.isRightMouseButton(evt)) {
                            launchpadButtons[id].setBackground(new Color(255, 255, 0));
                            pad.setColor(id, new LColor(3, 3));
                        } else {
                            launchpadButtons[id].setBackground(new Color(255, 0, 0));
                            pad.setColor(id, new LColor(3, 0));
                        }
                    }
                    mousePressed = false;

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mousePressed = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    mousePressed = true;
                }
            });

            buttonPanel.add(launchpadButtons[i]);
        }
    }

    //public void launchpadButtonPressed(ActionEvent event) {
    //    LaunchpadButton button = (LaunchpadButton) event.getSource();
    //    int buttonID = button.getID();

    //    launchpadButtons[buttonID].setBackground(new Color(255, 255, 0));
    //    buttonPanel.revalidate();
    //    buttonPanel.repaint();

    //    System.out.println(buttonID);
    //}
}


/*
 * This class is only for quickly storing button ID's used in handling events
 */
class LaunchpadButton extends JButton {

    private final int id;

    public LaunchpadButton(int id) {
        super();
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
