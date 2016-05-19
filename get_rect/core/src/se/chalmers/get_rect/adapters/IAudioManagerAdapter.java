package se.chalmers.get_rect.adapters;

public interface IAudioManagerAdapter {
    void playMusic(String soundName);
    void playMusic(String soundName, float volume);
    void playSound(String soundName);
    void mute();
    void unmute();
    void stopMusic(String soundName);
    void stopAllMusic();
    void playSound(String soundName, float volume);
    void panSound(String soundName, float panning, float volume);
}
