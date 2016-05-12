package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.Random;

public class SawmillExpress extends AbstractNPCModel {
    private static final int SPEED = 50;
    private static final int WIDTH = 219;
    private static final int HEIGHT = 200;
    private boolean isFlying = false;
    private IRepository<String> dialogRepository;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory, IRepository dialogRepository) {
        super(point, new Point(0, 0), false, rectangleFactory);
        setBoundingBox(WIDTH, HEIGHT);
        this.dialogRepository = dialogRepository;

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
        try {
            showDialog(dialogRepository.get("dialogs").get(0));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        nextDialog();
        if (model instanceof ICombatModel){
            ((ICombatModel) model).addHealth(((ICombatModel) model).getMaxHealth());
        }
    }

    public boolean isFlying() {
        return isFlying;
    }

}
