package Entities.Character.Stats.StatData;

import java.util.ArrayList;
import java.util.List;

import Entities.Character.Stats.Stat;

public class StatHistory {
    private List<StatPowerStepFinalInstance> statInstances = new ArrayList<>();

    public StatHistory(){
        
    }

    public boolean addStat(Stat stat){
        if(stat == null)
            throw new NullPointerException("Stat cannot be null.");

        return this.statInstances.add(new StatPowerStepFinalInstance(stat));
    }

    public List<StatPowerStepFinalInstance> getStatInstances(){ return List.copyOf(this.statInstances); }
}
