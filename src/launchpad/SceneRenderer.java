/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package launchpad;

import com.rngtng.launchpad.Launchpad;

/**
 *
 * @author Jocopa3
 */
public class SceneRenderer extends Renderer {

    public SceneRenderer(Launchpad device) {
        super(device);
    }

    @Override
    public void render(){
        device.changeAll(Scene);
        clearScene();
    }
}
