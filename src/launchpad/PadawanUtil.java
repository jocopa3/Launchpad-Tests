/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package launchpad;

import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;
import static launchpad.LaunchPadawan.device;

/**
 *
 * @author Jocopa3
 */
public class PadawanUtil {
    public static Launchpad device;
    
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
    
    public static void showColors(Launchpad device){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                device.changeGrid(i, j, new LColor((int)(i/2)%4, (int)(j/2)%4));
    }
    
    public static void rapidTest(Launchpad device){
        device.changeGrid(0, 0, new LColor(1, 0));
        device.changeGrid(2, 0, new LColor(1, 1));
        device.changeGrid(4, 0, new LColor(2, 2));
        device.changeGrid(6, 0, new LColor(3, 3));
        while(true){
            device.changeGrid(1, 0, new LColor(1, 0));
            device.changeGrid(3, 0, new LColor(2, 1));
            device.changeGrid(5, 0, new LColor(3, 1));
            delay(0.01);
            device.changeGrid(1, 0, new LColor(1, 1));
            device.changeGrid(3, 0, new LColor(2, 2));
            device.changeGrid(5, 0, new LColor(3, 3));
            delay(0.01);
        }
    }
    
    public static void hueTest(Launchpad device){
        LColor l = new LColor(0, 0);
        int t = 0;
        while(true){
            l.setHue(t);
            device.changeGrid(0, 0, l);
            t=(t+1)%16;
            delay(0.1);
        }
    }
    
    public static void outro(Launchpad device){
        device.testLeds(3);
        delay(0.15);
        device.testLeds(2);
        delay(0.15);
        device.testLeds(1);
        delay(0.15);
        device.dispose();
        System.exit(0);
    }
}
