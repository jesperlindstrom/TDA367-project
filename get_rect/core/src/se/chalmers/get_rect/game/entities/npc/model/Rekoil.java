package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.ListUtils;
import se.chalmers.get_rect.utilities.Point;
import java.util.List;

public class Rekoil extends AbstractNPCModel {
    private final int width;
    private final int height;
    private boolean isShowingArch = false;
    private List<String> dialogList;
    private boolean isInteractedWith = false;

    public Rekoil(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases, int width, int height, QuestManager quests) {
        super(point, new Point(0, 0), false, true, rectangleFactory);
        this.width = width;
        this.height = height;
        setBoundingBox(this.width, this.height);
        setQuest(quests.get(1));
        this.dialogList = phrases;
    }

    public Rekoil(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases, QuestManager quests) {
        this(point, rectangleFactory, phrases, 90, 158, quests);
    }

    @Override
    public void onInteract(IModel model) {
        super.onInteract(model);
        triggerEvent("rekoil", "interacted");
        isInteractedWith = true;
    }

    @Override
    public void onQuestCompletion(IModel model) {
        if (model instanceof ICombatModel){
            ((ICombatModel) model).addHealth(((ICombatModel) model).getMaxHealth());
            isShowingArch = true;
        }
    }

    @Override
    protected String getSmallTalk() {
        return ListUtils.randomItem(dialogList);
    }

    public boolean showArch() {
        return isShowingArch;
    }
    public boolean isInteractedWith() {
        return isInteractedWith;
    }
}
