package se.chalmers.get_rect.game.entities.overlays.model;

import com.badlogic.gdx.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.physics.IPhysicsEngine;

public class DebugTest {
    private Debug debug;
    private IPhysicsModel model;
    private IPhysicsEngine physicsEngine;
    private ICamera camera;
    private GameInput gameInput;
    @Before
    public void setup() {
        model = mock(IPhysicsModel.class);
        camera = mock(ICamera.class);
        gameInput = mock(GameInput.class);
        physicsEngine = mock(IPhysicsEngine.class);
        debug = new Debug(model, camera, gameInput, physicsEngine);
    }

    @Test
    public void testGetFrameRate() throws Exception {

    }

    @Test
    public void testGetPlayer() throws Exception {
        assertEquals("should be the correct model", model, debug.getPlayer());
    }

    @Test
    public void testGetPhysics() throws Exception {
        assertEquals("should be the correct physics", physicsEngine, debug.getPhysics());
    }

    @Test
    public void testGetGameInput() throws Exception {
        assertEquals("should be the correct gameInput", gameInput, debug.getGameInput());
    }

    @Test
    public void testGetCamera() throws Exception {
        ICameraAdapter cameraAdapter = mock(ICameraAdapter.class);
        when(camera.getAdapter()).thenReturn(cameraAdapter);
        assertEquals("should be the correct camera", camera.getAdapter(), debug.getCamera());
    }

    @Test
    public void testUpdate() throws Exception {
        FrameRate frameRate = debug.getFrameRate();
        debug.update(18);
        double oldDelta = frameRate.getDelta();
        debug.update(16);
        assertNotEquals("delta should be updated", oldDelta, frameRate.getDelta());

    }

    @Test
    public void testGetCameraPosition() throws Exception {
        assertEquals("should be the correct position", camera.getPosition(), debug.getCameraPosition());
    }
}