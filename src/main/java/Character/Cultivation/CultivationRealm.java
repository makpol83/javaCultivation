package Character.Cultivation;

public enum CultivationRealm {
    MORTAL(0,0),
    QI_CONDENSATION(1, 50),
    FOUNDATION_ESTABLISHMENT(100, 200),
    CORE_FORMATION(400, 600),
    NASCENT_SOUL(1200, 2000),
    SPIRIT_SEVERING(4000, 10000),
    DAO_SEEKING(10000, 100000),
    IMMORTAL(100000, Integer.MAX_VALUE);

    private int statMinimumValue = 0;
    private int statMaximumValue = 1;

    private CultivationRealm(int statMinimumValue, int statMaximumValue){
        this.statMinimumValue = statMinimumValue;
        this.statMaximumValue = statMaximumValue;
    }

    public int getStatMinimumValue(){ return this.statMinimumValue; }
    public int getStatMaximumValue(){ return this.statMaximumValue; }
}
