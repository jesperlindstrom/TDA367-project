package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.Rekoil;
import se.chalmers.get_rect.utilities.Point;

public class RekoilView extends AbstractAnimatedView {
    private static final int DEFAULT = 1;
    private static final int SHOW_ARCH = 2;
    private Rekoil model;

    public RekoilView(Rekoil model) {
        super(model, DEFAULT);
        this.model = model;
        super.setDrawOffset(new Point(0, -100));
        addAnimationFrame(DEFAULT, "img/entities/rekoil/rekoil1.png");
        addAnimationFrame(SHOW_ARCH, "img/entities/rekoil/rekoil2.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.showArch()) {
            addAnimationFrame(0, "img/interact/exclamation.png");
            playSequence(SHOW_ARCH);
        } else {
            playSequence(DEFAULT);
        }
        super.draw(graphics);
    }
}
