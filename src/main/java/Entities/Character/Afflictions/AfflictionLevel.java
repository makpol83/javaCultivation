package Entities.Character.Afflictions;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "affliction_level")
public class AfflictionLevel {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String onRolEffect;
    
    //Add support to add data modifiers

    //Owner is affliction type
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "type_id")
    private AfflictionType type;

    public AfflictionLevel(){}

    public AfflictionLevel(String onRolEffect, AfflictionType type) {
        if(onRolEffect == null)
            throw new NullPointerException("On rol effect can't be null.");

        if(type == null)
            throw new NullPointerException("Type can't be null.");

        this.onRolEffect = onRolEffect;
        this.type = type;
    }

    public String getOnRolEffect() {
        return onRolEffect;
    }

    public AfflictionType getAfflictionType() {
        return this.type;
    }

    public void setOnRolEffect(String onRolEffect) {
        if(onRolEffect == null)
            throw new NullPointerException("On rol effect can't be null.");

        this.onRolEffect = onRolEffect;
    }
}
