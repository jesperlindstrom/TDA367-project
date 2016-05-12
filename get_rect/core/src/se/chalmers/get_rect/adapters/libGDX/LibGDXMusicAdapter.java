package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.audio.Music;
import se.chalmers.get_rect.adapters.IMusicAdapter;
import se.chalmers.get_rect.game.GameConfig;

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
        if(GameConfig.MUSIC_ON)
        music.play();
    }

    @Override
    public void pause() {
        music.pause();
    }
}
