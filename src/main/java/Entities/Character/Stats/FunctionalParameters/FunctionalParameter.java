package Entities.Character.Stats.FunctionalParameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FunctionalParameter {
    private double actualValue;
    private double maxValue;

    private List<FunctionalParameterModifier> modifiers = new ArrayList<>();
    
    private FunctionalParameterType type;

    public FunctionalParameter(double maxValue, FunctionalParameterType type, Collection<FunctionalParameterModifier> modifiers){
        this.maxValue = maxValue;
        this.actualValue = maxValue;

        this.modifiers.addAll(modifiers);

        this.type = type;
    }

    public double getActualValue(){ return this.actualValue; }
    public double getMaxValue(){ return this.maxValue; }
    public FunctionalParameterType getType(){ return this.type; }
    public void modifyValue(double value){
        if(this.actualValue - value < 0){
            value = this.actualValue - value;
        }

        this.actualValue -= value;
    }

    public void addModifier(FunctionalParameterModifier modifier){
        this.modifiers.add(modifier);
    }

    public void removeModifier(FunctionalParameterModifier modifier){
        this.modifiers.remove(modifier);
    }

    public List<FunctionalParameterModifier> getModifiers(){ return List.copyOf(this.modifiers); }
}
