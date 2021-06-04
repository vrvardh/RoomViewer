package models;

import javafx.scene.image.Image;

/**
 * The model class for Item
 */
public class Item {
    private String name;
    private Image image;

    /**
     * Constructor for Item Class
     *
     * @param name  Name of the Item
     * @param image Image of the Item
     */
    public Item(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    /**
     * To get the name of the Item
     *
     * @return item name
     */
    public String getItemName() {
        return this.name;
    }

    /**
     * To get the image of the Item
     *
     * @return image of the item
     */
    public Image getItemImage() {
        return this.image;
    }

}