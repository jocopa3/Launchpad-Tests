package com.jocopa3.macropad;

import com.rngtng.launchpad.Launchpad;
import javax.swing.UIManager;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Matt
 */
public class main {

    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        /* Set the system look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.print("Could not set look and feel");
        }

        Launchpad device = new Launchpad();
        //device.setDutyCycle(1, 5);
        new MacroPad(device);
    }
}
