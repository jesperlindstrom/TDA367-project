package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.audio.Sound;
import se.chalmers.get_rect.adapters.ISoundAdapter;

public class LibGDXSoundAdapter implements ISoundAdapter {
    private Sound sound;

    public LibGDXSoundAdapter(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void play() {
            sound.play();
    }

    public void play(Float volume) {
        sound.play(volume);
    }

    public void pause() {
        sound.pause();
    }

    public void setPitch(long time, float pitch){
        sound.setPitch(time, pitch);
    }

    public void loop() {
        sound.loop();
    }

    @Override
    public void loop(float volume) {
        sound.loop(volume);
    }

    @Override
    public void setPan(long soundId, float pan, float volume) {
        sound.setPan(soundId, pan, volume);
    }
}
