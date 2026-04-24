package PowerSystem.PowerStepData;

import java.util.ArrayList;
import java.util.List;

public class AdvanceType {
    private double statMultiplier;

    private List<AbstractRequirementType> abstractRequirements = new ArrayList<>();
    private List<DataRequirementType> dataRequirements = new ArrayList<>();

    public boolean canAdvanceViaDataRequirements(Character character){
        return false;
    }

    public List<DataRequirementType> getDataRequirementTypesFulfilled(Character character){
        return null;
    }
}
