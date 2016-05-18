package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.audio.Sound;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.GameConfig;

public class LibGDXSoundAdapter implements ISoundAdapter {
    private Sound sound;

    public LibGDXSoundAdapter(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void play() {
        if (GameConfig.SOUND_ON) {
            sound.play();
        }
    }

    public void play(Float volume) {
        if (GameConfig.SOUND_ON) {
            sound.play(volume);
        }
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
    public void resume() {
        sound.resume();
    }
}
