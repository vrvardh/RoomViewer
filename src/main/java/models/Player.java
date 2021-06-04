package models;

import javafx.scene.image.Image;
import utils.ItemSet;

/**
 * Model class for Player
 */
public class Player {
    private Room currentRoom;
    private ItemSet itemSet;
    private Direction direction;

    /**
     * Constructor for Player Class
     *
     * @param room      CurrentRoom the player is in
     * @param direction Direction faced by the player
     * @param itemSet   item set with player
     */
    public Player(Room room, Direction direction, ItemSet itemSet) {
        this.currentRoom = room;
        this.direction = direction;
        this.itemSet = itemSet;
    }

    /**
     * To get the current image of the room based on direction
     *
     * @param direction Required direction of the room
     * @return Image corresponding to the direction
     */
    public Image getCurrentImage(Direction direction) {
        return this.currentRoom.getWallImage(direction);
    }

    /**
     * To get the Direction of the player
     *
     * @return The current direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * To get the current room the player is in
     *
     * @return The current room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * To get the item set held by the player
     *
     * @return set of items
     */
    public ItemSet getItems() {
        return this.itemSet;
    }

    /**
     * Helps the player to navigate Left/Right
     *
     * @param direction The direction in which the payer wishes to turn
     */
    public void navigate(Direction direction) {
        if (this.currentRoom.containsWall(direction)) {
            this.direction = direction;
        }
    }

    /**
     * Helps the player to move forward to another room
     */
    public void moveForward() {
        Wall wall = null;

        //check current room has a wall in specified direction
        if (this.getCurrentRoom().containsWall(this.direction)) {
            wall = this.getCurrentRoom().getWall(direction);
        }
        //Set current room with the another room if the wall has a room associated with it
        if (wall != null && wall.hasAnotherRoom()) {
            this.currentRoom = wall.getRoom();
        }
    }
}