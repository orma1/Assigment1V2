import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables  = new ArrayList<>();

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (collidables.isEmpty()) return null;
        Collidable closestColidable = collidables.getFirst();
        Collidable currentColidable;
        Point closest = null;
        double mimimumDistance = Double.MAX_VALUE;


        for (int i = 0; i < collidables.size(); i++) {
            currentColidable = collidables.get(i);
            List<Point> collisionPoints = currentColidable.getCollisionRectangle().intersectionPoints(trajectory);
            for (int j = 0; j < collisionPoints.size(); j++) {
                    Point currentPoint = collisionPoints.get(j);
                    if(currentPoint == null) continue;
                    double currentDistance = currentPoint.distance(trajectory.start());
                    if (closest == null) {
                        closest = currentPoint;
                        mimimumDistance = currentDistance;
                    }
                    else {
                        if (currentDistance < mimimumDistance) {
                            closestColidable = currentColidable;
                            closest = currentPoint;
                        }
                    }

            }
        }
        if(closest == null) return null;
        //if we do have collidables in the list, but we found no collision points, we return null
        return new CollisionInfo(closest, closestColidable);
    }
}