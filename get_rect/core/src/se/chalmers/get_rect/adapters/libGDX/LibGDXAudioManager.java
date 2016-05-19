package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.audio.Music;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import java.util.HashSet;
import java.util.Set;

public class LibGDXAudioManager implements IAudioManagerAdapter{
    private LibGDXAssetManagerAdapter assetManager;
    private Set<Music> musicList;
    private boolean muted = false;

    public LibGDXAudioManager(LibGDXAssetManagerAdapter assetManager) {
        musicList = new HashSet<>();
        this.assetManager = assetManager;
    }
    @Override
    public void playMusic(String soundName) {
        playMusic(soundName, 1.0f);
    }

    @Override
    public void playMusic(String soundName, float volume) {
        Music file = assetManager.getMusic(soundName);

        if (file == null) return;

        musicList.add(file);

        file.setVolume(volume);
        file.play();

        removeFinished();
    }
    @Override
    public void playSound(String soundName) {
        if (muted) return;

        assetManager.getSound("sounds/" + soundName + "mp3");
    }

    private void removeFinished() {
        while (musicList.iterator().hasNext()) {
            Music file = musicList.iterator().next();

            if (!file.isPlaying()) {
                musicList.remove(file);
            }
        }
    }
    @Override
    public void mute() {
        muted = true;

        for (Music file : musicList) {
            file.pause();
        }
    }
    @Override
    public void unmute() {
        muted = false;

        for (Music file : musicList) {
            file.play();
        }
    }
}
