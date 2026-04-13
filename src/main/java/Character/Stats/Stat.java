package Character.Stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Stat {
    private double baseMultiplier = 1.0;
    private int value = 1;
    private StatQuality quality = StatQuality.AVERAGE;
    private StatType type = StatType.STRENGTH;
    private List<StatModifier> modifiers = null;

    public Stat(double baseMultiplier, int value, 
        StatQuality quality, StatType type, Collection<StatModifier> modifiers)
    {
        if(baseMultiplier < 0 || value < 0)
            // TODO --> Exception to add
        
        this.baseMultiplier = baseMultiplier;
        this.value = value;
        this.quality = quality;
        this.type = type;
        this.modifiers = new ArrayList<>(modifiers);
    }

    public String getName(){ return this.type.name(); }
    public double getBaseMultiplier(){ return this.baseMultiplier; }
    public int getValue(){ return this.value; }
    public StatQuality getQuality(){ return this.quality; }
    public StatType getType(){ return this.type; }
    public Collection<StatModifier> getModifiers(){ return List.copyOf(this.modifiers); }
}
