package Exceptions.Item;

import Entities.Interfaces.Container;
import Entities.Item.Item;

public class ItemAlreadyContainedException extends Exception{
    private Container container;
    private Item item;

    public ItemAlreadyContainedException(Container container, Item item){
        super("Item " + item.getId() + " is already contained on container.");
        this.container = container;
        this.item = item;
    }

    public Container getInventory(){ return this.container; }
    public Item getItem(){ return this.item; }
    
}
