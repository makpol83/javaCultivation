package Entities.Character.Stats.StatData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Stats.Stat;
import PowerSystem.PowerStepType;

@DatabaseTable(tableName = "stat_instance_on_advance")
public class StatPowerStepFinalInstance {
    
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Stat statInstance;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerStepType stepAssociated;

    public StatPowerStepFinalInstance(Stat statInstance, PowerStepType stepAssociated){
        if(statInstance == null)
            throw new NullPointerException("Stat instance cannot be null");
        
        if(stepAssociated == null)
            throw new NullPointerException("Step associated cannot be null");
        

        Stat newStatToSave = new Stat(
            statInstance.getBaseMultiplier(),
            statInstance.getValue(),
            statInstance.getQuality(),
            statInstance.getType(),
            statInstance.getModifiers(),
            statInstance.isVisible(),
            statInstance.isPaused());

        this.statInstance = newStatToSave;
        this.stepAssociated = stepAssociated;
    }

    public PowerStepType getAssociatedPowerStep(){ return this.stepAssociated; }
    public Stat getAssociatedStat(){ return this.statInstance;}
}
