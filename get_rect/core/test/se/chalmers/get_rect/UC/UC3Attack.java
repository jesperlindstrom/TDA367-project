package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.player.Player;


public class UC3Attack {

    private Player player;
    private Zombie zombie;

    @Before


    @Test
    public void testAttack(){
        player.shoot(player.getPosition());
    }
}
