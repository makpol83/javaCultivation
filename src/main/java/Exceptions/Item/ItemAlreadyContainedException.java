package Exceptions.Item;

import Entities.Item.Inventory;
import Entities.Item.ItemInstance;

public class ItemAlreadyContainedException extends Exception{
    private Inventory container;
    private ItemInstance item;

    public ItemAlreadyContainedException(Inventory container, ItemInstance item){
        super("Item " + item.getId() + " is already contained on inventory.");
        this.container = container;
        this.item = item;
    }

    public ItemAlreadyContainedException(ItemInstance item){
        super("Item " + item.getId() + " is already contained on inventory.");
        this.item = item;
    }

    public Inventory getInventory(){ return this.container; }
    public ItemInstance getItem(){ return this.item; }
    
}
