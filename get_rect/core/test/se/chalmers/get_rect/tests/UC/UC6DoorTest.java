package se.chalmers.get_rect.tests.UC;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.world.IWorld;
import se.chalmers.get_rect.game.entities.worldObjects.model.Door;
import se.chalmers.get_rect.game.world.HorsalsvagenWorld;
import se.chalmers.get_rect.game.world.TestWorld;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;
import se.chalmers.get_rect.physics.CollisionData;


import static org.junit.Assert.*;


public class UC6DoorTest {

    private Door door;
    private StateManager<IWorld> stateManager;
    private Player player;
    private CollisionData playerSideData;
    private CollisionData otherSideData;
    private TestWorld testScene;

    @Before
    public void setup(){
        stateManager = new StateManager<>();
        testScene = Mockito.mock(TestWorld.class);
        stateManager.add(22, testScene);
        stateManager.add(21, Mockito.mock(HorsalsvagenWorld.class));

        RectangleFactoryAdapterStub rectangleFactoryAdapterStub = new RectangleFactoryAdapterStub();
        door = new Door(new Point(0,0),100,100, rectangleFactoryAdapterStub , stateManager , 22 );
        player = new Player(rectangleFactoryAdapterStub);
        playerSideData = new CollisionData();
        playerSideData.set(Side.LEFT);
        otherSideData = new CollisionData();
        otherSideData.set(Side.RIGHT);
    }

    @Test
    public void testInteractWithDoor(){
        player.onCollision(door, playerSideData,false);
        door.onCollision(player,otherSideData,false);
        player.interact();
        System.out.println(stateManager.getState());
        assertEquals("Should be true", stateManager.getState(), testScene);

    }
}
