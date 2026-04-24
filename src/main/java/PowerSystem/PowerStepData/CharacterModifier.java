package PowerSystem.PowerStepData;

public interface CharacterModifier {
    void setChanges(Character character);
    boolean reverseChanges(Character character);
    void pauseChanges(Character character);
    String getChanges();
}
