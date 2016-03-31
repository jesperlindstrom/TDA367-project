package se.chalmers.get_rect.adapters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by simsund on 2016-03-31.
 */
public class LibGDXGraphicsAdapter implements IGraphicsAdapter {

    private SpriteBatch batch;

    public LibGDXGraphicsAdapter(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void draw(String img, float x, float y) {
        batch.draw(new Texture(img), x, y);
    }

    @Override
    public void start() {
        batch.begin();
    }

    @Override
    public void end() {
        batch.end();
    }


}
