package Entities.Character.Stats.StatData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "stat_modifiers")
public class StatModifier {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private double multiplierModifier = 1.0;
    private StatType statAffected = StatType.STRENGTH;

    public StatModifier(double multiplierModifier, StatType statAffected) throws IllegalStateException{
        if(multiplierModifier < 0)
            throw new IllegalStateException("Multiplier modifer cannot be null.");

        if(statAffected == null)
            throw new NullPointerException("Stat affected cannot be null.");

        this.multiplierModifier = multiplierModifier;
        this.statAffected = statAffected;
    }

    public double getMultiplierModifier(){ return this.multiplierModifier; }
    public StatType statAffected(){ return this.statAffected; }
}
