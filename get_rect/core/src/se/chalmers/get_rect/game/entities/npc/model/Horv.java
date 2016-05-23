package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.ListUtils;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;

public class Horv extends AbstractNPCModel {
    private final int width;
    private final int height;
    private List<String> dialogList;

    public Horv(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases, int width, int height, QuestManager quests) {
        super(point, new Point(0, 0), false, true, rectangleFactory);
        this.width = width;
        this.height = height;
        setQuest(quests.get(2));
        setBoundingBox(this.width, this.height);
        this.dialogList = phrases;
    }

    public Horv(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases, QuestManager quests) {
        this(point, rectangleFactory, phrases, 58, 158, quests);
    }

    @Override
    protected String getSmallTalk() {
        return ListUtils.randomItem(dialogList);
    }

    @Override
    public void onInteract(IModel model) {
        super.onInteract(model);
        triggerEvent("horv", "interacted");
    }
}
