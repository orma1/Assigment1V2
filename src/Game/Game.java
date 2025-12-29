package Game;

import Sprites_And_Collidables.Collidable;
import Sprites_And_Collidables.Paddle;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites_And_Collidables.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.*;

public class Game implements Animation {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private Paddle paddle;
    GUI gui;
    private final Counter numberOfBlocks;
    private final BlockRemover blockRemover;
    private final Counter numberOfBalls;
    private final BallRemover ballRemover;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    public Game(){
        //initialize data members
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        numberOfBlocks = new Counter();
        numberOfBalls = new Counter();
        blockRemover = new BlockRemover(this, numberOfBlocks);
        ballRemover = new BallRemover(this, numberOfBalls);
    }
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    public void removeCollidable(Collidable c){environment.removeCollidable(c);}
    public boolean shouldStop() { return !this.running; }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    public void removeSprite(Sprite s){sprites.removeSprite(s);}

    // Initialize a new game: create the Blocks and Sprites.Ball (and Sprites.Paddle)
    // and add them to the game.
    public void initialize() {
        gui = new GUI("check", FRAME_WIDTH, FRAME_HEIGHT);
        runner = new AnimationRunner(60, gui);
        keyboard = gui.getKeyboardSensor();
        PrintingHitListener hl = new PrintingHitListener();
        paddle = new Paddle(new Rectangle(new Geometry.Point(300,500),100,20),Color.YELLOW,gui);
        paddle.addToGame(this);
        Ball ball = new Ball(400, 300, 10, Color.WHITE, environment);
        ball.setVelocity(new Velocity(5,5));
        ball.addToGame(this);
        numberOfBalls.increase(1);
        Ball secondBall = new Ball(300, 300, 10, Color.WHITE, environment);
        secondBall.setVelocity(new Velocity(5,5));
        secondBall.addToGame(this);
        numberOfBalls.increase(1);
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
        bottom.addHitListener(ballRemover);
        for (int i = 100; i < FRAME_WIDTH-thickness; i+=thickness) {//Gray Row
            Block current = new Block(new Geometry.Point(i,100),thickness,20,Color.GRAY);
            current.addToGame(this);
            current.addHitListener(hl);
            numberOfBlocks.increase(1);
            current.addHitListener(blockRemover);
        }
        for (int i = 120; i < FRAME_WIDTH-thickness; i+=thickness) {//Red Row
            Block current = new Block(new Geometry.Point(i,120),thickness,20,Color.RED);
            current.addToGame(this);
            current.addHitListener(hl);
            numberOfBlocks.increase(1);
            current.addHitListener(blockRemover);
        }
        for (int i = 140; i < FRAME_WIDTH-thickness; i+=thickness) {//Yellow Row
            Block current = new Block(new Geometry.Point(i,140),thickness,20,Color.YELLOW);
            current.addToGame(this);
            current.addHitListener(hl);
            numberOfBlocks.increase(1);
            current.addHitListener(blockRemover);
        }
        for (int i = 160; i < FRAME_WIDTH-thickness; i+=thickness) {//Blue Row
            Block current = new Block(new Point(i,160),thickness,20,Color.BLUE);
            current.addToGame(this);
            current.addHitListener(hl);
            numberOfBlocks.increase(1);
            current.addHitListener(blockRemover);
        }
        for (int i = 180; i < FRAME_WIDTH-thickness; i+=thickness) {//Pink Row
            Block current = new Block(new Geometry.Point(i,180),thickness,20,Color.PINK);
            current.addToGame(this);
            current.addHitListener(hl);
            numberOfBlocks.increase(1);
            current.addHitListener(blockRemover);
        }
        for (int i = 200; i < FRAME_WIDTH-thickness; i+=thickness) {//Green Row
            Block current = new Block(new Geometry.Point(i,200),thickness,20,Color.GREEN);
            current.addToGame(this);
            current.addHitListener(hl);
            numberOfBlocks.increase(1);
            current.addHitListener(blockRemover);
        }
    }
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();

            if(numberOfBlocks.getValue() == 0 || numberOfBalls.getValue() == 0){//if no balls or no block we finish
                gui.close();
                this.running = false;
            }




    }

    // Run the game -- start the animation loop.
    public void run() {
        this.running = true;
        initialize();
        this.runner.run(this);
    }


}
