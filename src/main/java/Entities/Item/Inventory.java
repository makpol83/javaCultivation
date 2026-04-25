package Entities.Item;

import java.util.ArrayList;
import java.util.List;

import Entities.Interfaces.Container;

public class Inventory extends ArrayList<Item> implements Container{
    private double baseCapacity;

    private Item itemAssociated;

    public Inventory(double baseCapacity, List<Item> itemsStored, Item itemAssociated) {
        this.baseCapacity = baseCapacity;
        this.addAll(itemsStored);
        this.itemAssociated = itemAssociated;
    }

    public double getBaseCapacity() {
        return baseCapacity;
    }

    public List<Item> getItemsStored() {
        return List.copyOf(this);
    }

    public Item getitemAssociated() {
        return itemAssociated;
    }

    public double getActualCapacity(){
        double actualCapacity = 0;
        for(Item item : this){
            actualCapacity += item.getCapacityRequired();
        }

        return baseCapacity - actualCapacity;
    }

    @Override
    public boolean add(Item item){
        if(item.getCapacityRequired() > getActualCapacity())
            return false;

        if(this.contains(item) == true)
            return false;
        
        this.remove(item);

        item.setContainedIn(this);
        return super.add(item);
    }

    @Override
    public boolean remove(Object item){
        if(this.contains(item) == false)
            return false;

        //We only store Items or items that extend from it
        ((Item) item).setContainedIn(null);
        return super.remove(item);
    }

    
    public Inventory clone(Item item){
        List<Item> copiedItems = new ArrayList<>();
        for(Item i : this){
            copiedItems.add(i.clone(null));
        }

        return new Inventory(baseCapacity, copiedItems, itemAssociated);
    }
}
