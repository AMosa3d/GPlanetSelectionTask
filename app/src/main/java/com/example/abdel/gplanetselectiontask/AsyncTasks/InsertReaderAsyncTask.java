package com.example.abdel.gplanetselectiontask.AsyncTasks;

import android.os.AsyncTask;

import com.example.abdel.gplanetselectiontask.Database.ApplicationDAO;
import com.example.abdel.gplanetselectiontask.Database.ApplicationDataSource;
import com.example.abdel.gplanetselectiontask.Readers.Reader;

public class InsertReaderAsyncTask extends AsyncTask<Reader,Void,Reader>
{
    private ApplicationDAO mAsyncDAO;
    private ApplicationDataSource.InsertingReaderCallBack mCallback;

    public InsertReaderAsyncTask(ApplicationDAO mAsyncDAO, ApplicationDataSource.InsertingReaderCallBack mCallback) {
        this.mAsyncDAO = mAsyncDAO;
        this.mCallback = mCallback;
    }

    @Override
    protected Reader doInBackground(Reader... readers) {
        long id = -1;
        try
        {
            Reader dbReader = mAsyncDAO.selectByReaderName(readers[0].getReaderName());
            if (dbReader != null)
                return null;   //could be handle with another way
            id = mAsyncDAO.insertReader(readers[0]);
            readers[0].setId((int) id);
        }
        catch (Exception e)
        {
            mCallback.onInsertReaderFailed();
        }
        return readers[0];
    }

    @Override
    protected void onPostExecute(Reader reader) {
        if (reader == null)
            mCallback.onInsertReaderFailed();
        else
            mCallback.onInsertReaderComplete(reader);
    }
}