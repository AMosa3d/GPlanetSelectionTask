package com.example.abdel.gplanetselectiontask;

import java.util.List;

public interface BaseView<T,E> {
    void setLoadingBar();
    void showLoadingSuccessful();
    void showLoadingError();
    void showEmptyList();
    void showList(List<E> list);
    void setPresenter(T presenter);
}
