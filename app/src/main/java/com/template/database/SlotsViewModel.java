package com.template.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class SlotsViewModel extends AndroidViewModel {

    private int coinAmount;

    public SlotsViewModel(@NonNull Application application) {
        super(application);
    }

}