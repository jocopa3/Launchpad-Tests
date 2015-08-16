/*
 * Simple main class
 */

package launchpad;

import com.rngtng.launchpad.*;

/**
 *
 * @author Jocopa3
 */
public class LaunchPadawan {

    public static Launchpad device;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        device = new Launchpad();
        new HelperPad(device);
    }
}
