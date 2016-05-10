package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class SandCastle extends AbstractInteractableModel {
    private boolean interactedWith = false;

    public SandCastle(Point position, IRectangleFactoryAdapter factory) {
        super(position, new Point(0,0), false, factory);
        setBoundingBox(100, 100);
    }

    @Override
    public void onInteract(IModel model) {
        if (!interactedWith){
            interactedWith = true;
        }
    }
    public boolean getInteractedWith(){
        return interactedWith;
    }
}
