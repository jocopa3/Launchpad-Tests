
/*
 * Renders colors to individual buttons.

 * This class is usefull if you don't need to batch edit many buttons at once
 */

package com.jocopa3.launchpad;

import com.rngtng.launchpad.LButton;
import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;

/**
 *
 * @author Jocopa3
 */
public class ButtonRenderer extends Renderer {
    
    public ButtonRenderer(Launchpad device){
        super(device);
    }
    
    @Override
    public void render(){
        clearScene();
    }

    @Override
    public void set(int button, LColor color){
        Scene[button] = color;
        if(button < 64){
            device.changeGrid(button & 7, button >> 3, color);
        }else if(button < 72){
            device.changeSceneButton(button - 63, color);
        }
        else if(button < 80){
            device.changeButton(button - 72, color);
        }
    }
    
    @Override
    public void setButton(int button, LColor color) {
        Scene[72+LButton.buttonNumber(button)] = color;
        device.changeButton(button, color);
    }

    @Override
    public void setScene(int scene, LColor color) {
        Scene[63+LButton.sceneButtonNumber(scene)] = color;
        device.changeSceneButton(scene, color);
    }

    @Override
    public void setGrid(int x, int y, LColor color) {
        Scene[x+(y<<3)] = color;
        device.changeGrid(x, y, color);
    }

    @Override
    public void setAll(LColor[] arr) {
        // Don't care, this renderer isn't compatible with this method
    }
}
