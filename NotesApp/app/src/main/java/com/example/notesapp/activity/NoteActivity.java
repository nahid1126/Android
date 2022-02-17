package com.example.notesapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteActivity extends AppCompatActivity {

    @BindView(R.id.txtTitleAdd)
    EditText txtTitleAdd;
    @BindView(R.id.txtNotesAdd)
    EditText txtNotesAdd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Notes notes;
    private boolean isOldNote = false;

    @OnClick(R.id.btnSave)
    void onSaveClicked() {
        String titleAdd = txtTitleAdd.getText().toString();
        String notesAdd = txtNotesAdd.getText().toString();
        if (TextUtils.isEmpty(notesAdd)) {
            Toast.makeText(this, "Please Add some notes", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
        Date date = new Date();
        if (!isOldNote){
            notes=new Notes();
        }
        notes.setTitle(titleAdd);
        notes.setNotes(notesAdd);
        notes.setDate(formatter.format(date));

        Intent intent = new Intent();
        intent.putExtra("notesAdd", notes);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);

        setupToolbar();

        try {
            notes=new Notes();
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            txtTitleAdd.setText(notes.getTitle());
            txtNotesAdd.setText(notes.getNotes());
            isOldNote = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white, null));
        } else {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Note");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            toolbar.getNavigationIcon().setColorFilter(new BlendModeColorFilter(Color.WHITE,
                    BlendMode.SRC_ATOP));
        } else {
            toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

    }
}