package Entities.Character.Stats.StatData;

public enum StatType{
    STRENGTH(StatCategory.PHYSICAL),
    VELOCITY(StatCategory.PHYSICAL),
    AGILITY(StatCategory.PHYSICAL),
    RESISTANCE(StatCategory.PHYSICAL),
    VITALITY(StatCategory.PHYSICAL),
    INTELLIGENCE(StatCategory.MENTAL),
    REFLEX(StatCategory.MENTAL),
    PRESENCE(StatCategory.MENTAL),
    COURAGE(StatCategory.MENTAL),
    SPIRITUAL_ENERGY(StatCategory.SPIRITUAL),
    QI_QUANTITY(StatCategory.SPIRITUAL),
    CONCENTRATION(StatCategory.SPIRITUAL);

    private StatCategory category = StatCategory.PHYSICAL;

    private StatType(StatCategory category){
        this.category = category;
    }

    public StatCategory getCategory(){ return this.category; }
}
