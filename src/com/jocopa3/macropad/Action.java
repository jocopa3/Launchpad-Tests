/*
 * Stores and handles execution of an action, such as a key or mouse press.
 * Needs some work, such as having custom delay's, and general improvements.
 */

package com.jocopa3.macropad;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Jocopa3
 */
public class Action extends ArrayList<ActionEvent> {
    
    private Robot robot;
    
    public Action() {
        super();
    }
    
    public Action(Robot robot) {
        super();
        this.robot = robot;
    }
    
    public Action(Robot robot, ActionEvent events[]) {
        super(Arrays.asList(events));
        this.robot = robot;
    }
    
    public Action(Robot robot, ArrayList<ActionEvent> events){
        super(events);
        this.robot = robot;
    }
    
    public Robot getRobot() {
        return robot;
    }
    
    public ActionEvent[] asArray() {
        return (ActionEvent[])this.toArray();
    }
    
    public void execute() {
        for(ActionEvent event : this) {
            event.executeEvent();
        }
    }
}