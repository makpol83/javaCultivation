package Entities.Character.Stats.StatData;

public class StatModifier {
    private double multiplierModifier = 1.0;
    private StatModifiable statAffected = StatType.STRENGTH;

    public StatModifier(double multiplierModifier, StatModifiable statAffected){
        this.multiplierModifier = multiplierModifier;
        this.statAffected = statAffected;
    }

    public double getMultiplierModifier(){ return this.multiplierModifier; }
    public StatModifiable statAffected(){ return this.statAffected; }
}
