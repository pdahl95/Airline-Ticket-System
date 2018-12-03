package edu.csumb.pdahl.project2.model;

public class Log {

    private int id;
    private int typeId;
    private String values;

    public Log(int id, int typeId, String values) {
        this.id = id;
        this.typeId = typeId;
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getValues() {
        return values;
    }
}
