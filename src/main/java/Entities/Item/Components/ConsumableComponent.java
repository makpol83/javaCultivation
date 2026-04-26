package Entities.Item.Components;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "consumable_components")
public class ConsumableComponent{

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private int availableUses;

    @DatabaseField
    private int maxUses;

    @DatabaseField
    private boolean isRefillable;

    @DatabaseField
    private String effect;

    public ConsumableComponent(int availableUses, int maxUses, boolean isRefillable, String effect)
    {
        if(this.availableUses < 0)
            throw new IllegalArgumentException("Uses cannot be below 0.");

        if(this.availableUses > maxUses)
            throw new IllegalArgumentException("Uses cannot be above maxUses.");

        if(this.maxUses < 0)
            throw new IllegalArgumentException("Max uses cannot be below 0.");

        if(effect == null || effect.length() == 0)
            throw new IllegalArgumentException("Effect cannot be empty.");

        this.availableUses = availableUses;
        this.maxUses = maxUses;
        this.isRefillable = isRefillable;
        this.effect = new String(effect);
    }

    public int getAvailableUses() {
        return availableUses;
    }
    public int getMaxUses() {
        return maxUses;
    }
    public boolean isRefillable() {
        return isRefillable;
    }
    public String getEffect() {
        return new String(effect);
    }

    public void use() throws 
        IllegalStateException
    {
        if(this.availableUses == 0)
            throw new IllegalStateException("Can't use an item without available uses");

        availableUses--;
    }

    public void refill(int numberUsesToRefill) throws IllegalStateException
    {
        if(numberUsesToRefill <= 0)
            throw new IllegalArgumentException("Number of uses to refill cannot be below 0");

        if(this.isRefillable == false)
            throw new IllegalStateException("Cannot refill a non-refillable object");

        if(this.availableUses + numberUsesToRefill > this.maxUses)
            numberUsesToRefill = this.maxUses - this.availableUses;
    
        availableUses += numberUsesToRefill;
    }

    public void setEffect(String newEffect){
        this.effect = new String(newEffect);
    }
}
