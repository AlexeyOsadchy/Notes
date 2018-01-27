package com.alexeyosadchy.android.notes.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexeyosadchy.android.notes.R;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenter;
import com.alexeyosadchy.android.notes.view.ListNotesActivityMvp;
import com.alexeyosadchy.android.notes.view.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private ListNotesPresenter<ListNotesActivityMvp> mPresenter;
    private List<Note> mNotes;

    public NoteAdapter(List<Note> notes, ListNotesPresenter<ListNotesActivityMvp> presenter){
        mNotes = notes;
        mPresenter = presenter;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_note, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        holder.mDescription.setText(mNotes.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{

        private TextView mDescription;

        public NoteHolder(View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            mDescription = (TextView) itemView.findViewById(R.id.text_description);
        }

        @Override
        public boolean onLongClick(View view) {
            mPresenter.onLongClickNote(getAdapterPosition());
            return false;
        }

        @Override
        public void onClick(View view) {
            mPresenter.onClickNote(getAdapterPosition());
        }
    }
}
