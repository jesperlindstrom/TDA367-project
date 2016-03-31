package se.chalmers.get_rect.game.entities.controller;

import se.chalmers.get_rect.game.entities.model.Player;
import se.chalmers.get_rect.game.entities.view.PlayerView;

public class PlayerEntity implements ISolidEntity {
    private Player model;
    private PlayerView view;

    public PlayerEntity() {
        model = new Player();
        view = new PlayerView(model);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }
}
