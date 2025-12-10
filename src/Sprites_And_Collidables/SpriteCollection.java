package Sprites_And_Collidables;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> sprites;
    public SpriteCollection(){
        this.sprites = new ArrayList<>();
    }
    public void addSprite(Sprite s){
        sprites.add(s);
    }
    public void removeSprite(Sprite s){sprites.remove(s);}

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}