package Game;

import Sprites_And_Collidables.Collidable;
import Sprites_And_Collidables.Paddle;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites_And_Collidables.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Game {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private Paddle paddle;
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

    // Initialize a new game: create the Blocks and Sprites.Ball (and Sprites.Paddle)
    // and add them to the game.
    public void initialize() {
        gui = new GUI("check", FRAME_WIDTH, FRAME_HEIGHT);
        paddle = new Paddle(new Rectangle(new Geometry.Point(300,500),100,20),Color.YELLOW,gui);
        paddle.addToGame(this);
        Ball ball = new Ball(400, 300, 10, Color.WHITE, environment);
        ball.setVelocity(new Velocity(5,5));
        ball.addToGame(this);
        Ball secondBall = new Ball(300, 300, 10, Color.WHITE, environment);
        secondBall.setVelocity(new Velocity(5,5));
        secondBall.addToGame(this);
        int thickness = 20;
        // Top Wall
        Block top = new Block(new Geometry.Point(0, 0), FRAME_WIDTH, thickness, Color.GRAY);
        top.addToGame(this);
        // Left Wall
        Block left = new Block(new Geometry.Point(0, 0), thickness, FRAME_HEIGHT, Color.GRAY);
        left.addToGame(this);
        // Right Wall (Starts at 800 - 50 = 750)
        Block right = new Block(new Geometry.Point(FRAME_WIDTH-thickness, 0), thickness, FRAME_HEIGHT, Color.GRAY);
        right.addToGame(this);
        // Bottom Wall (Starts at 600 - 50 = 550)
        Block bottom = new Block(new Geometry.Point(0, FRAME_HEIGHT-thickness), FRAME_WIDTH, thickness, Color.GRAY);
        bottom.addToGame(this);
        for (int i = 100; i < FRAME_WIDTH-thickness; i+=thickness) {//Gray Row
            Block current = new Block(new Geometry.Point(i,100),thickness,20,Color.GRAY);
            current.addToGame(this);
        }
        for (int i = 120; i < FRAME_WIDTH-thickness; i+=thickness) {//Red Row
            Block current = new Block(new Geometry.Point(i,120),thickness,20,Color.RED);
            current.addToGame(this);
        }
        for (int i = 140; i < FRAME_WIDTH-thickness; i+=thickness) {//Yellow Row
            Block current = new Block(new Geometry.Point(i,140),thickness,20,Color.YELLOW);
            current.addToGame(this);
        }
        for (int i = 160; i < FRAME_WIDTH-thickness; i+=thickness) {//Blue Row
            Block current = new Block(new Point(i,160),thickness,20,Color.BLUE);
            current.addToGame(this);
        }
        for (int i = 180; i < FRAME_WIDTH-thickness; i+=thickness) {//Pink Row
            Block current = new Block(new Geometry.Point(i,180),thickness,20,Color.PINK);
            current.addToGame(this);
        }
        for (int i = 200; i < FRAME_WIDTH-thickness; i+=thickness) {//Green Row
            Block current = new Block(new Geometry.Point(i,200),thickness,20,Color.GREEN);
            current.addToGame(this);
        }
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
