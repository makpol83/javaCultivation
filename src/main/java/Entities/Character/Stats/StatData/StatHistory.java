package Entities.Character.Stats.StatData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Stats.Stat;
import PowerSystem.PowerStepType;

@DatabaseTable(tableName = "stat_historials")
public class StatHistory {

    @DatabaseField(generatedId = true)
    private long id;

    @ForeignCollectionField(eager = true)
    private Collection<StatPowerStepFinalInstance> statInstances = new ArrayList<>();

    public StatHistory(){
        
    }

    public boolean addStat(Stat stat, PowerStepType type){
        if(stat == null)
            throw new NullPointerException("Stat cannot be null.");

        if(type == null)
            throw new NullPointerException("Type cannot be null.");

        return this.statInstances.add(new StatPowerStepFinalInstance(stat, type, this));
    }

    public Collection<StatPowerStepFinalInstance> getStatInstances(){ return List.copyOf(this.statInstances); }
}
