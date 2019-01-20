package com.example.abdel.gplanetselectiontask.Readings;

import com.example.abdel.gplanetselectiontask.BasePresenter;
import com.example.abdel.gplanetselectiontask.BaseView;

import java.util.List;

public interface ReadingContract {

    interface View extends BaseView<Presenter,Reading>
    {
        void insertReadingCompleted(Reading reading);
        void insertReadingFailed();
    }

    interface Presenter extends BasePresenter<Reading>
    {
        void addReading(String interval, String readerId);
        void onInsertComplete(Reading reading);
        void onInsertFailed();
        void onRetrieveListComplete(List<Reading> list);
    }
}
