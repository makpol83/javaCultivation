package Character.Race;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Character.Stats.StatModifier;
import Character.Stats.StatType;

public abstract class Race {
    private List<StatModifier> modifiers = null;
    private List<StatType> specialStats = null;

    public Race(Collection<StatModifier> modifiers, Collection<StatType> specialStats)
    {
        this.modifiers = new ArrayList<>(modifiers);
        this.specialStats = new ArrayList<>(specialStats);
    }

    public Collection<StatModifier> getModifiers(){ return List.copyOf(this.modifiers); }
    public Collection<StatType> getSpecialStats(){ return List.copyOf(this.specialStats); }
}
