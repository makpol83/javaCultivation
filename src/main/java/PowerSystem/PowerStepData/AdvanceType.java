package PowerSystem.PowerStepData;

import java.util.ArrayList;
import java.util.List;

public class AdvanceType {
    private double statMultiplier;

    private List<AbstractRequirementType> abstractRequirements = new ArrayList<>();
    private List<DataRequirementType> dataRequirements = new ArrayList<>();

    public AdvanceType(double statMultiplier, List<AbstractRequirementType> abstractRequirements, List<DataRequirementType> dataRequirements){
        if(statMultiplier < 0)
            throw new IllegalArgumentException("Stat multiplier cannot be negative.");

        if(abstractRequirements != null)
            this.abstractRequirements.addAll(abstractRequirements);

        if(dataRequirements != null)
            this.dataRequirements.addAll(dataRequirements);
    }

    public void add(AbstractRequirementType requirement){
        if(requirement != null)
            this.abstractRequirements.add(requirement);
    }

    public void add(DataRequirementType requirement){
        if(requirement != null)
            this.dataRequirements.add(requirement);
    }

    public void remove(AbstractRequirementType requirement){
        if(requirement != null)
            this.abstractRequirements.remove(requirement);
    }

    public void remove(DataRequirementType requirement){
        if(requirement != null)
            this.dataRequirements.remove(requirement);
    }

    public double getStatMultiplier(){ return this.statMultiplier; }

    public boolean canAdvanceViaDataRequirements(Character character){
        if(getDataRequirementTypesFulfilled(character).size() == this.dataRequirements.size())
            return true;

        return false;
    }

    public List<DataRequirementType> getDataRequirementTypesFulfilled(Character character){
        List<DataRequirementType> fulfilled = new ArrayList<>();
        for(DataRequirementType requirement : this.dataRequirements){
            if(requirement.isFulfilled(character) == true){
                fulfilled.add(requirement);
            }
        }

        return fulfilled;
    }
}
