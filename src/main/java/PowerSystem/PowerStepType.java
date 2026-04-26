package PowerSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import PowerSystem.PowerStepData.AdvanceType;
import PowerSystem.PowerStepData.SpecialCharacterModifier;
import PowerSystem.PowerStepData.CharacterModifiers.StatCharModifier;

import Entities.Character.Characterr;

@DatabaseTable(tableName = "power_steps_types")
public class PowerStepType {
    
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(foreign = true, columnName = "system_id")
    private PowerSystem powerSystem;

    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField
    private int minimumStatValue;
    @DatabaseField
    private int maximumStatValue;

    @ForeignCollectionField(eager = true)
    private Collection<PowerStepType> subLevels = new ArrayList<>();

    @DatabaseField(foreign = true, columnName = "parent_id", canBeNull = true)
    private PowerStepType parentLevel;

    @DatabaseField(foreign = true, columnName = "previous_step_id", canBeNull = true)
    private PowerStepType previousStep;

    @DatabaseField(foreign = true, columnName = "next_step_id", canBeNull = true)
    private PowerStepType nextStep;

    @ForeignCollectionField(eager = true)
    private Collection<AdvanceType> advanceTypes = new ArrayList<>();

    @ForeignCollectionField(eager = true, foreignFieldName = "powerStepType")
    private Collection<StatCharModifier> dataModifiers = new ArrayList<>();

    @ForeignCollectionField(eager = true)
    private Collection<SpecialCharacterModifier> abstractModifiers = new ArrayList<>();

    public PowerStepType(){}
    
    public PowerStepType(
            String name,
            String description,
            int minimumStatValue,
            int maximumStatValue,
            PowerStepType parentLevel,
            PowerStepType previousStep,
            PowerStepType nextStep,
            PowerSystem system) {
        if (name == null)
            throw new NullPointerException("Name cannot be null.");
        if (description == null)
            throw new NullPointerException("Description cannot be null.");
        if (advanceTypes == null)
            throw new NullPointerException("Advance types cannot be null.");

        this.name = name;
        this.description = description;
        this.minimumStatValue = minimumStatValue;
        this.maximumStatValue = maximumStatValue;

        this.parentLevel = parentLevel;
        this.previousStep = previousStep;
        this.nextStep = nextStep;

        this.powerSystem = system;
    }

    public void setParent(PowerStepType step){
        this.parentLevel = step;
    }

    public void add(PowerStepType type){
        if(this.subLevels.contains(type) == false){
            this.subLevels.add(type);
            type.setParent(type);
        }
    }

    public void remove(PowerStepType type){
        if(this.subLevels.contains(type) == true){
            this.subLevels.remove(type);
            type.setParent(null);
        }
    }

    public void add(AdvanceType advance){
        if(this.advanceTypes.contains(advance) == false){
            this.advanceTypes.add(advance);
            advance.setPowerStep(this);
        }
    }

    public void remove(AdvanceType advance){
        if(this.advanceTypes.contains(advance) == true){
            this.advanceTypes.remove(advance);
            advance.setPowerStep(null);
        }
    }

    public void addCharacterModifier(StatCharModifier modifier){
        if(this.dataModifiers.contains(modifier) == false){
            this.dataModifiers.add(modifier);
        }
    }

    public void removeCharacterModifier(StatCharModifier modifier){
        if(this.dataModifiers.contains(modifier) == true){
            this.dataModifiers.remove(modifier);
        }
    }

    public void addSpecialCharacterModifier(SpecialCharacterModifier modifier){
        if(this.abstractModifiers.contains(modifier) == false){
            this.abstractModifiers.add(modifier);
        }
    }

    public void removeSpecialCharacterModifier(SpecialCharacterModifier modifier){
        if(this.abstractModifiers.contains(modifier) == true){
            this.abstractModifiers.remove(modifier);
        }
    }
    
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getMinimumStatValue() {
        return minimumStatValue;
    }

    public int getMaximumStatValue() {
        return maximumStatValue;
    }

    public Collection<PowerStepType> getSubLevels() {
        return List.copyOf(subLevels);
    }

    public PowerStepType getParentLevel() {
        return parentLevel;
    }

    public PowerStepType getPreviousStep() {
        return previousStep;
    }

    public PowerStepType getNextStep() {
        return nextStep;
    }

    public Collection<AdvanceType> getAdvanceTypes() {
        return List.copyOf(advanceTypes);
    }

    public Collection<StatCharModifier> getDataModifiers() {
        return List.copyOf(dataModifiers);
    }

    public Collection<SpecialCharacterModifier> getAbstractModifiers() {
        return List.copyOf(abstractModifiers);
    }
    
    public void applyModifiers(Characterr character){
        for(StatCharModifier modifier : this.dataModifiers){
            modifier.realizeChanges(character);
        }
    }

}
