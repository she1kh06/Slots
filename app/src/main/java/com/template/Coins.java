package com.template;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "coins_db")
public class Coins {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private int coinCount;

    public Coins(int uid, int coinCount) {
        this.uid = uid;
        this.coinCount = coinCount;
    }

    @Ignore
    public Coins(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }
}
