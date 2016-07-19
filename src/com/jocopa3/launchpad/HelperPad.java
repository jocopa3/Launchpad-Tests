/*
 * Don't use this class. Only left it here because I'm scrapping portions of code.
 */

package com.jocopa3.launchpad;

import com.jocopa3.macropad.Action;
import com.rngtng.launchpad.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jocopa3
 */
public class HelperPad implements LaunchpadListener, KeyListener {
    
    private boolean exiting = false;
    private boolean sleeping = false;
    private Launchpad device;

    private boolean hold[] = new boolean[80];
    private long held[] = new long[80];
    
    private Action actions[] = new Action[80];
    private LColor tempColors[] = new LColor[80];
    
    private int powerButton = 0;
    
    private final Renderer renderer;
    
    public int frameCount = 0;
    
    private Robot robot;
    
    public HelperPad(Launchpad device){
        this.device = device;
        device.addListener(this);
        renderer = new ButtonRenderer(device);
        
        try{
            robot = new Robot();
        }catch(AWTException e){
            e.printStackTrace();
            System.exit(-1);
        }
        
        addAction(new Action(robot), new LColor(3, 0), 72); // Blank action for power button

        setupCSGOProfile();
        //setupGeneralUseProfile();
        
        //addAction(new Action(robot, new int[]{KeyEvent.))
                                             
        clearAll();
        setupMenu();  
        loop();
    }
    
    private void setupCSGOProfile(){
        // Setup for CS:GO
//        addAction(new Action(robot, new int[]{KeyEvent.VK_W}, false), new LColor(0, 3), 2, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_A}, false), new LColor(0, 3), 1, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_S}, false), new LColor(0, 3), 2, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_D}, false), new LColor(0, 3), 3, 3);
//        
//        addAction(new Action(robot, new int[]{KeyEvent.VK_R}, false), new LColor(0, 1), 3, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_SPACE}, false), new LColor(3, 3), 4, 5);
//        
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL}, false), new LColor(3, 3), 0, 4);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_G}, false), new LColor(3, 0), 0, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_B}, false), new LColor(3, 0), 3, 4);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_E}, false), new LColor(3, 3), 4, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_3}, false), new LColor(3, 3), 1, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_ESCAPE}, false), new LColor(3, 0), 5, 5);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_F3}, false), new LColor(3, 0), 0, 5);
    }
    
    private void setupGeneralUseProfile(){
//        addAction(new Action(robot, new int[]{KeyEvent.BUTTON3_MASK}, true), new LColor(3, 3), 5, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_C}), new LColor(1, 3), 2, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_V}), new LColor(1, 3), 4, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_A}), new LColor(3, 1), 1, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_D}), new LColor(3, 1), 0, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_X}), new LColor(3, 0), 3, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_Z}), new LColor(3, 0), 2, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_Y}), new LColor(3, 0), 3, 3);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT}), new LColor(3, 0), 7, 0);
//        
//        addAction(new Action(robot, new int[]{KeyEvent.VK_SHIFT}), new LColor(3, 3), 1, 1);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_UP}), new LColor(0, 3), 3, 0);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_DOWN}), new LColor(0, 3), 3, 1);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_LEFT}), new LColor(0, 3), 2, 1);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_RIGHT}), new LColor(0, 3), 4, 1);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL}), new LColor(3, 3), 5, 1);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_PAGE_UP}), new LColor(3, 0), 4, 0);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_PAGE_DOWN}), new LColor(3, 0), 2, 0);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_ENTER}), new LColor(3, 3), 1, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_DELETE}), new LColor(3, 0), 0, 2);
//        addAction(new Action(robot, new int[]{KeyEvent.VK_BACK_SPACE}), new LColor(3, 0), 5, 2);
    }
    
    public void loop(){
        while(!exiting){
            if(held[72] >= 0 && System.currentTimeMillis() - held[72] > 1000){
                exiting = true;
                break;
            }
           
            if(!sleeping){
                render();    
            }else{
                if(frameCount%100 == 0){
                    renderer.setButton(72, new LColor(3, 0));
                }else if(frameCount%101 == 0){
                    renderer.setButton(72, new LColor(0, 0));
                }
            }
           
            frameCount++;
            delay(0.01);
        }
        outro();
        device.dispose();
        System.exit(0);
    }
    
    public void render(){
        
    }
    
    public void setupMenu(){
        //l.setMode(LColor.FLASHING);
        renderer.setButton(powerButton, new LColor(3,0));
        hold[72] = true;
    }
    
    public void outro(){
        LColor scene[] = new LColor[80];
        for(int i = 0; i < 80; i++)
            scene[i] = new LColor(0,0);
        
        for(int t = 0; t < 8; t++){
            for(int i = 0; i < 8; i++)
                for(int j = 0; j < 8; j++)
                    //device.changeGrid(i, j, new LColor(i==t?3:0,j==t?3:0));
                    scene[i+j*8].setRedGreen(i==t?3:0,j==t?3:0);
            
            device.changeAll(scene);
            delay(0.01);
        }
        delay(0.05);
    }
    
    public void clearAll(){
        for(int i = 0; i < 80; i++){
            hold[i] = false;
            held[i] = -1;
        }
    }
    
    public static void delay(int ms){
        try{
            Thread.sleep(ms);
        }catch(java.lang.InterruptedException e){
            System.out.println(e);
        }
    }
    
    public static void delay(double sec){
        try{
            Thread.sleep((long)(sec*1000));
        }catch(java.lang.InterruptedException e){
            System.out.println(e);
        }
    }
    
    private void addAction(Action a, LColor color, int button){
        actions[button] = a;
        renderer.set(button, color);
    }
    
    private void addAction(Action a, LColor color, int x, int y){
        actions[x+(y<<3)] = a;
        renderer.setGrid(x, y, color);
    }
    
    @Override
    public void launchpadGridPressed(int x, int y) {
        if(exiting||actions[x+(y<<3)] == null)
            return;
        
//        LColor c = renderer.getGrid(x, y);
//        tempColors[x+(y<<3)] = c;
//        c.setRedGreen(c.getRed() > 0 ? c.getRed()-1 : c.getRed(), 
//                      c.getGreen() > 0 ? c.getGreen()-1 : c.getGreen());
//        renderer.setGrid(x, y, c);
        
        //actions[x+(y<<3)].holdAction();
        held[x+(y<<3)] = System.currentTimeMillis();
        
        // Inefficient, but meh.
        LColor c = renderer.getGrid(x, y);
        c.darken();
        renderer.setGrid(x, y, c);
    }

    @Override
    public void launchpadGridReleased(int x, int y) {
        if(exiting||actions[x+(y<<3)] == null)
            return;
        
//        LColor c = tempColors[x+(y<<3)];
//        renderer.setGrid(x, y, c);
        
        //actions[x+(y<<3)].releaseAction();     
        held[x+(y<<3)] = -1;
        
        // Inefficient, but meh.
        LColor c = renderer.getGrid(x, y);
        c.lighten();
        renderer.setGrid(x, y, c);
    }

    @Override
    public void launchpadButtonPressed(int buttonCode) {
        if(exiting||actions[72+LButton.buttonNumber(buttonCode)] == null)
            return;
        
//        LColor c = renderer.getButton(buttonCode);
//        tempColors[72+LButton.buttonNumber(buttonCode)] = c;
//        c.setRedGreen(c.getRed() > 0 ? c.getRed()-1 : c.getRed(), 
//                      c.getGreen() > 0 ? c.getGreen()-1 : c.getGreen());
//        renderer.setButton(buttonCode, c);
        
        //actions[72+LButton.buttonNumber(buttonCode)].holdAction();
        
        held[72+LButton.buttonNumber(buttonCode)] = System.currentTimeMillis();
    }

    @Override
    public void launchpadButtonReleased(int buttonCode) {
        if(exiting||actions[72+LButton.buttonNumber(buttonCode)] == null)
            return;
        
//        LColor c = tempColors[72+LButton.buttonNumber(buttonCode)];
//        renderer.setButton(buttonCode, c);
        
        //actions[72+LButton.buttonNumber(buttonCode)].releaseAction();
        
        if(buttonCode == LButton.UP){
            sleeping = !sleeping;
            if(!sleeping)
                renderer.setButton(powerButton, new LColor(3, 0));
            else{
                //clearColors();
                renderer.setButton(powerButton, new LColor(0, 0));
            }
            
            held[72] = -1;
        }
        
    }

    @Override
    public void launchpadSceneButtonPressed(int buttonCode) {
        if(exiting||actions[63+LButton.sceneButtonNumber(buttonCode)] == null)
            return;
        
//        LColor c = renderer.getScene(buttonCode);
//        tempColors[63+LButton.sceneButtonNumber(buttonCode)] = c;
//        c.setRedGreen(c.getRed() > 0 ? c.getRed()-1 : c.getRed(), 
//                      c.getGreen() > 0 ? c.getGreen()-1 : c.getGreen());
//        renderer.setScene(buttonCode, c);
        
        //actions[63+LButton.sceneButtonNumber(buttonCode)].holdAction();
        
        held[63+LButton.sceneButtonNumber(buttonCode)] = System.currentTimeMillis();
    }

    @Override
    public void launchpadSceneButtonReleased(int buttonCode) {
        if(exiting||actions[63+LButton.sceneButtonNumber(buttonCode)] == null)
            return;
        
//        LColor c = tempColors[63+LButton.sceneButtonNumber(buttonCode)];
//        renderer.setScene(buttonCode, c);
        
        //actions[63+LButton.sceneButtonNumber(buttonCode)].releaseAction();
        
        held[63+LButton.sceneButtonNumber(buttonCode)] = -1;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println(ke.getKeyCode());
    }
}