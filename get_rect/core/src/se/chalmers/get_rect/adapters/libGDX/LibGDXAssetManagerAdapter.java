package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;

public class LibGDXAssetManagerAdapter implements IAssetManagerAdapter {
    private AssetManager manager;

    public LibGDXAssetManagerAdapter() {
        manager = new AssetManager();
    }

    @Override
    public void loadTexture(String path) {
        manager.load(path, Texture.class);
    }

    @Override
    public void loadSound(String path) {
        manager.load(path, Sound.class);
    }

    @Override
    public ISoundAdapter getSound(String path) {
        // todo: move this to some hashmap or something, or we'll be creating too many instances
        return new LibGDXSoundAdapter(manager.get(path, Sound.class));
    }

    @Override
    public boolean update() {
        return manager.update();
    }

    @Override
    public float getProgress() {
        return manager.getProgress();
    }

    public Texture getTexture(String path) {
        return manager.get(path, Texture.class);
    }
}
