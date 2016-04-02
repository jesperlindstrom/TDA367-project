package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.physics.ISolidObject;

public interface ISolidEntity extends IEntity, ISolidObject {
    interface Model extends IEntity.Model {
        // todo: return type LibGDX adapter for rectangle?
        void getBoundingBox();
    }
}