package Entities.Character.Stats.FunctionalParameters;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "functional_parameters")
public class FunctionalParameter {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private double actualValue;

    @DatabaseField
    private double maxValue;
    
    @DatabaseField(dataType = com.j256.ormlite.field.DataType.ENUM_STRING)
    private FunctionalParameterType type;

    public FunctionalParameter(){}

    public FunctionalParameter(double maxValue, FunctionalParameterType type){
        if(maxValue < 0)
            throw new IllegalArgumentException("Max value is not valid, >= 0");

        if(type == null)
            throw new NullPointerException("Type cannot be null");

        this.maxValue = maxValue;
        this.actualValue = maxValue;

        this.type = type;
    }

    public double getActualValue(){ return this.actualValue; }
    public double getMaxValue(){ return this.maxValue; }
    public FunctionalParameterType getType(){ return this.type; }
    public void modifyValue(double value){
        this.actualValue -= value;

        if(this.actualValue < 0)
            this.actualValue = 0;
    }
}
