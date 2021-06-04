package controllers;

import models.Player;

/**
 * Abstract Controller which acts as the parent to the class extending it
 */
public abstract class AbstractController {
    protected Player player;

    /**
     * Set the child controller with the player
     *
     * @param player Player that needs to be set to the controller
     */
    public void setPlayer(Player player) {
        this.player = player;
        this.updateView();
    }

    /**
     * Helps in updating the view when player is set to the model
     * child controller needs to implement the method
     */
    abstract void updateView();
}
