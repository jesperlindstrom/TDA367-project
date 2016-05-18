package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.entities.worldObjects.model.Door;
import se.chalmers.get_rect.game.scenes.HorsalsvagenScene;
import se.chalmers.get_rect.game.scenes.TestScene;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;
import se.chalmers.get_rect.utilities.CollisionData;


import static org.junit.Assert.*;


public class UC6DoorTest {

    private Door door;
    private StateManager<IScene> stateManager;
    private Player player;
    private CollisionData playerSideData;
    private CollisionData otherSideData;
    private TestScene testScene;

    @Before
    public void setup(){
        stateManager = new StateManager<>();
        testScene = Mockito.mock(TestScene.class);
        stateManager.add(22, testScene);
        stateManager.add(21, Mockito.mock(HorsalsvagenScene.class));

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
