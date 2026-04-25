package Entities.Character.Afflictions;

public class Affliction {
    private AfflictionType type;
    private AfflictionLevel level;

    public Affliction(AfflictionType type, AfflictionLevel level) {
        this.type = type;
        this.level = level;
    }
    public AfflictionType getType() {
        return type;
    }
    public void setType(AfflictionType type) {
        this.type = type;
    }
    public AfflictionLevel getLevel() {
        return level;
    }
    public void setLevel(AfflictionLevel level) {
        if(this.type.getLevels().contains(level) == false)
            return;

        this.level = level;
    }

    
}
