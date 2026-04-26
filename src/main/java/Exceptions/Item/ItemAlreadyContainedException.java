package Exceptions.Item;

import Entities.Interfaces.Container;
import Entities.Item.ItemInstance;

public class ItemAlreadyContainedException extends Exception{
    private Container container;
    private ItemInstance item;

    public ItemAlreadyContainedException(Container container, ItemInstance item){
        super("Item " + item.getId() + " is already contained on container.");
        this.container = container;
        this.item = item;
    }

    public Container getInventory(){ return this.container; }
    public ItemInstance getItem(){ return this.item; }
    
}
