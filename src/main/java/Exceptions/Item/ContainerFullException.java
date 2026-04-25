package Exceptions.Item;

import Entities.Interfaces.Container;

public class ContainerFullException extends Exception{
    Container container;

    public ContainerFullException(Container container){
        super("Container is full and can't add any items.");
        this.container = container;
    }

    public Container getInventory(){ return this.container; }
}
