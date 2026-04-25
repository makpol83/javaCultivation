package Exceptions;

import Entities.Entity;

public class EntityException extends Exception{
    private Entity entity;

    public EntityException(Entity entity, String errorMessage){
        super(errorMessage);
        this.entity = entity;
    }

    public Entity getEntity(){
        return entity;
    }
}
