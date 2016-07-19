/*
 * Listens to SysEx messages sent from the launchpad.
 * 
 * Made separate from LaunchpadListener as usually users won't care about 
 * SysEx messages.
 */

package com.rngtng.launchpad;

import javax.sound.midi.MidiMessage;

/**
 *
 * @author Matt
 */
public interface LaunchpadSysExListener extends LaunchpadListener {
    // Used for messages the user is expecting
    public void messageRecieved(MidiMessage message, long timestamp);
}
