package com.example.tp6;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Planet {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "size")
    private final int size;

    @Ignore
    Planet(String name, int size){
        this.name = name;
        this.size = size;
    }
    Planet(int uid, String name, int size){
        this.uid = uid;
        this.name = name;
        this.size = size;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

}