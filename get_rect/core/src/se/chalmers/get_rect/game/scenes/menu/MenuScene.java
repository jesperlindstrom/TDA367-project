package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class MenuScene implements IGameComponent {

    private IInputAdapter input;
    private Point position;

    public MenuScene(IGame game) {
        this.input = game.getInput();
        position = new Point(0, 0);

    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("scenes/MenuBackground.png", position);
    }

}
