/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package launchpad;

import com.rngtng.launchpad.LButton;
import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;

/**
 *
 * @author Jocopa3
 */
public class Renderer {
    
    protected final Launchpad device;
    protected LColor Scene[];
    
    public Renderer(Launchpad device){
        this.device = device;
        Scene = new LColor[80];
        for(int i = 0; i < Scene.length; i++)
            Scene[i] = new LColor(0, 0);
    }
    
    public void render(){
        device.changeAll(Scene);
    }
    
    public void clearScene(){
        for(LColor color : Scene)
            color.setRedGreen(0, 0);
    }

    public void set(int button, LColor color) {
        Scene[button] = color;
    }
    
    public void setButton(int button, LColor color) {
        Scene[72+LButton.buttonNumber(button)] = color;
    }

    public void setScene(int scene, LColor color) {
        Scene[63+LButton.sceneButtonNumber(scene)] = color;
    }

    public void setGrid(int x, int y, LColor color) {
        Scene[x+(y<<3)] = color;
    }

    public void setAll(LColor[] arr) {
        Scene = arr;
    }

    public LColor get(int button) {
        return Scene[button];
    }
    
    public LColor getButton(int button) {
        return Scene[72+LButton.buttonNumber(button)];
    }

    public LColor getScene(int scene) {
        return Scene[63+LButton.sceneButtonNumber(scene)];
    }

    public LColor getGrid(int x, int y) {
        return Scene[x+(y<<3)];
    }

    public LColor[] getAll() {
        return Scene;
    }
}
