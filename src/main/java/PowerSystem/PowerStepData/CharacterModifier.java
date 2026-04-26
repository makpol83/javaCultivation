package PowerSystem.PowerStepData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public abstract class CharacterModifier {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String changes;

    @DatabaseField
    private CharacterModifierType type;

    public CharacterModifier(){}

    public CharacterModifier(String changes){
        if(changes == null)
            throw new NullPointerException("Changes cannot be null");
        
        this.changes = new String(changes);
    }

    public boolean reverseChanges(Character character){
        throw new UnsupportedOperationException("Method setChanges not implemented.");
    }

    public void pauseChanges(Character character){
        throw new UnsupportedOperationException("Method pauseChanges not implemented.");
    }

    public void realizeChanges(Character character){
        throw new UnsupportedOperationException("Method realizeChanges not implemented.");
    }

    public String getChanges(){
        return new String(this.changes);
    }

    public void setChanges(String changes){
        if(changes == null)
            throw new NullPointerException("Changes cannot be null");
        
        this.changes = new String(changes);
    }

}
