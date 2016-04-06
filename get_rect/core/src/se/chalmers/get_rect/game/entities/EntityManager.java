package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<IController> list;

    EntityManager(){
        list = new ArrayList<>();
    }
    EntityManager(List<IController> list){
        this.list = list;
    }
    public void add(IController c){
        list.add(c);
    }
    public void update(long delta){
        for (IController entity : list){
            entity.update(delta);
        }
    }
    public void draw(IGraphicsAdapter g){
        for (IController entity : list){
            entity.draw(g);
        }
    }
}
