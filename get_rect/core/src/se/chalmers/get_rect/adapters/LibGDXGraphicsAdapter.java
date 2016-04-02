package se.chalmers.get_rect.adapters;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LibGDXGraphicsAdapter implements IGraphicsAdapter {
    private SpriteBatch batch;
    private AssetManager assetManager;
    private GL20 graphics;

    public LibGDXGraphicsAdapter(SpriteBatch batch, GL20 graphics) {
        this.batch = batch;
        this.graphics = graphics;
        assetManager = new AssetManager();
    }

    /**
     *
     * @param img image location
     * @param x x co-ordinates
     * @param y y co-ordinates
     */
    @Override
    public void draw(String img, float x, float y) {
        if (!assetManager.containsAsset(img)) {
            System.out.println("loading " + img);
            assetManager.load(img, Texture.class);
        }

        batch.draw(assetManager.get(img, Texture.class), x, y);
    }

    /**
     * Starts the collections of sprites.
     */
    @Override
    public void start() {
        batch.begin();
    }

    /**
     * Clear the canvas
     */
    @Override
    public void clear() {
        graphics.glClearColor(1, 0, 0, 1);
        graphics.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Ends the collections of sprites and draws them
     */
    @Override
    public void end() {
        batch.end();
    }
}
