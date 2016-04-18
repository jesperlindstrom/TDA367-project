package se.chalmers.get_rect.adapters;

public class CameraFactoryAdapterStub implements ICameraFactoryAdapter {
    @Override
    public ICameraAdapter make(int width, int height) {
        return new CameraAdapterStub();
    }
}
