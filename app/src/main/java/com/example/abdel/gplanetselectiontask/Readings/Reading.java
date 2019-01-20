package com.example.abdel.gplanetselectiontask.Readings;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.abdel.gplanetselectiontask.Readers.Reader;

@Entity(tableName = "reading_table" ,
foreignKeys = @ForeignKey(entity = Reader.class,parentColumns = "reader_id",childColumns = "reader_id",onDelete = ForeignKey.CASCADE))
public class Reading implements Comparable<Reading> {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reading_id")
    private int id;

    @ColumnInfo(name = "page_from")
    private int pageFrom;

    @ColumnInfo(name = "page_to")
    private int pageTo;

    @ColumnInfo(name = "reader_id")
    private int readerId;

    public Reading(int id, int pageFrom, int pageTo, int readerId) {
        this.id = id;
        this.pageFrom = pageFrom;
        this.pageTo = pageTo;
        this.readerId = readerId;
    }

    @Ignore
    public Reading(int pageFrom, int pageTo, int readerId) {
        this.pageFrom = pageFrom;
        this.pageTo = pageTo;
        this.readerId = readerId;
    }

    public int getId() {
        return id;
    }

    public int getPageFrom() {
        return pageFrom;
    }

    public int getPageTo() {
        return pageTo;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPageFrom(int pageFrom) {
        this.pageFrom = pageFrom;
    }

    public void setPageTo(int pageTo) {
        this.pageTo = pageTo;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    @Override
    public int compareTo(@NonNull Reading reading) {
        return pageFrom - reading.pageFrom;
    }
}
