import biuoop.GUI;
import biuoop.DrawSurface;
public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    // constructor
    public Ball(Point center, int r, java.awt.Color color){
        this.center = center;
        this.r = r;
        this.color = color;
    }
    public Ball(int x, int y, int r, java.awt.Color color){
        this.center = new Point(x,y);
        this.r = r;
        this.color = color;
    }

    // accessors
    public int getX(){
        return (int) center.getX();
    }
    public int getY(){
        return (int) center.getY();
    }
    public int getSize(){
        return (int)(Math.PI*r*r);
    }
    public java.awt.Color getColor(){
        return color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface){
        surface.fillCircle(getX(),getY(),r);
    }
}