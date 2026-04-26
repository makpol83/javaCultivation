package Entities.Character.Equipment;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Item.ItemInstance;
import Entities.Item.Components.EquippableZone;

@DatabaseTable(tableName = "equipment_slots")
public class Slot {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private EquippableZone zone;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = true)
    private ItemInstance item;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "owner_id")
    private Equipment equipment;

    public Slot(){}

    public Slot(EquippableZone zone, ItemInstance item){
        if(zone == null)
            throw new NullPointerException("Zone of slot cannot be null");

        this.zone = zone;
        this.item = item;
    }

    public void setEquipment(Equipment e){
        this.equipment = e;
    }

    public boolean isUsed(){
        if(item == null)
            return false;
        
        return true;
    }

    public EquippableZone getZone(){ return this.zone; }

    public void equip(ItemInstance item){
        this.item = item;
    }

    public void unequip(){
        this.item = null;
    }

    public ItemInstance getEquippedItem(){
        return item;
    }
}
