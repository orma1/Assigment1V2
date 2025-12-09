package Sprites_And_Collidables;

import Geometry.Point;

// Sprites.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx,dy;
    // constructor
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }
    public double getDx() {
        return this.dx;
    }
    public void setDx(double dx){
        this.dx = dx;
    }
    public double getDy() {
        return this.dy;
    }
    public void setDy(double dy){
        this.dy = dy;
    }


    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point((p.getX()+dx),(p.getY()+dy));
    }
    
}