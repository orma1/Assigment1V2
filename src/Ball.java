import biuoop.DrawSurface;

import java.awt.*;
import java.util.Random;

public class Ball implements Sprite {
    private Point center;
    private int r; //radius
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    // constructor
    public Ball(Point center, int r, java.awt.Color color){

        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0,0);
    }
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
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
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment){
        this.center = new Point(x,y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
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
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(),getY(),r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
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
    //move one step according to velocity
    public void moveOneStep() {
        //if no frame is given, we set the default to 200
        //moveOneStepWithFrame(new Point(0,0), new Point(200,200));
        if (v == null) v = new Velocity(0,0);// if velocity is null we create 0 velocity to not crash
        //we create the Line of the trajectory from the current position to the new one after applying velocity
        Line trajectory = new Line(center,
                new Point(center.getX()+v.getDx(),center.getY()+v.getDy()));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if(collisionInfo == null){
            //if no collisions, move like normal
            center.setX(center.getX()+v.getDx());
            center.setY(center.getY()+v.getDy());

        }
        else {

            Point collisionPoint = collisionInfo.collisionPoint();

            // 2. Define a small "epsilon" distance to back up
            double epsilon = 0.00001;

            // 3. Calculate the new location slightly "before" the impact
            // We back up on the X axis opposite to the velocity direction
            double newX = collisionPoint.getX();
            if (v.getDx() > 0) {
                newX = newX - epsilon;
            } else if (v.getDx() < 0) {
                newX = newX + epsilon;
            }

            // We back up on the Y axis opposite to the velocity direction
            double newY = collisionPoint.getY();
            if (v.getDy() > 0) {
                newY = newY - epsilon;
            } else if (v.getDy() < 0) {
                newY = newY + epsilon;
            }

            // 4. Move the ball to this "almost" collision point
            this.center.setX(newX);
            this.center.setY(newY);

            // 5. Notify the hit object and update velocity
            Collidable collided = collisionInfo.collisionObject();

            // Important: We pass the EXACT collision point to the hit method,
            // not the "almost" point, because the block expects to know where it was hit.
            this.v = collided.hit(collisionPoint, this.v);
        }
    }
    //move step with borders of the frame.
    public void moveOneStepWithFrame(Point p1, Point p2) {
        if (v == null){
            //if there is no velocity, we create one, so we will not get null reference error
            v = new Velocity(0,0);
        }
        if(this.getX() + v.getDx() - r <= p1.getX() && v.getDx()<0 ){
            //check if we get to the left border with negative x velocity
            // if so we change the x velocity to be positive
            v.setDx(-v.getDx());
            //we set the ball position to be exactly at the left border,
            // to make sure it does not go outside
            this.center.setX(p1.getX() + r);
        }
        if(this.getX() + v.getDx() + r >= p2.getX() && v.getDx()>0 ){
            v.setDx(-v.getDx());
            this.center.setX(p2.getX() - r);
        }
        if(this.getY() + v.getDy() - r<= p1.getY() && v.getDy()<0 ){
            v.setDy(-v.getDy());
            this.center.setY(p1.getY() + r);
        }
        if(this.getY() + v.getDy() + r>= p2.getY() && v.getDy()>0 ){
            v.setDy(-v.getDy());
            this.center.setY(p2.getY() - r);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    public void addToGame(Game game){
        game.addSprite(this);
    }
}