package com.example.abdel.gplanetselectiontask.AsyncTasks;

import android.os.AsyncTask;

import com.example.abdel.gplanetselectiontask.Database.ApplicationDAO;
import com.example.abdel.gplanetselectiontask.Database.ApplicationDataSource;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

import java.util.List;

public class GetReadingAsyncTask extends AsyncTask<Void,Void,List<Reading>>
{
    private ApplicationDAO mAsyncDAO;
    private ApplicationDataSource mApplicationDataSource;

    public GetReadingAsyncTask(ApplicationDAO mAsyncDAO, ApplicationDataSource mApplicationDataSource) {
        this.mAsyncDAO = mAsyncDAO;
        this.mApplicationDataSource = mApplicationDataSource;
    }

    @Override
    protected List<Reading> doInBackground(Void... voids) {
        try {
            List<Reading> readingList = mAsyncDAO.selectAllReadings();
            return readingList;
        }
        catch (Exception e)
        {

        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Reading> readingList) {
        mApplicationDataSource.setReadingList(readingList);
    }
}