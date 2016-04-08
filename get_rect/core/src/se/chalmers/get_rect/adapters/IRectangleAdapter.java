package se.chalmers.get_rect.adapters;

public interface IRectangleAdapter {
    boolean intersects(IRectangleAdapter otherRectangle);
    float getWidth();
    float getHeight();
    float getX();
    float getY();
}
