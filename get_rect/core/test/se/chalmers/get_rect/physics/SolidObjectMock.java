package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.RectangleAdapterStub;

public class SolidObjectMock implements ISolidObject {
    private IRectangleAdapter boundingBox;

    public SolidObjectMock() {

    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(RectangleAdapterStub rect) {
        boundingBox = rect;
    }

    @Override
    public void onCollision(ISolidObject otherObject) {

    }
}
