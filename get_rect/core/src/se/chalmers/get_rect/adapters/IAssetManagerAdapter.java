package se.chalmers.get_rect.adapters;

public interface IAssetManagerAdapter {
    void loadTexture(String path);
    void loadSound(String path);
    ISoundAdapter getSound(String path);
    boolean update();
    float getProgress();
}