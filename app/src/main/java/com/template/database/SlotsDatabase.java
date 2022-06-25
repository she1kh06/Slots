package com.template.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.template.activities.GameInfo;

@Database(entities = {GameInfo.class},version = 1,exportSchema = false)
public abstract class SlotsDatabase extends RoomDatabase {

    public static final String GAME_DB_NAME = "GAME.DB";
    private static SlotsDatabase database;
    private static final Object object = new Object();

    public static SlotsDatabase getInstance(Context context) {
        synchronized (object) {
            if (database == null) {
                database = Room.databaseBuilder(context, SlotsDatabase.class, GAME_DB_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return database;
    }

    public abstract SlotsDao getDao();
}
