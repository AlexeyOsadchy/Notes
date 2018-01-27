package com.alexeyosadchy.android.notes.view;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private long id;
    private int clickedPosition = -1;
    private String description;

    public Note(long id, String description){
        this.id =id;
        this.description = description;
    }

    public Note(String description){
        this.description = description;
    }

    protected Note(Parcel in) {
        id = in.readLong();
        clickedPosition = in.readInt();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(clickedPosition);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getDescription(){
        return description;
    }

    public void setDescription(String newText){
        description = newText;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public int getClickedPosition(){
        return clickedPosition;
    }

    public void setClickedPosition(int clickedPosition){
        this.clickedPosition = clickedPosition;
    }
}
