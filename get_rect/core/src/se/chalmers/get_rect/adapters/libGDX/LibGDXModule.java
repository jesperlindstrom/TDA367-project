package se.chalmers.get_rect.adapters.libGDX;

import com.google.inject.AbstractModule;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class LibGDXModule extends AbstractModule {
    private IGameLoopAdapter gameLoopAdapter;

    public LibGDXModule(IGameLoopAdapter gameLoopAdapter) {
        this.gameLoopAdapter = gameLoopAdapter;
    }

    @Override
    protected void configure() {
        bind(IAssetManagerAdapter.class).toInstance(new LibGDXAssetManagerAdapter());
        bind(ICameraFactoryAdapter.class).to(LibGDXCameraFactoryAdapter.class);
        bind(IGraphicsAdapter.class).to(LibGDXGraphicsAdapter.class);
        bind(IInputAdapter.class).to(LibGDXInputAdapter.class);
        bind(IRectangleFactoryAdapter.class).to(LibGDXRectangleFactoryAdapter.class);
        bind(IGameLoopAdapter.class).toInstance(gameLoopAdapter);
    }
}
