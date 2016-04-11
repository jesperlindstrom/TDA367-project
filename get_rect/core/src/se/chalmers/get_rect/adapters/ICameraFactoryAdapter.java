package se.chalmers.get_rect.adapters;

public interface ICameraFactoryAdapter {
    ICameraAdapter make(int width, int height);
}
