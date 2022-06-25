package com.template.activities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "slots_db")
public class GameInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int coin_amount;
    private boolean isWin;
    private int tern;
    private int emoji_1;
    private int emoji_2;
    private int emoji_3;

    public GameInfo(int id, int coin_amount, boolean isWin , int tern, int emoji_1, int emoji_2, int emoji_3) {
        this.id = id;
        this.isWin = isWin;
        this.coin_amount = coin_amount;
        this.tern = tern;
        this.emoji_1 = emoji_1;
        this.emoji_2 = emoji_2;
        this.emoji_3 = emoji_3;
    }

    @Ignore
    public GameInfo(int coin_amount, boolean isWin, int tern, int emoji_1, int emoji_2, int emoji_3) {
        this.coin_amount = coin_amount;
        this.tern = tern;
        this.isWin = isWin;
        this.emoji_1 = emoji_1;
        this.emoji_2 = emoji_2;
        this.emoji_3 = emoji_3;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    @Ignore
    public GameInfo(int coin_amount) {
        this.coin_amount = coin_amount;
    }

    public int getCoin_amount() {
        return coin_amount;
    }

    public void setCoin_amount(int coin_amount) {
        this.coin_amount = coin_amount;
    }

    public int getTern() {
        return tern;
    }

    public void setTern(int tern) {
        this.tern = tern;
    }

    public int getEmoji_1() {
        return emoji_1;
    }

    public void setEmoji_1(int emoji_1) {
        this.emoji_1 = emoji_1;
    }

    public int getEmoji_2() {
        return emoji_2;
    }

    public void setEmoji_2(int emoji_2) {
        this.emoji_2 = emoji_2;
    }

    public int getEmoji_3() {
        return emoji_3;
    }

    public void setEmoji_3(int emoji_3) {
        this.emoji_3 = emoji_3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
