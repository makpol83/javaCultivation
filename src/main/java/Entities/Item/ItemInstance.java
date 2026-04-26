package Entities.Item;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Entity;
import Entities.EntityType;
import Entities.Item.Components.ConsumableComponent;
import Entities.Item.Components.DurabilityComponent;
import Entities.Item.Components.EquipableComponent;
import Exceptions.Item.InventoryFullException;
import Exceptions.Item.ItemAlreadyContainedException;

@DatabaseTable(tableName = "item_instances")
public class ItemInstance extends Entity{

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Item originalItem;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private EquipableComponent equipableComponent;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ConsumableComponent consumableComponent;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Inventory inventoryData;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Inventory containedIn;

    public ItemInstance(){

    }

    public ItemInstance(Item originalItem){
        this.originalItem = originalItem;

        if(originalItem.isEquippable() == true){
            EquipableComponent ec = originalItem.getEquipableData();
            DurabilityComponent dc = ec.getDurabilityData();
            DurabilityComponent dcToAdd = new DurabilityComponent(dc.getActualDurability(), dc.getMaxDurability(), dc.isRepairable(), dc.canBeRepairedIfBroken(), dc.getRepairMethod());

            this.equipableComponent = new EquipableComponent(ec.getPyshicalArmorPoints(), ec.getSpiritualArmorPoints(), ec.getCriticalPyshicalDefenseModifier(), ec.getCriticalSpiritualDefenseModifier(), ec.getBaseDamage(), ec.getCriticalDamageModifier(), ec.getEquippableEffect(), dcToAdd, ec.getZonesNeededToEquip());

        } else {
            this.equipableComponent = null;
        }

        if(originalItem.isConsumable() == true){
            ConsumableComponent cc = originalItem.getConsumableData();

            this.consumableComponent = new ConsumableComponent(cc.getAvailableUses(), cc.getMaxUses(), cc.isRefillable(), cc.getEffect());
        } else {
            this.consumableComponent = null;
        }

        if(originalItem.hasInventory() == true){
            this.inventoryData = new Inventory(originalItem.getInventoryOfItem().getBaseCapacity(), this);
        } else {
            this.inventoryData = null;
        }
    }

    public void setInventoryContainer(Inventory inventory){
        //can be null
        this.containedIn = inventory;
    }

    public void add(ItemInstance item) throws IllegalStateException, InventoryFullException, ItemAlreadyContainedException{
        if(item == null)
            throw new NullPointerException("Item cannot be null");

        if(originalItem.hasInventory() == false)
            throw new IllegalStateException("Cannot add an item to the inventory if item does not have inventory.");

        this.inventoryData.add(item);
    }

    public void remove(ItemInstance item) throws IllegalStateException{
        if(item == null)
            throw new NullPointerException("Item cannot be null.");

        if(originalItem.hasInventory() == false)
            throw new IllegalStateException("Cannot remove an item from the inventory if item does not have inventory.");

        this.inventoryData.remove(item);
    }

    public boolean contains(ItemInstance item){
        if(item == null)
            throw new NullPointerException("Item cannot be null.");

        if(originalItem.hasInventory() == false)
            throw new IllegalStateException("Item does not have inventory.");

        return this.inventoryData.contains(item);
    }

    public String getName() {
        return originalItem.getName();
    }

    public String getDescription() {
        return originalItem.getDescription();
    }

    public double getCapacityRequired() {
        return originalItem.getCapacityRequired();
    }

    public boolean isRedimensionable() {
        return originalItem.isRedimensionable();
    }

    public EquipableComponent getEquipableData() {
        return this.equipableComponent;
    }

    public ConsumableComponent getConsumableData() {
        return this.consumableComponent;
    }

    public Inventory getInventoryOfItem(){
        return inventoryData;
    }

    public boolean isEquippable(){
        return originalItem.isEquippable();
    }

    public boolean isConsumable(){
        return originalItem.isConsumable();
    }

    public boolean hasInventory(){
        if(inventoryData == null)
            return false;

        return true;
    }

    @Override
    public EntityType getEntityType(){ return EntityType.ITEM; }
}
