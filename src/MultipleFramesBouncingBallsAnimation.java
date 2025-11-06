import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {

        public static void main(String [] args){
            int size;
            List<Ball> balls = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < args.length; i++) {
                size = Integer.parseInt(args[i]);
                int x = rand.nextInt(200);
                int y = rand.nextInt(200);
                Point start = new Point(x,y);
                Velocity v;
                if (size > 50) v = Velocity.fromAngleAndSpeed(x,3);
                else v = new Velocity(x,200/size);
                drawAnimation(start, v.getDx(),v.getDy(), size);


            }
        }
        static void drawAnimation(Point start, double dx, double dy) {
            int r = 30;
            drawAnimation(start,dx,dy,r);
        }
        static void drawAnimation(Point start, double dx, double dy, int r) {
            GUI gui = new GUI("title", 200, 200);
            Sleeper sleeper = new Sleeper();
            Ball ball = new Ball((int) start.getX(), (int) start.getY(), r, java.awt.Color.BLACK);
            ball.setVelocity(5, 5);
            while (true) {
                ball.moveOneStep();
                DrawSurface d = gui.getDrawSurface();
                ball.drawOn(d);
                gui.show(d);
                sleeper.sleepFor(50);  // wait for 50 milliseconds.
            }


    }


}
