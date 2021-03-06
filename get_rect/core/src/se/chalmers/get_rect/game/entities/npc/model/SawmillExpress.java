package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.ListUtils;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;

public class SawmillExpress extends AbstractNPCModel {
    private final int flyingSpeed;
    private boolean isFlying = false;
    private List<String> dialogList;
    private WeaponRepository weaponRepository;
    private boolean isInteractedWith = false;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> dialogList, QuestManager quests, WeaponRepository weaponRepository) {
        this(point, rectangleFactory, dialogList, 50, 219, 200, quests);
        this.weaponRepository = weaponRepository;
    }

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> dialogList, int speed, int width, int height, QuestManager questManager) {
        super(point, true, rectangleFactory);
        this.flyingSpeed = speed;
        IQuest quest = questManager.get(0);
        setQuest(quest);
        checkIfCompleted(quest);
        setBoundingBox(width, height);
        this.dialogList = dialogList;
    }

    private void checkIfCompleted(IQuest quest) {
        if (quest.getState().equals(QuestState.COMPLETED))
            setShouldBeRemoved();
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
        isInteractedWith = true;
    }

    @Override
    protected void onQuestCompletion(IModel model) {
        if (model instanceof ICombatModel){
            Player player = (Player) model;
            player.addHealth(((ICombatModel) model).getMaxHealth());
            IWeapon weapon = weaponRepository.getSingleWeapon("lasersword");
            player.addNewWeapon(weapon);
            triggerEvent("weaponUnlock", "lasersword");
        }

        isFlying = true;
    }

    @Override
    protected String getSmallTalk() {
        return ListUtils.randomItem(dialogList);
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying() {
        isFlying = true;
    }
    public boolean isInteractedWith() {
        return isInteractedWith;
    }
}