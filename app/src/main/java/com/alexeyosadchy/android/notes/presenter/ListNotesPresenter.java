package com.alexeyosadchy.android.notes.presenter;

import com.alexeyosadchy.android.notes.view.ListNotesActivityMvp;
import com.alexeyosadchy.android.notes.view.Note;

public interface ListNotesPresenter<V extends ListNotesActivityMvp> extends BasePresenter<V> {

    void onInitialisation();

    void onLongClickNote(int position);

    void onClickNote(int position);

    void onClickFloatingActionBtn();

    void onActivityResult(Note note);
}
