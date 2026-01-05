package Game;

import Sprites_And_Collidables.Block;
import Sprites_And_Collidables.Sprite;
import Sprites_And_Collidables.Velocity;

import java.util.List;

public class Level2 implements LevelInformation{
    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return List.of();
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public String levelName() {
        return "";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return List.of();
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }
}
