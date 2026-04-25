package Entities.Item;

import java.util.ArrayList;
import java.util.List;

import Entities.Interfaces.Container;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

public class Inventory implements Container{
    private double baseCapacity;
    private List<Item> itemsStored = new ArrayList<>();

    private Item itemAssociated;

    public Inventory(double baseCapacity, List<Item> itemsStoredadd, Item itemAssociated){
        if(baseCapacity <= 0)
            throw new IllegalArgumentException("Base capacity cannot be 0 or negative");

        this.baseCapacity = baseCapacity;
        if(itemsStoredadd != null)
            this.itemsStored.addAll(itemsStored);
        this.itemAssociated = itemAssociated;
    }

    public double getBaseCapacity() {
        return baseCapacity;
    }

    public List<Item> getItemsStored() {
        return List.copyOf(this.itemsStored);
    }

    public Item getitemAssociated() {
        return itemAssociated;
    }

    public double getActualCapacity(){
        double actualCapacity = 0;
        for(Item item : this.itemsStored){
            actualCapacity += item.getCapacityRequired();
        }

        return baseCapacity - actualCapacity;
    }

    public void add(Item item) throws ContainerFullException, ItemAlreadyContainedException{
        if(item.getCapacityRequired() > getActualCapacity())
            throw new ContainerFullException(this);

        if(this.itemsStored.contains(item) == true)
            throw new ItemAlreadyContainedException(this, item);
        
        this.itemsStored.remove(item);

        item.setContainedIn(this);
        this.itemsStored.add(item);
    }

    public void remove(Item item){
        if(this.itemsStored.contains(item) == false)
            return;

        item.setContainedIn(null);
        this.itemsStored.remove(item);
    }

    @Override
    public boolean contains(Item item){
        return this.itemsStored.contains(item);
    }
    
    public Inventory clone(Item item){
        List<Item> copiedItems = new ArrayList<>();
        for(Item i : this.itemsStored){
            copiedItems.add(i.clone(null));
        }

        return new Inventory(baseCapacity, copiedItems, itemAssociated);
    }
}
