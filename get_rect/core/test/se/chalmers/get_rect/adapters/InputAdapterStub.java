package se.chalmers.get_rect.adapters;

public class InputAdapterStub implements IInputAdapter {
    @Override
    public boolean isKeyPressed(Keys key) {
        return false;
    }

    @Override
    public boolean isTranslateable(Keys key) {
        return false;
    }
}
