package Entities.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import Exceptions.Item.InventoryFullException;
import Exceptions.Item.ItemAlreadyContainedException;

@DatabaseTable(tableName = "inventories")
public class Inventory{
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private double baseCapacity;

    @ForeignCollectionField(eager = true)
    private Collection<ItemInstance> itemsStored = new ArrayList<>();

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ItemInstance itemAssociated;

    protected Inventory() {}

    public Inventory(double baseCapacity, ItemInstance itemAssociated){
        if(baseCapacity <= 0)
            throw new IllegalArgumentException("Base capacity cannot be 0 or negative");

        this.baseCapacity = baseCapacity;
        this.itemAssociated = itemAssociated;
    }

    public double getBaseCapacity() {
        return baseCapacity;
    }

    public Collection<ItemInstance> getItemsStored() {
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

    public void add(ItemInstance item) throws InventoryFullException, ItemAlreadyContainedException{
        double actualCapacity = getActualCapacity();
        if(item.getCapacityRequired() + actualCapacity > this.baseCapacity)
            throw new InventoryFullException(this);

        if(this.itemsStored.contains(item) == true)
            throw new ItemAlreadyContainedException(this, item);
        
        item.setInventoryContainer(this);
        this.itemsStored.add(item);
    }

    public void remove(ItemInstance item){
        if(this.itemsStored.contains(item) == false)
            return;

        item.setInventoryContainer(null);
        this.itemsStored.remove(item);
    }

    public boolean contains(ItemInstance item){
        return this.itemsStored.contains(item);
    }
}
