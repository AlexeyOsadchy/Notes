package com.alexeyosadchy.android.notes.model;

import com.alexeyosadchy.android.notes.view.Note;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface RepositoryNotes {

    Observable<List<EntityNote>> getAllNotes();

    Single<EntityNote> getNote(Long id);

    Single<Long> addNote(EntityNote entityNote);

    void updateNote(EntityNote entityNote);

    void deleteNote(Long id);
}
