package se.chalmers.get_rect.adapters;

import com.badlogic.gdx.Input;

import java.util.HashMap;
import java.util.Map;

public class LibGDXInputAdapter implements IInputAdapter{

    private Input input;

    private Map<Keys, Integer> keyMap;

    public LibGDXInputAdapter(Input input) {
        this.input = input;
        initKeyMap();

    }

    @Override
    public boolean isKeyPressed(Keys key) {
        if(isTranslateable(key)) {
            return input.isKeyPressed(keyMap.get(key));
        }
        return false;
    }

    @Override
    public boolean isTranslateable(Keys key) {
        if (keyMap.get(key) != null) {
            return true;
        }
        return false;
    }


    private void initKeyMap(){
        this.keyMap = new HashMap<>();
        keyMap.put(Keys.Q, Input.Keys.Q);
        keyMap.put(Keys.A, Input.Keys.A);
        keyMap.put(Keys.W, Input.Keys.W);
        keyMap.put(Keys.E, Input.Keys.E);
        keyMap.put(Keys.S, Input.Keys.S);
        keyMap.put(Keys.D, Input.Keys.D);
        keyMap.put(Keys.SPACE, Input.Keys.SPACE);
        keyMap.put(Keys.ENTER, Input.Keys.ENTER);
        keyMap.put(Keys.ESC, Input.Keys.ESCAPE);
        keyMap.put(Keys.UPKEY, Input.Keys.UP);
        keyMap.put(Keys.LEFTKEY, Input.Keys.LEFT);
        keyMap.put(Keys.RIGHTKEY, Input.Keys.RIGHT);
        keyMap.put(Keys.DOWNKEY, Input.Keys.DOWN);
    }

}
