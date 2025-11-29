import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Game {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    GUI gui;
    public Game(){
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        gui = new GUI("check", FRAME_WIDTH, FRAME_HEIGHT);
        Ball ball = new Ball(400, 300, 20, Color.RED, environment);
        ball.setVelocity(new Velocity(5,5));
        ball.addToGame(this);
        int thickness = 50;

        // Top Wall
        Block top = new Block(new Point(0-thickness, 0-thickness), FRAME_WIDTH, thickness);
        top.addToGame(this);

        // Left Wall
        Block left = new Block(new Point(0-thickness, 0-thickness), thickness, FRAME_HEIGHT);
        left.addToGame(this);

        // Right Wall (Starts at 800 - 50 = 750)
        Block right = new Block(new Point(FRAME_WIDTH, 0-thickness), thickness, FRAME_HEIGHT);
        right.addToGame(this);

        // Bottom Wall (Starts at 600 - 50 = 550)
        Block bottom = new Block(new Point(0-thickness, FRAME_HEIGHT), FRAME_WIDTH, thickness);
        bottom.addToGame(this);
        /*for(int i = 0; i<= FRAME_WIDTH;i+=50){
            Block left = new Block(new Point(-50,i),50,50);
            left.addToGame(this);
            Block top = new Block(new Point(i,-50),50,50);
            top.addToGame(this);
            Block right = new Block(new Point(FRAME_WIDTH,i),50,50);
            right.addToGame(this);
            Block bottom = new Block(new Point(i,FRAME_HEIGHT),50,50);
            bottom.addToGame(this);
        }*/
    }


    // Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
    }
}
