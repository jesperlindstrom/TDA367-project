package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.npc.AbstractNPCModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class SawmillExpress extends AbstractNPCModel {
    private static final int SPEED = 50;
    private static final int WIDTH = 219;
    private static final int HEIGHT = 276;
    private boolean isFlying = false;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory) {
        super(point, new Point(0, 0), false, rectangleFactory);
        setBoundingBox(getPosition(), WIDTH, HEIGHT);
    }

    @Override
    public void update() {
        super.update();
        if (isFlying) {
            setVelocity(new Point(0, SPEED));
        }
    }

    @Override
    public QuestState getQuestState() {
        return QuestState.AVAILABLE;
    }


    @Override
    public void onInteract(IModel model) {
        isFlying = true;
        showDialog("wäääh");
    }

    public boolean isFlying() {
        return isFlying;
    }

}
