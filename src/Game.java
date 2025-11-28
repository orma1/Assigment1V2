import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
public class Game {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;

    public void addCollidable(Collidable c){
        environment.addCollidable(c);
    }
    public void addSprite(Sprite s){
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize(){
      //TODO - read idfk
    }

    // Run the game -- start the animation loop.
    public void run(){
        public void run() {
            GUI gui = new GUI("check", FRAME_WIDTH, FRAME_HEIGHT);
            Sleeper sleeper = new Sleeper();
            //...
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
}