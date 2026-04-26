package Entities.Character.Afflictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "affliction_types")
public class AfflictionType {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false, unique = true)
    private String name;

    @DatabaseField
    private String description;

    @DatabaseField
    private String cause;

    @DatabaseField
    private String cure;

    @ForeignCollectionField(eager = true)
    private Collection<AfflictionLevel> levels = new ArrayList<>();

    public AfflictionType(){}

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

        this.name = new String(name);
        this.description = new String(description);
        this.cause = new String(cause);
        this.cure = new String(cure);
        this.levels.addAll(levels);
    }

    public String getName() {
        return new String(name);
    }
    public void setName(String name) {
        if(name == null)
            throw new NullPointerException("Name can't be null.");
        this.name = new String(name);
    }
    public String getDescription() {
        return new String(description);
    }
    public void setDescription(String description) {
        if(description == null)
            throw new NullPointerException("Description can't be null.");

        this.description = new String(description);
    }
    public String getCause() {
        return new String(cause);
    }
    public void setCause(String cause) {
        if(cause == null)
            throw new NullPointerException("Cause can't be null.");
        this.cause = new String(cause);
    }
    public String getCure() {
        return new String(cure);
    }
    public void setCure(String cure) {
        if(cure == null)
            throw new NullPointerException("Cure can't be null.");
        this.cure = new String(cure);
    }

    public Collection<AfflictionLevel> getLevels(){
        return List.copyOf(levels);
    }


}
