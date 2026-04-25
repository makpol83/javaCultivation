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
            String equippableEffect, DurabilityComponent durabilityData, List<EquippableZone> zonesNeededToEquip) {
        this.pyshicalArmorPoints = pyshicalArmorPoints;
        this.spiritualArmorPoints = spiritualArmorPoints;
        this.criticalPyshicalDefenseModifier = criticalPyshicalDefenseModifier;
        this.criticalSpiritualDefenseModifier = criticalSpiritualDefenseModifier;
        this.baseDamage = baseDamage;
        this.criticalDamageModifier = criticalDamageModifier;
        this.equippableEffect = equippableEffect;
        this.durabilityData = durabilityData;
        this.zonesNeededToEquip.addAll(zonesNeededToEquip);
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
        return new EquipableComponent(pyshicalArmorPoints, spiritualArmorPoints, criticalPyshicalDefenseModifier, criticalSpiritualDefenseModifier, baseDamage, criticalDamageModifier, equippableEffect, durabilityData, zonesNeededToEquip)
    }
}
