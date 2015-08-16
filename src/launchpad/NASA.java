/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package launchpad;

import com.rngtng.launchpad.*;

/**
 *
 * @author Jocopa3
 */
public class NASA implements LaunchpadListener {
    
    private boolean exiting = false;
    private boolean sleeping = false;
    private Launchpad device;
    
    private LColor scene[] = new LColor[80];
    private boolean hold[] = new boolean[80];
    private long held[] = new long[80];
    
    private final Renderer renderer;
    
    public int frameCount = 0;
    
    public NASA(Launchpad device){
        this.device = device;
        device.addListener(this);
        renderer = new Renderer(device);
        
        clearAll();
        setupMenu();  
        loop();
    }
    
    public void loop(){
        boolean tots = false;
        while(!exiting){
            if(held[72] >= 0 && System.currentTimeMillis() - held[72] > 1000){
                exiting = true;
                break;
            }
           
            if(!sleeping){
                if(tots){
                    tots = false;
                }
                render();    
            }else{
                if(!tots){
                    clearColors();
                    tots = true;
                }
                
                if(frameCount%100 == 0){
                    scene[72].setRed(1);
                    device.changeAll(scene);
                }else if(frameCount%101 == 0){
                    scene[72].setRed(0);
                    device.changeAll(scene);
                }
            }
           
            frameCount++;
            delay(0.01);
        }
        outro();
        device.dispose();
        System.exit(0);
    }
    
    private boolean DestroyWorld = true;
    private boolean safe = false;
    private boolean missile = false;
    public void render(){
        if(DestroyWorld&&!safe&&!missile)
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++){
                boolean b = frameCount%6>3;
                int c = 6-(frameCount%6);
                int d = frameCount%6;
                scene[i+(j<<3)].setRedGreen(b ? c:d, 0);
            }
        else if(missile&&!safe)
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++){
                boolean b = frameCount%6>3;
                int c = 6-(frameCount%6);
                int d = frameCount%6;
                scene[i+(j<<3)].setRedGreen(b ? c:d, b ? c:d);
            }
        else if(safe)
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                scene[i+(j<<3)].setRedGreen(0, 3);
        
        
        for(int i = 64; i < 72; i++){
            if(hold[i])
                continue;
            
            scene[i].setRedGreen(0, 0);
        }
        
        for(int i = 72; i < 80; i++){
            if(hold[i])
                continue;
            
            scene[i].setRedGreen(0, 0);
        }
            
        device.changeAll(scene);
    }
    
    public void setupMenu(){
        LColor l = new LColor(3,0);
        //l.setMode(LColor.FLASHING);
        scene[72] = l;
        hold[72] = true;
    }
    
    public void outro(){
        clearAll();
        
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
    
    public void clearColors(){
        for(int i = 0; i < scene.length; i++){
            if(hold[i])
                continue;
            scene[i].setRedGreen(0, 0);
        }
        device.changeAll(scene);
    }
    
    public void clearAll(){
        for(int i = 0; i < scene.length; i++){
            scene[i] = new LColor(0, 0);
            scene[i].setMode(LColor.BUFFERED);
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
    
    @Override
    public void launchpadGridPressed(int x, int y) {
        if(exiting)
            return;

        int a = ((int)(Math.random()*4))%4, b = ((int)(Math.random()*4))%4;
        if(a==b&&a==0)
            if(Math.round(Math.random())==0)
                a = 1;
            else
                b = 1;
        scene[x+(y<<3)].setRedGreen(a, b);
    }

    @Override
    public void launchpadGridReleased(int x, int y) {
        if(exiting)
            return;
        
        held[x+(y<<3)] = System.currentTimeMillis();
        if(x==6&&y==5){
            missile = true;
            scene[72].setRedGreen(3, 3);
        }
    }

    @Override
    public void launchpadButtonPressed(int buttonCode) {
        if(exiting)
            return;
        
        if(buttonCode == LButton.UP)
            held[72] = System.currentTimeMillis();
        if(buttonCode == LButton.SESSION){
            DestroyWorld = false;
            clearColors();
        }
    }

    @Override
    public void launchpadButtonReleased(int buttonCode) {
        if(exiting)
            return;
        
        int a = ((int)(Math.random()*4))%4, b = ((int)(Math.random()*4))%4;
        if(a==b&&a==0)
            if(Math.round(Math.random())==0)
                a = 1;
            else
                b = 1;
        scene[72+LButton.buttonNumber(buttonCode)].setRedGreen(a, b);
        
        if(buttonCode == LButton.UP){
            sleeping = !sleeping;
            if(!sleeping)
                scene[72].setRed(3);
            else{
                clearColors();
                scene[72].setRed(0);
            }
            
            held[72] = -1;
        }
        
    }

    @Override
    public void launchpadSceneButtonPressed(int buttonCode) {
        if(exiting)
            return;
        int a = ((int)(Math.random()*4))%4, b = ((int)(Math.random()*4))%4;
        if(a==b&&a==0)
            if(Math.round(Math.random())==0)
                a = 1;
            else
                b = 1;
        scene[63+LButton.sceneButtonNumber(buttonCode)].setRedGreen(a, b);
        if(LButton.sceneButtonNumber(buttonCode) == 3){
            safe = true;
            scene[72].setRedGreen(0, 3);
        }
    }

    @Override
    public void launchpadSceneButtonReleased(int buttonCode) {
        
    }
}