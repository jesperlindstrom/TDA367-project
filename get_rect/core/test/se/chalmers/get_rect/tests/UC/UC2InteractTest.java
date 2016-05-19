package se.chalmers.get_rect.tests.UC;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.DialogRepository;
import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.npc.NpcFactory;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;
import se.chalmers.get_rect.physics.CollisionData;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class UC2InteractTest {

    private Player player;
    private SawmillExpress sawmillExpress;
    private CollisionData playerSide;
    private CollisionData otherSide;


    @Before
    public void setup(){
        IRectangleFactoryAdapter  rectangleFactoryAdapter = new RectangleFactoryAdapterStub();
        IRepository repository = Mockito.mock(IRepository.class);
        List<String> list = new ArrayList<>();
        list.add("sawmill");
        try {
            Mockito.when(repository.get("sawmill")).thenReturn(list);
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        this.player = new Player(rectangleFactoryAdapter);
        this.sawmillExpress = new SawmillExpress(new Point(1,1), rectangleFactoryAdapter, repository, 10, 10 ,10);
        this.playerSide = new CollisionData();
        this.otherSide = new CollisionData();
        playerSide.set(Side.RIGHT);
        otherSide.set(Side.LEFT);
    }

    /**
     * checks if player can interact with intractable entity
     */
    @Test
    public void testInteraction(){
        assertFalse("Should not talking", sawmillExpress.isDialogVisible());
        player.onCollision(sawmillExpress,playerSide,false);
        sawmillExpress.onCollision(player, otherSide,false);
        player.interact();
        assertTrue("Should be talking", sawmillExpress.isDialogVisible());
    }

    /**
     * Alternate flow of events, Case 1-3a: Player tries to interact with a non-interactable entity
     */
    @Test
    public void testAlternateFlow(){
        Zombie zombie = Mockito.mock(Zombie.class);
        CollisionData zombieCollisionData = new CollisionData();
        zombieCollisionData.set(Side.LEFT);
        zombie.onCollision(player, zombieCollisionData,false);
        player.onCollision(zombie, playerSide, false);
        assertNull("Should be null", player.getCurrentNpc());
    }

    /**
     * Test if dialog with sawmill works
     */
    @Test
    public void testDialog(){
        player.onCollision(sawmillExpress,playerSide,false);
        sawmillExpress.onCollision(player,otherSide,false);
        assertFalse("Should be false", sawmillExpress.isDialogVisible());
        player.interact();
        assertTrue("Should be true", sawmillExpress.isDialogVisible());
        assertEquals("Should be equal", sawmillExpress.getDialog(), "sawmill\n");
    }
}
