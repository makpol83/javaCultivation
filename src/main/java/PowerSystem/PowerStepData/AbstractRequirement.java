package PowerSystem.PowerStepData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "abstract_requirements")
public class AbstractRequirement{

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String requirement;

    @DatabaseField(foreign = true)
    private AdvanceType advanceAssociated;
    
    public AbstractRequirement(){}

    public AbstractRequirement(String requirement, AdvanceType advanceAssociated){
        if(requirement == null)
            throw new NullPointerException("Requirement cannot be null");

        if(advanceAssociated == null)
            throw new NullPointerException("Advance type cannot be null");

        this.requirement = new String(requirement);
        this.advanceAssociated = advanceAssociated;
    }

    public String getRequirement(){ return new String(this.requirement); }

    public void setRequirement(String requirement){
        if(requirement == null)
            throw new NullPointerException("Requirement cannot be null");

        this.requirement = new String(requirement);
    }
}
