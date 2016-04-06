package se.chalmers.get_rect.io;

public interface DataStore<T> {
    T extract();
    void compress(T entity);
}
