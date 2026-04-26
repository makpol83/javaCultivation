package Entities.Item;

import java.util.List;

import Entities.Entity;
import Entities.EntityType;
import Entities.Interfaces.Container;
import Entities.Item.Components.ConsumableComponent;
import Entities.Item.Components.DurabilityComponent;
import Entities.Item.Components.EquipableComponent;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

public class ItemInstance extends Entity implements Container{
    private Item originalItem;

    private EquipableComponent equipableComponent;
    private ConsumableComponent consumableComponent;
    private Inventory inventoryData;

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
            this.inventoryData = new Inventory(originalItem.getInventoryOfItem().getBaseCapacity(), List.of(), this);
        } else {
            this.inventoryData = null;
        }
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
        return equipableComponent;
    }

    public ConsumableComponent getConsumableData() {
        return consumableComponent;
    }

    public Inventory getInventoryOfItem(){
        return inventoryData;
    }

    public boolean isEquippable(){
        if(equipableComponent == null)
            return false;

        return true;
    }

    public boolean isConsumable(){
        if(consumableComponent == null)
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
