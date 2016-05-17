package se.chalmers.get_rect.game.entities.enemies.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.enemies.model.Demon;
import se.chalmers.get_rect.utilities.Point;

public class DemonView extends AbstractAnimatedView {
    private static final int WALKING = 1;
    private Demon model;
    private Point offset = new Point(250, 0);

    /**
     * changeFrame decides the amount of frames for each picture
     * it is dynamic depending on the speed of the flap of the demon
     * @param model
     */

    public DemonView(Demon model){
        super(model, WALKING);
        this.model = model;
        int changeFrame = model.getRandFlap()/3;

        addAnimationFrame(WALKING, "img/entities/demons/mjolnir1.png", changeFrame);
        addAnimationFrame(WALKING, "img/entities/demons/mjolnir2.png", changeFrame);

    }

    /**
     * flips image if the demon is moving to the right
     * uses a fixed offset based on the picture to make sure the image is flipped to the right place
     * @param graphics
     */

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.getVelocity().getX() < 3 && model.getVelocity().getX() > -3) {
            setFlip(true, offset);
        } else {
            setFlip(false, new Point(0, 0));
        }

        super.draw(graphics);

    }
}
