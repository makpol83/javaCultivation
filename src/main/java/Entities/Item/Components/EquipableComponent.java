package Entities.Item.Components;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "equipable_components")
public class EquipableComponent implements Cloneable {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private int pyshicalArmorPoints;

    @DatabaseField
    private int spiritualArmorPoints;

    @DatabaseField
    private int criticalPyshicalDefenseModifier;

    @DatabaseField
    private int criticalSpiritualDefenseModifier;

    @DatabaseField
    private double baseDamage;

    @DatabaseField
    private double criticalDamageModifier;

    @DatabaseField
    private String equippableEffect;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private DurabilityComponent durabilityData;

    //We have to save as a json due to database capabilites
    @DatabaseField(columnName = "zones_json")
    private String zonesJson;

    //Created to avoid creating one each time its used
    private static final Gson gson = new Gson();

    //Used by ORMLite
    public EquipableComponent(){}
    
    public EquipableComponent(int pyshicalArmorPoints, int spiritualArmorPoints, int criticalPyshicalDefenseModifier,
            int criticalSpiritualDefenseModifier, double baseDamage, double criticalDamageModifier,
            String equippableEffect, DurabilityComponent durabilityData, Collection<EquippableZone> zonesNeededToEquipAdd){

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
        this.equippableEffect = new String(equippableEffect);
        this.durabilityData = durabilityData;
        this.zonesJson = gson.toJson(zonesNeededToEquipAdd);
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

        return new String(equippableEffect);
    }
    public DurabilityComponent getDurabilityData() {
        return durabilityData;
    }
    public List<EquippableZone> getZonesNeededToEquip() {
        if (zonesJson == null || zonesJson.isEmpty()) {
            return new ArrayList<>();
        }
        // Gson convierte el texto de vuelta a una lista de Enums
        Type listType = new TypeToken<ArrayList<EquippableZone>>(){}.getType();
        return gson.fromJson(zonesJson, listType);
    }

    @Override
    public EquipableComponent clone(){
        DurabilityComponent durabData = new DurabilityComponent(durabilityData.getActualDurability(), durabilityData.getMaxDurability(), durabilityData.isRepairable(), durabilityData.canBeRepairedIfBroken(), new String(durabilityData.getRepairMethod()));
        return new EquipableComponent(pyshicalArmorPoints, spiritualArmorPoints, criticalPyshicalDefenseModifier, criticalSpiritualDefenseModifier, baseDamage, criticalDamageModifier, new String(equippableEffect), durabData, getZonesNeededToEquip());
    }
}
