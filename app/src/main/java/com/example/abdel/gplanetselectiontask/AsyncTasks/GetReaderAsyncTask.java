package com.example.abdel.gplanetselectiontask.AsyncTasks;

import android.os.AsyncTask;

import com.example.abdel.gplanetselectiontask.Database.ApplicationDAO;
import com.example.abdel.gplanetselectiontask.Database.ApplicationDataSource;
import com.example.abdel.gplanetselectiontask.Readers.Reader;

import java.util.List;

public class GetReaderAsyncTask extends AsyncTask<Void,Void,List<Reader>>
{
    private ApplicationDAO mAsyncDAO;
    private ApplicationDataSource mApplicationDataSource;

    public GetReaderAsyncTask(ApplicationDAO mAsyncDAO, ApplicationDataSource mApplicationDataSource) {
        this.mAsyncDAO = mAsyncDAO;
        this.mApplicationDataSource = mApplicationDataSource;
    }

    @Override
    protected List<Reader> doInBackground(Void... voids) {
        try {
            List<Reader>readerList =mAsyncDAO.selectAllReaders();
            return readerList;
        }
        catch (Exception e)
        {

        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Reader> readers) {
        mApplicationDataSource.setReadersList(readers);
    }
}