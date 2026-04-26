package Entities.Character.Stats.StatData;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Stats.Stat;

@DatabaseTable(tableName = "stat_modifiers")
public class StatModifier {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private double multiplierModifier = 1.0;

    @DatabaseField(dataType = DataType.ENUM_STRING)
    private StatType statAffected = StatType.STRENGTH;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Stat statAssociated;

    public StatModifier(){}

    public StatModifier(double multiplierModifier, StatType statAffected, Stat statAsociated) throws IllegalStateException{
        if(multiplierModifier < 0)
            throw new IllegalStateException("Multiplier modifier cannot be null.");

        if(statAffected == null)
            throw new NullPointerException("Stat affected cannot be null.");

        this.multiplierModifier = multiplierModifier;
        this.statAffected = statAffected;
        this.statAssociated = statAsociated;
    }

    public double getMultiplierModifier(){ return this.multiplierModifier; }
    public StatType statAffected(){ return this.statAffected; }

    public void setStatAssociated(Stat stat){
        this.statAssociated = stat;
    }
}
