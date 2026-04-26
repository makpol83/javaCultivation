package Entities.Character;

import java.util.ArrayList;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Entity;
import Entities.EntityType;
import Entities.Character.Afflictions.Affliction;
import Entities.Character.Data.LoreData;
import Entities.Character.Data.PyshicalData;
import Entities.Character.Equipment.Equipment;
import Entities.Character.Stats.StatData.StatData;

@DatabaseTable(tableName = "characters")
public class Character extends Entity{

    @DatabaseField
    private String name;

    
    private LoreData loreData;

    private PyshicalData pyshicalData;

    private Collection<Affliction> afflictions = new ArrayList<>();

    private Equipment equipment;
    
    private StatData statData;
    
    @Override
    public EntityType getEntityType(){ return EntityType.CHARACTER; }
}
