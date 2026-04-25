package Entities.Character.Data;

public class PyshicalData {
    private double lifespan;
    private int age;
    private String gender;
    
    public PyshicalData(double lifespan, int age, String gender) {
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

    public void setLifespan(double lifespan) {
        this.lifespan = lifespan;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }


}
