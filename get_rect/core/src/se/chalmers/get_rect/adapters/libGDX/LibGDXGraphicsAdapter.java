package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class LibGDXGraphicsAdapter implements IGraphicsAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private LibGDXAssetManagerAdapter assetManager;
    private GL20 graphics;
    private Map<Colors,Color> colorsMap;

    public LibGDXGraphicsAdapter(SpriteBatch batch, GL20 graphics, LibGDXAssetManagerAdapter assetManager) {
        this.batch = batch;
        this.graphics = graphics;
        this.assetManager = assetManager;
        font = new BitmapFont();
        initColor();
    }

    /**
     *
     * @param img image location
     * @param x x co-ordinates
     * @param y y co-ordinates
     */
    @Override
    public void draw(String img, float x, float y) {
        Texture texture = assetManager.getTexture(img);
        batch.draw(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw(String img, float x, float y, float width, float height) {
        batch.draw(assetManager.getTexture(img), x, y, width, height);
    }

    @Override
    public void draw(String img, float x, float y, float width, float height, float offsetX, float offsetY) {
        Texture texture = assetManager.getTexture(img);
        offsetY = texture.getHeight() - offsetY - height;
        TextureRegion region = new TextureRegion(texture, (int)offsetX, (int)offsetY, (int)width, (int)height);

        batch.draw(region, x, y, width, height);
    }

    @Override
    public void draw(String img, Point point, float width, float height, Point offsetPoint) {
        draw(img, point.getX(), point.getY(), width, height, offsetPoint.getX(), offsetPoint.getY());
    }

    @Override
    public void draw(String img, Point point) {
        draw(img, point.getX(), point.getY());
    }

    @Override
    public void draw(String img, Point point, float width, float height) {
        draw(img, point.getX(), point.getY(), width, height);
    }

    //todo den är jättebroken
    public void draw(String img, Point position, Point origin, float scaleX, float scaleY, float rotation) { // int srcX, int srcY, int srcWidth, int srcHeight,
        Texture texture = assetManager.getTexture(img);
        TextureRegion textureRegion = new TextureRegion( texture, position.getX(), position.getY(), texture.getWidth(), texture.getHeight());
        batch.draw(textureRegion, (float)position.getX(), (float)position.getY(), origin.getX(), origin.getY(), texture.getWidth(), texture.getHeight(), scaleX, scaleY, rotation);
    }

    @Override
    public void drawText(String text, Point point) {
        font.setColor(Color.RED);
        font.draw(batch, text, point.getX(), point.getY());
    }

    @Override
    public void drawText(String text, Point point, Colors color) {
        font.setColor(colorsMap.get(color));

        font.draw(batch,text,point.getX(), point.getY());
    }

    @Override
    public void drawText(String text, int x, int y) {
        drawText(text, new Point(x, y));
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
        graphics.glClearColor(0, 0, 0, 1);
        graphics.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Ends the collections of sprites and draws them
     */
    @Override
    public void end() {
        batch.end();
    }

    public void setMatrix(Matrix4 cameraMatrix) {
        batch.setProjectionMatrix(cameraMatrix);
    }

    private void initColor(){
        this.colorsMap = new HashMap<>();
        colorsMap.put(Colors.WHITE, Color.WHITE);
        colorsMap.put(Colors.BLACK, Color.BLACK);
        colorsMap.put(Colors.RED, Color.RED);

    }
}
