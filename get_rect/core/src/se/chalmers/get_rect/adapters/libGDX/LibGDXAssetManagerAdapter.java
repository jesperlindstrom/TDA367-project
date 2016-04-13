package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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

    @Override
    public void dispose() {
      manager.dispose();
    }

    public Texture getTexture(String path) {
        return manager.get(path, Texture.class);
    }

    /**
     * Loads all .png files in directory path and its subdirectories.
     * @param DirectoryPath path to the directory
     */
    @Override
    public void loadTextureDir(String DirectoryPath) {
        FileHandle dir = Gdx.files.internal(DirectoryPath);

        if (!dir.isDirectory()) {
            System.out.println("Not a Directory");
            return;
        }
        FileHandle[] fileList = dir.list();

        for (FileHandle file : fileList) {
            if (file.isDirectory()) {
                loadTextureDir(file.path());
            } else if (file.name().contains(".png")){
                loadTexture(file.path());
            }
        }

    }
    /**
     * Loads all .mp3 files in directory path and its subdirectories.
     * @param DirectoryPath path to the directory to load
     */
    @Override
    public void loadSoundsDir(String DirectoryPath) {
        FileHandle dir = Gdx.files.internal(DirectoryPath);

        if (!dir.isDirectory()) {
            System.out.println("Not a Directory");
            return;
        }
        FileHandle[] fileList = dir.list();

        for (FileHandle file : fileList) {
            if (file.isDirectory()) {
                loadSound(file.path());
            } else if (file.name().contains(".mp3")){
                loadSound(file.path());
            }
        }

    }


}
