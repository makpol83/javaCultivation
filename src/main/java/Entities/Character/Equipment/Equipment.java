package Entities.Character.Equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Entities.Interfaces.Container;
import Entities.Item.Item;
import Entities.Item.Components.EquippableZone;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

public class Equipment implements Container{
    private List<Item> equippedItems = new ArrayList<>();
    private List<Slot> slots = new ArrayList<>();

    public Equipment(Collection<Slot> slots){
        this.slots.addAll(slots);
    }

    public void add(Slot slotToAdd) throws IllegalStateException{
        for(Slot slot : this.slots){
            if(slot.getZone().equals(slotToAdd.getZone()) == true)
                throw new IllegalStateException("Already has the slot.");
        }

        this.slots.add(slotToAdd);
    }

    public void addAll(Collection<Slot> slotsToAdd) throws IllegalStateException{
        for(Slot slot : slotsToAdd){
            this.add(slot);
        }
    }

    public Item remove(Slot slot) throws IllegalStateException{
        if(this.slots.contains(slot) == false)
            throw new IllegalStateException("Slot is not owned by this equipment.");

        this.slots.remove(slot);
        return slot.getEquippedItem();
    }

    public Collection<Item> remove(Collection<Slot> slotsToRemove) throws IllegalStateException{
        List<Item> itemsRemoved = new ArrayList<>();

        for(Slot slot : slotsToRemove){
            Item item = this.remove(slot);
            if(item != null){
                itemsRemoved.add(item);
            }
        }

        return itemsRemoved;
    }

    public void add(Item item) throws ContainerFullException, ItemAlreadyContainedException{
        if(item == null) 
            throw new NullPointerException("Item to add cannot be null.");

        if(this.equippedItems.contains(item) == true)
            throw new ItemAlreadyContainedException(this, item);

        this.equip(item);
    }

    public boolean contains(Item o){
        return this.equippedItems.contains(o);
    }

    public void remove(Item itemToRemove) {
        if(this.equippedItems.contains(itemToRemove) == false)
            return;

        for(Slot slot : slots){
            if(slot.getEquippedItem().equals(itemToRemove) == true){
                slot.unequip();
            }
        }
        this.equippedItems.remove(itemToRemove);
    }

    private void equip(Item item) throws ContainerFullException, IllegalStateException{
        if(item.isEquippable() == false)
            throw new IllegalStateException("Cannot equip a non-equippable item.");

        Collection<EquippableZone> zonesToUse = item.getEquipableData().getZonesNeededToEquip();
        List<Slot> slotsToUse = new ArrayList<>();

        for(EquippableZone zone : zonesToUse){
            for(Slot slot : slots){
                if(slot.getZone().equals(zone) == true){
                    if(slot.isUsed() == true)
                        throw new IllegalStateException("Slot already used by " + slot.getEquippedItem());
                    slotsToUse.add(slot);
                }
            }
        }

        if(slotsToUse.size() != zonesToUse.size())
            throw new IllegalStateException("Not found the zones to equip an item");

        this.equippedItems.add(item);
        item.setContainedIn(this);

        for(Slot slot : slotsToUse){
            slot.equip(item);
        }
    }
}
