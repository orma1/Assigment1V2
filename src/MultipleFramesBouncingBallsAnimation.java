import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {
    static final int FRAME1_SIZE = 450;
    static final int FRAME1_START = 50;
    static final int FRAME2_SIZE = 150;
    static final int FRAME2_START = 450;
    static final int FRAME_HEIGHT = 600;
    static final int FRAME_WIDTH = 1000;
    public static void main(String[] args){
        int size;
        Random rand = new Random();
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < balls.length; i++) {
            size = Integer.parseInt(args[i]);
            int x,y;
            if(i < Math.floor(balls.length)/2) {
                x = rand.nextInt(50,500);
                y = rand.nextInt(50,500);
            }
            else {
                x = rand.nextInt(450,600);
                y = rand.nextInt(450,600);
            }
            Point start = new Point(x,y);
            Velocity v;
            if (size > 50) v = Velocity.fromAngleAndSpeed(x,3);
            else v = Velocity.fromAngleAndSpeed(x,200/size);
            balls[i] = new Ball(start,size, Color.BLACK);
            balls[i].setVelocity(v);

        }
        drawAnimation(balls);

    }
    static void drawAnimation(Ball[] balls) {
        GUI gui = new GUI("check", FRAME_WIDTH, FRAME_HEIGHT);
        Sleeper sleeper = new Sleeper();
        DrawSurface d;
        while (true) {
            d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(FRAME1_START,FRAME1_START,FRAME1_SIZE,FRAME1_SIZE);
            d.setColor(Color.YELLOW);
            d.fillRectangle(FRAME2_START,FRAME2_START,FRAME2_SIZE,FRAME2_SIZE);
            Point frame1Start = new Point(FRAME1_START,FRAME1_START);
            Point frame1End = new Point(FRAME1_START+FRAME1_SIZE,FRAME1_START+FRAME1_SIZE);
            Point frame2Start = new Point(FRAME2_START,FRAME2_START);
            Point frame2End = new Point(FRAME2_START+FRAME2_SIZE,FRAME2_START+FRAME2_SIZE);
            for (int i = 0; i < balls.length; i++) {
                if(i < Math.floor(balls.length)/2) balls[i].moveOneStepWithFrame(frame1Start,frame1End);
                else balls[i].moveOneStepWithFrame(frame2Start,frame2End);
                d.setColor(Color.BLUE);
                balls[i].drawOn(d);
                sleeper.sleepFor(50);  // wait for 50 milliseconds.
            }
            gui.show(d);
            }

        }


    }

