package Entities.Character.Stats.StatData.StatData;

import java.util.ArrayList;
import java.util.List;

import Character.Stats.Stat;

public class StatHistory {
    private List<StatPowerStepFinalInstance> statInstances = new ArrayList<>();

    public StatHistory(){
        
    }

    public boolean addStat(Stat stat){
        return this.statInstances.add(new StatPowerStepFinalInstance(stat));
    }

    public List<StatPowerStepFinalInstance> getStatInstances(){ return List.copyOf(this.statInstances); }
}
