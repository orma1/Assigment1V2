// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx,dy;
    // constructor
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point((p.getX()+dx),(p.getY()+dy));
    }
}