package models;

import javafx.scene.image.Image;

/**
 * Model class for Wall
 */
public class Wall {
    private Image image;
    private Room room;

    /**
     * Constructor for wall class
     *
     * @param image Image of the current wall faced by the player
     * @param room  Another room that is attached to the current wall (if applicable)
     */
    public Wall(Image image, Room room) {
        this.image = image;
        this.room = room;
    }

    /**
     * To get the image of the wall
     *
     * @return Image of the wall
     */
    public Image getWallImage() {
        return this.image;
    }

    /**
     * To get the next room accessible from the wall
     *
     * @return Room object
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * To set the wall with the room
     *
     * @param room The room moved into
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * To find whether or not the wall has a room associated with it
     *
     * @return true or false
     */
    public Boolean hasAnotherRoom() {
        return this.room != null;
    }

}
