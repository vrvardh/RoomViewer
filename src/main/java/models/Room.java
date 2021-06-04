package models;

import javafx.scene.image.Image;
import utils.ItemSet;
import utils.WallsMap;

/**
 * Model Class for Room
 */
public class Room {

    private String name;
    private WallsMap wallsMap;
    private ItemSet itemSet;

    /**
     * Constructor for Room class
     *
     * @param name     Name of the current room
     * @param wallsMap Walls associated with the room
     * @param itemSet  Items placed in the room
     */
    public Room(String name, WallsMap wallsMap, ItemSet itemSet) {
        this.name = name;
        this.wallsMap = wallsMap;
        this.itemSet = itemSet;
    }

    /**
     * To get the Hashmap containing walls of the room
     *
     * @return walls Hashmap
     */
    public WallsMap getWallsMap() {
        return wallsMap;
    }

    /**
     * To get the name of the room
     *
     * @return Room name
     */
    public String getName() {
        return name;
    }

    /**
     * To get Image of the wall
     *
     * @param direction Desired direction
     * @return Image associated with the direction specified
     */
    public Image getWallImage(Direction direction) {
        if (this.wallsMap == null) {
            return null;
        }
        return this.wallsMap.getWallImage(direction);
    }

    /**
     * To get the set of the items placed in the room
     *
     * @return item set of the room
     */
    public ItemSet getItems() {
        return this.itemSet;
    }

    /**
     * To get the wall based on the direction
     *
     * @param direction Desired direction
     * @return wall of the specified direction
     */
    public Wall getWall(Direction direction) {
        if (this.wallsMap == null) {
            return null;
        }
        return this.wallsMap.getWall(direction);
    }

    /**
     * To find if there is a wall in the specified direction
     *
     * @param direction Desired direction
     * @return True or False
     */
    public Boolean containsWall(Direction direction) {
        if (this.wallsMap == null) {
            return false;
        }
        return this.wallsMap.containsWall(direction);
    }

}