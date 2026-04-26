package Entities.Item.Components;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "durability_components")
public class DurabilityComponent implements Cloneable{
    
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private double actualDurability;

    @DatabaseField
    private double maxDurability;
    
    @DatabaseField
    private boolean isRepairable;

    @DatabaseField
    private boolean canBeRepairedIfBroken;

    @DatabaseField
    private String repairMethod;

    //Used by ORMLite
    protected DurabilityComponent(){}

    public DurabilityComponent(double actualDurability, double maxDurability, boolean isRepairable, boolean canBeRepairedIfBroken, String repairMethod){
        if(actualDurability < 0)
            throw new IllegalArgumentException("Durability cannot go below 0");

        if(actualDurability > maxDurability)
            throw new IllegalArgumentException("Durability cannot go above max durability");

        if(maxDurability < 0)
            throw new IllegalArgumentException("Max durability cannot go below 0");

        if(isRepairable == true && (repairMethod == null || repairMethod.length() == 0))
            throw new IllegalArgumentException("If repairable, repair method cannot be empty or null");
        
        this.actualDurability = actualDurability;
        this.maxDurability = maxDurability;
        this.isRepairable = isRepairable;
        this.canBeRepairedIfBroken = canBeRepairedIfBroken;
        this.repairMethod = repairMethod;
    }

    public double getActualDurability(){ return this.actualDurability; }

    public double getMaxDurability() {
        return maxDurability;
    }

    public boolean isRepairable() {
        return isRepairable;
    }

    public boolean canBeRepairedIfBroken() {
        return canBeRepairedIfBroken;
    }

    public String getRepairMethod() {
        return repairMethod;
    }

    public void damage(double percentage){
        if(percentage < 0 || percentage > 1)
            throw new IllegalArgumentException("Percentage must be between 0 and 1.");

        this.actualDurability = this.actualDurability - this.maxDurability*percentage;
        if(this.actualDurability < 0) this.actualDurability = 0;
    }

    public void repair(double percentage)
        throws IllegalStateException{
        if(percentage < 0 || percentage > 1)
            throw new IllegalArgumentException("Percentage must be between 0 and 1.");
        if(this.isRepairable == false)
            throw new IllegalStateException("Cannot repair a non-repairable item.");
        if(this.actualDurability == 0 && this.canBeRepairedIfBroken == false)
            throw new IllegalStateException("Item cannot be repaired if broken");

        this.actualDurability = this.actualDurability + this.maxDurability*percentage;
        if(this.actualDurability < 0) this.actualDurability = 0;
    }

    @Override
    public DurabilityComponent clone(){
        return new DurabilityComponent(actualDurability, maxDurability, isRepairable, canBeRepairedIfBroken, new String(repairMethod));
    }
}
