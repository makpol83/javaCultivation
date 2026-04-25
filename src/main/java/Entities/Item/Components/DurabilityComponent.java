package Entities.Item.Components;

public class DurabilityComponent implements Cloneable{
    private double actualDurability;
    private double maxDurability;
    
    private boolean isRepairable;
    private boolean canBeRepairedIfBroken;

    private String repairMethod;

    public DurabilityComponent(double actualDurability, double maxDurability, boolean isRepairable, boolean canBeRepairedIfBroken, String repairMethod){
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

    public boolean isCanBeRepairedIfBroken() {
        return canBeRepairedIfBroken;
    }

    public String getRepairMethod() {
        return repairMethod;
    }

    @Override
    public DurabilityComponent clone(){
        return new DurabilityComponent(actualDurability, maxDurability, isRepairable, canBeRepairedIfBroken, repairMethod);
    }
}
