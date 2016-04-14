package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.css.Rect;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;

public class LibGDXRectangleAdapter implements IRectangleAdapter {
    private Rectangle rectangle;

    public LibGDXRectangleAdapter(float x, float y, float width, float height) {
        rectangle = new Rectangle(x, y, width, height);

    }

    @Override
    public boolean intersects(IRectangleAdapter rect) {
        Rectangle otherRectangle;

        // Only create new rectangles if there isn't one already
        if (rect.getClass().equals(getClass())) {
            otherRectangle = ((LibGDXRectangleAdapter) rect).getRectangle();
        } else {
            otherRectangle = new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }

        return rectangle.overlaps(otherRectangle);
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

    public COLLISION_SIDE getCollisionSide(IRectangleAdapter rectangle) {
        if (this.getY() + this.getHeight() == rectangle.getY()) {
            return COLLISION_SIDE.TOP;
        }
        if (this.getY() == rectangle.getY() + rectangle.getHeight()) {
            return COLLISION_SIDE.BOTTOM;
        }
        if (this.getX() + this.getWidth() == rectangle.getX()) {
            return COLLISION_SIDE.RIGHT;
        }
        if (this.getX() == rectangle.getX() + rectangle.getWidth()) {
            return COLLISION_SIDE.LEFT;
        }

        return COLLISION_SIDE.NO_COLLISION;
    }

}
