/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocopa3.macropad;

import com.jocopa3.launchpad.Renderer;

import com.rngtng.launchpad.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.rngtng.launchpad.LState;
import com.rngtng.launchpad.LAction;
import java.util.Arrays;
import javax.sound.midi.MidiMessage;
import com.jocopa3.launchpad.effects.SimplexNoise;
import com.jocopa3.macropad.gui.LaunchpadGui;
import com.jocopa3.macropad.gui.LaunchpadButton;
import static com.jocopa3.macropad.main.*;


/**
 *
 * @author Matt
 */
public class MacroPad implements LaunchpadSysExListener, KeyListener {
    
    private boolean exiting = false;
    private boolean sleeping = false;
    private Launchpad device;

    private LAction states[] = new LAction[80];
    private Action actions[] = new Action[80];
    private LColor colors[] = new LColor[80];
    
    private int powerButton = 72;
    
    private final Renderer renderer;
    
    public int frameCount = 0;
    
    public Robot robot;
    
    private LaunchpadGui gui;
    
    public MacroPad(Launchpad device){
        this.device = device;
        device.reset();
        device.addListener(this);

        renderer = new Renderer(device);
        
        try{
            robot = new Robot();
        }catch(AWTException e){
            e.printStackTrace();
            System.exit(-1);
        }
        gui = new LaunchpadGui(this);
        
        //addAction(new Action(robot), new LColor(3, 0), 72); // Blank action for power button
        
        
        //addAction(new Action(robot, new int[]{KeyEvent.VK_W}, false), new LColor(0, 3), 2, 2);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_A}, false), new LColor(0, 3), 1, 3);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_S}, false), new LColor(0, 3), 2, 3);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_D}, false), new LColor(0, 3), 3, 3);
        
        //addAction(new Action(robot, new int[]{KeyEvent.VK_R}, false), new LColor(0, 1), 3, 2);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_SPACE}, false), new LColor(3, 3), 4, 5);
        
        //addAction(new Action(robot, new int[]{KeyEvent.VK_CONTROL}, false), new LColor(3, 3), 0, 4);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_G}, false), new LColor(3, 0), 0, 3);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_B}, false), new LColor(3, 0), 3, 4);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_E}, false), new LColor(3, 3), 4, 2);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_3}, false), new LColor(3, 3), 1, 2);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_ESCAPE}, false), new LColor(3, 0), 5, 5);
        //addAction(new Action(robot, new int[]{KeyEvent.VK_F3}, false), new LColor(3, 0), 0, 5);
                                
        for(int i = 0; i < 80; i++) {
            states[i] = new LAction();
            actions[i] = new Action(robot);
            colors[i] = new LColor(0, 0);
        }
        
        clearAll();
        setupMenu();
        loop();
    }
    
    public void loop(){
        //device.waitForMessage();
        //device.outputRaw(toByteArray("F0 7E 7F 06 01 F7".replaceAll(" ", "")));
        while(!exiting){
            handleInput();
            
            if(states[72].getTime() >= 0 && System.currentTimeMillis() - states[72].getTime() > 1000){
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
            delay(1/60);
        }
        
        outro();
        device.close();
        System.exit(0);
    }
    
    private SimplexNoise noise = new SimplexNoise();
    public void render() {
        
        int scale = 10;
        for(int i = 0; i < 80; i++) {
            float n;
            if(i < 64)
                n = noise.noise3D((i%8f)/scale, (1 + i/8f)/scale , frameCount/500f);
            else if(i < 72)
                n = noise.noise3D(8f/scale, (1+(i-63)%9f)/scale, frameCount/500f);
            else
                n = noise.noise3D((i%8f)/scale, 0f, frameCount/500f);
            
            int hue = (int)(n*8+8);
            
            colors[i].setHue(hue);
            gui.getButton(i).setColor(colors[i]);
        }
        
        /*
        for(int i = 0; i < colors.length; i++)
            gui.getButton(i).setColor(colors[i]);
        */
        
        renderer.render();
        //device.changeAll(renderer.getAll());
    }
    
    public void handleInput() {
        LaunchpadButton b;
        for(int i = 0; i < 80; i++) {
            b = gui.getButton(i);
            
            if(states[i].getTime() < 0)
                states[i].setState(LState.NONE);
            
            switch(states[i].getState())
            {
                case PRESSED:
                    colors[i].setRedGreen(0, 3);
                    break;
                case RELEASED:
                    colors[i].setRedGreen(3, 0);
                    break;
                case HELD:
                    colors[i].setRedGreen(3, 3);
                    break;
                case NONE:
                    colors[i].setRedGreen(0, 0);
                    break;
            }
        }
        for(int i = 0; i < 80; i++) {
            switch(states[i].getState())
            {
                case PRESSED:
                    if(states[i].frames == 0) {
                        states[i].frames++;
                        break;
                    }
                    if(i == 72)
                        gui.toFront();
                    states[i].setState(LState.HELD);
                    break;
                case RELEASED:
                    if(states[i].frames == 0) {
                        states[i].frames++;
                        break;
                    }
                    states[i].setState(LState.NONE);
                    break;
                default:
                    break;
            }
        }
        
        renderer.setAll(colors);
    }
    
    public Renderer getRenderer(){
        return renderer;
    }
    
    public void setupMenu(){
        //l.setMode(LColor.FLASHING);
        //renderer.setButton(powerButton, new LColor(3,0));
        //hold[72] = true;
    }
    
    // Does simple outro animation
    public void outro(){
        LColor scene[] = new LColor[80];
        for(int i = 0; i < 80; i++)
            scene[i] = new LColor(0,0);
        /*
        for(int t = 0; t < 8; t++){
            for(int i = 0; i < 8; i++)
                for(int j = 0; j < 8; j++)
                    //device.changeGrid(i, j, new LColor(i==t?3:0,j==t?3:0));
                    scene[i+j*8].setRedGreen(i==t?3:0,j==t?3:0);
            
            device.changeAll(scene);
            delay(0.01);
        }
        */
        
        
        
        delay(0.05);
    }
    
    public void clearAll(){
        for(int i = 0; i < 80; i++){
            states[i].setState(LState.NONE);
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
    
    public void setColor(int x, int y, LColor color){
        renderer.setGrid(x, y, color);
    }
    
    public void setColor(int id, LColor color){
        renderer.set(id, color);
    }
    
    private void addAction(Action a, LColor color, int button){
        actions[button] = a;
        renderer.set(button, color);
    }
    
    private void addAction(Action a, LColor color, int x, int y){
        actions[x+(y<<3)] = a;
        renderer.setGrid(x, y, color);
    }
    
    public void setState(int button, LState state) {
        states[button].setState(state);
        states[button].frames = 0;
    }
    
    @Override
    public void launchpadGridPressed(int x, int y) {
        if(exiting)//||actions[x+(y<<3)] == null)
            return;
        
//        LColor c = renderer.getGrid(x, y);
//        tempColors[x+(y<<3)] = c;
//        c.setRedGreen(c.getRed() > 0 ? c.getRed()-1 : c.getRed(), 
//                      c.getGreen() > 0 ? c.getGreen()-1 : c.getGreen());
//        renderer.setGrid(x, y, c);
        
//        setColor(x, y, new LColor(0, 3));
//        actions[x+(y<<3)].holdAction();
//        
//        held[x+(y<<3)] = System.currentTimeMillis();
        
        int button = x+(y<<3);
        setState(button, LState.PRESSED);
    }

    @Override
    public void launchpadGridReleased(int x, int y) {
        if(exiting)//||actions[x+(y<<3)] == null)
            return;
        
//        LColor c = tempColors[x+(y<<3)];
//        renderer.setGrid(x, y, c);
        
//        actions[x+(y<<3)].releaseAction();
//        
//        held[x+(y<<3)] = -1;
        
        int button = x+(y<<3);
        setState(button, LState.RELEASED);
    }

    @Override
    public void launchpadButtonPressed(int buttonCode) {
        if(exiting)//||actions[72+LButton.buttonNumber(buttonCode)] == null)
            return;
        
//        LColor c = renderer.getButton(buttonCode);
//        tempColors[72+LButton.buttonNumber(buttonCode)] = c;
//        c.setRedGreen(c.getRed() > 0 ? c.getRed()-1 : c.getRed(), 
//                      c.getGreen() > 0 ? c.getGreen()-1 : c.getGreen());
//        renderer.setButton(buttonCode, c);
        
        //actions[72+LButton.buttonNumber(buttonCode)].holdAction();
        
        //held[72+LButton.buttonNumber(buttonCode)] = System.currentTimeMillis();

        int button = 72+LButton.buttonNumber(buttonCode);
        setState(button, LState.PRESSED);
    }

    @Override
    public void launchpadButtonReleased(int buttonCode) {
        if(exiting)//||actions[72+LButton.buttonNumber(buttonCode)] == null)
            return;
        
//        LColor c = tempColors[72+LButton.buttonNumber(buttonCode)];
//        renderer.setButton(buttonCode, c);
//        
//        actions[72+LButton.buttonNumber(buttonCode)].releaseAction();
//        
//        if(buttonCode == LButton.UP){
//            sleeping = !sleeping;
//            if(!sleeping)
//                renderer.setButton(powerButton, new LColor(3, 0));
//            else{
//                //clearColors();
//                renderer.setButton(powerButton, new LColor(0, 0));
//            }
//            
//            held[72] = -1;
//        }
        
        int button = 72+LButton.buttonNumber(buttonCode);
        setState(button, LState.RELEASED);
    }

    @Override
    public void launchpadSceneButtonPressed(int buttonCode) {
        if(exiting)//||actions[63+LButton.sceneButtonNumber(buttonCode)] == null)
            return;
        
//        LColor c = renderer.getScene(buttonCode);
//        tempColors[63+LButton.sceneButtonNumber(buttonCode)] = c;
//        c.setRedGreen(c.getRed() > 0 ? c.getRed()-1 : c.getRed(), 
//                      c.getGreen() > 0 ? c.getGreen()-1 : c.getGreen());
//        renderer.setScene(buttonCode, c);
        
//        actions[63+LButton.sceneButtonNumber(buttonCode)].holdAction();
//        
//        held[63+LButton.sceneButtonNumber(buttonCode)] = System.currentTimeMillis();
        
        states[63+LButton.sceneButtonNumber(buttonCode)].setState(LState.PRESSED);
        int button = 63+LButton.sceneButtonNumber(buttonCode);
        setState(button, LState.PRESSED);
    }

    @Override
    public void launchpadSceneButtonReleased(int buttonCode) {
        if(exiting)//||actions[63+LButton.sceneButtonNumber(buttonCode)] == null)
            return;
        
//        LColor c = tempColors[63+LButton.sceneButtonNumber(buttonCode)];
//        renderer.setScene(buttonCode, c);
        
//        actions[63+LButton.sceneButtonNumber(buttonCode)].releaseAction();
//        
//        held[63+LButton.sceneButtonNumber(buttonCode)] = -1;
        
        int button = 63+LButton.sceneButtonNumber(buttonCode);
        setState(button, LState.RELEASED);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println(ke.getKeyCode());
    }

    @Override
    public void messageRecieved(MidiMessage message, long timestamp) {
        System.out.println(byteArrayToHex(message.getMessage()));
    }
}