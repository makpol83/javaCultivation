package Entities.Character.Stats.StatData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Entities.Character.Stats.Stat;

public class StatData {
    private List<Stat> stats = null;

    public StatData(Collection<Stat> stats){
        this.stats = new ArrayList<>(stats);
    }

    public List<Stat> getStats(){ return List.copyOf(this.stats); }
    
}
