package com.example.abdel.gplanetselectiontask.AsyncTasks;

import android.os.AsyncTask;

import com.example.abdel.gplanetselectiontask.Database.ApplicationDAO;
import com.example.abdel.gplanetselectiontask.Database.ApplicationDataSource;
import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

public class InsertReadingAsyncTask extends AsyncTask<Reading,Void,Reading>
{
    private ApplicationDAO mAsyncDAO;
    private ApplicationDataSource.InsertingReadingCallBack mCallback;

    public InsertReadingAsyncTask(ApplicationDAO mAsyncDAO, ApplicationDataSource.InsertingReadingCallBack mCallback) {
        this.mAsyncDAO = mAsyncDAO;
        this.mCallback = mCallback;
    }

    @Override
    protected Reading doInBackground(Reading... readings) {
        long id = -1;
        try
        {
            Reader dbReader = mAsyncDAO.selectByReaderId(readings[0].getReaderId());
            if (dbReader == null)
                return null;
            id = mAsyncDAO.insertReading(readings[0]);
            readings[0].setId((int) id);
        }
        catch (Exception e)
        {
            mCallback.onInsertReadingFailed();
        }
        return readings[0];
    }

    @Override
    protected void onPostExecute(Reading reading) {
        if(reading == null)
            mCallback.onInsertReadingFailed();
        else
            mCallback.onInsertReadingComplete(reading);
    }
}
