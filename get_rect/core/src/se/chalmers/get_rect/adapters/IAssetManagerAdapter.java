package se.chalmers.get_rect.adapters;

import java.io.FileNotFoundException;

public interface IAssetManagerAdapter {
    void loadTexture(String path);
    void loadSound(String path);
    ISoundAdapter getSound(String path);
    boolean update();
    float getProgress();
    void dispose();
    void loadTextureDir(String path) throws FileNotFoundException;
    void loadSoundsDir(String path) throws FileNotFoundException;
}