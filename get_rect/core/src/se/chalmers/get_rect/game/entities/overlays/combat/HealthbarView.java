package se.chalmers.get_rect.game.entities.overlays.combat;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class HealthbarView implements IView {

    private CombatList combatList;

    public HealthbarView(CombatList combatList) {
        this.combatList = combatList;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IEntity entity : combatList.get()) {
            ICombatModel combatModel = (ICombatModel) entity.getModel();
            drawHealthbar(graphics, combatModel);
        }
    }

    public void drawHealthbar(IGraphicsAdapter graphics, ICombatModel model) {
        if (model.getcurrentHealth() < model.getMaxHealth()){
            float dmgTaken = (float)(100*((model.getcurrentHealth())/(double)model.getMaxHealth()));
            graphics.draw("img/entities/health/dmg.png",new Point(model.getPosition().add(5,150)));
            graphics.draw("img/entities/health/hp.png", new Point(model.getPosition().add(5,150)), dmgTaken, 10);
            graphics.draw("img/entities/health/healthbar.png",new Point(model.getPosition().add(5,150)));
        }
    }
}
