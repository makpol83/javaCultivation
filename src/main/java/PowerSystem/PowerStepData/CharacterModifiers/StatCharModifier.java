package PowerSystem.PowerStepData.CharacterModifiers;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import Entities.Character.Stats.StatData.StatType;
import PowerSystem.PowerStepType;
import Entities.Character.Characterr;

@DatabaseTable(tableName = "stat_char_modifiers")
public class StatCharModifier{

    @DatabaseField(generatedId = true)
    protected long id;

    @DatabaseField
    protected String changes;

    @DatabaseField(dataType = DataType.ENUM_STRING)
    private StatType statToModify;

    @DatabaseField(dataType = DataType.ENUM_STRING)
    private StatModifierType actionType;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "power_step_type_id")
    private PowerStepType powerStepType;
    
    public StatCharModifier(){}

    public StatCharModifier(StatType statToModify, StatModifierType actionType, PowerStepType step){

        if(statToModify == null)
            throw new NullPointerException("Stat to modify can't be null");

        if(actionType == null)
            throw new NullPointerException("Stat to modify can't be null");

        if(step == null)
            throw new NullPointerException("Step can't be null");

        this.changes = "Modifies the stat: " + statToModify.name() + " by doing a " + actionType.name();
        this.statToModify = statToModify;
        this.actionType = actionType;
        this.powerStepType = step;
    }

    public boolean reverseChanges(Characterr character){
        // TODO --> Añadir
        return false;
    }

    public void pauseChanges(Characterr character){
        // TODO --> Añadir
    }

    public void realizeChanges(Characterr character){
        // TODO --> Añadir
    }

    public String getChanges(){
        return this.changes;
    }

    public void setChanges(String changes){
        if(changes == null)
            throw new NullPointerException("Changes cannot be null");
        
        this.changes = changes;
    }

}
