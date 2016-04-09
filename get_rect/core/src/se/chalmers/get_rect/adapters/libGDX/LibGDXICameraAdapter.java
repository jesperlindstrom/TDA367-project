package se.chalmers.get_rect.adapters.libGDX;


import com.badlogic.gdx.graphics.OrthographicCamera;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import com.badlogic.gdx.graphics.Camera;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public class LibGDXICameraAdapter implements ICameraAdapter {


    private Camera camera;

    public LibGDXICameraAdapter(){
        this(1920,1080);
    }

    public LibGDXICameraAdapter(float width, float height){
        this.camera = new OrthographicCamera(width,height);
    }

    @Override
    public void translate(float x, float y) {
        camera.translate(x,y,0);
    }

    @Override
    public void update() {
        camera.update();
    }

    public void draw(IGraphicsAdapter g) {
        if (g.getClass().equals(LibGDXGraphicsAdapter.class))  {
            LibGDXGraphicsAdapter graphics = (LibGDXGraphicsAdapter) g;
            graphics.setMatrix(camera.combined);
        }
    }

}