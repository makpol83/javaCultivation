package Entities.Character.Equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Entities.Interfaces.Container;
import Entities.Item.Item;
import Entities.Item.Components.EquippableZone;

public class Equipment implements Container{
    private List<Item> equippedItems = new ArrayList<>();
    private List<Slot> slots = new ArrayList<>();

    public Equipment(Collection<Slot> slots){
        this.slots.addAll(slots);
    }

    public boolean add(Item item){
        return this.equip(item);
    }

    public boolean remove(Object item){
        if(this.equippedItems.contains(item) == false)
            return false;

        Item itemToRemove = (Item)item;
        for(Slot slot : slots){
            if(slot.getEquippedItem().equals(itemToRemove) == true){
                slot.unequip();
            }
        }
        this.equippedItems.remove(itemToRemove);

        return true;
    }

    private boolean equip(Item item){
        if(item.isEquippable() == false)
            return false;

        Collection<EquippableZone> zonesToUse = item.getEquipableData().getZonesNeededToEquip();
        List<Slot> slotsToUse = new ArrayList<>();

        for(EquippableZone zone : zonesToUse){
            for(Slot slot : slots){
                if(slot.getZone().equals(zone) == true){
                    slotsToUse.add(slot);
                }
            }
        }

        if(slotsToUse.size() != zonesToUse.size())
            return false;

        this.equippedItems.add(item);
        item.setContainedIn(this);

        for(Slot slot : slotsToUse){
            slot.equip(item);
        }

        return true;
    }
}
