package com.template.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.template.Coins;
import com.template.R;
import com.template.database.MoneyDatabase;

public class MainActivity extends AppCompatActivity {
    private MoneyDatabase database;
    private TextView textViewYourCoins;
    private String coinamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = MoneyDatabase.getInstance(this);
        textViewYourCoins = findViewById(R.id.textViewAmountOfCoins);
        if (database.getDao().getCoins() == null) {
            database.getDao().setCoins(new Coins(50));
        }
        coinamount = String.valueOf(database.getDao().getCoins().getCoinCount());
        textViewYourCoins.setText(String.format(getString(R.string.earned_coins_s), coinamount));
    }

    public void onClickPlayCasino(View view) {
        if (database.getDao().getCoins().getCoinCount() <= 5) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            int reward = generateCoin(40, 70);
            alertDialog.setMessage(String.format("Your reward is %s coins", reward));
            alertDialog.setTitle("Congratulations");
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Claim",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            textViewYourCoins.setText(getString(R.string.earned_coins_s, String.valueOf(reward)));
                            database.getDao().setCoins(new Coins(reward));
                        }
                    });
        }
        Intent intentToGame = new Intent(this, GameActivity.class);
        startActivity(intentToGame);
    }

    public void onClickShowHistory(View view) {
        Intent intentToHistory = new Intent(this, HistoryActivity.class);
        startActivity(intentToHistory);
    }

    private int generateCoin(int minimum, int maximum) {
        return (int) (Math.random() * (maximum - minimum + 1) + minimum);
    }
}