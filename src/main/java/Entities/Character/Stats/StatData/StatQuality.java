package Entities.Character.Stats.StatData;

public enum StatQuality {
    USELESS         (0.5 , 0.75),
    LOW_PROFICIENCY (0.75, 0.85),
    BELOW_AVERAGE   (0.85, 0.95),
    AVERAGE         (0.95, 1.05),
    UNCOMMON        (1.05, 1.15),
    PROFICCIENT     (1.15, 1.25),
    GENIUS          (1.25, 1.5 );

    private double multiplierMinimum = 1.0;
    private double multiplierMaximum = 1.0;

    private StatQuality(double multiplierMinimum, double multiplierMaximum){
        this.multiplierMinimum = multiplierMinimum;
        this.multiplierMaximum = multiplierMaximum;
    }

    public double getMultiplierMinimum(){ return this.multiplierMinimum; }
    public double getMultiplierMaximum(){ return this.multiplierMaximum; }
}
