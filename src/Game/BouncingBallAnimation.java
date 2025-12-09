package Game;

import Geometry.Point;
import Sprites_And_Collidables.Ball;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


public class BouncingBallAnimation {
    public static void main(String [] args){
        drawAnimation(new Point(Integer.parseInt(args[0]),Integer.parseInt(args[1])),
                Integer.parseInt(args[2]),Integer.parseInt(args[3]));
    }
    static void drawAnimation(Geometry.Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, java.awt.Color.BLACK);
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

