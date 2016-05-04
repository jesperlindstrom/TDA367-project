package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.worldObjects.model.SandCastle;

public class SandCastleView extends AbstractAnimatedView {
    private static final int NONE = 0;
    private static final int BUILT = 1;

    private SandCastle model;

    public SandCastleView(IModel model) {
        super(model, NONE);
        this.model = (SandCastle)model;

        addAnimationFrame(NONE, "img/entities/sandcastle/no_sand_castle.png");
        addAnimationFrame(BUILT, "img/entities/sandcastle/sand_castle.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if(model.getInteractedWith()){
            playSequence(BUILT);
        }else{
            playSequence(NONE);
        }

        super.draw(graphics);
    }
}
