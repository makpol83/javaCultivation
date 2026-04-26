package Entities.Character.Stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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
    private Collection<StatModifier> modifiers = null;

    @DatabaseField
    private boolean isVisible = false;

    @DatabaseField
    private boolean isPaused = true;

    private StatHistory statHistory = new StatHistory();

    public Stat(){}

    public Stat(
        double baseMultiplier, 
        int value, 
        StatQuality quality, 
        StatType type, 
        Collection<StatModifier> modifiers,
        boolean isVisible,
        boolean isPaused)
    {
        if(baseMultiplier < 0)
            throw new IllegalArgumentException("Base multiplier cannot be negative.");

        if(value < 0)
            throw new IllegalArgumentException("Value cannot be negative.");

        if(quality == null)
            throw new NullPointerException("Quality cannot be null.");

        if(type == null)
            throw new NullPointerException("Type cannot be null.");

        this.baseMultiplier = baseMultiplier;
        this.value = value;
        this.quality = quality;
        this.type = type;
        if(modifiers != null)
            this.modifiers = new ArrayList<>(modifiers);

        this.isVisible = isVisible;
        this.isPaused = isPaused;
    }

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

        if(this.value - value < 0)
            value = this.value - value;
        
        this.value += value;
        
        return true;
    }

    public boolean multiplyStat(double value){
        if(this.isPaused == true)
            return false;

        if(value < 0)
            value = 0;

        this.value *= value;

        return true;
    }

    public void saveStatOnHistory(PowerStepType stepAssociated){
        this.statHistory.addStat(this, stepAssociated);
    }
}
