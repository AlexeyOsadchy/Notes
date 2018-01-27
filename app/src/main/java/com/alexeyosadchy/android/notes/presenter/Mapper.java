package com.alexeyosadchy.android.notes.presenter;

import com.alexeyosadchy.android.notes.model.EntityNote;
import com.alexeyosadchy.android.notes.view.Note;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    protected static Note convertToNote(EntityNote entityNote){
        return new Note(entityNote.getId(), entityNote.getText());
    }

    protected static EntityNote convertToEntityNote(Note note){
        EntityNote entityNote = new EntityNote();
        entityNote.setText(note.getDescription());
        entityNote.setId(note.getId());
        return entityNote;
    }

    protected static List<Note> convertToListOfNotes(List<EntityNote> entityNotes) {
        List<Note> newNotes = new ArrayList<>();
        for (EntityNote entityNote : entityNotes) {
            newNotes.add(convertToNote(entityNote));
        }
        return newNotes;
    }
}
