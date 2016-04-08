package se.chalmers.get_rect.adapters.libGDX;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;

public class LibGDXRectangleFactoryAdapter implements IRectangleFactoryAdapter {
    @Override
    public IRectangleAdapter make(float x, float y, float width, float height) {
        return new LibGDXRectangleAdapter(x, y, width, height);
    }
}
