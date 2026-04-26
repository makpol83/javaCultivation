package Entities.Character.Afflictions;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Characterr;

@DatabaseTable(tableName = "afflictions")
public class Affliction {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private AfflictionType type;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private AfflictionLevel level;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "character_id")
    private Characterr character;

    public Affliction(){}

    public Affliction(AfflictionType type, AfflictionLevel level) {
        if(type == null)
            throw new NullPointerException("Type cannot be null.");

        if(level == null)
            throw new NullPointerException("Level cannot be null.");

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
    public void setLevel(AfflictionLevel level) throws IllegalStateException{
        if(this.type.getLevels().contains(level) == false)
            throw new IllegalStateException("Cannot add a level to an affliction that doesn't match type.");

        this.level = level;
    }

    
}
