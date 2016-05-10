package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.io.IOFacade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T> implements IRepository {
    private String fileName;
    private Class<T> className;

    protected AbstractRepository(String fileName, Class<T> className) {
        this.fileName = fileName;
        this.className = className;
    }

    public List<IPhysicsEntity> get(String sceneName) throws FileNotFoundException {
        List<IPhysicsEntity> entities = new ArrayList<>();

        IOFacade<T> dataLoader = new IOFacade<>("scenes/" + sceneName + "/" + fileName + ".json", className);
        List<T> dataList = dataLoader.load();

        if (dataList != null) {
            for (T data : dataList) {
                entities.add(makeFromDataStore(data));
            }
        }

        return entities;
    }

    abstract protected IPhysicsEntity makeFromDataStore(T data);
}
