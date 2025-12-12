package Sprites_And_Collidables;

import Game.Game;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.*;

public class Paddle implements Sprite, Collidable {
    private final Geometry.Rectangle collisionRectangle;
    private final Color color;
    private final biuoop.KeyboardSensor keyboard;
    public Paddle(Geometry.Rectangle collisionRectangle, Color color, GUI gui){
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        keyboard = gui.getKeyboardSensor();
    }
    public void moveLeft(){
        Geometry.Point current = collisionRectangle.getUpperLeft();
        this.collisionRectangle.setUpperLeft(new Geometry.Point(current.getX() -5,current.getY()));
    }
    public void moveRight(){
        Geometry.Point current = collisionRectangle.getUpperLeft();
        this.collisionRectangle.setUpperLeft(new Geometry.Point(current.getX() + 5,current.getY()));
    }

    // Sprites.Sprite
    public void timePassed(){
        if(keyboard.isPressed(KeyboardSensor.LEFT_KEY)) moveLeft();
        if(keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) moveRight();
    }
    public void drawOn(DrawSurface d){
        d.setColor(color);
        Geometry.Point upperLeft = this.collisionRectangle.getUpperLeft();
        double width = this.collisionRectangle.getWidth();
        double height = this.collisionRectangle.getHeight();
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
    }

    // Sprites_And_Colidables.Collidable
    public Rectangle getCollisionRectangle(){
        return this.collisionRectangle;
    }
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity){
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
        // If so, we change the velocity according to the region
        if (Math.abs(y - rectTop) < epsilon || Math.abs(y - rectBottom) < epsilon) {
            double regionWidth = getCollisionRectangle().getWidth()/5;
            double collisionX = collisionPoint.getX();
            if(collisionX > rectLeft && collisionX <= rectLeft + regionWidth){// region 1
                return Velocity.fromAngleAndSpeed(300, 5);
            }
            else if(collisionX > rectLeft + regionWidth && collisionX <= rectLeft + regionWidth*2) {// region 2
                return Velocity.fromAngleAndSpeed(330, 5);
            }
            else if(collisionX > rectLeft + regionWidth*2 && collisionX <= rectLeft + regionWidth*3) {// region 3
                return new Velocity(dx,-dy);
            }
            else if(collisionX > rectLeft + regionWidth*3 && collisionX <= rectLeft + regionWidth*4) {// region 4
                return Velocity.fromAngleAndSpeed(30, 5);
            }
            else if(collisionX > rectLeft + regionWidth*4 && collisionX <= rectLeft + regionWidth*5) {// region 5
                return Velocity.fromAngleAndSpeed(60, 5);
            }
        }
        return new Velocity(dx,dy);
    }

    // Add this paddle to the game.
    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}