package se.chalmers.get_rect.io.json;

import se.chalmers.get_rect.io.IOStrategy;

import java.util.List;

public class JsonIOStrategy<T> implements IOStrategy<T> {
    @Override
    public List<T> read(String file) {
        return null;
    }

    @Override
    public void write(String file, List data) {

    }
}
