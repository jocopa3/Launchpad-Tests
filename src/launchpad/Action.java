/*
 * Stores and handles execution of an action, such as a key or mouse press.
 * Needs some work, such as having custom delay's, and general improvements.
 */

package launchpad;

import java.awt.Robot;

/**
 *
 * @author Jocopa3
 */
public class Action {
    
    private int[] keys;
    private boolean isMouse;
    private Robot robot;
    
    private int delay;
    
    public Action(Robot robot){
        this(robot, new int[0], false, 0); // Default is keyboard shortcut
    }
    
    public Action(Robot robot, int[] keys){
        this(robot, keys, false, 15); // Default is keyboard shortcut
    }
    
    public Action(Robot robot, int[] keys, boolean isMouse){
        this(robot, keys, isMouse, 15);
    }
    
    public Action(Robot robot, int[] keys, boolean isMouse, int delay){
        this.robot = robot;
        this.keys = keys;
        this.isMouse = isMouse;
        this.delay = delay;
    }
    
    public Action(Robot robot, int[] keys, boolean isMouse, int delay, boolean repeat){
        this.robot = robot;
        this.keys = keys;
        this.isMouse = isMouse;
        this.delay = delay;
    }
    
    public void performAction(){
        if(!isMouse){
            for(int i = keys.length-1; i >= 0; i--){
                robot.keyRelease(keys[i]);
                robot.delay(delay);
            }
            for(int key : keys){
                robot.keyRelease(key);
                robot.delay(delay);
            }
        }else if(isMouse){
            for(int key : keys){
                robot.mousePress(key);
                robot.delay(delay);
            }
            for(int i = keys.length-1; i >= 0; i--){
                robot.mouseRelease(keys[i]);
                robot.delay(delay);
            }
        }
    }
    
    public void holdAction(){
        if(!isMouse){
            for(int key : keys){
                robot.keyPress(key);
                robot.delay(delay);
            }
        }else if(isMouse){
            for(int key : keys){
                robot.mousePress(key);
                robot.delay(delay);
            }
        }
    }
    
    public void releaseAction(){
        if(!isMouse){
            for(int i = keys.length-1; i >= 0; i--){
                robot.keyRelease(keys[i]);
                robot.delay(delay);
            }
        }else if(isMouse){
            for(int i = keys.length-1; i >= 0; i--){
                robot.mouseRelease(keys[i]);
                robot.delay(delay);
            }
        }
    }
    
    public void setDelay(int delay){
        this.delay = delay;
    }
}