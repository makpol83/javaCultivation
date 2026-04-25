package Entities.Character.Stats.StatData;

import java.time.LocalDateTime;

import Entities.Character.Stats.Stat;

public class StatPowerStepFinalInstance {
    private LocalDateTime date;
    private Stat statInstance;

    public StatPowerStepFinalInstance(Stat statInstance){
        if(statInstance == null){
            throw new NullPointerException("Stat instance cannot be null");
        }

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
