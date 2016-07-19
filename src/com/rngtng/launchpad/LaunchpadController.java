package com.rngtng.launchpad;

import themidibus.MidiBus;

/**
 * Will be the default Launchpad class that all other launchpad classes extend.
 * 
 * @author Matt
 */
public abstract class LaunchpadController {
    protected MidiBus midibus;
    
    protected LaunchpadController() {
        midibus = new MidiBus();
    }
    
    protected LaunchpadController(MidiBus bus) {
        midibus = bus;
    }
    
    public void close() {
        midibus.close();
    }
}
