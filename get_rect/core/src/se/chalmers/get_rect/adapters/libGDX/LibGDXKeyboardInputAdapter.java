package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import se.chalmers.get_rect.adapters.IKeyboardInputAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class LibGDXKeyboardInputAdapter implements IKeyboardInputAdapter {

    private Input input;
    private Map<Keys, Integer> keyMap;

    public LibGDXKeyboardInputAdapter() {
        this.input = Gdx.input;
        initKeyMap();
    }

    @Override
    public Point getMousePosition() {
        return new Point(input.getX(),input.getY());
    }

    /**
     * Checks if the key is being pressed
     * @param key the key to look for
     * @return true if the key is currently pressed
     */
    @Override
    public boolean isKeyPressed(Keys key) {
        return keyMap.containsKey(key) && input.isKeyPressed(keyMap.get(key));
    }

    /**
     * Checks whether the key in question was recently pressed
     * @param key the key in question
     * @return true if the key was recently pressed
     */
    @Override
    public boolean isKeyJustPressed(Keys key) {
        return keyMap.containsKey(key) && input.isKeyJustPressed(keyMap.get(key));
    }

    /**
     * Checks if the key being pressed is implemented
     * @param key The key in question
     * @return true if is available in KeyMap
     */
    @Override
    public boolean isTranslatable(Keys key) {
        if (keyMap.get(key) != null) {
            return true;
        }
        return false;
    }



    /**
     * Maps our Keys with the corresponding key for libGDX.
     */
    private void initKeyMap(){
        this.keyMap = new HashMap<>();
        keyMap.put(Keys.Q, Input.Keys.Q);
        keyMap.put(Keys.A, Input.Keys.A);
        keyMap.put(Keys.W, Input.Keys.W);
        keyMap.put(Keys.E, Input.Keys.E);
        keyMap.put(Keys.S, Input.Keys.S);
        keyMap.put(Keys.D, Input.Keys.D);
        keyMap.put(Keys.X, Input.Keys.X);
        keyMap.put(Keys.H, Input.Keys.H);
        keyMap.put(Keys.M, Input.Keys.M);
        keyMap.put(Keys.SPACE, Input.Keys.SPACE);
        keyMap.put(Keys.ENTER, Input.Keys.ENTER);
        keyMap.put(Keys.ESC, Input.Keys.ESCAPE);
        keyMap.put(Keys.UP_KEY, Input.Keys.UP);
        keyMap.put(Keys.LEFT_KEY, Input.Keys.LEFT);
        keyMap.put(Keys.RIGHT_KEY, Input.Keys.RIGHT);
        keyMap.put(Keys.DOWN_KEY, Input.Keys.DOWN);
        keyMap.put(Keys.MOUSELEFT, Input.Buttons.LEFT);
        keyMap.put(Keys.MOUSERIGHT, Input.Buttons.RIGHT);
    }

}
