import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.*;

public class Paddle implements Sprite, Collidable {
    private Rectangle collisionRectangle;
    private Color color;
    private biuoop.KeyboardSensor keyboard;
    public Paddle(Rectangle collisionRectangle, Color color, GUI gui){
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        keyboard = gui.getKeyboardSensor();
    }
    public void moveLeft(){
        Point current = collisionRectangle.getUpperLeft();
        this.collisionRectangle.setUpperLeft(new Point(current.getX() -5,current.getY()));
    }
    public void moveRight(){
        Point current = collisionRectangle.getUpperLeft();
        this.collisionRectangle.setUpperLeft(new Point(current.getX() + 5,current.getY()));
    }

    // Sprite
    public void timePassed(){
        if(keyboard.isPressed(KeyboardSensor.LEFT_KEY)) moveLeft();
        if(keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) moveRight();
    }
    public void drawOn(DrawSurface d){
        d.setColor(color);
        Point upperLeft = this.collisionRectangle.getUpperLeft();
        double width = this.collisionRectangle.getWidth();
        double height = this.collisionRectangle.getHeight();
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
    }

    // Collidable
    public Rectangle getCollisionRectangle(){
        return this.collisionRectangle;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity){
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        // Get the rectangle borders
        double rectLeft = this.collisionRectangle.getUpperLeft().getX();
        double rectRight = this.collisionRectangle.getUpperLeft().getX() + this.collisionRectangle.getWidth();
        double rectTop = this.collisionRectangle.getUpperLeft().getY();
        double rectBottom = this.collisionRectangle.getUpperLeft().getY() + this.collisionRectangle.getHeight();


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

    // Add this paddle to the game.
    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}