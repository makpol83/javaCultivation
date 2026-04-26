package Entities.Item;

import java.util.ArrayList;
import java.util.List;

import Entities.Interfaces.Container;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

public class Inventory implements Container{

    private double baseCapacity;

    private List<ItemInstance> itemsStored = new ArrayList<>();

    private ItemInstance itemAssociated;

    public Inventory(double baseCapacity, List<ItemInstance> itemsStoredadd, ItemInstance itemAssociated){
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

    public List<ItemInstance> getItemsStored() {
        return List.copyOf(this.itemsStored);
    }

    public ItemInstance getitemAssociated() {
        return itemAssociated;
    }

    public double getActualCapacity(){
        double actualCapacity = 0;
        for(ItemInstance item : this.itemsStored){
            actualCapacity += item.getCapacityRequired();
        }

        return baseCapacity - actualCapacity;
    }

    public void add(ItemInstance item) throws ContainerFullException, ItemAlreadyContainedException{
        if(item.getCapacityRequired() > getActualCapacity())
            throw new ContainerFullException(this);

        if(this.itemsStored.contains(item) == true)
            throw new ItemAlreadyContainedException(this, item);
        
        this.itemsStored.add(item);
    }

    public void remove(ItemInstance item){
        if(this.itemsStored.contains(item) == false)
            return;

        this.itemsStored.remove(item);
    }

    @Override
    public boolean contains(ItemInstance item){
        return this.itemsStored.contains(item);
    }
}
