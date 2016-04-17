package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IView;

public class ButtonView implements IView {

    private static final String STANDARD_IMG_PATH = "img/pauseMenu/";
    private String imgPath;
    private IButton button;
    private CameraManager camera;

    public ButtonView(String img, IButton button, CameraManager camera) {
        imgPath = STANDARD_IMG_PATH + img;
        this.button = button;
        this.camera = camera;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath, camera.getPosition().add(button.getPosition()));

        if (button.isActive()) {
            graphics.draw(STANDARD_IMG_PATH + "pause_menu_button_overlay.png", camera.getPosition().add(button.getPosition()));
        }
    }
}
