import biuoop.DrawSurface;
public class Ball {
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
    //move one step according to velocity
    public void moveOneStep() {
        //if no frame is given, we set the default to 200
        //moveOneStepWithFrame(new Point(0,0), new Point(200,200));
        if (v == null) v = new Velocity(0,0);
        Line trajectory = new Line(center,
                new Point(center.getX()+v.getDx(),center.getY()+v.getDy()));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if(collisionInfo == null){
            //if no collisions, move like normal
            center.setX(center.getX()+v.getDx());
            center.setY(center.getY()+v.getDy());

        }
        else{
            //we move the ball to almost the collision point
            //TODO - Implement almost collision point
            //we notify the object, and we update the velocity through hit.
           Collidable collided = collisionInfo.collisionObject();
           v = collided.hit(collisionInfo.collisionPoint(),v);
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
}