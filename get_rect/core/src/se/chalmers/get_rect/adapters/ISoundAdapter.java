package se.chalmers.get_rect.adapters;

public interface ISoundAdapter {
    void play();
    void pause();
    void setPitch(long time, float pitch);
    void play(Float volume);
    void loop();
    void loop(float volume);
    void setPan(long soundId, float pan, float volume);
    void setLooping(long id, boolean isLooping);
    void resume();
}
