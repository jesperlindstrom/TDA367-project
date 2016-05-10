package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class SawmillExpress extends AbstractNPCModel {
    private static final int SPEED = 50;
    private static final int WIDTH = 219;
    private static final int HEIGHT = 200;
    private boolean isFlying = false;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory) {
        super(point, new Point(0, 0), false, rectangleFactory);
        setBoundingBox(WIDTH, HEIGHT);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        if (isFlying) {
            setVelocity(new Point(0, SPEED));
        }
    }

    @Override
    public QuestState getQuestState() {
        return isFlying ? QuestState.UNAVAILABLE : QuestState.AVAILABLE;
    }

    @Override
    public void onInteract(IModel model) {
        showDialog("Hello, I am Sawmill! I live in this express because reasons. That is why it is called ''Sawmill Express''. But I have a problem, I can not start the express because I fucked up with the start button. It is on the outside.. Please press the start button! I can heal your broken soul!");
        nextDialog();
        if (model instanceof ICombatModel){
            ((ICombatModel) model).addHealth(((ICombatModel) model).getMaxHealth());
        }
    }

    public boolean isFlying() {
        return isFlying;
    }

}
