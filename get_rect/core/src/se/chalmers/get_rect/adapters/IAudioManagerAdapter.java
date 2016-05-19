package se.chalmers.get_rect.adapters;

public interface IAudioManagerAdapter {
    void playMusic(String soundName);
    void playMusic(String soundName, float volume);
    void playSound(String soundName);
    void mute();
    void unmute();

}
