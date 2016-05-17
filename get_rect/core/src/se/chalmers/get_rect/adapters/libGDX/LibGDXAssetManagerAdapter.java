package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IMusicAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;

import java.io.FileNotFoundException;

public class LibGDXAssetManagerAdapter implements IAssetManagerAdapter {
    private AssetManager manager;

    private interface FileLoader {
        void load(String path);
    }

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
    public void loadMusic(String path) {
        manager.load(path, Music.class);
    }

    @Override
    public ISoundAdapter getSound(String path) {
        return new LibGDXSoundAdapter(manager.get(path, Sound.class));
    }

    @Override
    public IMusicAdapter getMusic(String path) {
        return new LibGDXMusicAdapter(manager.get(path, Music.class));
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
     * @param path path to the directory
     */
    @Override
    public void loadTextureDir(String path) throws FileNotFoundException {
        readDirectory(path, ".png", this::loadTexture);
    }

    /**
     * Loads all .mp3 files in directory path and its subdirectories.
     * @param path path to the directory to load
     */
    @Override
    public void loadSoundsDir(String path) throws FileNotFoundException {
        readDirectory(path, ".mp3", this::loadSound);
    }

    @Override
    public void loadMusicDir(String path) throws FileNotFoundException {
        readDirectory(path, ".mp3", this::loadMusic);
    }

    /**
     * Recursively read all assets of a certain extension in a directory
     * @param path The directory path
     * @param extension The extension, starting with .
     * @param loader A FileLoader
     * @throws FileNotFoundException
     */
    private void readDirectory(String path, String extension, FileLoader loader) throws FileNotFoundException {
        FileHandle dir = Gdx.files.internal(path);

        if (!dir.isDirectory()) {
            throw new FileNotFoundException("Directory not found");
        }

        FileHandle[] fileList = dir.list();

        for (FileHandle file : fileList) {
            if (file.isDirectory()) {
                readDirectory(file.path(), extension, loader);
            } else if (file.name().contains(extension)){
                loader.load(file.path());
            }
        }
    }
}
