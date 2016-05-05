package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractNPCModel extends AbstractInteractableModel implements INpcModel {
    protected AbstractNPCModel(Point position, Point velocity, boolean solid, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, rectangleFactory);
    }

    @Override
    public QuestState getQuestState() {
        return QuestState.UNAVAILABLE;
    }
}
