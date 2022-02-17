package com.example.notesapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.adapter.NotesAdapter;
import com.example.notesapp.adapter.NotesInterface;
import com.example.notesapp.databaseManager.RoomDB;
import com.example.notesapp.models.Notes;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.notesRecyclerView)
    RecyclerView notesRecyclerView;


    private NotesAdapter notesAdapter;
    private List<Notes> notesList = new ArrayList<>();
    private RoomDB roomDatabase;
    final int REQUEST_CODE = 101;
    final int EDIT_REQUEST_CODE = 102;
    private Notes selectedNote;
    private long backPressed;


    @OnClick(R.id.btnFab)
    void onFabClicked() {
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @BindView(R.id.searchBar)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        roomDatabase = RoomDB.getInstance(this);
        notesList = roomDatabase.mainDAO().getAll();

        setUpRecyclerView(notesList);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    //for searching filter
    private void filter(String newText) {
        List<Notes> filteredList = new ArrayList<>();
        for (Notes singleNote : notesList) {
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
                    || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        notesAdapter.filterList(filteredList);
    }

    private void setUpRecyclerView(List<Notes> notesList) {
        notesRecyclerView.setHasFixedSize(true);
        //for 2column in one row
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(this, notesList, new NotesInterface() {
            @Override
            public void onClick(Notes notes) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("old_note", notes);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }

            @Override
            public void onLongClick(Notes notes, CardView cardView) {
                selectedNote = new Notes();
                selectedNote = notes;
                showPopUp(cardView);
            }
        });
        notesRecyclerView.setAdapter(notesAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Notes newNotes = (Notes) data.getSerializableExtra("notesAdd");
            roomDatabase.mainDAO().insert(newNotes);
            notesList.clear();
            notesList.addAll(roomDatabase.mainDAO().getAll()); //for changing note
            notesAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Successfully note added", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Notes oldNotes = (Notes) data.getSerializableExtra("notesAdd");
            roomDatabase.mainDAO().update(oldNotes.getID(), oldNotes.getTitle(), oldNotes.getNotes());
            notesList.clear();
            notesList.addAll(roomDatabase.mainDAO().getAll()); //for update note
            notesAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Not Insert or update", Toast.LENGTH_SHORT).show();
        }
    }


    private void showPopUp(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.pin:
                if (selectedNote.isPinned()) {
                    roomDatabase.mainDAO().pin(selectedNote.getID(), false);
                    Toast.makeText(this, "Unpinned!", Toast.LENGTH_SHORT).show();
                } else {
                    roomDatabase.mainDAO().pin(selectedNote.getID(), true);
                    Toast.makeText(this, "Pinned!", Toast.LENGTH_SHORT).show();
                }
                notesList.clear();
                notesList.addAll(roomDatabase.mainDAO().getAll());
                notesAdapter.notifyDataSetChanged();
                return true;
            case R.id.delete:
                roomDatabase.mainDAO().delete(selectedNote);
                notesList.remove(selectedNote);
                notesAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Note delete!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;

        }
    }

    @Override
    public void onBackPressed() {
        if (backPressed+2000>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }
}

