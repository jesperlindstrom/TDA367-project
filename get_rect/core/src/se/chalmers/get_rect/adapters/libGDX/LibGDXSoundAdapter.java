package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.audio.Sound;
import se.chalmers.get_rect.adapters.ISoundAdapter;

public class LibGDXSoundAdapter implements ISoundAdapter {
    private Sound sound;

    public LibGDXSoundAdapter(Sound sound) {
        this.sound = sound;
    }

    @Override
    public long play() {
        sound.play();
        return sound.play();
    }

    public long play(Float volume) {
        sound.play(volume);
        return sound.play();
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
    public void setLooping(long id, boolean isLooping) {
        sound.setLooping(id, isLooping);
    }
}
