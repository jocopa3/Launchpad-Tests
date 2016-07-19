package com.jocopa3.macropad;

import com.rngtng.launchpad.Launchpad;
import javax.swing.UIManager;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Matt
 */
public class main {

    Launchpad launchpad;

    
    public static void setA(Launchpad launchpad, int numerator, int denominator) {
        numerator = Math.max(Math.min(numerator, 16), 1);
        denominator = Math.max(Math.min(numerator, 18), 3);
        if (numerator < 9) {
            launchpad.output(0xB0, 0x1E, ((numerator - 1) * 0x10) + (denominator - 3));
        } else {
            launchpad.output(0xB0, 0x1F, ((numerator - 9) * 0x10) + (denominator - 3));
        }
    }

    public static void send(Launchpad device, String message, String splitter) {
        String[] bytesString = message.split(splitter);
        byte[] bytes = new byte[bytesString.length];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = Byte.valueOf(bytesString[i], 16);
        }

        device.outputRaw(bytes);
    }

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
        setA(device, 1, 3);
        new MacroPad(device);
    }
}
