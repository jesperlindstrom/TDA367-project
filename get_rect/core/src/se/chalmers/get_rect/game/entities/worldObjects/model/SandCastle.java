package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class SandCastle extends AbstractInteractableModel {
    private static final int QUEST_ID = 0;
    private boolean interactedWith = false;
    private int paddleign;
    private WeaponRepository weaponRepository;

    public SandCastle(Point position, IRectangleFactoryAdapter factory, QuestManager questManager, WeaponRepository weaponRepository) {
        super(position, new Point(0,0), false, true, factory);
        setBoundingBox(100, 100);
        this.weaponRepository = weaponRepository;
        interactedWith = isCompleted(questManager.get(QUEST_ID));
        paddleign = 0;
    }

    private boolean isCompleted(IQuest quest) {
        if (quest == null)
            return false;

        if (quest.getState().equals(QuestState.COMPLETED))
            return true;

        for (Objective obj : quest.getObjectives()) {
            if (obj.getType().equals("sandCastle") && obj.getAction().equals("interacted")) {
                return obj.isCompleted();
            }
        }

        return false;
    }

    @Override
    public void onInteract(IModel model) {
        triggerEvent("sandCastle", "interacted");

        if (!interactedWith){
            interactedWith = true;
        }
        if (paddleign == 6) {
            if (model instanceof Player) {
                ((Player) model).addNewWeapon(weaponRepository.getSingleWeapon("paddle"));
            }
        }
        paddleign++;
    }
    public boolean getInteractedWith(){
        return interactedWith;
    }
}
