import biuoop.DrawSurface;

public class Block implements Collidable, Sprite {
    private Rectangle collisionRect;
    private Point upperLeft;
    private double width, height;


    public Block(Rectangle collisionRect) {
        this.collisionRect = collisionRect;
    }

    public Block(Point upperLeft, double width, double height) {
        this.collisionRect = new Rectangle(upperLeft, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        // Get the rectangle borders
        double rectLeft = this.collisionRect.getUpperLeft().getX();
        double rectRight = this.collisionRect.getUpperLeft().getX() + this.collisionRect.getWidth();
        double rectTop = this.collisionRect.getUpperLeft().getY();
        double rectBottom = this.collisionRect.getUpperLeft().getY() + this.collisionRect.getHeight();

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Small threshold for double comparison
        double epsilon = 0.0001;

        // Check if the collision point is on the vertical edges (Left or Right)
        // If so, reverse the horizontal direction
        if (Math.abs(x - rectLeft) < epsilon || Math.abs(x - rectRight) < epsilon) {
            dx = -dx;
        }

        // Check if the collision point is on the horizontal edges (Top or Bottom)
        // If so, reverse the vertical direction
        if (Math.abs(y - rectTop) < epsilon || Math.abs(y - rectBottom) < epsilon) {
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }
    public void drawOn(DrawSurface surface){
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) width, (int) height);
    }

    @Override
    public void timePassed() {
        return;// for now, we do nothing
    }
    public void addToGame(Game game){
        game.addSprite(this);
        game.addCollidable(this);
    }
}