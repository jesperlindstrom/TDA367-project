package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.physics.SolidCollision;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

public class LibGDXRectangleAdapter implements IRectangleAdapter {
    private Rectangle rectangle;

    public LibGDXRectangleAdapter(float x, float y, float width, float height) {
        rectangle = new Rectangle(x, y, width, height);
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

    /**
     * Check whether this rectangle intersects another
     * @param rect Another rectangle
     * @return The side of the intersection, or null the rectangles don't overlap.
     */
    @Override
    public SolidCollision intersects(IRectangleAdapter rect) {
        Rectangle otherRectangle = getRealRectangle(rect);
        Rectangle intersection = new Rectangle();
        Intersector.intersectRectangles(rectangle, otherRectangle, intersection);

        SolidCollision solidCollision = new SolidCollision();

        if (this.getX() + this.getWidth() >= otherRectangle.getX()) {
            solidCollision.set(Side.RIGHT);
        }

        if (this.getY() + this.getHeight() >= otherRectangle.getY()) {
            solidCollision.set(Side.TOP);
        }

        if (this.getX() <= otherRectangle.getX() + otherRectangle.getWidth()) {
            solidCollision.set(Side.LEFT);
        }

        if (this.getY() <= otherRectangle.getY() + otherRectangle.getHeight()) {
           solidCollision.set(Side.BOTTOM);
        }
        return solidCollision;
    }

    @Override
    public float getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public float getHeight() {
        return rectangle.getHeight();
    }

    @Override
    public float getX() {
        return rectangle.getX();
    }

    @Override
    public float getY() {
        return rectangle.getY();
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
