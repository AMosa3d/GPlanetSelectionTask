package com.example.abdel.gplanetselectiontask.Contest;

import com.example.abdel.gplanetselectiontask.BasePresenter;
import com.example.abdel.gplanetselectiontask.BaseView;
import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

import java.util.List;

public interface ContestantContract {
    interface View extends BaseView<Presenter,Contestant>
    {

    }

    interface Presenter extends BasePresenter<Contestant>
    {
        String buildContestantClickString(String name, String numberOfPages);
        void onRetrievesListComplete(List<Reader> readerList, List<Reading> readingList);
    }
}
