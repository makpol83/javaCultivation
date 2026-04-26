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
    private Collection<PowerStepType> steps = new ArrayList<>();

    public PowerSystem(){}
    
    public PowerSystem(String name, String description){
        if(name == null)
            throw new NullPointerException("Name cannot be null.");
        if(description == null)
            throw new NullPointerException("Description cannot be null.");

        this.name = name;
        this.description = description;
    }

    public String getName(){ return this.name; }
    public String getDescription(){ return this.description; }
    public List<PowerStepType> getPowerSteps(){ return List.copyOf(steps); }

    public void add(PowerStepType step){
        if(step == null)
            throw new NullPointerException("Step cannot be null");

        this.steps.add(step);
    }

    public void remove(PowerStepType step){
        this.steps.remove(step);
    }
}
