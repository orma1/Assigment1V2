package Sprites_And_Collidables;

import Game.Game;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.*;
public class Block implements Collidable, Sprite {
    private final Geometry.Rectangle collisionRect;
    private final Geometry.Point upperLeft;
    private final double width;
    private final double height;
    private final Color color;




    public Block(Point upperLeft, double width, double height, Color color) {
        this.color = color;
        this.collisionRect = new Geometry.Rectangle(upperLeft, width, height);
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRect;
    }

    @Override
    public Velocity hit(Geometry.Point collisionPoint, Velocity currentVelocity) {
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
        double epsilon = 0.001;

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
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) width, (int) height);
    }

    @Override
    public void timePassed() {
        // for now, we do nothing
    }
    public void addToGame(Game game){
        game.addSprite(this);
        game.addCollidable(this);
    }
}