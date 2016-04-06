package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

class Player implements IModel {

    private Point position;
    private int currentHealth;
    private int maxHealth;
    private int level;
    private boolean isWalking;
    private boolean isJumping;

    /**
     * Initialize a new player with fixed position and 10 hp and level 1.
     * @param position
     */
    public Player(Point position){
        this.position = position;
        this.maxHealth = 10;
        this.currentHealth = maxHealth;
        this.level = 1;
        this.isWalking = false;
        this.isJumping = false;
    }

    /**
     * Setter for player x & y coordinates
     * @param xCoordinate
     * @param yCoordinate
     */
    public void setPosition(int xCoordinate, int yCoordinate){
        position.setPosition(xCoordinate, yCoordinate);
    }

    /**
     * Setter for player xCoordinate
     * @param xCoordinate
     */
    public void setX(int xCoordinate) {
        position.setxCoodrinate(xCoordinate);
    }

    /**
     * Setter for player yCoordinate
     * @param yCoordinate
     */
    public void setY(int yCoordinate) {
        position.setyCoordinate(yCoordinate);
    }

    /**
     * Getter for player xCoordinate
     * @return
     */
    public int getX() {
        return position.getxCoodrinate();
    }

    /**
     * Getter for player yCoordinate
     * @return
     */
    public int getY() {
        return position.getyCoordinate();
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
}
