package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public class CameraAdapterStub implements ICameraAdapter {
    @Override
    public void translate(float x, float y) {

    }

    @Override
    public void translate(Point position) {

    }

    @Override
    public void update(double delta) {

    }


    @Override
    public void draw(IGraphicsAdapter g) {

    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
