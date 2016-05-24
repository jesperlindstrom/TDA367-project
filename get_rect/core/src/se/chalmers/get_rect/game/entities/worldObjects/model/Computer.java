package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Computer extends AbstractInteractableModel {
    public static final int MAC = 0;
    public static final int BIOS = 1;
    public static final int ARCH_INSTALL = 2;
    public static final int ARCH = 3;
    private static final int ARCH_INSTALL_TIME = 120;
    private IQuest quest;
    private int currentState = MAC;
    private int installTicks = 0;

    public Computer(Point position, IRectangleFactoryAdapter factory, QuestManager questManager) {
        super(position, new Point(0,0), false, true, factory);
        setBoundingBox(69, 54);
        quest = questManager.get(0);

        if (quest.getState().equals(QuestState.COMPLETED)) {
            currentState = ARCH;
        }
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        if (currentState == ARCH_INSTALL) {
            installTicks++;
        }

        if (installTicks == ARCH_INSTALL_TIME) {
            currentState = ARCH;
            triggerEvent("computer", "installed arch");
        }
    }

    @Override
    public void onInteract(IModel model) {
        if (!quest.getState().equals(QuestState.IN_PROGRESS))
            return;

        triggerEvent("computer", "interacted");

        if (currentState == MAC) {
            currentState = BIOS;
        } else if (currentState == BIOS) {
            currentState = ARCH_INSTALL;
        }
    }

    public int getState() {
        return currentState;
    }
}
