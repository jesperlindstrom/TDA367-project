package se.chalmers.get_rect.game.entities;

import java.io.FileNotFoundException;
import java.util.List;

public interface IRepository {
    List<IPhysicsEntity> get(String sceneName) throws FileNotFoundException;
}
