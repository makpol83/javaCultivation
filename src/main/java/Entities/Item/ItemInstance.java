package Entities.Item;

import java.util.List;

import com.google.gson.Gson;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Entity;
import Entities.EntityType;
import Entities.Interfaces.Container;
import Entities.Item.Components.ConsumableComponent;
import Entities.Item.Components.DurabilityComponent;
import Entities.Item.Components.EquipableComponent;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

@DatabaseTable(tableName = "item_instances")
public class ItemInstance extends Entity implements Container{

    @DatabaseField
    private Item originalItem;

    @DatabaseField
    private String equipableComponentJSON;

    @DatabaseField
    private String consumableComponentJSON;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Inventory inventoryData;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Inventory containedIn;

    private static final Gson gson = new Gson();

    private transient EquipableComponent cachedEquipable;
    private transient ConsumableComponent cachedConsumable;

    public ItemInstance(){

    }

    public ItemInstance(Item originalItem){
        this.originalItem = originalItem;

        if(originalItem.isEquippable() == true){
            EquipableComponent ec = originalItem.getEquipableData();
            DurabilityComponent dc = ec.getDurabilityData();
            DurabilityComponent dcToAdd = new DurabilityComponent(dc.getActualDurability(), dc.getMaxDurability(), dc.isRepairable(), dc.canBeRepairedIfBroken(), dc.getRepairMethod());

            EquipableComponent finalec = new EquipableComponent(ec.getPyshicalArmorPoints(), ec.getSpiritualArmorPoints(), ec.getCriticalPyshicalDefenseModifier(), ec.getCriticalSpiritualDefenseModifier(), ec.getBaseDamage(), ec.getCriticalDamageModifier(), ec.getEquippableEffect(), dcToAdd, ec.getZonesNeededToEquip());
            this.equipableComponentJSON = gson.toJson(finalec);

        } else {
            this.equipableComponentJSON = null;
        }

        if(originalItem.isConsumable() == true){
            ConsumableComponent cc = originalItem.getConsumableData();

            ConsumableComponent ccFinal = new ConsumableComponent(cc.getAvailableUses(), cc.getMaxUses(), cc.isRefillable(), cc.getEffect());
            this.consumableComponentJSON = gson.toJson(ccFinal);
        } else {
            this.consumableComponentJSON = null;
        }

        if(originalItem.hasInventory() == true){
            this.inventoryData = new Inventory(originalItem.getInventoryOfItem().getBaseCapacity(), List.of(), this);
        } else {
            this.inventoryData = null;
        }
    }

    public void setInventoryContainer(Inventory inventory){
        //can be null
        this.containedIn = inventory;
    }

    public void add(ItemInstance item) throws IllegalStateException, ContainerFullException, ItemAlreadyContainedException{
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
        return new String(originalItem.getName());
    }

    public String getDescription() {
        return new String(originalItem.getDescription());
    }

    public double getCapacityRequired() {
        return originalItem.getCapacityRequired();
    }

    public boolean isRedimensionable() {
        return originalItem.isRedimensionable();
    }

    public EquipableComponent getEquipableData() {
        if (cachedEquipable == null && equipableComponentJSON != null) {
            cachedEquipable = gson.fromJson(equipableComponentJSON, EquipableComponent.class);
        }
        return cachedEquipable;
    }

    public void updateEquipableData(EquipableComponent ec){
        this.cachedEquipable = ec;
        this.equipableComponentJSON = gson.toJson(ec);
    }

    public ConsumableComponent getConsumableData() {
        if (cachedConsumable == null && consumableComponentJSON != null) {
            cachedConsumable = gson.fromJson(consumableComponentJSON, ConsumableComponent.class);
        }
        return cachedConsumable;
    }

    public void updateConsumableData(ConsumableComponent cc){
        this.cachedConsumable = cc;
        this.consumableComponentJSON = gson.toJson(cc);
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
