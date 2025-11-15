import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.*;
import java.util.Random;

public class MultipleBouncingBallsAnimation {

        public static void main(String [] args){
            int size;
            Ball[] balls = new Ball[args.length];
            Random rand = new Random();
            for (int i = 0; i < args.length; i++) {
                size = Integer.parseInt(args[i]);//size is from the run arguments
                int x = rand.nextInt(200);//random x and y coordinate.
                int y = rand.nextInt(200);
                Point start = new Point(x,y);
                Velocity v;
                if (size > 50) v = Velocity.fromAngleAndSpeed(x,3);
                else v = Velocity.fromAngleAndSpeed(x,200.0/size);
                balls[i] = new Ball(start,size, Color.BLACK);
                balls[i].setVelocity(v);
            }
            drawAnimation(balls);
        }
        static void drawAnimation(Ball[] balls) {
            GUI gui = new GUI("title", 200, 200);
            Sleeper sleeper = new Sleeper();
            DrawSurface d;
            while (true) {
                d = gui.getDrawSurface();
                for (int i = 0; i < balls.length; i++) {
                    balls[i].moveOneStep();
                    balls[i].drawOn(d);
                    sleeper.sleepFor(50);  // wait for 50 milliseconds.
                }
                gui.show(d);
            }
    }
}
