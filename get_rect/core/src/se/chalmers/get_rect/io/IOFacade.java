package se.chalmers.get_rect.io;

import se.chalmers.get_rect.io.strategies.json.JsonIOStrategy;
import se.chalmers.get_rect.io.strategies.IOStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class IOFacade<T> {
    private String file;
    private IOStrategy<T> io;

    public IOFacade(String file, Class<T> className) {
        this.file = file;
        this.io = new JsonIOStrategy<>(className);
    }

    public List<T> load() throws FileNotFoundException {
        return io.read(file);
    }

    public void save(List<T> data) throws IOException {
        io.write(file, data);
    }
}
