import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width, height;
    List<Line> recLines;
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

    }
    private void setRecLines(){
        recLines = new ArrayList<>();
        //top
        recLines.add(new Line(upperLeft,
                new Point(upperLeft.getX()+width,upperLeft.getY())));
        //bottom
        recLines.add(new Line(new Point(upperLeft.getX(), upperLeft.getY()+height)
                ,new Point(upperLeft.getX()+width,upperLeft.getY()+height)));
        //left
        recLines.add(new Line(upperLeft,
                new Point(upperLeft.getX(),upperLeft.getY()+height)));
        //right
        recLines.add(new Line(new Point(upperLeft.getX()+width,upperLeft.getY()),
                new Point(upperLeft.getX()+width,upperLeft.getY()+height)));
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line){

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < recLines.size(); i++) {
            points.add(line.intersectionWith(recLines.get(i)));
        }
        return points;
    }

    // Return the width and height of the rectangle
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft(){
        return upperLeft;
    }
}