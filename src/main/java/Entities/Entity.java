package Entities;

public abstract class Entity {
    private final long id;
    private static long lastId = 0;

    public Entity(){
        this.id = Entity.lastId++;
    }

    public abstract EntityType getEntityType();
    public long getId(){ return this.id; }

    public static void setLastId(int lastId){
        if(Entity.lastId < 0)
            throw new IllegalArgumentException("Last id cannot be null, must be > 0.");

        Entity.lastId = lastId;
    }
}
