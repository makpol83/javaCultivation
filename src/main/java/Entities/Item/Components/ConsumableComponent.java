package Entities.Item.Components;

public class ConsumableComponent implements Cloneable{
    private int availableUses;
    private int maxUses;
    private boolean isRefillable;
    private String effect;

    public ConsumableComponent(int availableUses, int maxUses, boolean isRefillable, String effect) {
        this.availableUses = availableUses;
        this.maxUses = maxUses;
        this.isRefillable = isRefillable;
        this.effect = effect;
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
        return effect;
    }

    public boolean use(){
        if(this.availableUses == 0)
            return false;

        availableUses--;
        return true;
    }

    public boolean refill(int numberUsesToRefill){
        if(this.isRefillable == false)
            return false;

        if(this.availableUses + numberUsesToRefill > this.maxUses)
            numberUsesToRefill = this.maxUses - this.availableUses;
    
        availableUses += numberUsesToRefill;
        return true;
    }

    public void setEffect(String newEffect){
        this.effect = newEffect;
    }

    @Override
    public ConsumableComponent clone(){
        return new ConsumableComponent(availableUses, maxUses, isRefillable, effect);
    }
}
