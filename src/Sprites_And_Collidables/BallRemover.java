package Sprites_And_Collidables;

import Game.GameLevel;

public class BallRemover implements HitListener{
    private GameLevel gameLevel;
    private Counter remainingBalls;

    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.remainingBalls.decrease(1);
    }
}
