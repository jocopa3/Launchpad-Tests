/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocopa3.macropad;

/**
 *
 * @author Matt
 */
public enum ActionType {
    BLANK_EVENT, // Do nothing
    DELAY_EVENT, // Add delay between executing events
    KEYBOARD_EVENT, // Emulate key presses and modifier keys
    MOUSE_CLICK_EVENT, // Emulate mouse button presses
    MOUSE_MOVE_EVENT, // Emulate mouse movement
    LAUNCHPAD_EVENT, // Emulate launchpad button presses
    COMMAND_LINE_EVENT; // Run commands (i.e. to launch programs or batch operations)
    
    public static final int TotalEnums = 7;
    
    public static ActionType fromInt(int type) {
        switch(type) {
            case 1:
                return DELAY_EVENT;
            case 2:
                return KEYBOARD_EVENT;
            case 3:
                return MOUSE_CLICK_EVENT;
            case 4:
                return MOUSE_MOVE_EVENT;
            case 5:
                return LAUNCHPAD_EVENT;
            case 6:
                return COMMAND_LINE_EVENT;
            default:
                return BLANK_EVENT;
        }
    }
    
    public static int toInt(ActionType type) {
        switch(type) {
            case DELAY_EVENT:
                return 1;
            case KEYBOARD_EVENT:
                return 2;
            case MOUSE_CLICK_EVENT:
                return 3;
            case MOUSE_MOVE_EVENT:
                return 4;
            case LAUNCHPAD_EVENT:
                return 5;
            case COMMAND_LINE_EVENT:
                return 6;
            default:
                return 0;
        }
    }
    
    public static String[] asStringArray() {
        return new String[] {
            asString(BLANK_EVENT),
            asString(DELAY_EVENT),
            asString(KEYBOARD_EVENT),
            asString(MOUSE_CLICK_EVENT),
            asString(MOUSE_MOVE_EVENT),
            asString(LAUNCHPAD_EVENT),
            asString(COMMAND_LINE_EVENT)
        };
    }
    
    public static String asString(ActionType type) {
        switch(type) {
            case BLANK_EVENT:
                return "Blank Event";
            case DELAY_EVENT:
                return "Delay Event";
            case KEYBOARD_EVENT:
                return "Keyboard Event";
            case MOUSE_CLICK_EVENT:
                return "Mouse Click Event";
            case MOUSE_MOVE_EVENT:
                return "Mouse Move Event";
            case LAUNCHPAD_EVENT:
                return "Launchpad Event";
            case COMMAND_LINE_EVENT:
                return "Command Line Event";
            default:
                return "Unknown Event";
        }
    }
}
