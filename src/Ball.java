import biuoop.GUI;
import biuoop.DrawSurface;
public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    // constructor
    public Ball(Point center, int r, java.awt.Color color){
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0,0);
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
    public void setVelocity(Velocity v){
        this.v = v;
    }
    public void setVelocity(double dx, double dy){
        this.v = new Velocity(dx,dy);
    }
    public Velocity getVelocity(){
        return this.v;
    }

    public void moveOneStep() {
        if (v == null){
            v = new Velocity(0,0);
        }
        if(this.getX() + v.getDx() - r <= 0 && v.getDx()<0 ){
            v.setDx(-v.getDx());
        }
        if(this.getX() + v.getDx() + r >= 200 && v.getDx()>0 ){
            v.setDx(-v.getDx());
        }
        if(this.getY() + v.getDy() - r<= 0 && v.getDy()<0 ){
            v.setDy(-v.getDy());
        }
        if(this.getY() + v.getDy() + r>= 200 && v.getDy()>0 ){
            v.setDy(-v.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

}