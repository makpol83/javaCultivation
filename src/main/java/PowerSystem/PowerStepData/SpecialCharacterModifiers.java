package PowerSystem.PowerStepData;

public class SpecialCharacterModifiers {
    private String abstractModifier;

    public SpecialCharacterModifiers(String abstractModifier){
        if(abstractModifier == null)
            throw new NullPointerException("Abstract modifier cannot be null.");

        this.abstractModifier = abstractModifier;
    }

    public String getModifier(){ return this.abstractModifier; }
    public void setModifier(String newModifier){ 
        if(newModifier != null)
            this.abstractModifier = newModifier;
    }
}
