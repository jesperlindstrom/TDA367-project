package se.chalmers.get_rect.game.entities.worldObjects.model;

import org.junit.Test;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.tests.physics.RectangleAdapterStub;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PortalTest {

    @Test
    public void onInteract() throws Exception {
        Point start = new Point(0, 0);
        Point goal = new Point(5, 5);
        IRectangleFactoryAdapter rectangleFactory = new RectangleFactoryAdapterStub();
        Portal portal = new Portal(start, goal, 10, 10, rectangleFactory);
        Player player = new Player(rectangleFactory, 10 ,10, 10, 10);
        player.setPosition(start);
        assertNotEquals(player.getPosition(), goal);
        portal.onInteract(player);
        assertEquals(player.getPosition(), goal);
    }

}