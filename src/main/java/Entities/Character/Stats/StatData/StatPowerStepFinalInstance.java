package Entities.Character.Stats.StatData.StatData;

import java.time.LocalDateTime;

import Character.Stats.Stat;

public class StatPowerStepFinalInstance {
    private LocalDateTime date;
    private Stat statInstance;

    public StatPowerStepFinalInstance(Stat statInstance){
        Stat newStatToSave = new Stat(
            statInstance.getBaseMultiplier(),
            statInstance.getValue(),
            statInstance.getQuality(),
            statInstance.getType(),
            statInstance.getModifiers(),
            statInstance.isVisible(),
            statInstance.isPaused());

        this.statInstance = newStatToSave;
        this.date = LocalDateTime.now();
    }
}
