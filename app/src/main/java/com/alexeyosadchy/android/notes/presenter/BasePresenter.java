package com.alexeyosadchy.android.notes.presenter;

import com.alexeyosadchy.android.notes.view.MvpView;

public interface BasePresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    V getMvpView();
}
