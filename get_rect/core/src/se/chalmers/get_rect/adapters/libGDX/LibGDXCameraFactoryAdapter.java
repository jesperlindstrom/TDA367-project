package se.chalmers.get_rect.adapters.libGDX;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.ICameraFactoryAdapter;

public class LibGDXCameraFactoryAdapter implements ICameraFactoryAdapter {
    @Override
    public ICameraAdapter make(int width, int height) {
        return new LibGDXCameraAdapter(width, height);
    }
}
