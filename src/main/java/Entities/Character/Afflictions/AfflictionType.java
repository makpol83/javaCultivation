package Entities.Character.Afflictions;

import java.util.ArrayList;
import java.util.List;

public class AfflictionType {
    private String name;
    private String description;
    private String cause;
    private String cure;

    private List<AfflictionLevel> levels = new ArrayList<>();

    public AfflictionType(String name, String description, String cause, String cure, List<AfflictionLevel> levels){
        if(name == null)
            throw new NullPointerException("Name can't be null.");

        if(description == null)
            throw new NullPointerException("Description can't be null.");

        if(cause == null)
            throw new NullPointerException("Cause can't be null.");

        if(cure == null)
            throw new NullPointerException("Cure can't be null.");

        if(levels.size() == 0)
            throw new IllegalArgumentException("Must have at least one level");

        this.name = name;
        this.description = description;
        this.cause = cause;
        this.cure = cure;
        this.levels.addAll(levels);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name == null)
            throw new NullPointerException("Name can't be null.");
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if(description == null)
            throw new NullPointerException("Description can't be null.");

        this.description = description;
    }
    public String getCause() {
        return cause;
    }
    public void setCause(String cause) {
        if(cause == null)
            throw new NullPointerException("Cause can't be null.");
        this.cause = cause;
    }
    public String getCure() {
        return cure;
    }
    public void setCure(String cure) {
        if(cure == null)
            throw new NullPointerException("Cure can't be null.");
        this.cure = cure;
    }

    public List<AfflictionLevel> getLevels(){
        return List.copyOf(levels);
    }


}
