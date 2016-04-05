package se.chalmers.get_rect.io;

import java.util.List;

public interface IOStrategy<T> {
    List<T> read(String file);
    void write(String file, List<T> data);
}
