package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

public class ZombieView implements IView{
    private Zombie model;

    public ZombieView(Zombie model){
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("data/zombie.png", model.getPosition());

    }
}
