package com.example.abdel.gplanetselectiontask.Readers;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "reader_table")
public class Reader {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "reader_id")
    private int id;

    @NonNull
    @ColumnInfo (name = "reader_name")
    private String readerName;

    public Reader(int id, String name) {
        this.id = id;
        this.readerName = name;
    }

    public Reader(@NonNull String readerName) {
        this.readerName = readerName;
    }

    public int getId() {
        return id;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReaderName(@NonNull String readerName) {
        this.readerName = readerName;
    }
}
