package se.chalmers.get_rect.game.entities.window.model;

public interface IGameControl {
    void exit();
    void load();
    void resume();
    void save();
    void startNew();
    boolean loadAvailable();
}
