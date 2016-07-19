/*
 * Todo: double buffer
 * Todo: buffer utils
 */

package com.jocopa3.launchpad;

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
