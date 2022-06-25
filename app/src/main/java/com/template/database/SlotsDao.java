package com.template.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.template.activities.GameActivity;
import com.template.activities.GameInfo;

import java.util.List;

@Dao
public interface SlotsDao {

    @Query("SELECT * FROM slots_db")
    List<GameInfo> getSlotsHistory();

    @Insert()
    void setGameInfo(GameInfo info);

    @Update
    void updateSlotObject(GameInfo gameInfo);
}
