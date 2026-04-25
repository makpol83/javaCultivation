package Entities.Character.Data;

public class LoreData {
    private String past;
    private String pyshicalDescription;

    public LoreData(String past, String pyshicalDescription) {
        this.past = past;
        this.pyshicalDescription = pyshicalDescription;
    }

    public String getPast() {
        return past;
    }
    public String getPyshicalDescription() {
        return pyshicalDescription;
    }

    public void setPast(String past) {
        this.past = past;
    }
    public void setPyshicalDescription(String pyshicalDescription) {
        this.pyshicalDescription = pyshicalDescription;
    }
}
