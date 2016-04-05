package se.chalmers.get_rect.game.scenes;

import javafx.scene.text.Text;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;

/**
 * Created by simsund on 2016-04-05.
 */
public class TestScene implements IScene {
    private IController player;

    public TestScene() {
        PlayerFactory playerFactory = new PlayerFactory();
        player = playerFactory.make();
    }

    @Override
    public void update(long delta) {
        player.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();
        graphics.draw("data/background.png", 0, 0);
        player.draw(graphics);
        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }

}
