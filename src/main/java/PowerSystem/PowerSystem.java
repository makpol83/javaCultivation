package PowerSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PowerSystem {
    private String name;
    private String description;
    private List<PowerStepType> steps = new ArrayList<>();
    
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

    public String getName(){ return this.name; }
    public String getDescription(){ return this.description; }
    public List<PowerStepType> getPowerSteps(){ return List.copyOf(steps); }
}
