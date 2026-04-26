package Exceptions.Item;

import Entities.Item.Inventory;

public class InventoryFullException extends Exception{
    Inventory container;

    public InventoryFullException(Inventory container){
        super("Container is full and can't add any items.");
        this.container = container;
    }

    public Inventory getInventory(){ return this.container; }
}
