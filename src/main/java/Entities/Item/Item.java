package Entities.Item;

import Entities.Entity;
import Entities.EntityType;
import Entities.Interfaces.Container;
import Entities.Item.Components.ConsumableComponent;
import Entities.Item.Components.EquipableComponent;

public class Item extends Entity implements Cloneable{

    private String name;
    private String description;
    private double capacityRequired;
    private boolean isRedimensionable;  //True if can be stored in the inventory of an object
    
    private EquipableComponent equipableData;
    private ConsumableComponent consumableData;

    private Inventory inventoryData; //If it has an inventory
    private Container containedIn; //Null means it is an Item type to clone

    public Item(String name, String description, double capacityRequired, boolean isRedimensionable,
            EquipableComponent equipableData, ConsumableComponent consumableData, Inventory inventoryData,
            Inventory containedIn) {
        super();
        this.name = name;
        this.description = description;
        this.capacityRequired = capacityRequired;
        this.isRedimensionable = isRedimensionable;
        this.equipableData = equipableData;
        this.consumableData = consumableData;
        this.inventoryData = inventoryData;
        this.containedIn = containedIn;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public Container getContainedInInventory(){
        return containedIn;
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

    public boolean add(Item item){
        if(hasInventory() == false)
            return false;

        return this.inventoryData.add(item);
    }

    public boolean remove(Item item){
        if(hasInventory() == false)
            return false;

        return this.inventoryData.remove(item);
    }

    public void setContainedIn(Container newContainer){
        this.containedIn.remove(this);
        this.containedIn = newContainer;
    }


    @Override
    public EntityType getEntityType(){ return EntityType.ITEM; }

    
    public Item clone(Inventory containedIn){
        EquipableComponent copyEqComp;
        ConsumableComponent copyCoComp;
        Inventory copyInvData;
        if(this.equipableData == null){
            copyEqComp = null;
        } else {
            copyEqComp = this.equipableData.clone();
        }

        if(this.consumableData == null){
            copyCoComp = null;
        } else {
            copyCoComp = this.consumableData.clone();
        }

        if(this.inventoryData == null){
            copyInvData = null;
        } else {
            copyInvData = this.inventoryData.clone(null);
        }
        

        return new Item(name, description, capacityRequired, isRedimensionable, copyEqComp, copyCoComp, copyInvData, containedIn);
    }
}
