package com.alexeyosadchy.android.notes.presenter;

import com.alexeyosadchy.android.notes.model.RepositoryNotes;
import com.alexeyosadchy.android.notes.view.ListNotesActivityMvp;
import com.alexeyosadchy.android.notes.view.Note;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ListNotesPresenterImpl<V extends ListNotesActivityMvp>
        extends BasePresenterImpl<V> implements ListNotesPresenter<V> {

    List<Note> currentNotes;

    @Inject
    RepositoryNotes repositoryNotes;

    @Inject
    public ListNotesPresenterImpl(RepositoryNotes repositoryNotes,
                                  CompositeDisposable compositeDisposable) {

        super(compositeDisposable);
        this.repositoryNotes = repositoryNotes;

        getActualInfo(notes -> currentNotes = notes);
    }

    @Override
    public void onInitialisation() {
        getActualInfo(notes -> {
            currentNotes = notes;
            mMvpView.prepareView(currentNotes);
        });
    }

    private void getActualInfo(Consumer<? super List<Note>> onNext) {
        getCompositeDisposable().add(repositoryNotes.getAllNotes()
                .map(Mapper::convertToListOfNotes)
                .subscribe(onNext));
    }

    @Override
    public void onLongClickNote(int position) {
        repositoryNotes.deleteNote(currentNotes.get(position).getId());
        currentNotes.remove(position);
        mMvpView.updateList(position);
    }

    @Override
    public void onClickFloatingActionBtn() {
        Note note = new Note("");
        mMvpView.openNote(note);
    }

    @Override
    public void onClickNote(int position) {
        Note note = currentNotes.get(position);
        note.setClickedPosition(position);
        mMvpView.openNote(note);
    }

    private void changedNote(Note note) {
        currentNotes.set(note.getClickedPosition(), note);
        mMvpView.updateList(currentNotes, note.getClickedPosition());
        repositoryNotes.updateNote(Mapper.convertToEntityNote(note));
    }


    private void newNote(Note note) {
        repositoryNotes.addNote(Mapper.convertToEntityNote(note)).subscribe(key -> {
            note.setId(key);
            currentNotes.add(note);
            mMvpView.updateList();
        });
    }

    @Override
    public void onActivityResult(Note note) {
        if (note.getId() == 0 && !note.getDescription().isEmpty()) {
            newNote(note);
        } else if (note.getId() > 0 && !note.getDescription().isEmpty()) {
            changedNote(note);
        } else if (note.getId() > 0 && note.getDescription().isEmpty()) {
            onLongClickNote(note.getClickedPosition());
        }
    }
}
