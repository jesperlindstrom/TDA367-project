package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractNPCModel extends AbstractInteractableModel implements INpcModel {
    protected AbstractNPCModel(Point position, Point velocity, boolean solid, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, affectedByGravity, rectangleFactory);
    }

    protected AbstractNPCModel(Point position, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory) {
        this(position, new Point(0, 0), false, affectedByGravity, rectangleFactory);
    }

    @Override
    public QuestState getQuestState() {
        return QuestState.UNAVAILABLE;
    }
}
