package com.rngtng.launchpad;

import com.rngtng.launchpad.LState;

/**
 *
 * @author Matt
 */
public class LAction {
    private LState state = LState.NONE;
    private long lastChange = -1;
    public int frames;
    
    public LAction() {}
    
    public void setState(LState state) {
        this.state = state;
        
        switch(state)
        {
            case PRESSED:
                lastChange = System.currentTimeMillis();
                break;
            case HELD:
                break;
            case RELEASED:
                break;
            default:
                lastChange = -1;
                break;
        }
    }
    
    public LState getState() {
        return state;
    }
    
    public long getTime() {
        return lastChange;
    }
}