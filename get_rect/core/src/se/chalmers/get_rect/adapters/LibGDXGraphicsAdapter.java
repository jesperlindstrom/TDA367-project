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

    /**
     *
     * @param img image location
     * @param x x co-ordinates
     * @param y y co-ordinates
     */
    @Override
    public void draw(String img, float x, float y) {
        batch.draw(new Texture(img), x, y);
    }

    /**
     * Starts the collections of sprites.
     */
    @Override
    public void start() {
        batch.begin();
    }

    /**
     * Ends the collections of sprites and draws them
     */
    @Override
    public void end() {
        batch.end();
    }


}
