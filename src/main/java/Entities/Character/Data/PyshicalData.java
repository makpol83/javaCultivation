package Entities.Character.Data;

public class PyshicalData {
    private double lifespan;
    private int age;
    private String gender;
    
    public PyshicalData(double lifespan, int age, String gender) {
        if(lifespan < 0)
            throw new IllegalArgumentException("Lifespan can't be negative.");

        if(age < 0)
            throw new IllegalArgumentException("Age can't be negative.");

        if(lifespan < age)
            throw new IllegalArgumentException("Lifespan cannot be lower than age.");

        if(gender == null)
            throw new NullPointerException("Gender cannot be null.");

        this.lifespan = lifespan;
        this.age = age;
        this.gender = gender;
    }

    public double getLifespan() {
        return lifespan;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }

    public void setLifespan(double lifespanToSet) {
        if(lifespanToSet < 0)
            throw new IllegalArgumentException("Lifespan can't be negative.");
        if(lifespanToSet < age)
            throw new IllegalArgumentException("Lifespan cannot be lower than age.");
        this.lifespan = lifespanToSet;
    }
    public void setAge(int ageToSet) {
        if(ageToSet < 0)
            throw new IllegalArgumentException("Age can't be negative.");
        if(lifespan < ageToSet)
            throw new IllegalArgumentException("Lifespan cannot be lower than age.");
        this.age = ageToSet;
    }
    public void setGender(String gender) {
        if(gender == null)
            throw new NullPointerException("Gender cannot be null.");
        this.gender = gender;
    }


}
