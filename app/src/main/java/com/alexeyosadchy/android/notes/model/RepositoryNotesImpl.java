package com.alexeyosadchy.android.notes.model;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class RepositoryNotesImpl implements RepositoryNotes {

    private EntityNoteDao mEntityNoteDao;

    DaoSession mDaoSession;

    @Inject
    public RepositoryNotesImpl(DaoSession daoSession) {
        mDaoSession = daoSession;
        mEntityNoteDao = mDaoSession.getEntityNoteDao();
    }

    @Override
    public Observable<List<EntityNote>> getAllNotes() {
        mEntityNoteDao.queryBuilder().orderAsc().build().list();
        return Observable.fromCallable(() -> mEntityNoteDao.queryBuilder().orderAsc().build().list());
    }

    @Override
    public Single<EntityNote> getNote(Long id) {
        return null;
    }

    @Override
    public Single<Long> addNote(EntityNote entityNote) {
        entityNote.setId(null);
        return Single.fromCallable(() -> mEntityNoteDao.insert(entityNote));
    }

    @Override
    public void updateNote(EntityNote entityNote) {
        mEntityNoteDao.update(entityNote);
    }

    @Override
    public void deleteNote(Long id) {
        mEntityNoteDao.deleteByKey(id);
    }
}
