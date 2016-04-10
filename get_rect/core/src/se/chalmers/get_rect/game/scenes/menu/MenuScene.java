package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class MenuScene implements IGameComponent {

    private IInputAdapter input;
    private Point position;
    private Map<String, Point> relativePosition;
    private CameraManager camera;

    public MenuScene(IInputAdapter input, CameraManager camera) {
        this.input = input;
        this.camera = camera;
        position = camera.getLastPosition();
        relativePosition = new HashMap<String, Point>();
        addPositions();
    }

    @Override
    public void update(long delta) {
        position = camera.getLastPosition();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/backgrounds/menuShader.png", getRelativePosition("shader"));
        graphics.draw("img/scenes/menuBackground.png", getRelativePosition("background"));
        graphics.draw("img/scenes/exitButton.png", getRelativePosition("exit"));
        graphics.draw("img/scenes/continueButton.png", getRelativePosition("continue"));
    }

    /**
     * fills the map with values
     */
    private void addPositions() {
        relativePosition.put("shader", new Point(-960, -240));
        relativePosition.put("background", new Point(-300, -100));
        relativePosition.put("exit", new Point(-225, 100));
        relativePosition.put("continue", new Point(-225, 400));
    }

    /**
     * Method for finding where to place textures when menu is active
     * @param id the id of the texture to be placed
     * @return the relative position to the camera center
     */
    private Point getRelativePosition(String id) {
        return position.add(relativePosition.get(id));
    }

}
