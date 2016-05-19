package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

public class LibGDXRectangleAdapter implements IRectangleAdapter {
    private Rectangle rectangle;

    public LibGDXRectangleAdapter(float x, float y, float width, float height) {
        rectangle = new Rectangle(x, y, width, height);
    }

    public LibGDXRectangleAdapter(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    private Rectangle getRealRectangle(IRectangleAdapter rect) {
        Rectangle otherRectangle;

        // Only create new rectangles if there isn't one already
        if (rect.getClass().equals(getClass())) {
            otherRectangle = ((LibGDXRectangleAdapter) rect).getRectangle();
        } else {
            otherRectangle = new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }

        return otherRectangle;
    }

    public IRectangleAdapter getIntersection(IRectangleAdapter rect) {
        Rectangle otherRectangle = getRealRectangle(rect);
        Rectangle intersection = new Rectangle();
        Intersector.intersectRectangles(rectangle, otherRectangle, intersection);
        return new LibGDXRectangleAdapter(intersection);
    }

    /**
     * Check whether this rectangle intersects another
     * @param rect Another rectangle
     * @return The side of the intersection, or null the rectangles don't overlap.
     */
    @Override
    public CollisionData intersects(IRectangleAdapter rect) {
        Rectangle otherRectangle = getRealRectangle(rect);
        Rectangle intersection = new Rectangle();
        Intersector.intersectRectangles(rectangle, otherRectangle, intersection);

        CollisionData collisionData = new CollisionData();

        if(rectangle.overlaps(otherRectangle)) {

            if (intersection.x > rectangle.x) {
                collisionData.set(Side.RIGHT);
            }

            if (intersection.y > rectangle.y) {
                collisionData.set(Side.TOP);
            }

            if (intersection.x + intersection.width < rectangle.x + rectangle.width) {
                collisionData.set(Side.LEFT);
            }

            if (intersection.y + intersection.height < rectangle.y + rectangle.height) {
                collisionData.set(Side.BOTTOM);
            }

            return collisionData;
        }
        return null;
    }

    @Override
    public int getWidth() {
        return (int)rectangle.getWidth();
    }

    @Override
    public int getHeight() {
        return (int)rectangle.getHeight();
    }

    @Override
    public int getX() {
        return (int)rectangle.getX();
    }

    @Override
    public int getY() {
        return (int)rectangle.getY();
    }

    @Override
    public Point getPosition() {
        return new Point((int)rectangle.getX(), (int)rectangle.getY());
    }

    @Override
    public void setPosition(Point newPoint) {

        rectangle.setX(newPoint.getX());
        rectangle.setY(newPoint.getY());

    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
