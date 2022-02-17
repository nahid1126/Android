package com.example.notesapp.databaseManager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.models.Notes;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB roomDB;
    private static String DATABASE_NAME = "NoteApp";

    //create instance
    public synchronized static RoomDB getInstance(Context context) {
        if (roomDB == null) {
            roomDB = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDB;
    }

    //instance of Dao=data access object
    public abstract MainDAO mainDAO();
}
