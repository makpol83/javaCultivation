package PowerSystem.PowerStepData;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Characterr;
import Entities.Character.Stats.StatData.StatType;

@DatabaseTable(tableName = "data_requirements")
public class DataRequirement{

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(dataType = DataType.ENUM_STRING)
    private StatType statToLookFor;

    @DatabaseField
    private int valueToReach;

    @DatabaseField(foreign = true)
    private AdvanceType advanceAssociated;

    public DataRequirement(){}

    public DataRequirement(StatType statToLookFor, int valueToReach, AdvanceType advanceAssociated){
        if(statToLookFor == null)
            throw new NullPointerException("Stat to look for cannot be null.");

        if(valueToReach < 0)
            throw new IllegalArgumentException("Value to reach must be greater than 0.");

        if(advanceAssociated == null)
            throw new NullPointerException("Advance type cannot be null");

        this.statToLookFor = statToLookFor;
        this.valueToReach = valueToReach;
        this.advanceAssociated = advanceAssociated;
    }

    public boolean isFulfilled(Characterr character){
        // TODO --> IMPLEMENT with stats
        return false;
    }

    public StatType getStatToLookFor(){ return this.statToLookFor; }

    public int getValueToReach(){ return this.valueToReach; }
}
