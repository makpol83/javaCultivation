package Entities.Character.Stats.StatData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Entities.Character.Stats.Stat;

public class StatData {
    private List<Stat> stats = new ArrayList<>();

    public StatData(Collection<Stat> stats){
        if(stats != null)
            this.stats.addAll(stats);
    }

    public void addStat(Stat statToAdd) throws IllegalStateException{
        for(Stat stat : stats){
            if(stat.getType().equals(statToAdd.getType()) == true)
                throw new IllegalStateException("Stat is already added to the stat data.");
        }
    }

    public void removeStat(Stat statToRemove){
        if(this.stats.contains(statToRemove) == false)
            return;

        this.stats.remove(statToRemove);
    }

    public List<Stat> getStats(){ return List.copyOf(this.stats); }
    
}
