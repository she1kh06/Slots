package com.template.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.template.Coins;

@Dao
public interface CoinsDao {

    @Insert
    void setCoins(Coins coins);

    @Query("SELECT * FROM coins_db")
    Coins getCoins();

    @Update
    void updateCoins(Coins coins);

    @Query("SELECT * FROM coins_db WHERE uid LIKE :uid")
    Coins getCoinById(int uid);
}
