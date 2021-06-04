package utils;

import models.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Class for Set of Items
 */
public class ItemSet {

    private HashSet<Item> items = new HashSet<>();

    /**
     * To get items
     *
     * @return Items Hashset
     */
    public HashSet<Item> getItem() {
        return items;
    }

    /**
     * To add item to the Item set
     *
     * @param item Desired item to be added
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * To get the number of items in the Item HashSet
     *
     * @return Number of items
     */
    public Integer getSize() {
        return this.items.size();
    }

    /**
     * To get the item from the specified index
     *
     * @param index Desired item index
     * @return Item
     */
    public Item getItemAt(Integer index) {
        try {
            List<Item> itemsList = new ArrayList<>(this.items);
            return itemsList.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * To remove item from the Item HashSet
     *
     * @param item Item to be removed
     */
    public void removeItem(Item item) {
        this.items.remove(item);
    }
}