package se.chalmers.get_rect.adapters;

public interface ISoundAdapter {
    void play();
    void pause();
    void setPitch(Long time, float pitch);
    void play(Float volume);
    void loop();
    void loop(float volume);
}
