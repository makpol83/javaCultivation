package Entities.Item;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Entity;
import Entities.EntityType;
import Entities.Item.Components.ConsumableComponent;
import Entities.Item.Components.EquipableComponent;

@DatabaseTable(tableName = "item_types")
public class Item extends Entity {

    @DatabaseField
    private String name;

    @DatabaseField
    private String description;

    @DatabaseField
    private double capacityRequired;

    @DatabaseField
    private boolean isRedimensionable;  //True if can be stored in the inventory of an object
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private EquipableComponent equipableData;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private ConsumableComponent consumableData;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Inventory inventoryData; //If it has an inventory

    public Item(String name, String description, double capacityRequired, boolean isRedimensionable,
            EquipableComponent equipableData, ConsumableComponent consumableData, Inventory inventoryData){
        super();

        if(name == null)
            throw new NullPointerException("Name cannot be null.");

        if(description == null)
            throw new NullPointerException("Description cannot be null.");

        if(capacityRequired < 0)
            throw new IllegalArgumentException("Capacity required must be >= 0.");

        this.name = new String(name);
        this.description = new String(description);
        this.capacityRequired = capacityRequired;
        this.isRedimensionable = isRedimensionable;
        this.equipableData = equipableData;
        this.consumableData = consumableData;
        this.inventoryData = inventoryData;
    }

    public String getName() {
        return new String(name);
    }

    public String getDescription() {
        return new String(description);
    }

    public double getCapacityRequired() {
        return capacityRequired;
    }

    public boolean isRedimensionable() {
        return isRedimensionable;
    }

    public EquipableComponent getEquipableData() {
        return equipableData;
    }

    public ConsumableComponent getConsumableData() {
        return consumableData;
    }

    public Inventory getInventoryOfItem(){
        return inventoryData;
    }

    public boolean isEquippable(){
        if(equipableData == null)
            return false;

        return true;
    }

    public boolean isConsumable(){
        if(consumableData == null)
            return false;

        return true;
    }

    public boolean hasInventory(){
        if(inventoryData == null)
            return false;

        return true;
    }

    @Override
    public EntityType getEntityType(){ return EntityType.ITEM; }
}
