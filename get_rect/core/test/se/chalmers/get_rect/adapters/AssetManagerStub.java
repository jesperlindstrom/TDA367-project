package se.chalmers.get_rect.adapters;

import java.io.FileNotFoundException;

public class AssetManagerStub implements IAssetManagerAdapter {
    @Override
    public void loadTexture(String path) {

    }

    @Override
    public void loadSound(String path) {

    }

    @Override
    public void loadMusic(String path) {

    }

    @Override
    public ISoundAdapter getSound(String path) {
        return null;
    }

    @Override
    public IMusicAdapter getMusic(String path) {
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

    @Override
    public void loadTextureDir(String path) {

    }

    @Override
    public void loadSoundsDir(String path) {

    }

    @Override
    public void loadMusicDir(String path) throws FileNotFoundException {

    }
}
