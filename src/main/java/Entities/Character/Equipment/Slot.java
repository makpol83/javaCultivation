package Entities.Character.Equipment;

import Entities.Item.Item;
import Entities.Item.Components.EquippableZone;

public class Slot {
    private EquippableZone zone;
    private Item item;

    public Slot(EquippableZone zone, Item item){
        if(zone == null)
            throw new NullPointerException("Zone of slot cannot be null");

        this.zone = zone;
        this.item = item;
    }

    public boolean isUsed(){
        if(item == null)
            return false;
        
        return true;
    }

    public EquippableZone getZone(){ return this.zone; }

    public void equip(Item item){
        this.item = item;
    }

    public void unequip(){
        this.item = null;
    }

    public Item getEquippedItem(){
        return item;
    }
}
