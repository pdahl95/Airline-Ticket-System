package edu.csumb.pdahl.project2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Log {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "type_id")
    private int typeId;
    private String values;


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
