package Entities.Character.Stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Stats.StatData.StatData;
import Entities.Character.Stats.StatData.StatHistory;
import Entities.Character.Stats.StatData.StatModifier;
import Entities.Character.Stats.StatData.StatQuality;
import Entities.Character.Stats.StatData.StatType;
import PowerSystem.PowerStepType;

@DatabaseTable(tableName = "stats")
public class Stat {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private double baseMultiplier = 1.0;
    
    @DatabaseField
    private int value = 1;
    
    @DatabaseField(dataType = com.j256.ormlite.field.DataType.ENUM_STRING)
    private StatQuality quality = StatQuality.AVERAGE;
    
    @DatabaseField(dataType = DataType.ENUM_STRING, columnName = "stat_type")
    private StatType type = null;

    @ForeignCollectionField(eager = true)
    private Collection<StatModifier> modifiers;

    @DatabaseField
    private boolean isVisible = false;

    @DatabaseField
    private boolean isPaused = true;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private StatHistory statHistory = new StatHistory();

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private StatData statData;

    public Stat(){}

    public Stat(
        double baseMultiplier, 
        int value, 
        StatQuality quality, 
        StatType type,
        boolean isVisible,
        boolean isPaused,
        StatData statData)
    {
        if(baseMultiplier < 0)
            throw new IllegalArgumentException("Base multiplier cannot be negative.");

        if(value < 0)
            throw new IllegalArgumentException("Value cannot be negative.");

        if(quality == null)
            throw new NullPointerException("Quality cannot be null.");

        if(type == null)
            throw new NullPointerException("Type cannot be null.");

        if(statData == null)
            throw new NullPointerException("Stat data cannot be null.");

        this.modifiers = new ArrayList<>();

        this.baseMultiplier = baseMultiplier;
        this.value = value;
        this.quality = quality;
        this.type = type;

        this.isVisible = isVisible;
        this.isPaused = isPaused;

        this.statData = statData;
    }

    public StatData getStatData(){ return this.statData; }

    public String getName(){ return this.type.name(); }
    public double getBaseMultiplier(){ return this.baseMultiplier; }
    public int getValue(){ return this.value; }
    public StatQuality getQuality(){ return this.quality; }
    public StatType getType(){ return this.type; }
    public Collection<StatModifier> getModifiers(){ return List.copyOf(this.modifiers); }
    public boolean isVisible(){ return this.isVisible; }
    public boolean isPaused(){ return this.isPaused; }

    public void setPaused(){ this.isPaused = true; }
    public void setUnPaused(){ this.isPaused = false; }

    public boolean modifyStat(int value){
        if(this.isPaused == true)
            return false;
        
        this.value += value;

        if(this.value < 0)
            this.value = 0;
        
        return true;
    }

    public boolean multiplyStat(double value){
        if(this.isPaused == true)
            return false;

        if(value < 0)
            value = 0;

        if(this.value == 0)
            this.value = 1;

        if(value == 0)
            value = 1;

        this.value *= value;

        return true;
    }

    public void saveStatOnHistory(PowerStepType stepAssociated){
        this.statHistory.addStat(this, stepAssociated);
    }

    public void add(StatModifier modifier){
        if(modifier.statAffected().equals(this.type) == false)
            return;

        modifier.setStatAssociated(this);
        this.modifiers.add(modifier);
    }

    public void remove(StatModifier modifier){
        if(modifier.statAffected().equals(this.type) == false)
            return;

        modifier.setStatAssociated(null);
        this.modifiers.remove(modifier);
    }
}
