package se.chalmers.get_rect.adapters;

public class RectangleFactoryAdapterStub implements IRectangleFactoryAdapter {
    @Override
    public IRectangleAdapter make(float x, float y, float width, float height) {
        return new RectangleAdapterStub();
    }
}
