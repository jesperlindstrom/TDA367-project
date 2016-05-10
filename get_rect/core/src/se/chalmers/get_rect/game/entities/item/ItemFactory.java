package se.chalmers.get_rect.game.entities.item;

import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.item.view.PistolView;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class ItemFactory {

    private IRectangleFactoryAdapter rectangleFactory;

    public ItemFactory(IRectangleFactoryAdapter rectangleFactory) {
        this.rectangleFactory = rectangleFactory;
    }

    public IEntity make(String type, IPhysicsModel model) {
        IModel tmpModel;
        IView tmpView;
        switch (type) {
            case "pistol" :
                tmpModel = new Pistol(rectangleFactory, model);
                tmpView = new PistolView(tmpModel);
            default:
                tmpModel = new Pistol(rectangleFactory, model);
                tmpView = new PistolView(tmpModel);
        }
        return new Entity(tmpModel, tmpView);
    }
}
