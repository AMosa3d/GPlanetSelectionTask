package com.example.abdel.gplanetselectiontask.Database;

import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

import java.util.List;

public interface ApplicationDataSource {

    void setReadersList(List<Reader> readerList);

    void setReadingList(List<Reading> readingList);

    void insertReader(String name);

    interface InsertingReaderCallBack
    {
        void onInsertReaderComplete(Reader reader);
        void onInsertReaderFailed();
    }

    void insertReading(int fromPage, int toPage, int readerId);

    interface InsertingReadingCallBack
    {
        void onInsertReadingComplete(Reading reading);
        void onInsertReadingFailed();
    }

    void invokeRetrieveReadersLists();

    void invokeRetrieveReadingLists();
}
