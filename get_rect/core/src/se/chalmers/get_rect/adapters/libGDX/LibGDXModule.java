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
        LibGDXAssetManagerAdapter assetManager = new LibGDXAssetManagerAdapter();
        bind(IAssetManagerAdapter.class).toInstance(assetManager);
        bind(ICameraFactoryAdapter.class).to(LibGDXCameraFactoryAdapter.class);
        bind(IGraphicsAdapter.class).toInstance(new LibGDXGraphicsAdapter(assetManager));
        bind(IInputAdapter.class).to(LibGDXInputAdapter.class);
        bind(IRectangleFactoryAdapter.class).to(LibGDXRectangleFactoryAdapter.class);
        bind(IGameLoopAdapter.class).toInstance(gameLoopAdapter);
    }
}
