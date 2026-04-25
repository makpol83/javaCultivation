package Entities.Character.Equipment;

import Entities.Item.Item;
import Entities.Item.Components.EquippableZone;

public class Slot {
    private EquippableZone zone;
    private Item item;

    public Slot(EquippableZone zone, Item item){
        this.zone = zone;
        this.item = item;
    }

    public boolean isUsed(){
        if(item == null)
            return false;
        
        return true;
    }

    public EquippableZone getZone(){ return this.zone; }

    public boolean equip(Item item){
        if(item.isEquippable() == false)
            return false;

        if(item.getEquipableData().getZonesNeededToEquip().contains(zone) == false)
            return false;

        this.item = item;
        return true;
    }

    public boolean unequip(){
        if(this.item == null)
            return false;

        this.item = null;
        return true;
    }

    public Item getEquippedItem(){
        return item;
    }
}
