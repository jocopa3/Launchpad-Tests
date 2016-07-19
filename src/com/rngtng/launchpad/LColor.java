package com.rngtng.launchpad;

import java.awt.Color;

/**
 * The Color class, to handle the MIDI Color and Mode codes
 *
 * @author rngtng - Tobias Bielohlawek
 *
 */
public class LColor {

    public final static int OFF = 0x0;
    public final static int LOW = 0x1;
    public final static int MEDIUM = 0x2;
    public final static int HIGH = 0x3;

    public final static int RED_OFF = OFF;
    public final static int RED_LOW = LOW;
    public final static int RED_MEDIUM = MEDIUM;
    public final static int RED_HIGH = HIGH;

    public final static int GREEN_OFFSET = 0x10;
    public final static int GREEN_OFF = OFF * GREEN_OFFSET;
    public final static int GREEN_LOW = LOW * GREEN_OFFSET;
    public final static int GREEN_MEDIUM = MEDIUM * GREEN_OFFSET;
    public final static int GREEN_HIGH = HIGH * GREEN_OFFSET;

    public final static int YELLOW_OFF = RED_OFF + GREEN_OFF;
    public final static int YELLOW_LOW = RED_LOW + GREEN_LOW;
    public final static int YELLOW_MEDIUM = RED_MEDIUM + GREEN_MEDIUM;
    public final static int YELLOW_HIGH = RED_HIGH + GREEN_HIGH;

    //Hack: I want NORMAL to be default MODE so I switched values of BUFFERED and NORMAL
    public final static int BUFFERED = 0xC; // updates the LED for the current update_buffer only
    public final static int FLASHING = 0x8; // flashing   updates the LED for flashing (the new value will be written to buffer 0 while the LED will be off in buffer 1, see buffering_mode)
    public final static int NORMAL = 0x0; // updates the LED for all circumstances (the new value will be written to both buffers)

    private int red;
    private int green;
    private int mode;

    /**
     * creates default black color
     */
    public LColor() {
        this(OFF);
    }

    /**
     * creates color with given red and green value, green can be 0-3 or
     * Constants
     *
     * @param red red value
     * @param green green value
     */
    public LColor(int red, int green) {
        this(red);
        setGreen(green);
    }

    /**
     * creates color with given red and green value, green can be 0-3 or
     * Constants
     *
     * @param red Red value
     * @param green Green value
     * @param mode Mode value
     */
    public LColor(int red, int green, int mode) {
        this(red + mode);
        setGreen(green);
    }

    /**
     * creates color with given red/green and mode value
     *
     * @param _color color value
     */
    public LColor(int _color) {
        int red_and_mode = _color % GREEN_OFFSET;
        if (red_and_mode < FLASHING) {
            this.setMode(NORMAL);
        } else if (red_and_mode < BUFFERED) {
            this.setMode(FLASHING);
        } else {
            this.setMode(BUFFERED);
        }
        this.setRed(red_and_mode - this.getMode());
        this.setGreen(_color - red_and_mode);
    }

    /**
     * Calculates the MIDI data 2 value (velocity) for given brightness and mode
     * values. * @return integer to be used for MIDI data 2
     *
     * Errors raised: [Launchpad::NoValidBrightnessError] when brightness values
     * aren't within the valid range
     */
    public int velocity() {
        //Hack switch values, as CONST have switch values for default reasons

        return this.green + this.red + mode;
    }

    /**
     * Returns the value of Red
     *
     * @return red value
     */
    public int getRed() {
        return red;
    }

    /**
     * Set the value of Red
     *
     * @param red
     */
    public void setRed(int red) {
        this.red = red;
    }

    /**
     * Returns the value of green
     *
     * @return green value
     */
    public int getGreen() {
        return green / GREEN_OFFSET;
    }

    /**
     * Set the value of green
     *
     * @param green
     */
    public void setGreen(int green) {
        if (green < GREEN_OFFSET) {
            green *= GREEN_OFFSET;
        }
        this.green = green;
    }

    /**
     * Set the hue where 0 = off, 1 = red and
     *
     * @param hue
     */
    /*
     public void setHue(int hue){
     setRed(hue&0x3);
     setGreen((hue>>2)&0x3);
     }
     */
    public void setHue(int hue) {
        if (hue <= 3) {
            setRed(hue);
        } else if (hue <= 6) {
            setRed(3);
        } else if (hue <= 9) {
            setRed(2);
        } else if (hue <= 12) {
            setRed(1);
        } else {
            setRed(0);
        }

        if (hue <= 3) {
            setGreen(0);
        } else if (hue <= 6) {
            setGreen(hue - 3);
        } else if (hue <= 9) {
            setGreen(10 - (hue));
        } else if (hue <= 12) {
            setGreen(hue - 9);
        } else if (hue <= 15) {
            setGreen(16 - hue);
        }
    }

    public void setRedGreen(int red, int green) {
        setRed(red);
        setGreen(green);
    }

    public void darken() {
        setRed(Math.max(red - 1, 0));
        setGreen(Math.max(getGreen() - 1, 0));

        System.out.println(red + " " + getGreen());
    }

    public void lighten() {
        setRed(Math.min(red + 1, 3));
        setGreen(Math.min(getGreen() + 1, 3));

        System.out.println(red + " " + getGreen());
    }

    /**
     * Returns the value of mode
     *
     * @return mode value
     */
    public int getMode() {
        return mode;
    }

    /**
     * Set the value of mode
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * Compares color with given int value
     *
     * @param color
     * @return true if color is equal
     */
    public boolean is(int color) {
        return velocity() == new LColor(color).velocity();
    }

    /**
     * Compares color with given LColor value
     *
     * @param color
     * @return true if color is equal
     */
    public boolean is(LColor color) {
        return velocity() == color.velocity();
    }

    /**
     * Compares red color value with given int value
     *
     * @param red
     * @return true if color is equal
     */
    public boolean isRed(int red) {
        return getRed() == new LColor(red).getRed();
    }

    /**
     * Compares red color value with given int value
     *
     * @param green
     * @return true if color is equal
     */
    public boolean isGreen(int green) {
        return getGreen() == new LColor(green).getGreen();
    }

    /**
     * Compares red color value with given int value
     *
     * @param mode
     * @return true if color is equal
     */
    public boolean isMode(int mode) {
        return getMode() == new LColor(mode).getMode();
    }

    public Color asColor() {
        int a = 255 / 3;
        int red = (1 << (getRed() * 3)) - 1;
        int green = (1 << (getGreen() * 3)) - 1;
        return new Color(red < 0 ? 0 : (red > 255 ? 255 : red), green < 0 ? 0 : (green > 255 ? 255 : green), 0);
    }
}
