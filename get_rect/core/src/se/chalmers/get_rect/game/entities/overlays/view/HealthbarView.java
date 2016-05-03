package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.overlays.model.CombatList;
import se.chalmers.get_rect.utilities.Point;

public class HealthbarView extends AbstractView {

    private CombatList combatList;
    private static final int DRAW_PRIORITY = 5;

    public HealthbarView(CombatList combatList) {
        super();
        this.combatList = combatList;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IModel model : combatList.get()) {
            ICombatModel combatModel = (ICombatModel)model;
            drawHealthbar(graphics, combatModel);
        }
    }

    public void drawHealthbar(IGraphicsAdapter graphics, ICombatModel model) {
        if (model.getCurrentHealth() < model.getMaxHealth()){
            float dmgTaken = (float)(100*((model.getCurrentHealth())/(double)model.getMaxHealth()));
            graphics.draw("img/entities/health/dmg.png",new Point(model.getPosition().add(7,150)), 100,12);
            graphics.draw("img/entities/health/hp.png", new Point(model.getPosition().add(6,150)), dmgTaken, 12);
            graphics.draw("img/entities/health/healthbar.png",new Point(model.getPosition().add(5,150)));
        }
    }
}
