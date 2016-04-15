package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.utilities.SideData;
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
    public SideData intersects(IRectangleAdapter rect, boolean isSolid) {
        Rectangle otherRectangle = getRealRectangle(rect);
        Rectangle intersection = new Rectangle();
        Intersector.intersectRectangles(rectangle, otherRectangle, intersection);

        SideData sideData = new SideData();

        if(rectangle.overlaps(otherRectangle)) {

            if (intersection.x > rectangle.x) {
                sideData.set(Side.RIGHT, isSolid);
            }

            if (intersection.y > rectangle.y) {
                sideData.set(Side.TOP, isSolid);
            }

            if (intersection.x + intersection.width < rectangle.x + rectangle.width) {
                sideData.set(Side.LEFT, isSolid);
            }

            if (intersection.y + intersection.height < rectangle.y + rectangle.height) {
                sideData.set(Side.BOTTOM, isSolid);
            }
        }
        return sideData;
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
