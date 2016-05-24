package se.chalmers.get_rect.io.strategies;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IOStrategy<T> {
    List<T> read(String file) throws FileNotFoundException;
    void write(String file, List<T> data) throws IOException;
}
