package com.example.abdel.gplanetselectiontask.Readers;

import com.example.abdel.gplanetselectiontask.Database.ApplicationRepo;

import java.util.List;

public class ReaderPresenter implements ReaderContract.Presenter {

    private final ReaderContract.View mReaderView;
    private final ApplicationRepo mApplicationRepo;

    public ReaderPresenter(ReaderContract.View mReaderView, ApplicationRepo mApplicationRepo) {
        this.mReaderView = mReaderView;
        this.mApplicationRepo = mApplicationRepo;
        this.mReaderView.setPresenter(this);
    }

    @Override
    public void addReader(String name) {
        mApplicationRepo.insertReader(name);
    }

    @Override
    public void onInsertComplete(Reader reader) {
        mReaderView.insertReaderCompleted(reader);
    }

    @Override
    public void onInsertFailed() {
        mReaderView.insertReaderFailed();
    }

    @Override
    public void start() {
        mReaderView.setLoadingBar();
        mApplicationRepo.invokeRetrieveReadersLists();
    }

    @Override
    public void onRetrieveListComplete(List<Reader> readerList) {
        mReaderView.showLoadingSuccessful();
        if (readerList.size() == 0)
            mReaderView.showEmptyList();
        else
            mReaderView.showList(readerList);
    }

}
