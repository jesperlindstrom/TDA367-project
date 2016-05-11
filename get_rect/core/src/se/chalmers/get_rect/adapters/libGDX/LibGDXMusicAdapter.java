package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.audio.Music;
import se.chalmers.get_rect.adapters.IMusicAdapter;

public class LibGDXMusicAdapter implements IMusicAdapter{
    private Music music;

    public LibGDXMusicAdapter(Music music){
        this.music = music;
    }

    @Override
    public boolean isPlaying() {
        return music.isPlaying();
    }

    @Override
    public void play() {
        music.play();
    }
}
