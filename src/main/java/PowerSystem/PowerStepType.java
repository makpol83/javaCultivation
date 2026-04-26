package PowerSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import PowerSystem.PowerStepData.AdvanceType;
import PowerSystem.PowerStepData.CharacterModifier;
import PowerSystem.PowerStepData.SpecialCharacterModifier;

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

    @DatabaseField(foreign = true, columnName = "previous_step_id")
    private PowerStepType previousStep;

    @DatabaseField(foreign = true, columnName = "next_step_id")
    private PowerStepType nextStep;

    @ForeignCollectionField(eager = true)
    private Collection<AdvanceType> advanceTypes = new ArrayList<>();

    @ForeignCollectionField(eager = true)
    private Collection<CharacterModifier> dataModifiers = new ArrayList<>();

    @ForeignCollectionField(eager = true)
    private Collection<SpecialCharacterModifier> abstractModifiers = new ArrayList<>();

    public PowerStepType(){}
    
    public PowerStepType(
            String name,
            String description,
            int minimumStatValue,
            int maximumStatValue,
            List<PowerStepType> subLevels,
            PowerStepType parentLevel,
            PowerStepType previousStep,
            PowerStepType nextStep,
            List<AdvanceType> advanceTypes,
            List<CharacterModifier> dataModifiers,
            List<SpecialCharacterModifier> abstractModifiers,
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

        if (subLevels != null)
            this.subLevels.addAll(subLevels);
        this.parentLevel = parentLevel;
        this.previousStep = previousStep;
        this.nextStep = nextStep;

        this.advanceTypes.addAll(advanceTypes);
        if (dataModifiers != null)
            this.dataModifiers.addAll(dataModifiers);
        if (abstractModifiers != null)
            this.abstractModifiers.addAll(abstractModifiers);

        this.powerSystem = system;
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

    public Collection<CharacterModifier> getDataModifiers() {
        return List.copyOf(dataModifiers);
    }

    public Collection<SpecialCharacterModifier> getAbstractModifiers() {
        return List.copyOf(abstractModifiers);
    }

    public void add(AdvanceType advance){
        this.advanceTypes.add(advance);
        advance.setPowerStep(this);
    }

    public void remove(AdvanceType advance){
        this.advanceTypes.remove(advance);
        advance.setPowerStep(null);
    }
    


}
