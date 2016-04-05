package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.IModel;

class Player implements IModel {

    private int xCoordinate;
    private int yCoordinate;
    private int currentHealth;
    private int maxHealth;
    private int level;

    /**
     * Initialize a new player with fixed position and 10 hp and level 1.
     * @param xCoordinate
     * @param yCoordinate
     */
    public Player(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.maxHealth = 10;
        this.currentHealth = maxHealth;
        this.level = 1;
    }

    /**
     * Setter for player x & y coordinates
     * @param xCoordinate
     * @param yCoordinate
     */
    public void setCoordinates(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Setter for player xCoordinate
     * @param xCoordinate
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Setter for player yCoordinate
     * @param yCoordinate
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Getter for player xCoordinate
     * @return
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Getter for player yCoordinate
     * @return
     */
    public int getyCoordinate() {
        return yCoordinate;
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
     * Setter for player level
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Getter for player level
     * @return
     */
    public int getLevel() {
        return level;
    }
}
