package com.example.abdel.gplanetselectiontask.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    ApplicationRepo mRepo;

    List<Reader> readerList;
    List<Reading> readingList;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        //mRepo = new ApplicationRepo(application);
        readerList = mRepo.getReaderList();
        readingList = mRepo.getReadingList();
    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    public List<Reading> getReadingList() {
        return readingList;
    }

    public void insertReader(String name)
    {
        mRepo.insertReader(name);
    }

    public void insertReading(int fromPage, int toPage, int readerId)
    {
        mRepo.insertReading(fromPage, toPage, readerId);
    }
}
