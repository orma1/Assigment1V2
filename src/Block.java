public class Block extends Rectangle implements Collidable {

    public Block(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //if vertical edge change horizontal direction,
        //if horizontal edge change vertical direction.
    }
}
