package Entities.Character.Afflictions;

public class AfflictionLevel {
    private String onRolEffect;

    public AfflictionLevel(String onRolEffect) {
        if(onRolEffect == null)
            throw new NullPointerException("On rol effect can't be null.");

        this.onRolEffect = onRolEffect;
    }

    public String getOnRolEffect() {
        return onRolEffect;
    }

    public void setOnRolEffect(String onRolEffect) {
        if(onRolEffect == null)
            throw new NullPointerException("On rol effect can't be null.");

        this.onRolEffect = onRolEffect;
    }
}
