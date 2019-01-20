package com.example.abdel.gplanetselectiontask.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

@Database(entities = {Reader.class,Reading.class}, version = ApplicationRoomDatabase.VERSION)
public abstract class ApplicationRoomDatabase extends RoomDatabase {

    final static int VERSION = 1;

    public abstract ApplicationDAO applicationDAO();

    private static ApplicationRoomDatabase INSTANCE;

    public static ApplicationRoomDatabase getInstance(final Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ApplicationRoomDatabase.class,"application_database")
                    .build();
        }
        return INSTANCE;
    }
}
