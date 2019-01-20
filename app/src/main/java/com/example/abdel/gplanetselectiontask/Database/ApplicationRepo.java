package com.example.abdel.gplanetselectiontask.Database;

import android.app.Application;

import com.example.abdel.gplanetselectiontask.AsyncTasks.GetReaderAsyncTask;
import com.example.abdel.gplanetselectiontask.AsyncTasks.GetReadingAsyncTask;
import com.example.abdel.gplanetselectiontask.AsyncTasks.InsertReaderAsyncTask;
import com.example.abdel.gplanetselectiontask.AsyncTasks.InsertReadingAsyncTask;
import com.example.abdel.gplanetselectiontask.Contest.ContestantContract;
import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readers.ReaderContract;
import com.example.abdel.gplanetselectiontask.Readings.Reading;
import com.example.abdel.gplanetselectiontask.Readings.ReadingContract;

import java.util.List;

public class ApplicationRepo implements ApplicationDataSource, ApplicationDataSource.InsertingReaderCallBack, ApplicationDataSource.InsertingReadingCallBack {

    private ApplicationDAO mApplicationDAO;
    private ReaderContract.Presenter mReaderPresenter;
    private ReadingContract.Presenter mReadingPresenter;
    private ContestantContract.Presenter mContestantPresenter;
    List<Reader> readerList;
    List<Reading> readingList;

    public ApplicationRepo(Application application) {
        mApplicationDAO = ApplicationRoomDatabase.getInstance(application).applicationDAO();
    }

    public void setReaderPresenter(ReaderContract.Presenter mReaderPresenter) {
        this.mReaderPresenter = mReaderPresenter;
    }

    public void setReadingPresenter(ReadingContract.Presenter mReadingPresenter) {
        this.mReadingPresenter = mReadingPresenter;
    }

    public void setContestantPresenter(ContestantContract.Presenter mContestantPresenter) {
        this.mContestantPresenter = mContestantPresenter;
    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    public List<Reading> getReadingList() {
        return readingList;
    }

    @Override
    public void setReadersList(List<Reader> readerList) {
        this.readerList = readerList;
        if (mReaderPresenter != null)
            mReaderPresenter.onRetrieveListComplete(this.readerList);
        if(readingList != null && mContestantPresenter != null)
            mContestantPresenter.onRetrievesListComplete(this.readerList,this.readingList);
    }

    @Override
    public void setReadingList(List<Reading> readingList) {
        this.readingList = readingList;
        if(mReadingPresenter != null)
        mReadingPresenter.onRetrieveListComplete(this.readingList);
        if(readerList != null && mContestantPresenter != null)
            mContestantPresenter.onRetrievesListComplete(this.readerList,this.readingList);
    }

    @Override
    public void insertReader(String name)
    {
        new InsertReaderAsyncTask(mApplicationDAO,this).execute(new Reader(name));
    }

    @Override
    public void insertReading(int fromPage, int toPage, int readerId)
    {
        new InsertReadingAsyncTask(mApplicationDAO,this).execute(new Reading(fromPage,toPage,readerId));
    }

    @Override
    public void invokeRetrieveReadersLists() {
        new GetReaderAsyncTask(mApplicationDAO,this).execute();
    }

    @Override
    public void invokeRetrieveReadingLists() {
        new GetReadingAsyncTask(mApplicationDAO,this).execute();
    }

    @Override
    public void onInsertReaderComplete(Reader reader) {
        mReaderPresenter.onInsertComplete(reader);
    }

    @Override
    public void onInsertReaderFailed() {
        mReaderPresenter.onInsertFailed();
    }

    @Override
    public void onInsertReadingComplete(Reading reading) {
        mReadingPresenter.onInsertComplete(reading);
    }

    @Override
    public void onInsertReadingFailed() {
        mReadingPresenter.onInsertFailed();
    }
}
