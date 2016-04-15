package se.chalmers.get_rect.adapters.libGDX;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import com.badlogic.gdx.graphics.Camera;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

public class LibGDXCameraAdapter implements ICameraAdapter {
    private Camera camera;

    public LibGDXCameraAdapter(float width, float height){
        camera = new OrthographicCamera(width,height);
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
        return camera.viewportWidth;
    }

    @Override
    public float getHeight() {
        return camera.viewportHeight;
    }
}
