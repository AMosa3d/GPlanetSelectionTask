package com.example.abdel.gplanetselectiontask.Readings;

import com.example.abdel.gplanetselectiontask.Database.ApplicationRepo;
import com.example.abdel.gplanetselectiontask.LogicUtils;

import java.util.List;

public class ReadingPresenter implements ReadingContract.Presenter {

    private final ReadingContract.View mReadingView;
    private final ApplicationRepo mApplicationRepo;

    public ReadingPresenter(ReadingContract.View mReadingView, ApplicationRepo mApplicationRepo) {
        this.mReadingView = mReadingView;
        this.mApplicationRepo = mApplicationRepo;
        this.mReadingView.setPresenter(this);
    }

    @Override
    public void addReading(String interval, String readerId) {
        String[] intervalEndings = interval.split(",");

        if (intervalEndings.length != 2 || !LogicUtils.isNumber(intervalEndings[0]) || !LogicUtils.isNumber(intervalEndings[1]) || !LogicUtils.isNumber(readerId))
        {
            onInsertFailed();
            return;
        }
        int i1 = Integer.parseInt(intervalEndings[0].trim());
        int i2 = Integer.parseInt(intervalEndings[1].trim());

        mApplicationRepo.insertReading(
                Math.min(i1,i2),
                Math.max(i1,i2),
                Integer.parseInt(readerId)
        );
    }

    @Override
    public void onInsertComplete(Reading reading) {
        mReadingView.insertReadingCompleted(reading);
    }

    @Override
    public void onInsertFailed() {
        mReadingView.insertReadingFailed();
    }

    @Override
    public void start() {
        mReadingView.setLoadingBar();
        mApplicationRepo.invokeRetrieveReadingLists();
    }

    @Override
    public void onRetrieveListComplete(List<Reading> readingList) {
        mReadingView.showLoadingSuccessful();
        if (readingList.size() == 0)
            mReadingView.showEmptyList();
        else
            mReadingView.showList(readingList);
    }


}
