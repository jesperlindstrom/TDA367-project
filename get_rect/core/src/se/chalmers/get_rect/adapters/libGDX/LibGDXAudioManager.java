package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;

import java.util.*;

public class LibGDXAudioManager implements IAudioManagerAdapter{
    private LibGDXAssetManagerAdapter assetManager;
    private Map<String, Music> musicList;
    private Map<String, Sound> soundList;
    private boolean muted = false;

    public LibGDXAudioManager(LibGDXAssetManagerAdapter assetManager) {
        musicList = new HashMap<>();
        soundList = new HashMap<>();
        this.assetManager = assetManager;
    }
    @Override
    public void playMusic(String soundName) {
        playMusic(soundName, 1.0f);
    }

    @Override
    public void playMusic(String soundName, float volume) {
        if (muted) return;
        Music file = musicList.get(soundName);

        if (file == null) {
            file = assetManager.getMusic("music/" + soundName + ".mp3");

            if (file == null)
                return;

            musicList.put(soundName, file);
        }

        file.setVolume(volume);
        file.play();
    }

    @Override
    public void playSound(String soundName, float volume) {
        if (muted) return;

        Sound file = soundList.get(soundName);

        if (file == null) {
            file = assetManager.getSound("sounds/" + soundName + ".mp3");

            if (file == null)
                return;

            soundList.put(soundName, file);
        }

        file.setVolume(soundList.get(soundName).play(), volume);
        file.play();

    }

    @Override
    public void panSound(String soundName, float panning, float volume) {
        if (soundList.get(soundName) == null) {
            return;
        }
        soundList.get(soundName).setPan(soundList.get(soundName).play(), panning, volume);
    }

    public void playSound(String soundName) {
        playSound(soundName, 1f);
    }

    @Override
    public void mute() {
        muted = true;
        musicList.forEach((k, m) -> m.pause());
    }


    @Override
    public void stopMusic(String soundName) {
        Music file = musicList.get(soundName);
        if (file != null)
            file.stop();
    }

    @Override
    public void stopAllMusic() {
        musicList.forEach((k, m) -> m.stop());
    }

    @Override
    public void unmute() {
        muted = false;
    }
}
