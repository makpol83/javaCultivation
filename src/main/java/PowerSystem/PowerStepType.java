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
        List<SpecialCharacterModifiers> abstractModifiers)
    {
        this.name = name;
        this.description = description;
        this.minimumStatValue = minimumStatValue;
        this.maximumStatValue = maximumStatValue;

        this.subLevels.addAll(subLevels);
        this.parentLevel = parentLevel;
        this.previousStep = previousStep;
        this.nextStep = nextStep;

        this.advanceTypes.addAll(advanceTypes);
        this.dataModifiers.addAll(dataModifiers);
        this.abstractModifiers.addAll(abstractModifiers);
    }

    public String getName(){ return this.name; }
    public String getDescription(){ return this.description; }


}
