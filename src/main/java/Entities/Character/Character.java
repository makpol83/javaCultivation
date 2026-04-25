package Entities.Character;

import Entities.Entity;
import Entities.EntityType;

public class Character extends Entity{
    
    @Override
    public EntityType getEntityType(){ return EntityType.CHARACTER; }
}
