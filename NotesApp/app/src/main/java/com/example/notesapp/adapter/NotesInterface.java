package com.example.notesapp.adapter;

import androidx.cardview.widget.CardView;

import com.example.notesapp.models.Notes;

public interface NotesInterface {
    void onClick(Notes notes);

    void onLongClick(Notes notes, CardView cardView);
}
