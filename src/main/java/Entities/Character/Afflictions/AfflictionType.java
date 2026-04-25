package Entities.Character.Afflictions;

import java.util.ArrayList;
import java.util.List;

public class AfflictionType {
    private String name;
    private String description;
    private String cause;
    private String cure;

    private List<AfflictionLevel> levels = new ArrayList<>();

    public AfflictionType(String name, String description, String cause, String cure, List<AfflictionLevel> levels) {
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
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCause() {
        return cause;
    }
    public void setCause(String cause) {
        this.cause = cause;
    }
    public String getCure() {
        return cure;
    }
    public void setCure(String cure) {
        this.cure = cure;
    }

    public List<AfflictionLevel> getLevels(){
        return List.copyOf(levels);
    }


}
