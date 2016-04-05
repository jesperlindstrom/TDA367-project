package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.IModel;

class Player implements IModel {

    private int xCoordinate;
    private int yCoordinate;
    private int health;

    /**
     * Initialize a new player
     */
    public Player(){

    }

    /**
     * Initialize a new player with fixed position
     * @param xCoordinate
     * @param yCoordinate
     */
    public Player(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Setter for plaer xCoordinate
     * @param xCoordinate
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Getter for player xCoordinate
     * @return
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Setter for player yCoordinate
     * @param yCoordinate
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Getter for player yCoordinate
     * @return
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Setter for player health
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Getter for player health
     * @return
     */
    public int getHealth() {
        return health;
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
}
