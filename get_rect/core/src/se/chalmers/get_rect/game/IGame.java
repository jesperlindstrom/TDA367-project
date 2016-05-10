package se.chalmers.get_rect.game;

public interface IGame {
    void exit();
    void load();
    void resume();
    void save();
    void startNew();
    boolean loadAvailable();
}
