package se.chalmers.get_rect.adapters;

public class AssetManagerStub implements IAssetManagerAdapter {
    @Override
    public void loadTexture(String path) {

    }

    @Override
    public void loadSound(String path) {

    }

    @Override
    public ISoundAdapter getSound(String path) {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public float getProgress() {
        return 0;
    }

    @Override
    public void dispose() {

    }
}
