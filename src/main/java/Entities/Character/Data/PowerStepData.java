package Entities.Character.Data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Characterr;

import PowerSystem.PowerStepType;
import PowerSystem.PowerSystem;
import PowerSystem.PowerStepData.AdvanceType;

@DatabaseTable(tableName = "char_power_steps")
public class PowerStepData {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerSystem martialSystem;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerSystem qiSystem;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerStepType actualMartialRealm;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerStepType actualMartialLevel;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerStepType actualQiRealm;
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private PowerStepType actualQiLevel;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Characterr associatedCharacter;

    public PowerStepData(){}

    public PowerStepData(PowerSystem martialSystem, PowerSystem qiSystem, PowerStepType actualMartialRealm,
            PowerStepType actualMartialLevel, PowerStepType actualQiRealm, PowerStepType actualQiLevel,
            Characterr character) {

        if(martialSystem == null)
            throw new NullPointerException();
        if(qiSystem == null)
            throw new NullPointerException();
        if(actualMartialRealm == null)
            throw new NullPointerException();
        if(actualMartialLevel == null)
            throw new NullPointerException();
        if(actualQiLevel == null)
            throw new NullPointerException();
        if(actualQiRealm == null)
            throw new NullPointerException();
        if(character == null)
            throw new NullPointerException();
        
        this.martialSystem = martialSystem;
        this.qiSystem = qiSystem;
        this.actualMartialRealm = actualMartialRealm;
        this.actualMartialLevel = actualMartialLevel;
        this.actualQiRealm = actualQiRealm;
        this.actualQiLevel = actualQiLevel;
        this.associatedCharacter = character;
    }

    public void advanceMartialLevel(AdvanceType advanceType){
        if(advanceType == null)
            throw new NullPointerException();
        
        if(this.actualMartialLevel.getNextStep() == null)
            return;

        if(this.actualMartialLevel.getAdvanceTypes().contains(advanceType) == false)
            return;

        if(this.actualMartialLevel.getParentLevel().equals(this.actualMartialLevel.getNextStep().getParentLevel()) == false){
            this.actualMartialRealm = this.actualMartialLevel.getNextStep().getParentLevel();
            this.actualMartialRealm.applyModifiers(this.associatedCharacter);
        }

        this.actualMartialLevel = this.actualMartialLevel.getNextStep();
        this.actualMartialLevel.applyModifiers(this.associatedCharacter);
    }

    public void advanceQiLevel(AdvanceType advanceType){
        if(advanceType == null)
            throw new NullPointerException();

        if(this.actualQiLevel.getNextStep() == null)
            return;

        if(this.actualQiLevel.getAdvanceTypes().contains(advanceType) == false)
            return;

        if(this.actualQiLevel.getParentLevel().equals(this.actualQiLevel.getNextStep().getParentLevel()) == false){
            this.actualQiRealm = this.actualQiLevel.getNextStep().getParentLevel();
            this.actualQiRealm.applyModifiers(this.associatedCharacter);
        }

        this.actualQiLevel = this.actualQiLevel.getNextStep();
        this.actualQiLevel.applyModifiers(this.associatedCharacter);
    }

    // Add reverse level for both

    
}
