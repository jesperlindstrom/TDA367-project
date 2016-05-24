package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.NyaBjorn;
import se.chalmers.get_rect.utilities.Point;

public class NyaBjornView extends AbstractAnimatedView {
    private static final int DEFAULT = 1;
    private static final int ON_THE_WAY = 2;
    private static final int SHOW = 3;
    private static final int NYA_BJORN = 4;
    private NyaBjorn model;

    public NyaBjornView(NyaBjorn model) {
        super(model, DEFAULT);
        this.model = model;
        super.setDrawOffset(new Point(-40, -40));
        addAnimationFrame(DEFAULT, "img/entities/urza/urzaToilet1.png");
        addAnimationFrame(ON_THE_WAY, "img/entities/urza/urzaToilet2.png", 60);
        addAnimationFrame(ON_THE_WAY, "img/entities/urza/urzaToilet3.png", 60);
        addAnimationFrame(SHOW, "img/entities/urza/urzaToilet4.png");
        addAnimationFrame(NYA_BJORN, "img/entities/urza/nyaBjorn.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.isShowing()) {
            addAnimationFrame(0, "img/interact/exclamation.png");
            playSequence(ON_THE_WAY);
            if (model.isShowingFull()) {
                playSequence(NYA_BJORN);
            }
        } else {
            playSequence(DEFAULT);
        }
        super.draw(graphics);
    }
}
