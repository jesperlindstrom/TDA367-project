package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager {

    private int index;
    private List<ButtonEntity> buttons;

    public ButtonManager() {
        buttons = new ArrayList<>();
    }

    public void add(ButtonEntity button) {
        buttons.add(button);
    }

    public void draw(IGraphicsAdapter graphics) {
        for (ButtonEntity b : buttons) {
            b.draw(graphics);
        }
    }

    public int getIndex() {
        return index;
    }

    private IButton getCurrentButton() {
        return buttons.get(index).getButton();
    }

    public void increaseIndex() {
        getCurrentButton().setActive(false);
        if (index == buttons.size()-1) {
            index = 0;
        } else {
            index++;
        }
        getCurrentButton().setActive(true);
    }

    public void decreaseIndex() {
        getCurrentButton().setActive(false);
        if (index == 0) {
            index = buttons.size()-1;
        } else {
            index--;
        }
        getCurrentButton().setActive(true);
    }

    public void pressButton(GameScreen game) {
        getCurrentButton().pressButton(game);
    }

    public void setIndex(int index) {
        getCurrentButton().setActive(false);
        this.index = index;
        getCurrentButton().setActive(true);
    }
}
