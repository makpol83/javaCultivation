package Entities.Character;

import java.util.ArrayList;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Entity;
import Entities.EntityType;
import Entities.Character.Afflictions.Affliction;
import Entities.Character.Data.LoreData;
import Entities.Character.Data.PowerStepData;
import Entities.Character.Data.PhysicalData;
import Entities.Character.Equipment.Equipment;
import Entities.Character.Equipment.Slot;
import Entities.Character.Stats.StatData.StatData;
import Entities.Item.ItemInstance;
import Entities.Item.Components.EquippableZone;
import PowerSystem.PowerStepType;
import PowerSystem.PowerSystem;

@DatabaseTable(tableName = "characters")
public class Characterr extends Entity{

    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private LoreData loreData;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PhysicalData physicalData;

    @ForeignCollectionField(eager = true)
    private Collection<Affliction> afflictions;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Equipment equipment;
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private StatData statData;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerStepData powerData;

    public Characterr(){}

    public Characterr(
        String name,
        String past,
        String physicalDescription,
        double lifespan,
        int age,
        String gender,
        PowerSystem martialSystem,
        PowerSystem qiSystem,
        PowerStepType actualMartialRealm,
        PowerStepType actualMartialLevel,
        PowerStepType actualQiRealm,
        PowerStepType actualQiLevel,
        Collection<EquippableZone> zonesWithSlot
    )
    {
        if(name == null)
            throw new NullPointerException();
        if(zonesWithSlot == null)
            throw new NullPointerException();

        this.name = name;

        this.afflictions = new ArrayList<>();

        this.loreData = new LoreData(past, physicalDescription);

        this.powerData = new PowerStepData(martialSystem, qiSystem, actualMartialRealm, actualMartialLevel, actualQiRealm, actualQiLevel, this);


        Collection<Slot> slotsToAdd = new ArrayList<>();
        for(EquippableZone zone : zonesWithSlot){
            slotsToAdd.add(new Slot(zone, null));
        }

        this.equipment = new Equipment();
        this.equipment.addAll(slotsToAdd);

        this.physicalData = new PhysicalData(lifespan, age, gender);

        this.statData = new StatData();
    }
    
    public String getName() {
        return name;
    }

    public LoreData getLoreData() {
        return loreData;
    }

    public PhysicalData getPyshicalData() {
        return physicalData;
    }

    public Collection<Affliction> getAfflictions() {
        return afflictions;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public StatData getStatData() {
        return statData;
    }

    public PowerStepData getPowerData() {
        return powerData;
    }

    @Override
    public EntityType getEntityType(){ return EntityType.CHARACTER; }
}
