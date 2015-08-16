package com.rngtng.launchpad;

/**
 * Set of MidiCodes to send to Launchpad
 *
 * @author rngtng - Tobias Bielohlawek
 *
 */
public interface LMidiCodes {
	public final static int STATUS_NIL     = 0x00;
	public final static int STATUS_OFF     = 0x80;
	public final static int STATUS_ON      = 0x90;
	public final static int STATUS_MULTI   = 0x92;
	public final static int STATUS_CC      = 0xB0;

	public final static int VELOCITY_TEST_LEDS     = 0x7C;

	public final static int GRIDLAYOUT_XY          = 0x01;
	public final static int GRIDLAYOUT_DRUM_RACK   = 0x02;

	public final static int BUFFER0       = 0x0;
	public final static int BUFFER1       = 0x1;
	public final static int MODE_FLASHING = 0x8;  //whether to start flashing by automatically switching between the two buffers for display
	public final static int MODE_COPY     = 0x10; //whether to copy the LEDs states from the new display_buffer over to the new update_buffer
    
    //names that the Launchpad may identify with; I don't know how many other names there might be
    public final static String[] NAMES = {"Launchpad", "Launchpad Mini", "Launchpad Mini 10", "Launchpad S"};
}