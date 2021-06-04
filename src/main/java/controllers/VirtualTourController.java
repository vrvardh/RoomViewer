package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Item;
import models.Room;
import utils.ItemSet;

/**
 * Controller Class VirtualTourController for mainTour.fxml
 */
public class VirtualTourController extends AbstractController {

    @FXML
    private ImageView imageBackground;

    @FXML
    private Button buttonForward;

    @FXML
    private Menu menuPickUp;

    @FXML
    private Menu menuPutDown;

    @FXML
    private ImageView itemImage1;

    @FXML
    private ImageView itemImage2;

    /**
     * To update the view on every action performed by the player
     */
    public void updateView() {
        if (this.player != null) {
            this.forwardButtonEnable();
            this.imageBackground.setImage(this.player.getCurrentImage(this.player.getDirection()));
            this.pickUpVisibility();
            this.putDownVisibility();
            if (this.player.getCurrentRoom().getItems() != null) {
                setItemFromPlayerItemSet(this.player.getCurrentRoom().getItems());
            } else {
                itemImage1.setImage(null);
                itemImage2.setImage(null);
            }
        }
    }

    /**
     * Method to update Pick Up Menu Items
     * Menu Items are populated based on the items available in the Current Room
     */
    private void pickUpVisibility() {
        menuPickUp.getItems().clear();
        for (Item oneItem : this.player.getCurrentRoom().getItems().getItem()) {
            if (oneItem.getItemImage() != null) {
                MenuItem menuItem = new MenuItem(oneItem.getItemName());
                menuPickUp.getItems().add(menuItem);

                //set onAction for the menu items created
                menuItem.setOnAction(this::pickUp);
            }
        }
    }

    /**
     * Method to update Put Down Menu Items
     * Menu Items are populated based on the items held by the player
     */
    private void putDownVisibility() {
        menuPutDown.getItems().clear();
        for (Item oneItem : this.player.getItems().getItem()) {
            MenuItem menuItem = new MenuItem(oneItem.getItemName());
            menuPutDown.getItems().add(menuItem);
            //set onAction for the menu items created
            menuItem.setOnAction(this::putDown);
        }
    }

    /**
     * Method that helps the player turn left
     */
    public void turnLeft() {
        this.player.navigate(this.player.getDirection().previous());
        updateView();
    }

    /**
     * Method that helps the player move forward
     */
    public void goForward() {
        this.player.moveForward();
        updateView();
    }

    /**
     * Method that helps the player turn right
     */
    public void turnRight() {
        this.player.navigate(this.player.getDirection().next());
        updateView();
    }

    /**
     * Method that helps the player pick up items from the Current Room
     */
    public void pickUp(ActionEvent actionEvent) {
        //Get the details of Menu Item which was clicked
        MenuItem menuItem = (MenuItem) actionEvent.getSource();

        //Get the corresponding Item from the current room ItemSet
        Item item = getItemFromMenuId(this.player.getCurrentRoom().getItems(), menuItem, true);

        if (item != null) {
            //Remove the Item from Current Room ItemSet and add to the Player ItemSet
            this.player.getCurrentRoom().getItems().removeItem(item);
            this.player.getItems().addItem(item);

            //Set Item Image to the available Image Viewers
            if (itemImage1.getImage() != null && itemImage1.getImage().equals(item.getItemImage())) {
                itemImage1.setImage(null);
            } else if (itemImage2.getImage() != null && itemImage2.getImage().equals(item.getItemImage())) {
                itemImage2.setImage(null);
            }
        }
        updateView();
    }

    /**
     * Method that helps the Player put down items to the Current Room
     */
    public void putDown(ActionEvent actionEvent) {
        //Get the details of Menu Item which was clicked
        MenuItem menuItem = (MenuItem) actionEvent.getSource();

        //Get the corresponding Item from the Player ItemSet
        Item item = getItemFromMenuId(this.player.getItems(), menuItem, false);

        //Remove the Item from Player ItemSet and add to the Current Room ItemSet
        if (item != null) {
            this.player.getItems().removeItem(item);
            this.player.getCurrentRoom().getItems().addItem(item);
            updateView();
        }
    }

    /**
     * Method to manage forward button disability
     * Enables only when the wall faced by the player has a room associated to it
     * Disables when there no exit
     */
    private void forwardButtonEnable() {
        Room nextRoom = this.player.getCurrentRoom().getWall(this.player.getDirection()).getRoom();
        this.buttonForward.setDisable(nextRoom == null);
    }

    /**
     * Method to fetch the Item
     *
     * @param listItems  list of items from Current Room in case of Pick Up
     *                   list of items from Player in case of Put Down
     * @param menuItem   Menu Item details which was clicked from the Menu
     * @param pickUpFlag True in case of calling method is Pick up
     *                   False in case of calling method is Put Down
     * @return Item
     */
    public Item getItemFromMenuId(ItemSet listItems, MenuItem menuItem, Boolean pickUpFlag) {
        Item output = null;
        if (menuItem.getText() != null && !menuItem.getText().isEmpty() && listItems != null) {
            for (int i = 0; i < listItems.getSize(); i++) {

                //Get the Item from ItemSet based on the Menu Item name
                if (listItems.getItemAt(i).getItemName() != null && !listItems.getItemAt(i).getItemName().isEmpty()
                        && listItems.getItemAt(i).getItemName().equals(menuItem.getText())) {
                    if (pickUpFlag) {
                        output = this.player.getCurrentRoom().getItems().getItemAt(i);
                    } else {
                        output = this.player.getItems().getItemAt(i);
                    }
                }
            }
        }
        return output;
    }

    /**
     * Method to set Images in Current Room
     *
     * @param currentRoomItemSet ItemSet of the Current Room
     */
    public void setItemFromPlayerItemSet(ItemSet currentRoomItemSet) {
        itemImage1.setImage(null);
        itemImage2.setImage(null);
        if (currentRoomItemSet != null) {
            for (Item oneItem : currentRoomItemSet.getItem()) {
                if (oneItem != null && oneItem.getItemImage() != null && !oneItem.getItemImage().equals(itemImage1.getImage())) {
                    updateCurrentImage(oneItem.getItemImage());
                }
            }
        }
    }

    /**
     * Method to update Image Viewers based on availability
     *
     * @param imageName name of the image
     */
    public void updateCurrentImage(Image imageName) {
        if (itemImage1.getImage() == null) {
            itemImage1.setImage(imageName);
        } else if (itemImage2.getImage() == null) {
            itemImage2.setImage(imageName);
        }
    }
}