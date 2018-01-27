package com.alexeyosadchy.android.notes.view;

import java.util.List;

public interface ListNotesActivityMvp extends MvpView {

    void updateList(List<Note> notes, int position);

    void updateList(int position);

    void updateList();

    void prepareView(List<Note> notes);

    void openNote(Note note);
}
