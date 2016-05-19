package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SawmillExpress extends AbstractNPCModel {
    private final int speed;
    private final int width;
    private final int height;
    private boolean isFlying = false;
    private List<String> dialogList;
    private Random r;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory, IRepository<String> dialogRepository, int speed, int width, int height) {
        super(point, new Point(0, 0), false, rectangleFactory);
        this.speed = speed;
        this.width = width;
        this.height = height;
        setBoundingBox(this.width, this.height);

        r = new Random();
        dialogList = new ArrayList<>();

        try {
            dialogList = dialogRepository.get("sawmill");
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory, IRepository dialogRepository) {
        this(point, rectangleFactory, dialogRepository, 50, 219, 200);
    }
    @Override
    public void update(double delta) {
        super.update(delta);

        if (isFlying) {
            setVelocity(new Point(0, speed));
        }
    }

    @Override
    public QuestState getQuestState() {
        return isFlying ? QuestState.UNAVAILABLE : QuestState.AVAILABLE;
    }

    @Override
    public void onInteract(IModel model) {
        if (!isDialogVisible()) {
            int random = (r.nextInt(dialogList.size()));
            showDialog(dialogList.get(random));
        } else {
            nextDialog();
        }
        if (model instanceof ICombatModel){
            ((ICombatModel) model).addHealth(((ICombatModel) model).getMaxHealth());
        }
    }

    public boolean isFlying() {
        return isFlying;
    }

}
