package Entities.Character.Stats.StatData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Stats.Stat;

@DatabaseTable(tableName = "stat_data")
public class StatData {

    @DatabaseField(generatedId = true)
    private long id;

    @ForeignCollectionField(eager = true)
    private Collection<Stat> stats = new ArrayList<>();

    public StatData(){}

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

    public Collection<Stat> getStats(){ return List.copyOf(this.stats); }
    
}
