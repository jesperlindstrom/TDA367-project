package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SawmillExpress extends AbstractNPCModel {
    private final int flyingSpeed;
    private boolean isFlying = false;
    private List<String> dialogList;
    private Random r;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory, IRepository<String> dialogRepository, QuestManager quests) {
        this(point, rectangleFactory, dialogRepository, 50, 219, 200, quests);
    }

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory, IRepository<String> dialogRepository, int speed, int width, int height, QuestManager questManager) {
        super(point, true, rectangleFactory);
        this.flyingSpeed = speed;
        setQuest(questManager.get(0));
        setBoundingBox(width, height);

        r = new Random();
        dialogList = new ArrayList<>();

        try {
            dialogList = dialogRepository.get("sawmill");
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        if (isFlying) {
            setVelocity(new Point(0, flyingSpeed));
        }
    }

    @Override
    public void onInteract(IModel model) {
        super.onInteract(model);
        triggerEvent("sawmillExpress", "interacted");
    }

    @Override
    protected void onQuestCompletion(IModel model) {
        if (model instanceof ICombatModel){
            ((ICombatModel) model).addHealth(((ICombatModel) model).getMaxHealth());
        }

        isFlying = true;
    }

    @Override
    protected String getSmallTalk() {
        int random = r.nextInt(dialogList.size());
        return dialogList.get(random);
    }

    public boolean isFlying() {
        return isFlying;
    }
}