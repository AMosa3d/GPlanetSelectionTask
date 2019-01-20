package com.example.abdel.gplanetselectiontask.Readers;

import com.example.abdel.gplanetselectiontask.BasePresenter;
import com.example.abdel.gplanetselectiontask.BaseView;

import java.util.List;

public interface ReaderContract {

    interface View extends BaseView<Presenter,Reader>
    {
        void insertReaderCompleted(Reader reader);
        void insertReaderFailed();
    }

    interface Presenter extends BasePresenter<Reader>
    {
        void addReader(String name);
        void onInsertComplete(Reader reader);
        void onInsertFailed();
        void onRetrieveListComplete(List<Reader> list);
    }
}
