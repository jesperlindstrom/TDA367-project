package se.chalmers.get_rect.adapters.libGDX;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import com.badlogic.gdx.graphics.Camera;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;

import se.chalmers.get_rect.utilities.Point;

public class LibGDXCameraAdapter implements ICameraAdapter {
    private Camera camera;
    private Viewport viewport;

    public LibGDXCameraAdapter(float width, float height){
        camera = new OrthographicCamera(width, height);
        viewport = new ScreenViewport(camera);
    }

    @Override
    public Point getRelativePosition(Point point) {
        Vector3 pos = camera.unproject(new Vector3(point.getX(), point.getY(), 0));

        return new Point((int)pos.x, (int)pos.y);
    }

    @Override
    public void translate(float x, float y) {
        camera.translate(x,y,0);
    }

    @Override
    public void translate(Point point) {
        translate(point.getX(), point.getY());
    }

    @Override
    public void update(double delta) {
        camera.update();
        viewport.update((int)getWidth(), (int)getHeight());

    }

    public void draw(IGraphicsAdapter g) {
        if (g.getClass().equals(LibGDXGraphicsAdapter.class))  {
            LibGDXGraphicsAdapter graphics = (LibGDXGraphicsAdapter) g;
            graphics.setMatrix(camera.combined);
        }
    }
    @Override
    public Point getPosition(){
        return new Point((int)camera.position.x, (int)camera.position.y);
    }

    @Override
    public float getWidth() {
        return Gdx.graphics.getWidth()/*camera.viewportWidth */;
    }

    @Override
    public float getHeight() {
        return Gdx.graphics.getHeight()/*camera.viewportHeight*/;
    }
}
