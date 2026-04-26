package PowerSystem.PowerStepData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import PowerSystem.PowerStepType;

@DatabaseTable(tableName = "abstract_character_modifiers")
public class SpecialCharacterModifier {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String abstractModifier;

    @DatabaseField(foreign = true)
    private PowerStepType stepType;

    public SpecialCharacterModifier(){}

    public SpecialCharacterModifier(String abstractModifier, PowerStepType stepType){
        if(abstractModifier == null)
            throw new NullPointerException("Abstract modifier cannot be null.");

        if(stepType == null)
            throw new NullPointerException("Step type cannot be null.");

        this.abstractModifier = abstractModifier;
        this.stepType = stepType;
    }

    public String getModifier(){ return this.abstractModifier; }
    public void setModifier(String newModifier){ 
        if(newModifier != null)
            this.abstractModifier = newModifier;
    }
}
