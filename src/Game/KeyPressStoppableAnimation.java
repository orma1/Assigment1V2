package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation){

    }

    @Override
    public void doOneFrame(DrawSurface d) {

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
