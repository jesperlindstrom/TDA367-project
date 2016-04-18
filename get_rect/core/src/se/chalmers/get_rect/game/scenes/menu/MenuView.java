package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.AbstractGridModel;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.IButton;
import se.chalmers.get_rect.utilities.Point;

public class MenuView implements IView {

    private static final String IMG_PATH = "img/pauseMenu/";
    private CameraManager camera;
    private AbstractGridModel model;

    public MenuView(CameraManager camera, AbstractGridModel model) {
        this.camera = camera;
        this.model = model;
    }


    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point cameraPos = camera.getPosition();

        graphics.draw(IMG_PATH + "menuShader.png", cameraPos);
        graphics.draw(IMG_PATH + "buttons/pause_menu_bg.png", cameraPos.add(new Point(360, 140)));
        graphics.draw(IMG_PATH + "inventory/inventory_bg.png", cameraPos.add(new Point(960, 240)));

        for (IButton item : model.getItemMap().values()) {
            graphics.draw(item.getImgPath(), cameraPos.add(item.getPosition()));
        }

        graphics.draw(IMG_PATH + "buttons/pause_menu_button_overlay.png", cameraPos.add(model.getCurrentlyMarkedButton().getPosition()));

    }

}
