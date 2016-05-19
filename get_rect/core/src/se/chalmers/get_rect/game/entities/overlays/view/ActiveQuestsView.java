package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class ActiveQuestsView extends AbstractView {
    private static final int DRAW_PRIORITY = 9;
    private QuestManager questManager;
    private ICamera camera;

    public ActiveQuestsView(QuestManager questManager, ICamera camera) {
        this.questManager = questManager;
        this.camera = camera;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        int x = camera.getPosition().getX() + (int)camera.getAdapter().getWidth() - 300;
        int y = camera.getPosition().getY() + (int)camera.getAdapter().getHeight() - 200;

        Point pos = new Point(x, y);

        for (IQuest quest : questManager.getAllActive()) {
            pos = drawQuest(graphics, pos, quest);
        }
    }

    public Point drawQuest(IGraphicsAdapter graphics, Point pos, IQuest quest) {
        System.out.println("Drawing at " + pos);
        graphics.drawText(quest.getTitle(), pos, IGraphicsAdapter.Colors.RED);

        if (quest.getState().equals(QuestState.COMPLETABLE)) {
            pos = pos.addY(-30);
            graphics.drawText("Talk to quest NPC to complete.", pos, IGraphicsAdapter.Colors.BLACK);
        } else {
            for (Objective obj : quest.getObjectives()) {
                pos = pos.addY(-30);
                String progress = obj.getCount() + " / " + obj.getRequiredCount();
                graphics.drawText(obj.getInfoText() + ": " + progress, pos, IGraphicsAdapter.Colors.BLACK);
            }
        }

        return pos.addY(-50);
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
