import java.util.List;

public class Line {
    private Point start,end;
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1,y1); // we create a point from two values
        this.end = new Point(x2,y2); // we create a point from two values
    }

    // Return the length of the line
    public double length() {
        return start.distance(end); //we can reuse the distance method from Point class for this
    }

    // Returns the middle point of the line
    public Point middle() {//get the average of x and y from two point and return it
        double middleX = (this.start.getX()+this.end.getX())/2.0;//we calculate the middle using 2.0 to stay double
        double middleY = (this.start.getY()+this.end.getY())/2.0;//we calculate the middle using 2.0 to stay double
        return (new Point(middleX,middleY));
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }
    private static double slope(Line line){
        return (line.end.getY()-line.start.getY())/(line.end.getX()-line.end.getY());
    }

    // Returns true if the lines intersect, false otherwise
    private double[] calculateValuesForDenom(Line other){
        double[] values = new double[3];
        double thisStartX = this.start.getX();
        double thisStartY = this.start.getY();
        double thisEndX = this.end.getX();
        double thisEndY = this.end.getY();
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndX = other.end.getX();
        double otherEndY = other.end.getY();
        //denominator
        values[0] = (thisStartX - thisEndX) * (otherStartY - otherEndY) - (thisStartY - thisEndY) * (otherStartX - otherEndX);
        //t
        values[1] = ((thisStartX - otherStartX) * (otherStartY - otherEndY) - (thisStartY - otherStartY) * (otherStartX - otherEndX)) / values[0];
        //u
        values[2] = ((thisStartX - otherStartX) * (thisStartY - thisEndY) - (thisStartY - otherStartY) * (thisStartX - thisEndX)) / values[0];
        return values;
    }
    public boolean isIntersecting(Line other) {
        double[] values = calculateValuesForDenom(other);
        // Parallel or coincident
        if (values[0] == 0) {
            return false;
        }
        double t = values[1];
        double u = values[2];
        // Check if intersection lies within both line segments
        return (t >= 0 && t <= 1 && u >= 0 && u <= 1);
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if(!isIntersecting(other)) return null;
        double[] values = calculateValuesForDenom(other);

        double t = values[1];
        return (new Point((this.start.getX()+t*(this.end.getX()-this.start.getX())),
                (this.start.getY()+t*(this.end.getY()-this.start.getY()))));
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if(start == other.start && end == other.end) return true;
        if(start == other.end && end == other.start) return true;
        return false;
    }

}