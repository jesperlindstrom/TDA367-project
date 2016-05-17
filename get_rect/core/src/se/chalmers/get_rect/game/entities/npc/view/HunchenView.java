package se.chalmers.get_rect.game.entities.npc.view;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.Hunchen;

public class HunchenView extends AbstractAnimatedView {

    private static final int FREE = 1;
    private Hunchen model;

    public HunchenView(Hunchen model) {
        super(model, FREE);
        this.model = model;
        addAnimationFrame(FREE, "img/entities/hunchen/hunchen.png", 5);
        addAnimationFrame(FREE, "img/entities/hunchen/hunchen2.png", 5);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (!model.isRiding()){
            if (model.getVelocity().getX() < 0){
                setFlip(true);
            } else {
                setFlip(false);
            }
            super.draw(graphics);
        }
    }
}
