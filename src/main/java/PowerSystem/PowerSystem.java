package PowerSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "power_systems")
public class PowerSystem {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String name;
    
    @DatabaseField
    private String description;

    @ForeignCollectionField(eager = true)
    private Collection<PowerStepType> steps;

    public PowerSystem(){}
    
    public PowerSystem(String name, String description, Collection<PowerStepType> steps){
        if(name == null)
            throw new NullPointerException("Name cannot be null.");
        if(description == null)
            throw new NullPointerException("Description cannot be null.");

        if(steps == null)
            throw new NullPointerException("Steps cannot be null.");

        if(steps.size() == 0)
            throw new IllegalArgumentException("Steps cannot be empty");

        this.name = name;
        this.description = description;
        this.steps.addAll(steps);
    }

    public String getName(){ return new String(this.name); }
    public String getDescription(){ return new String(this.description); }
    public List<PowerStepType> getPowerSteps(){ return List.copyOf(steps); }
}
