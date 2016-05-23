package se.chalmers.get_rect.tests.combat;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.utilities.Point;

public class MeleeStub implements IMelee {
    @Override
    public float getSwingDegrees() {
        return 0;
    }

    @Override
    public int getTilt() {
        return 0;
    }

    @Override
    public void use(Point direction, IEntityHolder entityHolder) {

    }

    @Override
    public void remove() {

    }

    @Override
    public Point getHandPos() {
        return null;
    }

    @Override
    public int getCooldownFrames() {
        return 0;
    }

    @Override
    public int getFacing() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public Point getPosition() {
        return new Point();
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void setScene(IEntityHolder scene) {

    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }
}
