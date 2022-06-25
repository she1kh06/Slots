package com.template.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.template.Coins;


@Database(entities = {Coins.class},version = 1,exportSchema = false)
public abstract class MoneyDatabase extends RoomDatabase {

    public static final String GAME_DB_NAME = "COINS.DB";
    private static MoneyDatabase database;
    private static final Object object = new Object();

    public static MoneyDatabase getInstance(Context context) {
        synchronized (object) {
            if (database == null) {
                database = Room.databaseBuilder(context, MoneyDatabase.class, GAME_DB_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return database;
    }

    public abstract CoinsDao getDao();
}
