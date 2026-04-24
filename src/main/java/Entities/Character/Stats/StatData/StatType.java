package Entities.Character.Stats.StatData;

public enum StatType implements StatModifiable{
    STRENGTH(StatCategory.PYSHICAL),
    VELOCITY(StatCategory.PYSHICAL),
    AGILITY(StatCategory.PYSHICAL),
    RESISTANCE(StatCategory.PYSHICAL),
    VITALITY(StatCategory.PYSHICAL),
    INTELLIGENCE(StatCategory.MENTAL),
    REFLEX(StatCategory.MENTAL),
    PRESENCE(StatCategory.MENTAL),
    COURAGE(StatCategory.MENTAL),
    SPIRITUAL_ENERGY(StatCategory.SPIRITUAL),
    QI_QUANTITY(StatCategory.SPIRITUAL),
    CONCENTRATION(StatCategory.SPIRITUAL);

    private StatCategory category = StatCategory.PYSHICAL;

    private StatType(StatCategory category){
        this.category = category;
    }

    public StatCategory getCategory(){ return this.category; }
}
