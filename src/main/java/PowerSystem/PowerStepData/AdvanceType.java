package PowerSystem.PowerStepData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Characterr;
import PowerSystem.PowerStepType;

@DatabaseTable(tableName = "advance_types")
public class AdvanceType {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private double statMultiplier;

    @ForeignCollectionField(eager = true)
    private Collection<AbstractRequirement> abstractRequirements = new ArrayList<>();

    @ForeignCollectionField(eager = true)
    private Collection<DataRequirement> dataRequirements = new ArrayList<>();

    @DatabaseField(foreign = true)
    private PowerStepType powerStepAssociated;

    public AdvanceType(){}

    public AdvanceType(double statMultiplier, PowerStepType powerStepAssociated){
        if(statMultiplier < 0)
            throw new IllegalArgumentException("Stat multiplier cannot be negative.");

        if(powerStepAssociated == null)
            throw new NullPointerException("Power step cannot be null.");

        this.statMultiplier = statMultiplier;
        this.powerStepAssociated = powerStepAssociated;
    }

    public void add(AbstractRequirement requirement){
        if(requirement != null)
            this.abstractRequirements.add(requirement);
    }

    public void add(DataRequirement requirement){
        if(requirement != null)
            this.dataRequirements.add(requirement);
    }

    public void remove(AbstractRequirement requirement){
        if(requirement != null)
            this.abstractRequirements.remove(requirement);
    }

    public void remove(DataRequirement requirement){
        if(requirement != null)
            this.dataRequirements.remove(requirement);
    }

    public double getStatMultiplier(){ return this.statMultiplier; }

    public boolean canAdvanceViaDataRequirements(Characterr character){
        if(getDataRequirementTypesFulfilled(character).size() == this.dataRequirements.size())
            return true;

        return false;
    }

    public Collection<DataRequirement> getDataRequirementTypesFulfilled(Characterr character){
        List<DataRequirement> fulfilled = new ArrayList<>();
        for(DataRequirement requirement : this.dataRequirements){
            if(requirement.isFulfilled(character) == true){
                fulfilled.add(requirement);
            }
        }

        return fulfilled;
    }

    public void setPowerStep(PowerStepType step){
        this.powerStepAssociated = step;
    }
}
