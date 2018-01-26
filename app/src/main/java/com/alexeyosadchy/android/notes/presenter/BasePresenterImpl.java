package com.alexeyosadchy.android.notes.presenter;

import com.alexeyosadchy.android.notes.view.MvpView;

import javax.inject.Inject;

public class BasePresenterImpl<V extends MvpView> implements BasePresenter<V> {

    protected V mMvpView;

    @Inject
    public BasePresenterImpl() {

    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }
}
