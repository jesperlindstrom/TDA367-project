package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;

class Player implements IPhysicsModel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private Point position;
    private IRectangleAdapter boundingBox;
    private int currentHealth;
    private int maxHealth;
    private int level;
    private boolean isWalking;
    private boolean isJumping;
    private int moveVelocity;
    private int jumpVelocity;

    /**
     * Initialize a new player with fixed position and 10 hp and level 1.
     * @param position
     * @param rectangleFactory
     */
    public Player(Point position, IRectangleFactoryAdapter rectangleFactory) {
        this.position = position;
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);
        this.maxHealth = 10;
        this.currentHealth = maxHealth;
        this.level = 1;
        this.isWalking = false;
        this.isJumping = false;
        this.moveVelocity = 30;
        this.jumpVelocity = 50;
    }

    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    /**
     * Setter for player x & y coordinates
     * @param xCoordinate
     * @param yCoordinate
     */
    public void setPosition(int xCoordinate, int yCoordinate){
        position = position.setPosition(xCoordinate, yCoordinate);
        boundingBox.setPosition(position);
    }

    @Override
    public void setPosition(Point point) {
        position = new Point(point);
        boundingBox.setPosition(position);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    /**
     * Setter for player xCoordinate
     * @param x
     */
    public void setX(int x) {
        position = position.setX(x);
        boundingBox.setPosition(position);
    }

    /**
     * Setter for player yCoordinate
     * @param y
     */
    public void setY(int y) {
        position = position.setY(y);
        boundingBox.setPosition(position);
    }

    /**
     * Setter for player current health
     * @param health
     */
    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    /**
     * Getter for player current health
     * @return
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Setter for player maxHealth
     * @param maxHealth
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Getter for player max health
     * @return
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Setter for player level.
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Getter for player level..
     * @return
     */
    public int getLevel() {
        return level;
    }


    /**
     * Setter for "player is walking".
     * @param Walking
     */
    public void setWalking(boolean Walking){
        this.isWalking = Walking;
    }

    /**
     * Geter to check if player walks.
     * @return
     */
    public boolean getWalking(){
        return isWalking;
    }

    /**
     * Setter to change player jumping state
     * @param jumping
     */
    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    /**
     * Getter to check if player jumps
     * @return
     */
    public boolean getJumping(){
        return isJumping;
    }

    /**
     * Getter for player velocity
     * @return
     */
    public Point getMoveVelocity() {
        return new Point(moveVelocity, 0);
    }

    /**
     * Getter for player jump velocity
     * @return
     */
    public double getJumpVelocity() {
        return jumpVelocity;
    }

    /**
     * This method sets the player's movement speed and direction from user input.
     * @param input
     */
    public void move(IInputAdapter input){
        if(input.isKeyPressed(IInputAdapter.Keys.A)){
            moveVelocity = -30;
        } else if (input.isKeyPressed(IInputAdapter.Keys.D)){
            moveVelocity = 30;
        } else {
            moveVelocity = 0;
        }
    }
}

