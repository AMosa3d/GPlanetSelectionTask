package com.example.abdel.gplanetselectiontask.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

import java.util.List;

@Dao
public interface ApplicationDAO {

    //Reader Methods
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertReader(Reader reader);

    @Query("Select * from reader_table where reader_id = :id")
    Reader selectByReaderId(int id);

    @Query("Select * from reader_table where reader_name = :name")
    Reader selectByReaderName(String name);

    @Query("Select * from reader_table")
    List<Reader> selectAllReaders();


    //Reading Methods
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertReading(Reading reading);

    @Query("Select * from reading_table")
    List<Reading> selectAllReadings();

}
