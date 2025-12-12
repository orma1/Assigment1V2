package Sprites_And_Collidables;

import Game.Game;

public class BallRemover implements HitListener{
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}
