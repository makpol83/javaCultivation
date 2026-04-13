package Character.Stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StatData {
    private List<Stat> stats = null;

    public StatData(Collection<Stat> stats){
        this.stats = new ArrayList<>(stats);
    }

    public List<Stat> getStats(){ return List.copyOf(this.stats); }
    
}
