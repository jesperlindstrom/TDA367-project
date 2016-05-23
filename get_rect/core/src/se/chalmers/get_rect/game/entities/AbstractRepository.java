package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.io.IOFacade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<D, T> implements IRepository<T> {
    private String fileName;
    private Class<D> className;

    protected AbstractRepository(String fileName, Class<D> className) {
        this.fileName = fileName;
        this.className = className;
    }

    public List<T> get(String folderName) throws FileNotFoundException, EntityNotFoundException {
        List<T> entities = new ArrayList<>();

        IOFacade<D> dataLoader = new IOFacade<>("data/" + folderName + "/" + fileName + ".json", className);
        List<D> dataList = dataLoader.load();

        if (dataList != null) {
            for (D data : dataList) {
                entities.add(makeFromDataStore(data));
            }
        }

        return entities;
    }

    abstract protected T makeFromDataStore(D data);
}
