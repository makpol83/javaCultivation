package Entities;

public abstract class Entity {
    private final int id;
    // TODO --> How to add id management --> Another class?

    public Entity(){
        this.id = 0;
    }

    public abstract EntityType getEntityType();
    public int getId(){ return this.id; }
}
