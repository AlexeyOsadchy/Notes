package com.alexeyosadchy.android.notes.view.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alexeyosadchy.android.notes.view.Note;
import com.alexeyosadchy.android.notes.view.NoteActivity;

import javax.inject.Inject;

/**
 * Class for screen transitions.
 */
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToNoteScreen(Context context, int requestCode, Note note){
        Intent noteScreenIntent = NoteActivity.getCallingIntent(context, note);
        ((Activity) context).startActivityForResult(noteScreenIntent, requestCode);
    }
}
