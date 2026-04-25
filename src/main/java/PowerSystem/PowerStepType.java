package PowerSystem;

import java.util.ArrayList;
import java.util.List;

import PowerSystem.PowerStepData.AdvanceType;
import PowerSystem.PowerStepData.CharacterModifier;
import PowerSystem.PowerStepData.SpecialCharacterModifiers;

public class PowerStepType {
    private String name;
    private String description;
    private int minimumStatValue;
    private int maximumStatValue;

    private List<PowerStepType> subLevels = new ArrayList<>();
    private PowerStepType parentLevel;

    private PowerStepType previousStep;
    private PowerStepType nextStep;

    private List<AdvanceType> advanceTypes = new ArrayList<>();

    private List<CharacterModifier> dataModifiers = new ArrayList<>();
    private List<SpecialCharacterModifiers> abstractModifiers = new ArrayList<>();
    
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
            List<SpecialCharacterModifiers> abstractModifiers) {
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

    public List<PowerStepType> getSubLevels() {
        return subLevels;
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

    public List<AdvanceType> getAdvanceTypes() {
        return advanceTypes;
    }

    public List<CharacterModifier> getDataModifiers() {
        return dataModifiers;
    }

    public List<SpecialCharacterModifiers> getAbstractModifiers() {
        return abstractModifiers;
    }

    


}
