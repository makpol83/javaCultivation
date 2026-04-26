package Entities;

import com.j256.ormlite.field.DatabaseField;

public abstract class Entity {
    @DatabaseField(generatedId = true)
    protected long id;

    public Entity(){}

    public abstract EntityType getEntityType();
    public long getId(){ return this.id; }

}
