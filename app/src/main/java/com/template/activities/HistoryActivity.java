package com.template.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.template.R;
import com.template.adapters.HistoryAdapter;
import com.template.database.SlotsDatabase;

import java.util.List;


public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SlotsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = SlotsDatabase.getInstance(this);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<GameInfo> infos =  (database.getDao().getSlotsHistory());
        if (infos.size() == 0 || infos == null) {
            setContentView(R.layout.empty);
        } else {
            HistoryAdapter adapter = new HistoryAdapter();
            adapter.setHistoryList(infos);
            for (GameInfo infor :
                    infos) {
                Log.v("object", infor.getEmoji_1() + " emoji");
            }
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         int itemId = item.getItemId();
         if (itemId == R.id.back) {
             Intent intent = new Intent(this,MainActivity.class);
             startActivity(intent);
         }
         return true;
    }
}