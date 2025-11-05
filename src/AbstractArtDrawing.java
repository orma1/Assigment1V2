import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {

    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Drawer", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Line line = generateRandomLine(rand);
            lines.add(line);
            drawLine(line,d);
            d.setColor(Color.BLUE);
            Point middle = line.middle();
            d.fillCircle((int)middle.getX(),(int)middle.getY(),3);
        }
        d.setColor(Color.RED);
        for (int i = 0; i < lines.size(); i++) {
            Line line1 = lines.get(i);
            for (int j = i+1; j < lines.size(); j++) {
                Line line2 = lines.get(j);
                Point intersection = line1.intersectionWith(line2);
                if(intersection != null){
                    d.fillCircle((int)intersection.getX(),(int)intersection.getY(),3);
                }

            }
        }
        gui.show(d);
    }
    public static Line generateRandomLine(Random rand){
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
        return (new Line(x1,y1,x2,y2));
    }
    public static void drawLine(Line l, DrawSurface d){
        d.setColor(Color.BLACK);
        int x1 = (int) l.start().getX();
        int y1 = (int) l.start().getY();
        int x2 = (int) l.end().getX();
        int y2 = (int) l.end().getY();
        d.drawLine(x1,y1,x2,y2);
    }


}