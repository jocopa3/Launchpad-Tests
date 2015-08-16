/*
 * My own custom listener for the Launchpad
 */

package launchpad;

import com.rngtng.launchpad.*;

/**
 *
 * @author Jocopa3
 */
public class PadawanListener implements LaunchpadListener {

    private Launchpad device;
    
    public boolean stopThePress = false;
    
    public PadawanListener(Launchpad device){
        this.device = device;
    }
    
    @Override
    public void launchpadGridPressed(int x, int y) {
        device.changeGrid(x, y, new LColor(x&3, y&3));
    }

    @Override
    public void launchpadGridReleased(int x, int y) {
        device.changeGrid(x, y, LColor.OFF);
    }

    @Override
    public void launchpadButtonPressed(int buttonCode) {
        
    }

    @Override
    public void launchpadButtonReleased(int buttonCode) {
        if(buttonCode == LButton.UP)
            PadawanUtil.outro(device);
    }

    @Override
    public void launchpadSceneButtonPressed(int buttonCode) {
        
    }

    @Override
    public void launchpadSceneButtonReleased(int buttonCode) {
        
    }
}
