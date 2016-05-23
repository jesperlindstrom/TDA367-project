package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.worldObjects.model.Computer;
import se.chalmers.get_rect.game.entities.worldObjects.model.SandCastle;
import se.chalmers.get_rect.utilities.Point;

public class ComputerView extends AbstractAnimatedView {
    private Computer model;

    public ComputerView(Computer model) {
        super(model, Computer.MAC);
        this.model = model;
        addAnimationFrame(Computer.MAC, "img/entities/computer/computer_mac.png");
        addAnimationFrame(Computer.BIOS, "img/entities/computer/computer_bios.png");
        addAnimationFrame(Computer.ARCH_INSTALL, "img/entities/computer/computer_booting_arch.png");
        addAnimationFrame(Computer.ARCH, "img/entities/computer/computer_arch_1.png", 30);
        addAnimationFrame(Computer.ARCH, "img/entities/computer/computer_arch_2.png", 30);
    }

    public int getSequence() {
        return model.getState();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        playSequence(getSequence());

        super.draw(graphics);
    }
}
