public class Point {
    private double x,y;
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt((this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y));
    }

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if(this.x != other.x) return false;
        if(this.y != other.y) return false;
        return true;
    }

    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }
    public void setX(double x){
        this.x = x;
    }
    public double getY() {
        return this.y;
    }
    public void setY(double y){
        this.y = y;
    }
}