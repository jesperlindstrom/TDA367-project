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
            sound.play();
    }
}
