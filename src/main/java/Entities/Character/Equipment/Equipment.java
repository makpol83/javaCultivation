package Entities.Character.Equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Interfaces.Container;
import Entities.Item.ItemInstance;
import Entities.Item.Components.EquippableZone;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

@DatabaseTable(tableName = "equipments")
public class Equipment implements Container{

    @DatabaseField(generatedId = true)
    private long id;

    @ForeignCollectionField(eager = true)
    private Collection<Slot> slots = new ArrayList<>();

    public Equipment(){}

    public Equipment(Collection<Slot> slots){
        this.slots.addAll(slots);
    }

    public Collection<ItemInstance> getEquippedItems(){
        Collection<ItemInstance> equippedItems = new ArrayList<>();
        for(Slot slot : slots){
            if(slot.isUsed() == true){
                equippedItems.add(slot.getEquippedItem());
            }
        }

        return equippedItems;
    }

    public void add(Slot slotToAdd) throws IllegalStateException{
        for(Slot slot : this.slots){
            if(slot.getZone().equals(slotToAdd.getZone()) == true)
                throw new IllegalStateException("Already has the slot.");
        }

        slotToAdd.setEquipment(this);
        this.slots.add(slotToAdd);
    }

    public void addAll(Collection<Slot> slotsToAdd) throws IllegalStateException{
        for(Slot slot : slotsToAdd){
            this.add(slot);
        }
    }

    public ItemInstance remove(Slot slot) throws IllegalStateException{
        if(this.slots.contains(slot) == false)
            throw new IllegalStateException("Slot is not owned by this equipment.");

        slot.setEquipment(null);
        this.slots.remove(slot);
        return slot.getEquippedItem();
    }

    public Collection<ItemInstance> remove(Collection<Slot> slotsToRemove) throws IllegalStateException{
        List<ItemInstance> itemsRemoved = new ArrayList<>();

        for(Slot slot : slotsToRemove){
            ItemInstance item = this.remove(slot);
            if(item != null){
                itemsRemoved.add(item);
            }
        }

        return itemsRemoved;
    }

    public void add(ItemInstance item) throws ContainerFullException, ItemAlreadyContainedException{
        if(item == null) 
            throw new NullPointerException("Item to add cannot be null.");

        if(getEquippedItems().contains(item) == true)
            throw new ItemAlreadyContainedException(this, item);

        this.equip(item);
    }

    public boolean contains(ItemInstance o){
        return this.getEquippedItems().contains(o);
    }

    public void remove(ItemInstance itemToRemove) {
        if(this.getEquippedItems().contains(itemToRemove) == false)
            return;

        for(Slot slot : slots){
            if(slot.getEquippedItem() == null)
                continue;

            if(slot.getEquippedItem().equals(itemToRemove) == true){
                slot.unequip();
            }
        }
    }

    private void equip(ItemInstance item) throws ContainerFullException, IllegalStateException{
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

        for(Slot slot : slotsToUse){
            slot.equip(item);
        }
    }
}
