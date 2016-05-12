package se.chalmers.get_rect.game.entities;

import java.io.FileNotFoundException;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.T;

public interface IRepository<T> {
    List<T> get(String sceneName) throws FileNotFoundException;
}
