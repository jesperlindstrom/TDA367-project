package se.chalmers.get_rect.adapters;

public interface IRectangleFactoryAdapter {
    IRectangleAdapter make(float x, float y, float width, float height);
}