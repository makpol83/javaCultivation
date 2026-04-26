package Entities.Character.Data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lore_data")
public class LoreData {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String past;

    @DatabaseField
    private String pyshicalDescription;
    
    public LoreData(){}

    public LoreData(String past, String pyshicalDescription) {
        if(past == null)
            throw new NullPointerException("Past can't be null.");

        if(pyshicalDescription == null)
            throw new NullPointerException("Pyshical description can't be null.");

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
        if(past == null)
            throw new NullPointerException("Past can't be null.");
        this.past = past;
    }
    public void setPyshicalDescription(String pyshicalDescription) {
        if(pyshicalDescription == null)
            throw new NullPointerException("Pyshical description can't be null.");
        this.pyshicalDescription = pyshicalDescription;
    }
}
