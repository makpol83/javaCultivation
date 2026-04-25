package Entities.Item.Components;

import java.util.ArrayList;
import java.util.List;

public class EquipableComponent implements Cloneable {
    private int pyshicalArmorPoints;
    private int spiritualArmorPoints;
    private int criticalPyshicalDefenseModifier;
    private int criticalSpiritualDefenseModifier;
    private double baseDamage;
    private double criticalDamageModifier;
    private String equippableEffect;

    private DurabilityComponent durabilityData;
    private List<EquippableZone> zonesNeededToEquip = new ArrayList<>();


    
    public EquipableComponent(int pyshicalArmorPoints, int spiritualArmorPoints, int criticalPyshicalDefenseModifier,
            int criticalSpiritualDefenseModifier, double baseDamage, double criticalDamageModifier,
            String equippableEffect, DurabilityComponent durabilityData, List<EquippableZone> zonesNeededToEquipAdd){

        if(criticalPyshicalDefenseModifier < 0) 
            throw new IllegalArgumentException("Critical pyshical defense modifier cannot go below 0.");

        if(criticalSpiritualDefenseModifier < 0) 
            throw new IllegalArgumentException("Critical spiritual defense modifier cannot go below 0.");

        if(criticalDamageModifier < 0) 
            throw new IllegalArgumentException("Critical damage modifier cannot go below 0.");

        if(zonesNeededToEquipAdd.size() == 0)
            throw new IllegalArgumentException("Must there be at least 1 zone to equip if equippable.");

        if(durabilityData == null)
            throw new NullPointerException("Durability data cannot be null.");

        if(equippableEffect == null)
            throw new NullPointerException("Equippable data cannot be null.");

        //Base damage can be negative to heal

        this.pyshicalArmorPoints = pyshicalArmorPoints;
        this.spiritualArmorPoints = spiritualArmorPoints;
        this.criticalPyshicalDefenseModifier = criticalPyshicalDefenseModifier;
        this.criticalSpiritualDefenseModifier = criticalSpiritualDefenseModifier;
        this.baseDamage = baseDamage;
        this.criticalDamageModifier = criticalDamageModifier;
        this.equippableEffect = equippableEffect;
        this.durabilityData = durabilityData;
        this.zonesNeededToEquip.addAll(zonesNeededToEquipAdd);
    }
    public int getPyshicalArmorPoints() {
        return pyshicalArmorPoints;
    }
    public int getSpiritualArmorPoints() {
        return spiritualArmorPoints;
    }
    public int getCriticalPyshicalDefenseModifier() {
        return criticalPyshicalDefenseModifier;
    }
    public int getCriticalSpiritualDefenseModifier() {
        return criticalSpiritualDefenseModifier;
    }
    public double getBaseDamage() {
        return baseDamage;
    }
    public double getCriticalDamageModifier() {
        return criticalDamageModifier;
    }
    public String getEquippableEffect() {
        if(equippableEffect == null)
            return new String("Sin efecto.");

        return equippableEffect;
    }
    public DurabilityComponent getDurabilityData() {
        return durabilityData;
    }
    public List<EquippableZone> getZonesNeededToEquip() {
        return List.copyOf(zonesNeededToEquip);
    }

    @Override
    public EquipableComponent clone(){
        DurabilityComponent durabData = new DurabilityComponent(durabilityData.getActualDurability(), durabilityData.getMaxDurability(), durabilityData.isRepairable(), durabilityData.canBeRepairedIfBroken(), new String(durabilityData.getRepairMethod()));
        return new EquipableComponent(pyshicalArmorPoints, spiritualArmorPoints, criticalPyshicalDefenseModifier, criticalSpiritualDefenseModifier, baseDamage, criticalDamageModifier, new String(equippableEffect), durabData, zonesNeededToEquip);
    }
}
