package com.alexeyosadchy.android.notes.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alexeyosadchy.android.notes.R;
import com.alexeyosadchy.android.notes.view.ListNotesFragment;
import com.alexeyosadchy.android.notes.view.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private ListNotesFragment.OnItemSelectedListener mListener;
    private List<Note> mNotes;
    public boolean multipleMode;
    public boolean[] listOfclickedItem;

    public NoteAdapter(List<Note> notes, ListNotesFragment.OnItemSelectedListener listener) {
        mNotes = notes;
        mListener = listener;
        listOfclickedItem = new boolean[mNotes.size()];
        multipleMode = false;
    }



    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_note, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        holder.mDescription.setText(mNotes.get(position).getDescription());
        if(multipleMode == false) {
           holder.mCardView.setCardBackgroundColor(Color.WHITE);
        } else if (listOfclickedItem[holder.getAdapterPosition()] == true) {
            holder.mCardView.setCardBackgroundColor(Color.parseColor("#70414033"));
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private TextView mDescription;
        private CardView mCardView;


        public NoteHolder(View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            mDescription = (TextView) itemView.findViewById(R.id.text_description);
            mCardView = (CardView) itemView.findViewById(R.id.cv);
        }

        @Override
        public boolean onLongClick(View view) {
            //mListener.onLongClick(getAdapterPosition());
            if(multipleMode == false) {
                mListener.showCancelMenuItem();
                multipleMode = true;
                listOfclickedItem = new boolean[mNotes.size()];
                this.mCardView.setCardBackgroundColor(Color.parseColor("#70414033"));
                listOfclickedItem[getAdapterPosition()] = true;
            }
            return false;
        }

        @Override
        public void onClick(View view) {
            if(multipleMode == true){
                if(listOfclickedItem[getAdapterPosition()] == false) {
                    this.mCardView.setCardBackgroundColor(Color.parseColor("#70414033"));
                    listOfclickedItem[getAdapterPosition()] = true;
                } else {
                    this.mCardView.setCardBackgroundColor(Color.WHITE);
                    listOfclickedItem[getAdapterPosition()] = false;
                }
            } else
             mListener.onClick(getAdapterPosition());
        }
    }
}
