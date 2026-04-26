package Entities.Character.Data;

public class LoreData {
    private String past;
    private String pyshicalDescription;

    public LoreData(String past, String pyshicalDescription) {
        if(past == null)
            throw new NullPointerException("Past can't be null.");

        if(pyshicalDescription == null)
            throw new NullPointerException("Pyshical description can't be null.");

        this.past = new String(past);
        this.pyshicalDescription = new String(pyshicalDescription);
    }

    public String getPast() {
        return new String(past);
    }
    public String getPyshicalDescription() {
        return new String(pyshicalDescription);
    }

    public void setPast(String past) {
        if(past == null)
            throw new NullPointerException("Past can't be null.");
        this.past = new String(past);
    }
    public void setPyshicalDescription(String pyshicalDescription) {
        if(pyshicalDescription == null)
            throw new NullPointerException("Pyshical description can't be null.");
        this.pyshicalDescription = new String(pyshicalDescription);
    }
}
