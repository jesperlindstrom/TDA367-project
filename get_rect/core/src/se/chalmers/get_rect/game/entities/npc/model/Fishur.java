package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.ListUtils;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;

public class Fishur extends AbstractNPCModel {

    private final int width;
    private final int height;
    private WeaponRepository weaponRepository;
    private List<String> dialogList;
    private boolean isInteractedWith = false;

    public Fishur(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases, int width, int height, QuestManager quests, WeaponRepository weaponRepository) {
        super(point, new Point(0, 0), false, true, rectangleFactory);
        this.width = width;
        this.height = height;
        this.weaponRepository = weaponRepository;
        setQuest(quests.get(4));
        setBoundingBox(this.width, this.height);
        this.dialogList = phrases;
    }

    public Fishur(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases, QuestManager quests, WeaponRepository weaponRepository) {
        this(point, rectangleFactory, phrases, 107, 200, quests, weaponRepository);
    }

    @Override
    protected String getSmallTalk() {
        return ListUtils.randomItem(dialogList);
    }

    @Override
    public void onInteract(IModel model) {
        super.onInteract(model);
        triggerEvent("fishur", "interacted");
        isInteractedWith = true;
    }
    public boolean isInteractedWith() {
        return isInteractedWith;
    }

    @Override
    protected void onQuestCompletion(IModel model) {
        if (model instanceof Player) {
            ((Player) model).addNewWeapon(weaponRepository.getSingleWeapon("boxingGlove"));
        }
    }
}