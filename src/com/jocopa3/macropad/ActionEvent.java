/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocopa3.macropad;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import static com.jocopa3.macropad.ActionEnums.*;

/**
 *
 * @author Matt
 */
public class ActionEvent {

    private ActionType type;
    private final Robot robot;

    private int value; // Meaning changes depending on the type
    private String command;

    public ActionEvent(Robot robot) {
        type = ActionType.BLANK_EVENT;
        this.robot = robot;
    }

    public ActionEvent(Robot robot, ActionType type, int value) {
        this.type = type;
        this.value = value;
        this.robot = robot;
    }

    /*
    public ActionEvent(Robot robot, int x, int y) {
        this.type = ActionType.MOUSE_MOVE_EVENT;
        this.value = ((y << MOUSE_MOVE_Y_OFFSET) & MOUSE_MOVE_Y_MASK) + (x & MOUSE_MOVE_X_MASK);
        this.robot = robot;
    }
    */
    
    public ActionEvent(Robot robot, ActionType type, String command) {
        this.type = type;
        this.command = command;
        this.robot = robot;
    }

    public ActionType getType() {
        return type;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setType(ActionType type) {
        this.type = type;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void executeEvent() {
        try {
            int a, b;
            
            switch (type) {
                case BLANK_EVENT:
                    break;

                case DELAY_EVENT:
                    robot.delay(value);
                    break;

                case KEYBOARD_EVENT:
                    a = value & KEY_MASK;
                    b = value & KEY_EVENT_MASK;

                    switch (b) {
                        case PRESSED:
                            robot.keyPress(a);
                            break;
                        case RELEASED:
                            robot.keyRelease(a);
                            break;
                        case TYPED:
                            robot.keyPress(a);
                            robot.keyRelease(a);
                            break;
                    }
                    break;

                case MOUSE_CLICK_EVENT:
                    a = value & KEY_MASK;
                    b = value & KEY_EVENT_MASK;

                    switch (b) {
                        case PRESSED:
                            robot.mousePress(a);
                            break;
                        case RELEASED:
                            robot.mouseRelease(a);
                            break;
                        case TYPED:
                            robot.mousePress(a);
                            robot.mouseRelease(a);
                            break;
                    }
                    break;

                case MOUSE_MOVE_EVENT:
                    a = value & MOUSE_MOVE_X_MASK;
                    b = (value & MOUSE_MOVE_Y_MASK) >> MOUSE_MOVE_Y_OFFSET;
                    robot.mouseMove(a, b);

                case LAUNCHPAD_EVENT:
                    break;

                case COMMAND_LINE_EVENT:
                    Runtime.getRuntime().exec(command);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        int a, b;
        
        switch (type) {
            case BLANK_EVENT:
                return "No Action";

            case DELAY_EVENT:
                return value + "ms Delay";

            case KEYBOARD_EVENT:
                a = value & KEY_MASK;
                b = value & KEY_EVENT_MASK;

                switch (b) {
                    case PRESSED:
                        return "Key Press: " + KeyEvent.getKeyText(a);
                    case RELEASED:
                        return "Key Release: " + KeyEvent.getKeyText(a);
                    case TYPED:
                        return "Type Key: " + KeyEvent.getKeyText(a);
                    default:
                        return "Unknown Keyboard Event";
                }

            case MOUSE_CLICK_EVENT:
                a = value & KEY_MASK;
                b = value & KEY_EVENT_MASK;

                switch (b) {
                    case PRESSED:
                        return "Mouse Press: " + KeyEvent.getKeyText(a);
                    case RELEASED:
                        return "Mouse Release: " + KeyEvent.getKeyText(a);
                    case TYPED:
                        return "Mouse Click: " + KeyEvent.getKeyText(a);
                }

            case MOUSE_MOVE_EVENT:
                a = value & MOUSE_MOVE_X_MASK;
                b = (value & MOUSE_MOVE_Y_MASK) >> MOUSE_MOVE_Y_OFFSET;
                return "Move Mouse: {" + a +", " + b + "}";

            case LAUNCHPAD_EVENT:
                return "Launchpad: Not Supported";

            case COMMAND_LINE_EVENT:
                return "Execute: "+command;
                
            default:
                return "Unknown Action";
        }
    }
}
